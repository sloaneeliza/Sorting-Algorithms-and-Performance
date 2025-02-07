import java.util.Arrays;
import java.util.Random;

public class Tester {
    private SortingAlgorithm sortingAlgorithm;

    public Tester(SortingAlgorithm sa) {
        this.sortingAlgorithm = sa;
    }

    private int[] generateRandomArray(int size) {
        Random random = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt();
        }
        return array;
    }

    private int[] generateKSorted(int size, int k) {
        int[] sortedArray = generateRandomArray(size);
        Arrays.sort(sortedArray);

        int[] kSortedArray = new int[size];
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            int minPos = Math.max(0, i - k);
            int maxPos = Math.min(size - 1, i + k);
            int newPos = minPos + random.nextInt(maxPos - minPos + 1);

            int count = 0;
            for (int j = 0; j < size; j++){
                if (kSortedArray[j] == sortedArray[i]){
                    count++;
                }
            }

            int insertPos = newPos;
            if (count > 0){
                for(int j = minPos; j <= maxPos; j++){
                    boolean found = false;
                    for(int l = 0; l < size; l++){
                        if (kSortedArray[l] == sortedArray[i]){
                            found = true;
                            break;
                        }
                    }
                    if (!found){
                        insertPos = j;
                        break;
                    }
                }
            }
            kSortedArray[insertPos] = sortedArray[i];
        }

        return kSortedArray;
    }

    public double singleTest(int size, boolean isKSorted, int k) {
        int[] array;
        if (isKSorted) {
            array = generateKSorted(size, k);
        } else {
            array = generateRandomArray(size);
        }

        long startTime = System.nanoTime();
        sortingAlgorithm.sorty(array);
        long endTime = System.nanoTime();

        return (endTime - startTime) / 1_000_000.0;
    }

    public void test(int iterations, int size, boolean isKSorted, int k) {
        double totalTime = 0;
        for (int i = 0; i < iterations; i++) {
            totalTime += singleTest(size, isKSorted, k);
        }
        double averageTime = totalTime / iterations;
        String type = isKSorted ? k + "-sorted" : "random";
        System.out.printf("Sorted %d %s elements in %.2f ms (avg)\n", size, type, averageTime);
    }

    /*
    public static void main(String[] args) {
        SortingAlgorithm sorter = new MergeSort();
        Tester tester = new Tester(sorter);

        int size = 10000;
        int iterations = 10;
        int k = 10;

        tester.test(iterations, size, false, k);
        tester.test(iterations, size, true, k);
    }
    */
}