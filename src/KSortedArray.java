import java.util.Arrays;
import java.util.Random;

public class KSortedArray {
    public static void generateKSorted(int[] array, int k) {
        Arrays.sort(array);
        Random random = new Random();

        for (int i = 0; i < array.length; i++) {
            int j = Math.min(i + random.nextInt(k + 1), array.length - 1);
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }
}