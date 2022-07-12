import java.util.Scanner;

public class Try {

    public static void main(String[] args) {

        RandomizedBST r = new RandomizedBST();
        String pathname = "suspects.txt";
        r.load(pathname);
        r.printByAFM();
        r.remove(3);
        System.out.println("\n\n\n\n");
        r.printByAFM();
        //System.out.println(r.getMeanSavings());
        /*Scanner in = new Scanner(System.in);
        System.out.println("remove afm:");
        int afm = in.nextInt();
        r.remove(afm);
        r.printByAFM();*/


        /*RandomizedBST r = new RandomizedBST();
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the total suspects");
        int k = in.nextInt();

        for(int i = 1; i <=k; i++) {
            Suspect suspect = new Suspect();
            System.out.println("Suspect " + i + ":");
            System.out.println("Enter AFM:");
            int AFM = in.nextInt();
            System.out.println("Enter first name:");
            String firstName = in.next();
            System.out.println("Enter last name:");
            String lastName = in.next();
            System.out.println("Enter savings:");
            double savings = in.nextDouble();
            System.out.println("Enter taxedIncome:");
            double taxedIncome = in.nextDouble();
            suspect.setAFM(AFM);
            suspect.setFirstName(firstName);
            suspect.setLastName(lastName);
            suspect.setSavings(savings);
            suspect.setTaxedIncome(taxedIncome);
            r.insert(suspect);
        }
        r.printByAFM();*/

        /*Scanner in = new Scanner(System.in);
        System.out.println("Enter the total suspects");
        int k = in.nextInt();
        SuspectQueue r = new SuspectQueue();
        for(int i = 1; i <= k; i++) {
            Suspect suspect = new Suspect();
            System.out.println("Suspect " + i + ":");
            System.out.println("Enter AFM:");
            int AFM = in.nextInt();
            System.out.println("Enter first name:");
            String firstName = in.next();
            System.out.println("Enter last name:");
            String lastName = in.next();
            System.out.println("Enter savings:");
            double savings = in.nextDouble();
            System.out.println("Enter taxedIncome:");
            double taxedIncome = in.nextDouble();
            suspect.setAFM(AFM);
            suspect.setFirstName(firstName);
            suspect.setLastName(lastName);
            suspect.setSavings(savings);
            suspect.setTaxedIncome(taxedIncome);
            r.addFirst(suspect);
        }
        r.printQueue();*/
    }
}
