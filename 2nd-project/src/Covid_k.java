 import java.io.BufferedReader;
 import java.io.File;
 import java.io.FileReader;
 import java.io.IOException;
 import java.util.Scanner;

public class Covid_k {

    public static void main(String[] args)  {

        Scanner in = new Scanner(System.in);
        System.out.println("Please enter the path of the txt file that you want to check: ");
        // for example: C:/Users/thanos/Desktop/Greece_Covid_Info.txt
        //if the txt file you want to check is included at src folder of the project, you can just enter: Greece_Covid_Info.txt
        String path = in.nextLine();
        File myFile = new File(path);
        BufferedReader reader = null;

        try
        {
            reader = new BufferedReader(new FileReader(myFile));
            String line;
            int n = 0;
            line = reader.readLine();

            while (line!=null) {
                n++;
                line = reader.readLine();
            }

            reader.close();
            reader = new BufferedReader(new FileReader(myFile));

            String name;
            int ID, population, CovidCases, index, i = 1;
            boolean file_ok = false;

            City[] cities = new City[n + 1];
            cities[0] = null;

            line = reader.readLine();

            while (line != null)
            {
                City city = new City();

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
                    city.setID(ID);
                    city.setName(name);
                    city.setPopulation(population);
                    city.setCovidCases(CovidCases);
                    city.calculateDensity();
                    cities[i] = city;
                }
                else
                {
                    break;
                }
                line = reader.readLine();
                i++;
            }

            reader.close();

            if (file_ok) {
                HeapSort hs = new HeapSort();
                hs.heapsort(cities);

                System.out.println("Let's check the cities with the higher number of covid cases per 50000 inhabitants...");

                System.out.print("Enter the number of cities you want to appear : ");
                int k = in.nextInt();

                if (k > 0 && k <= cities.length - 1) {

                    for (i = 1; i <= k; i++) {
                        //System.out.println(cities[i].getName() + " " + cities[i].getDensity());
                        System.out.println(cities[i].getName());
                    }

                }
                else {
                    System.out.println("Invalid number. Try again");
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