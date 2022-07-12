import java.io.*;
import java.util.Scanner;

public class RandomizedBST implements MafiaInterface{

    private class TreeNode {

        Suspect item;
        TreeNode left;  // pointer to left subtree
        TreeNode right; // pointer to right subtree
        int N;          // number of nodes in subtree rooted at this TreeNode

        TreeNode(Suspect item) {
            this.item = item;
            left = null;
            right = null;
            N = 1;
        }

        TreeNode() {}
    }

    private TreeNode root;

    RandomizedBST() {
        root = null;
    }

    @Override
    public void insert(Suspect item) {
        if (searchByAFM(item.key()) == null)
            root = insertR(root, item);
        else
            System.out.println("Cannot insert the suspect. " +
                    "Suspect with AFM " + item.key() + ", already exists.");
    }

    private TreeNode insertR(TreeNode h, Suspect item) {
        if (h == null)
            return new TreeNode(item);
        if (Math.random() * (h.N + 1) < 1.0)
            return insertAsRoot(item, h);
        /*if (i % 3 == 0)
           return insertAsRoot(item,h);*/
        if (item.less(h.item.key()))
            h.left = insertR(h.left, item);
        else
            h.right = insertR(h.right, item);
        h.N++;
        return h;
    }

    @Override
    public void load(String filename) {
        File suspects = new File(filename);
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(suspects));
            String line = reader.readLine();
            int index;

            while (line != null) {
                index = line.indexOf(" ");
                int AFM = Integer.parseInt(line.substring(0, index));
                line = line.substring(index + 1);

                index = line.indexOf(" ");
                String firstName = line.substring(0, index);
                line = line.substring(index + 1);

                index = line.indexOf(" ");
                String lastName = line.substring(0, index);
                line = line.substring(index + 1);

                index = line.indexOf(" ");
                double savings = Double.parseDouble(line.substring(0, index));
                line = line.substring(index + 1);

                double taxedIncome = Double.parseDouble(line);

                Suspect item = new Suspect(AFM, firstName, lastName, savings, taxedIncome);
                insert(item);

                line = reader.readLine();
            }

