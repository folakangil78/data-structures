public class sliding_window_copy {
    public static int slide(int[] arr, int k) {
        // max sum of any contiguous subarray of length k
        if (k > arr.length) {
            throw new IllegalArgumentException("k window size larger than array len");
        }
        int max_sum = 0;

        for (int i = 0; i < k; i++) {
            max_sum = max_sum + arr[i];
        }

        int current_sum = max_sum;
        for (int i = k; i < arr.length; i++) {
            current_sum = current_sum + arr[i] - arr[i-k];
            if (current_sum > max_sum) {
                max_sum = current_sum;
            }
        }
        
        return max_sum; // space complexity constant O(1), time complexity linear O(n)
    }
    public static void main(String[] args) {
        int[] test = {1, 21, 3, 4, 5, 6};
        System.out.println(slide(test, 9));
    }
}
