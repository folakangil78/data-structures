import java.util.Arrays;

public class reverse {
    public static int[] reverse_arr(int[] arr) {
        int offset = 1;
        for (int i = 0; i < arr.length/2; i++) {
            int cache = arr[i];
            arr[i] = arr[arr.length - offset];
            arr[arr.length - offset] = cache;
            offset++;
        }
        return arr;
    }
    public static void main(String[] args) {
        int[] test = {1, 2, 3, 4, 5};
        System.out.println(Arrays.toString(reverse_arr(test)));
    }
}
