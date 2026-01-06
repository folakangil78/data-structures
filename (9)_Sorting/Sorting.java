import java.util.Random;

public class Sorting {
    public static void main(String[] args) {
        for (int i = 2; i < 11000; i *= 2) { // magic values okay for loop indices
            experiment(i); // passes size of array in powers of 2 to experiment method and runs test that #
                           // of times
        } // important to step in powers of 2 to ensure that performance can be measurably
          // tracked across rapidly changing differing orders of magnitude
    }

    /**
     * Method to track elapsed time to measure performance for sorting algorithms
     * MergeSort and InsertionSort
     * Method subtracts nanotime values pre-method-call and post-method-call to find
     * elapsed
     * 
     * @param size passed into array randomization
     */
    static void experiment(int size) {
        int[] merge_test_array = randomArray(size, 0, size); // inserts values from 0 to size of length size
        int[] insertion_test_array = new int[size]; // fresh copy of randomized array for insertion method, wouldn't
                                                    // want insertion to run on sorted version of merge_test_array

        for (int i = 0; i < merge_test_array.length; i++) {
            insertion_test_array[i] = merge_test_array[i]; // array copy populating
        }

        long merge_start_time = System.nanoTime(); // stores starting time of nanotime of system pre-method call

        mergeSort(merge_test_array);

        long merge_end_time = System.nanoTime(); // stores ending nanotime post-method-call to calculate elapsed time by
                                                 // substraction

        long insertion_start_time = System.nanoTime();

        insertionSort(insertion_test_array);

        long insertion_end_time = System.nanoTime();

        long merge_start_time2 = System.nanoTime();

        mergeSort(merge_test_array); // conducting merge sort on already sorted array from prev method call for time
                                     // comparison to initial mergesort

        long merge_end_time2 = System.nanoTime();

        System.out.println("arr size: " + size + " mergesort time: " + ((merge_end_time - merge_start_time))
                + " insertionsort time: " + ((insertion_end_time - insertion_start_time)) + " mergesorttime2: "
                + ((merge_end_time2 - merge_start_time2)));
    }

    /**
     * In-place insertion sort of an array
     * 
     * @param arr int[] to sort in-place
     */
    static void insertionSort(int[] arr) {
        // Consider every element of the array
        for (int i = 0; i < arr.length; i++) {
            // Grab a copy of the current element
            int current = arr[i];
            // Prepare to compare current element with every
            // element to its left
            int j = i - 1;
            // As long as the current element is less than the element to its left,
            // move to the left and compare again
            while (j >= 0 && arr[j] > current) {
                arr[j + 1] = arr[j];
                j -= 1;
            }
            // Place the current value to a position where nothing smaller
            // is found to its left
            arr[j + 1] = current;
        }
    } // method insertionSort

    /**
     * Orchestrates an in-place merge sort for an array
     * 
     * @param arr int[] to sort in-place
     */
    static void mergeSort(int[] arr) {
        int n = arr.length; // shortcut
        // Process only arrays with two or more elements
        if (n > 1) {
            // Initialize the recursive method with left and right edges set
            // to the first and the last elements of the input arrays.
            int leftEdge = 0;
            int rightEdge = n - 1;
            sort(arr, leftEdge, rightEdge);
        }
    } // method mergeSort

    /**
     * Recursive method to merge sort a subarray.
     * 
     * @param arr       int[] where the subarray is located
     * @param leftEdge  int index to the beginning of the subarray (inclusive)
     * @param rightEdge int index to the end of the subarry (not inclusive)
     */
    static void sort(int[] arr, int leftEdge, int rightEdge) {
        // Ensure that left edge is to the left of the right edge; when
        // leftEdge==rightEdge, the subarray has length 1 and it's
        // sorted by definition.
        if (leftEdge < rightEdge) {
            // Find the middle of the subarray
            int middle = leftEdge + (rightEdge - leftEdge) / 2;
            // Sort the left half of the subarray
            sort(arr, leftEdge, middle);
            // Sort the right half of the subarray
            sort(arr, middle + 1, rightEdge);
            // Begin assemble the results of sorting
            merge(arr, leftEdge, middle, rightEdge);
        }
    } // method sort

    /**
     * Merges two sorted subarrays into a sorted subarray
     * 
     * @param arr       int[] where the subarrays are located
     * @param leftEdge  beginning of first subarray
     * @param middle    end of first subarray and beginning of second
     * @param rightEdge end of second subarry
     */
    static void merge(int[] arr, int leftEdge, int middle, int rightEdge) {
        // Sizes of the two subarrays to sort
        int sizeLeft = middle - leftEdge + 1;
        int sizeRight = rightEdge - middle;
        // Temporary arrays to hold contents of subarrays to sort
        int[] tempLeft = new int[sizeLeft];
        int[] tempRight = new int[sizeRight];
        // Copy first subarray to temporary array
        for (int i = 0; i < sizeLeft; i++) {
            tempLeft[i] = arr[leftEdge + i];
        }
        // Copy second subarray to temporary array
        for (int j = 0; j < sizeRight; j++) {
            tempRight[j] = arr[middle + 1 + j];
        }
        // Prepare to merge the two temporary arrays
        int i = 0; // cursor for first temp array
        int j = 0; // cursor for second temp array
        int k = leftEdge; // cursor for in-place assignment in output array
        // Process data from both arrays
        while (i < sizeLeft && j < sizeRight) {
            if (tempLeft[i] < tempRight[j]) {
                arr[k++] = tempLeft[i++];
            } else {
                arr[k++] = tempRight[j++];
            }
        }
        // If second array is out of data, copy elements from first
        // temporary array into output array
        while (i < sizeLeft) {
            arr[k++] = tempLeft[i++];
        }
        // If first array is out of data, copy elements from second
        // temporary array into output array
        while (j < sizeRight) {
            arr[k++] = tempRight[j++];
        }
    } // method merge

    /**
     * Crates an array and populates it with random values
     * 
     * @param size     size of array of create
     * @param minValue smallest value in the array
     * @param maxValue largest value in the array
     * @return array of specified length with random numbers between min and max
     *         values given.
     */
    static int[] randomArray(int size, int minValue, int maxValue) {
        Random rand = new Random();
        int[] randArr = new int[size];
        for (int i = 0; i < randArr.length; i++) {
            randArr[i] = minValue + rand.nextInt(1 + maxValue);
        }
        return randArr;
    } // method randomArray;

} // class Sorting