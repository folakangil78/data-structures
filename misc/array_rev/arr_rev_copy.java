import java.util.Arrays;

public class arr_rev_copy {
    public static int[] reverseArray(int[] arr){
        int offset = 1;

        for (int i = 0; i < arr.length/2; i++) {
            int cached = arr[i];
            arr[i] = arr[arr.length - offset];
            arr[arr.length - offset] = cached;
            offset++;
        }
        return arr;
    }
    public static void main(String[] args) {
        int[] test = {1, 2, 3, 4, 5};
        System.out.println(Arrays.toString(reverseArray(test)));
    }
}
