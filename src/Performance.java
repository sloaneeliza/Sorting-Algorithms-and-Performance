import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Performance {
    public static void main(String[] args) {
        int[] sizes = {100, 500, 1000, 2000, 5000, 10000, 20000, 75000, 150000};
        int k = 10;

        SortingAlgorithm[] algorithms = {
                new BubbleSort(),
                new QuickSort(),
                new MergeSort(),
                new ShellSort(),
                new SelectionSort(),
                new InsertionSort(),
        };

        String outputFile = "performance_report_ksorted.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            for (SortingAlgorithm algorithm : algorithms) {
                writer.write("Sorting Algorithm -" + algorithm.getClass().getSimpleName() + "\n");
                Tester tester = new Tester(algorithm);

                writer.write("Random Data:\n");
                for (int size : sizes) {
                    tester.test(20, size, true, k);
                    writer.write("Sorted " + size + " elements in " +
                            String.format("%.4f", tester.singleTest(size, false, k)) + "ms (avg)\n");
                }
                writer.write("\n");

                writer.write(k + "-sorted Data:\n");
                for (int size : sizes) {
                    tester.test(20, size, true, k);
                    writer.write("Sorted " + size + " elements in " +
                            String.format("%.4f", tester.singleTest(size, true, k)) + "ms (avg)\n");
                }
                writer.write("\n");
            }
            System.out.println("Generated Performance Report: " + outputFile);
        } catch (IOException e) {
            System.out.println("An error has occured.");
            e.printStackTrace();
        }
    }
}