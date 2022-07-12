import java.util.Scanner;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class DynamicCovid_k_withPQ {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.println("Please enter the path of the txt file that you want to check: ");
        // for example: C:\\Users\\thanos\\Desktop\\Greece_Covid_Info.txt
        //if the txt file you want to check is included at src folder of the project, you can just enter: Greece_Covid_Info.txt

        String path = in.nextLine();

        File myFile = new File(path);
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(myFile));

            boolean file_ok = false;

            PQ pq = new PQ();
            System.out.println("Let's check the cities with the higher number of covid cases per 50000 inhabitants...");

            System.out.print("Enter the number of cities you want to appear : ");
            int k = in.nextInt();

            String line, name;
            int ID, population, CovidCases, index;

            line = reader.readLine();

            while (line != null)
            {
                index = line.indexOf(" ");
                ID = Integer.parseInt(line.substring(0, index));
                line = line.substring(index + 1);

                index = line.lastIndexOf(" ");
                CovidCases = Integer.parseInt(line.substring(index + 1));
                line = line.substring(0, index);

                index = line.lastIndexOf(" ");
                population = Integer.parseInt(line.substring(index + 1));
                line = line.substring(0, index);

                name = line;

                file_ok = (ID >= 1 && ID <= 999) && (name.length() <= 50) && (population > 0 && population <= 10000000) && (CovidCases >= 0 && CovidCases <= population);

                if (file_ok)
                {
                    City city = new City();
                    city.setID(ID);
                    city.setName(name);
                    city.setPopulation(population);
                    city.setCovidCases(CovidCases);
                    city.calculateDensity();

                    if (pq.size() < k) {
                        pq.insert(city);
                    } else {
                        if (city.compareTo(pq.max())) {
                            pq.remove(pq.max().getID());
                            //pq.getmax();
                            pq.insert(city);
                        }
                    }
                }

                line = reader.readLine();

            }

            reader.close();

            if (file_ok) {

                //we use pq.size() instead of not k, to prove that pq.size() equals k
                int size = pq.size();
                pq.minToMax();
                for (int i = 1; i <= size; i++) {
                    //System.out.println(pq.max().getName() + "    " + pq.max().getDensity());
                    System.out.println(pq.max().getName());
                    pq.getmax();
                }
            } else {
                System.out.println("Invalid ID/Name/Population/Covid Cases in txt file ... \n " +
                        "Check your file and try again");
            }

        }
        catch (IOException e)
        {
            System.err.println("File not found. Please try again.");
        }

    }
}
