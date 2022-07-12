import java.io.*;
import java.util.Scanner;

public class Dynamic_Median {

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
            String line;

            String name;
            int ID, population, CovidCases, index;
            boolean file_ok = false;

            PQ maxHeap = new PQ();
            PQ minHeap = new PQ();
            maxHeap.minToMax();

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

                if (file_ok) {
                    City city = new City();
                    city.setID(ID);
                    city.setName(name);
                    city.setPopulation(population);
                    city.setCovidCases(CovidCases);
                    city.calculateDensity();

                    if (maxHeap.isEmpty() || maxHeap.max().getDensity() > city.getDensity()) {
                        maxHeap.insert(city);
                    } else {
                        minHeap.insert(city);
                    }

                    if (maxHeap.size() > minHeap.size() + 1) {
                        minHeap.insert(maxHeap.getmax());
                    } else if (minHeap.size() > maxHeap.size() + 1) {
                        maxHeap.insert(minHeap.getmax());
                    }

                    if ((maxHeap.size() + minHeap.size()) % 5 == 0) {
                        if (maxHeap.size() > minHeap.size()) {
                            System.out.println("Median now is: " + maxHeap.max().getDensity());
                        } else if (minHeap.size() > maxHeap.size()) {
                            System.out.println("Median now is: " + minHeap.max().getDensity());
                        } else {
                            System.out.println("Median now is: " + Math.max(maxHeap.max().getDensity(), minHeap.max().getDensity()));
                        }
                    }

                } else {
                    System.out.println("Invalid ID/Name/Population/Covid Cases in txt file ... \n " +
                            "Check your file and try again");
                    break;
                }
                line = reader.readLine();
            }

            reader.close();

        } catch (IOException e) {
            System.err.println("File not found. Please try again.");
        }
    }
}
