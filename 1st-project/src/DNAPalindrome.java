import java.util.Scanner;

public class DNAPalindrome {

    //epistrefei to  sumplirwma kathe komvou-xaraktira stin oura
    public static char Complement (char c) {
        if (c == 'A') {
            return 'T';
        } else if (c == 'T') {
            return 'A';
        } else if (c == 'C') {
            return 'G';
        } else if (c == 'G') {
            return 'C';
        }
        return 0 ;
    }

    public static void main (String[] args) {

        Scanner in = new Scanner(System.in);

        System.out.println("Please enter the DNA...: ");
        String DNA = in.nextLine();
        int DNAsize = DNA.length();

        boolean canBeDNA = DNA.toUpperCase().equals(DNA) && !DNA.contains(" ");

        //xrisimopoioume tin Complement gia na ginei elegxos gia to DNA string pou dothike apo ton xristi
        //an i sinartisi epistrepsei 0 simainei pws uparxei xaraktiras pou den einai dektos
        for(int i=0; i<DNAsize; i++) {
            if (Complement(DNA.charAt(i)) == 0) {
                canBeDNA = false;
                break;
            }
        }

        if(!canBeDNA) {
            System.out.println("Sorry, invalid input.");

        } else {

            StringDoubleEndedQueueImpl<Character> DNAqueue = new StringDoubleEndedQueueImpl<Character>();

            boolean isDNA = false;

            if (DNAsize % 2 == 0) {
                for (int i = DNAsize / 2 - 1; i >= 0; i--) {
                    DNAqueue.addFirst(DNA.charAt(i));
                    DNAqueue.addLast(DNA.charAt(DNAsize - 1 - i));
                    if (DNAqueue.getFirst().equals(Complement(DNAqueue.getLast()))) {
                        isDNA = true;
                        continue;
                    }
					
                    isDNA = false;
                    break;
                }
            }

            if (isDNA || DNA.equals("")) {
                System.out.println("\"" + DNA + "\" is Watson-Crick complemented palindrome.");
            } else {
                System.out.println("\"" + DNA + "\" is not Watson-Crick complemented palindrome.");
            }
        }
    }
}
