public class slide {
    //given window size, return largest sum achieved thru a contiguous sub-array

    public static int slider(int[] arr, int window) {
        int max_sum = 0;
        for (int i = 0; i < window; i++) {
            max_sum = max_sum + arr[i];
        }

        int current_sum = max_sum;
        for (int i = window; i < arr.length; i++) {
            current_sum = current_sum - arr[i-window] + arr[i];
            if (current_sum > max_sum) {
                max_sum = current_sum;
            }
        }
        
        return max_sum;
    }
    public static void main(String[] args) {
        int[] testing = {10, 45, 89, 34, 30};
        System.out.println(slider(testing, 2));
    }
}
