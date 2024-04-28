import java.io.*;
import java.net.*;
import java.util.*;

public class exp6_group_comm_master {
    public static void main(String ar[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        Socket socket = new Socket("localhost", 9001);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        System.out.println("Enter Your name: ");
        String name = sc.nextLine();
        out.println(name); // Send the name to the server
        while (true) {
            String line = in.readLine();
            if (line.startsWith("MESSAGE"))
                System.out.println(line.substring(8));
            System.out.print("Enter a message: ");
            String message = sc.nextLine();
            out.println(message);
        }
    }
}