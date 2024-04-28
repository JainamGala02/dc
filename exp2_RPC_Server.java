import java.io.*;
import java.net.*;

public class exp2_RPC_Server {
    public static void main(String[] args) throws Exception {
        ServerSocket sersock = new ServerSocket(3000);
        System.out.println("Server ready");
        Socket sock = sersock.accept();
        BufferedReader keyRead = new BufferedReader(new InputStreamReader(System.in));

        InputStream istream = sock.getInputStream();
        BufferedReader receiveRead = new BufferedReader(new InputStreamReader(istream));
        OutputStream ostream = sock.getOutputStream();
        PrintWriter pwrite = new PrintWriter(ostream, true);
        int a, b, c;
        String fun;
        while (true) {
            fun = receiveRead.readLine();
            if (fun == null) {
                System.out.println("Operation is null");
                break;
            }
            System.out.println("Operation: " + fun);
            a = Integer.parseInt(receiveRead.readLine());
            System.out.println("Parameter 1: " + a);
            b = Integer.parseInt(receiveRead.readLine());
            if (fun.compareTo("add") == 0) {
                c = a + b;
                System.out.println("Addition = " + c);
                pwrite.println("Addition = " + c);
            } else if (fun.compareTo("sub") == 0) {
                c = a - b;
                System.out.println("Subtraction = " + c);
                pwrite.println("Subtraction = " + c);
            } else if (fun.compareTo("mul") == 0) {
                c = a * b;
                System.out.println("Multiplication = " + c);
                pwrite.println("Multiplication = " + c);
            } else if (fun.compareTo("div") == 0) {
                if (b != 0) {
                    c = a / b;
                    System.out.println("Division = " + c);
                    pwrite.println("Division = " + c);
                } else {
                    System.out.println("Cannot divide by zero");
                    pwrite.println("Cannot divide by zero");
                }
            } else if (fun.compareTo("mod") == 0) {
                c = a % b;
                System.out.println("mod = " + c);
                pwrite.println("mod = " + c);
            } else if (fun.compareTo("square") == 0) {
                c = a * a;
                System.out.println("square = " + c);
                pwrite.println("square = " + c);
            } else {
                System.out.println("Invalid operation: " + fun);
                pwrite.println("Invalid operation: " + fun);
            }
            pwrite.flush();
        }

        sock.close();
        sersock.close();
    }
}