import java.util.*;

public class ArrayLists {
    public static void main(String[] args) {
        int[] test = {1, 2, 3, 4};
        System.out.println(Arrays.toString(doubleSize(test)));
    }

    public static int[] doubleSize(int[] input_array){
        int[] output = new int[2 * input_array.length];
        for(int i = 0; i < input_array.length; i++){
            output[i] = input_array[i];
        }
        return output;
    }


    // OBJECT FIELDS ARE PRIVATE, OBJECT METHODS ARE PUBLIC
}
