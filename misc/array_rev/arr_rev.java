import java.util.Arrays;

public class arr_rev {
    public static int[] reverseArray(int[] arr) {
        // no .reverse() method or helper libs, try to store within arr param

        int offset_counter = 1;
        for (int i = 0; i < arr.length/2; i++) {
            int cached_value = arr[i];

            arr[i] = arr[arr.length - offset_counter];
            arr[arr.length - offset_counter] = cached_value;
            offset_counter++;
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] test_array = {1, 2};

        System.out.println(Arrays.toString(reverseArray(test_array)));
    }
}