            System.out.println("\nSuccessful registration of suspects");
            reader.close();

        } catch (IOException e) {
            System.err.println("Invalid path / Incorrect information");
        }
    }

    @Override
    public void updateSavings(int AFM, double savings) {
        if (searchByAFM(AFM) == null) {
            System.out.println("Suspect with AFM: " + AFM + ", not found");
            return;
        }
        searchByAFM(AFM).setSavings(savings);
    }

    @Override
    public Suspect searchByAFM(int AFM) {
        TreeNode current = root;

        while (true) {
            if (current == null) {
                //System.out.println("Suspect with AFM: " + AFM + ", not found");
                return null;
            }
            if (current.item.equals(AFM)) {
                System.out.println(current.item);
                return current.item;
            }
            if (current.item.less(AFM)) {
                current = current.right;
            } else {
                current = current.left;
            }
        }
    }

    @Override
    public List searchByLastName(String last_name) {

        List queue = new List();
        searchBLN(root, last_name, queue);
        if (queue.size() == 0)
            return null;
        if (queue.size() <= 5)
            queue.printQueue();
        return queue;
    }

    private void searchBLN(TreeNode h, String last_name, List queue) {
        if (h == null)
            return;
        searchBLN(h.left, last_name, queue);
        if (h.item.getLastName().equals(last_name))
            queue.addFirst(h.item);
        searchBLN(h.right, last_name, queue);
    }

    @Override
    public void remove(int AFM) {

        Suspect suspect = searchByAFM(AFM);
        if (suspect == null)
        {
            System.out.println("Suspect with AFM: " + AFM + ", not found");
            return;
        } else {
            System.out.println("Suspect " + suspect.getFirstName() + " " + suspect.getLastName()
                    + ", with AFM " + AFM + ", has been removed.");
            root = removeR(root, AFM);
        }
    }

    private TreeNode removeR(TreeNode h, int AFM) {
        if (h == null)
            return null;
        if (h.item.greater(AFM))
        {
            h.left = removeR(h.left, AFM);
            h.N--;
        }
        if (h.item.less(AFM))
        {
            h.right = removeR(h.right, AFM);
            h.N--;
        }
        if (h.item.equals(AFM))
            h = joinLR(h.left, h.right);
        return h;
    }

    private TreeNode joinLR (TreeNode a, TreeNode b) {
        if (a == null)
            return b;
        if (b == null)
            return a;
        int N = a.N + b.N;
        if (Math.random() * N < 1.0 * a.N) {
            a.N += b.N;
            a.right = joinLR(a.right, b);
            return a;
        } else {
            b.N += a.N;
            b.left = joinLR(a, b.left);
            return b;
        }
    }

    @Override
    public double getMeanSavings() {
        if (root == null)
            return 0.0;
        double meanSavings = MeanSavings(root);
        return meanSavings / root.N;
    }

    private Double MeanSavings(TreeNode h) {
        if (h == null)
            return 0.0;
        return h.item.getSavings() + MeanSavings(h.left) + MeanSavings(h.right);
    }

    @Override
    public void printTopSuspects(int k) {
        PQ pq = new PQ();
        setPQSuspects(root, pq, k);
        //System.out.println(pq.size());
        int size = pq.size();
        for (int i = 1; i <= size; i++) {
            System.out.println(pq.getmax());
        }
    }

    private void setPQSuspects(TreeNode h ,PQ pq, int k) {
        if (h == null)
            return;

        setPQSuspects(h.left, pq, k);
        if (pq.size() < k) {
            pq.insert(h.item);
        } else {
            if (pq.max().compareTo(h.item)) {
                pq.getmax();
                pq.insert(h.item);
            }
        }
        setPQSuspects(h.right, pq, k);
    }

    @Override
    public void printByAFM() {
        inOrder(root);
    }

    private void inOrder (TreeNode h) {
        if (h == null)
            return;

        inOrder(h.left);
        System.out.println(h.item);
        inOrder(h.right);
    }


    private TreeNode insertAsRoot (Suspect item, TreeNode h) {
        if (h == null)
            return new TreeNode(item);
        if (item.less(h.item.key())) {
            h.left = insertAsRoot(item, h.left);
            h = rotR(h);
        } else {
            h.right = insertAsRoot(item, h.right);
            h = rotL(h);
        }
        h.N++;
        return h;
    }

    private TreeNode rotR(TreeNode h) {
        TreeNode x = h.left;
        h.left = x.right;
        x.right = h;
        x.N = h.N;
        if (x.left != null)
            h.N = h.N - x.left.N;
        return x;
    }

    private TreeNode rotL(TreeNode h) {
        TreeNode x = h.right;
        h.right = x.left;
        x.left = h;
        x.N = h.N;
        if (x.right != null)
            h.N = h.N - x.right.N;
        return x;
    }

    public static void main (String[] args) {
        Scanner in = new Scanner(System.in);
        RandomizedBST bst = new RandomizedBST();

        int choice ;

        while (true) {
            System.out.println("0 : exit");
            System.out.println("1 : insert a suspect");
            System.out.println("2 : load a file with suspectss");
            System.out.println("3 : update savings for a suspect");
            System.out.println("4 : search a suspect by AFM");
            System.out.println("5 : search suspects by last name");
            System.out.println("6 : remove a suspect");
            System.out.println("7 : get mean savings");
            System.out.println("8 : print top suspects");
            System.out.println("9 : print suspects in ascending order based on AFM");

            System.out.print("> ");
            choice = in.nextInt();

            System.out.println("");

            if (choice == 0) {
                break;
            }
            else if (choice == 1) {
                System.out.print("Enter AFM \n> ");
                int AFM = in.nextInt();
                System.out.print("Enter first name\n> ");
                String first_name = in.next();
                System.out.print("Enter last name \n> ");
                String last_name = in.next();
                System.out.print("Enter savings \n> ");
                double savings = in.nextDouble();
                System.out.print("Enter taxed income \n> ");
                double taxed_income  = in.nextDouble();
                Suspect suspect = new Suspect(AFM, first_name, last_name, savings, taxed_income);
                bst.insert(suspect);
            }
            else if (choice == 2) {
                System.out.print("Please enter the path of txt file to load the suspects \n> ");
                String path = in.next();
                bst.load(path);
            }
            else if (choice == 3) {
                System.out.print("Enter suspect's AFM \n> ");
                int AFM = in.nextInt();
                System.out.print("Enter updated savings \n> ");
                double savings = in.nextDouble();
                bst.updateSavings(AFM, savings);
            }
            else if (choice == 4) {
                System.out.print("Enter suspect's AFM \n> ");
                int AFM = in.nextInt();
                Suspect suspect = bst.searchByAFM(AFM);
            }
            else if (choice == 5) {
                System.out.print("Enter last name: \n> ");
                String last_name = in.next();
                List list = bst.searchByLastName(last_name);
            }
            else if (choice == 6) {
                System.out.print("Enter suspect's AFM \n> ");
                int AFM = in.nextInt();
                bst.remove(AFM);
            }
            else if (choice == 7) {
                double mean_savings = bst.getMeanSavings();
                if (mean_savings == 0.0)
                    System.out.print("No suspect found ...");
                else
                    System.out.print(mean_savings);
            }
            else if (choice == 8) {
                System.out.print("Enter the num of suspects you want to print \n> ");
                int k = in.nextInt();
                bst.printTopSuspects(k);
            }
            else if (choice == 9) {
                bst.printByAFM();
            }
            /*else {
                System.out.println("Invalid entry");
            }*/
            System.out.println("\n");
        }
    }
}
