import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Deanna Liu
 */
public class HealthCenter {

    static final Path TREATMENTS_CACHE = Paths.get("C:\\Users\\cbex9\\Desktop\\Mei Qi Deanna Liu - Coding Portfolio\\Health Center\\treatments.csv"); // TODO path to treatments.csv

    public static void main(String... args) throws IOException {
        List<String> lines = Files.readAllLines(TREATMENTS_CACHE, StandardCharsets.UTF_8);
        List<Treatment> treatments = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.trim().split(",");
            treatments.add(new Treatment(parts[0], parts[1], Double.parseDouble(parts[2]), Double.parseDouble(parts[3])));
        }
        PriorityQueue<Treatment> pq1 = PriorityQueue.fromCollection(treatments, new Treatment.PriceBasedTreatmentComparator());
        System.out.println(pq1.toString());
        /*
            pq1.insert(new Treatment("Deanna", "influenzaB", 0.1, .25));
            pq1.insert(new Treatment("Angela", "influenzeC", 0.55, 1.80));
            System.out.println(pq1.size());
            System.out.println("Find Best " + pq1.findBest());
            System.out.println("Deleted Best " + pq1.deleteBest());
            System.out.println("Deleted Best " + pq1.deleteBest());
            pq1.insert(new Treatment("Banerjee", "CSE214", 1.5, .8));
            pq1.insert(new Treatment("Julianne", "influenzaD", 1.0, 7.8));
            System.out.println();
            System.out.println(pq1.toString());
       */

        System.out.println("---------------------------------------------------------------------------------------------------");
        System.out.println();

        // TODO Similarly, for the comparator based on the success rate of the treatment:
       PriorityQueue<Treatment> pq2 = PriorityQueue.fromCollection(treatments, new Treatment.SuccessBasedTreatmentComparator());
       System.out.println(pq2.toString());
       /*    pq2.insert(new Treatment("Banerjee", "CSE214", 1.5, .8));
             pq2.insert(new Treatment("Julianne", "influenzaD", 1.0, 7.8));
             System.out.println("Find Best " + pq2.findBest());
             System.out.println("Deleted Best " + pq2.deleteBest());
             System.out.println("Deleted Best " + pq2.deleteBest());
             System.out.println(pq2.size());
             pq2.insert(new Treatment("Deanna", "influenzaB", 0.1, .25));
             pq2.insert(new Treatment("Angela", "influenzaC", 0.55, 1.80));
             pq2.deleteBest();
             System.out.println();
             System.out.println(pq2.size());
             System.out.println(pq2.toString());
             pq2.clear();
            System.out.println(pq2.toString());
       */
    }
}