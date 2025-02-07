public class QuickSort implements SortingAlgorithm{
   /*
    public int[] sorty(int[] input) {
        quickSort(input, 0, input.length - 1);
        return input;
    }

    private void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }
    private int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }
    */
   public int[] sorty(int[] input) {
       quickSort(input, 0, input.length - 1);
       return input;
   }

    private void quickSort(int[] arr, int low, int high) {
        if (high - low < 10) { // Threshold for insertion sort (adjust as needed)
            insertionSort(arr, low, high);
        } else if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private int partition(int[] arr, int low, int high) {
        int middle = low + (high - low) / 2;
        int pivot = medianOfThree(arr[low], arr[middle], arr[high]);

        if (pivot == arr[middle]) {
            swap(arr, high, middle);
        } else if (pivot == arr[low]) {
            swap(arr, high, low);
        }

        pivot = arr[high]; // Pivot is now at the end.
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    private int medianOfThree(int a, int b, int c) {
        if ((a <= b && b <= c) || (c <= b && b <= a))
            return b;
        else if ((a <= c && c <= b) || (b <= c && c <= a))
            return c;
        else
            return a;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private void insertionSort(int[] arr, int low, int high) {
        for (int i = low + 1; i <= high; ++i) {
            int key = arr[i];
            int j = i - 1;

            while (j >= low && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }
}

