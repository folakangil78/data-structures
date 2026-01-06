public class var_size_window {
    public static int slide_target(int[] arr, int target) {
        int sum = 0;
        int min_len = 0;
        for (int i = 0; i < arr.length; i++) {
            if (sum < target) {
                sum = sum + arr[i];
                min_len++;
            } else if (sum >= target) {
                break;
            }
        }

        for (int i = min_len; i < arr.length; i++) {
            sum = sum - arr[i - min_len];
            if (sum >= target) {
                min_len--;
            }
            if (sum < target) {
                sum = sum + arr[i];
                if (sum >= target) {
                    min_len++;
                }
            }
        }
        return min_len;
    }
    public static void main(String[] args) {
        int[] test = {2, 1, 5, 2, 3, 2};
        System.out.println(slide_target(test, 7));
    }
}
