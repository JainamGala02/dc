import java.util.Scanner;

class Main {
    static void printLoad(int servers, int processes) {
        int each = processes / servers;
        int extra = processes % servers;
        int total = 0;
        for (int i = 0; i < servers; i++) {
            if (extra-- > 0) {
                total = each + 1;
            } else {
                total = each;
            }
            System.out.println("Servers " + (char) ('A' + i) + " has " + total + " processes");
        }
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of servers and processes: ");
        int servers = sc.nextInt();
        int processes = sc.nextInt();
        while (true) {
            printLoad(servers, processes);
            System.out.print("1. Add Servers 2. Remove Servers 3. Add Processes 4. Remove Processes 5. Exit: ");
            switch (sc.nextInt()) {
                case 1:
                    System.out.print("How many more servers?: ");
                    servers += sc.nextInt();
                    break;
                case 2:
                    System.out.print("How many servers to remove?: ");
                    servers -= sc.nextInt();
                    break;
                case 3:
                    System.out.print("How many more processes?: ");
                    processes += sc.nextInt();
                    break;
                case 4:
                    System.out.print("How many processes to remove?: ");
                    processes -= sc.nextInt();
                    break;
                case 5:
                    return;
            }
        }
    }
}