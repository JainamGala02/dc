import java.io.*;

class BullyAlgo {
    int cood, ch, crash;
    int prc[];

    public void election(int n) throws IOException {
        System.out.println("\nThe Coordinator Has Crashed!");
        crash = 0;
        for (int i1 = 0; i1 < n; i1++)
            if (prc[i1] == 0)
                crash++;

        if (crash == n) {
            System.out.println("\n*** ALL Processes Are Crashed ***");
            System.out.println("Initiating election with process " + n + " as initiator");
            initiateElection(n, n);
        } else {
            int initiator = -1;
            for (int i = n - 1; i >= 0; i--) {
                if (prc[i] == 1) {
                    initiator = i + 1;
                    break;
                }
            }
            if (initiator == -1) {
                System.out.println("Error: No running process found");
            } else {
                System.out.println("Initiating election with process " + initiator + " as initiator");
                initiateElection(n, initiator);
            }
        }
    }

    private void initiateElection(int n, int initiator) {
        for (int i1 = initiator - 1; i1 < n; i1++)
            System.out.println("Process " + (i1 + 1) + " Called For Election");
        System.out.println(" ");
        for (int i1 = initiator - 1; i1 < n; i1++) {
            if (prc[i1] == 0) {
                System.out.println("Process " + (i1 + 1) + " Is Dead");
            } else
                System.out.println("Process " + (i1 + 1) + " Is In");
        }

        for (int i1 = n - 1; i1 >= 0; i1--)
            if (prc[i1] == 1) {
                cood = (i1 + 1);
                System.out.println("\n*** New Coordinator Is " + (cood) + " ***");
                return;
            }
    }

    public void Bully() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter The Number of Processes:");
        int n = Integer.parseInt(br.readLine());
        prc = new int[n];
        crash = 0;
        for (int i = 0; i < n; i++)
            prc[i] = 1;
        cood = n;
        do {
            System.out.println("\n\t1. Crash A Process");
            System.out.println("\t2. Recover A Process");
            System.out.println("\t3. Display New Coordinator");
            System.out.println("\t4. Exit");
            ch = Integer.parseInt(br.readLine());
            switch (ch) {
                case 1:
                    System.out.println("\nEnter A Process To Crash");
                    int cp = Integer.parseInt(br.readLine());
                    if ((cp > n) || (cp < 1)) {
                        System.out.println("Invalid Process! Enter A Valid Process");
                    } else if ((prc[cp - 1] == 1) && (cood != cp)) {
                        prc[cp - 1] = 0;
                        System.out.println("\nProcess " + cp + " Has Been Crashed");
                    } else if ((prc[cp - 1] == 1) && (cood == cp)) {
                        prc[cp - 1] = 0;
                        election(n);
                    } else
                        System.out.println("\nProcess " + cp + " Is Already Crashed");
                    break;

                case 2:
                    System.out.println("*\nCrashed Processes Are: \n");
                    for (int i = 0; i < n; i++) {
                        if (prc[i] == 0)
                            System.out.println(i + 1);
                        crash++;
                    }
                    System.out.println("Enter The Process You Want To Recover");
                    int rp = Integer.parseInt(br.readLine());
                    if ((rp < 1) || (rp > n))
                        System.out.println("\nInvalid Process. Enter A Valid ID");
                    else if ((prc[rp - 1] == 0) && (rp > cood)) {
                        prc[rp - 1] = 1;
                        System.out.println("\nProcess " + rp + " Has Recovered");
                        cood = rp;
                        System.out.println("\nProcess " + rp + " Is The New Coordinator");
                    } else if (crash == n) {
                        prc[rp - 1] = 1;
                        cood = rp;
                        System.out.println("\nProcess " + rp + " IS The New Coordinator");
                        crash--;
                    } else if ((prc[rp - 1] == 0) && (rp < cood)) {
                        prc[rp - 1] = 1;
                        System.out.println("\nProcess " + rp + " Was Recovered");
                    } else
                        System.out.println("\nProcess " + rp + " Is Not A Crashed Process");
                    break;
                case 3:
                    System.out.println("\nCurrent Coordinator Is " + cood);
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("\ninvalid Entry!");
                    break;
            }
        } while (ch != 4);
    }

    public static void main(String args[]) throws IOException {
        BullyAlgo ob = new BullyAlgo();
        ob.Bully();
    }
}