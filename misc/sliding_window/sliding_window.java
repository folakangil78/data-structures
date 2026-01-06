public class sliding_window {
    public static int slide_window(int[] arr, int k) { // return the maximum sum of any contiguous subarray of length k
        int max_sum = 0;

        for (int i = 0; i < k; i++) {
            max_sum = max_sum + arr[i];
        }

        int new_sum = max_sum;
        for (int i = k; i < arr.length; i++) {
            new_sum = new_sum - arr[i-k] + arr[i]; // subtracting elem at i-k index bc the leaving elem happens to be exactly k steps behind
            if (new_sum > max_sum) {
                max_sum = new_sum;
            }
        }

        return max_sum;
    }

    public static void main(String[] args) {
        int[] test_array = {1, 11, 12, 4, 5, 6, 7};
        System.out.println(slide_window(test_array, 3));
    }
}
