import java.io.*;
import java.net.*;

public class exp4_naming {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter Website URL to resolve its Name to address:");
        String name = br.readLine();
        try {
            InetAddress ip = InetAddress.getByName(name);
            System.out.println("\nAddress: " + ip.getHostAddress());
        } catch (UnknownHostException e) {
            System.out.println("No HOST IS PRESENT");
            System.out.println("TRY AGAIN");
        }
    }
}