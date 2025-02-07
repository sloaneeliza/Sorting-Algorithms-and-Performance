public class SelectionSort implements SortingAlgorithm{
    public int[] sorty(int[] input) {
        int n = input.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (input[j] < input[minIndex]) {
                    minIndex = j;
                }
            }
            // Swap input[i] and input[minIndex]
            int temp = input[i];
            input[i] = input[minIndex];
            input[minIndex] = temp;
        }
        return input;
    }
}
