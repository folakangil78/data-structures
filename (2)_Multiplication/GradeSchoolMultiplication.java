import java.util.Arrays;

public class GradeSchoolMultiplication {
    private static final int DEFAULT_BASE = 10;

    public static void main(String[] args) {
        int[] x = {1, 2, 3, 4}; //example test case taken from Github instructions
        int[] y = {5, 6, 7, 8};
        int[] product = multiply(x, y, DEFAULT_BASE); // should have used given second method in starter code to call multiply method 
        // without having to pass in defaultbase-parameter in main
        System.out.println(Arrays.toString(product));  // expected output - [7, 0, 0, 6, 6, 5, 2]
        int[] a = {4, 5, 6, 7, 9, 0};
        int[] b = {2, 4, 6, 3, 4, 1};
        int[] result = multiply(a, b, DEFAULT_BASE);
        System.out.println(Arrays.toString(result)); // expected output - [1, 1, 2, 5, 2, 6, 1, 0, 5, 3, 9, 0]
    }

    /**
     * Method to two large ints but represented and outputted as arrays of singular digits
     * @param x first number being multiplied as array of digits
     * @param y second number being multiplied as array of digits
     * @param base base numbering system, used as final constant for base_10
     * @return product of array_based integer multiplication, represented also as array
     */
    public static int[] multiply(final int[] x, final int[] y, final int base) {
        int[] result = new int[y.length + x.length];  // regardless of indiv array length, result should be num of digits of both
        // https://math.stackexchange.com/questions/3134615/digits-in-product-of-two-numbers#:~:text=When%20we%20multiply%20a%20m,m%2Bn%E2%88%921%20digits.
        
        // digit by digit multiply
        for (int i = x.length - 1; i >= 0; i--) { // decrement backwards in array b/c multiplication is right to left
            for (int j = y.length - 1; j >= 0; j--) {
                int pos = i + j + 1; // increment digit_position that's being multiplied
                int product = x[i] * y[j];
                
                // cache in result array
                result[pos] += product;
                result[pos - 1] += result[pos] / base;  // Carry to the next higher position to the left by checking for
                // digit overflow, if there is overflow, the division will add the overflow to the next column of multiplied digits
                result[pos] %= base;  // Keep only the single digit in right hand side column of product
            }
        }
        
        // excising leading zeroes that result array was initialized with
        // MathStackExchange discussed how the product might be a digit less than x.length + y.length
        // this can result in result[0] = 0
        int front = 0;
        while (result[front] == 0 && front < result.length - 1) {
            front++; //tracks number of 0s in array, with loop condition excluding last digit
        }

        // create new array without frontward zeroes
        int[] product_result = new int[result.length - front]; // removes the number of digits/slots in result array that correspond to frontward zeroes
        for (int i = front; i < result.length; i++) {
            product_result[i - front] = result[i]; // copying values from result array from digits after moving past front-zeroes with front var
        }
        
        return product_result;
    } // method multiply
}
