/******************************************************************
 *
 *
 *   This java file contains the problem solutions for the methods selectionSort,
 *   mergeSortDivisibleByKFirst, asteroidsDestroyed, and numRescueCanoes methods.
 *
 ********************************************************************/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ProblemSolutions {

    /**
     * Method SelectionSort
     *
     * This method performs a selection sort, file was spot-checked
     *
     * This is an in-place sorting operation that has two function signatures. This
     * allows the second parameter to be optional, and if not provided, defaults to an
     * ascending sort. If the second parameter is provided and is false, a descending
     * sort is performed.
     *
     * @param values        - int[] array to be sorted.
     * @param ascending     - if true,method performs an ascending sort, else descending.
     *                        There are two method signatures allowing this parameter
     *                        to not be passed and defaulting to 'true (or ascending sort).
     */

    public  void selectionSort(int[] values) {
        selectionSort(values, true);
    }

    public static void selectionSort(int[] values, boolean ascending ) {

        int n = values.length;

        for (int i = 0; i < n - 1; i++) {
            // outer loop for each elem in array (except last)
            int targetIdx = i;
            // assume current elem is smallest

            for (int j = i + 1; j < n; j++) {
                // inner loop finding smallest elem in unsorted part of array
                if (ascending && values[j] < values[targetIdx]) {
                    targetIdx = j; // update to smaller elem
                } else if (!ascending && values[j] > values[targetIdx]) {
                    // if sorting in descending order, find largest elem
                    targetIdx = j;
                }
            }

            int temp = values[targetIdx]; // swap found target elem with elem at current index i
            values[targetIdx] = values[i];
            values[i] = temp;

        }

    } // End method selectionSort


    /**
     *  Method mergeSortDivisibleByKFirst
     *
     *  This method will perform a merge sort algorithm. However, all numbers
     *  that are divisible by the argument 'k', are returned first in the sorted
     *  list. Example:
     *        values = { 10, 3, 25, 8, 6 }, k = 5
     *        Sorted result should be --> { 10, 25, 3, 6, 8 }
     *
     *        values = { 30, 45, 22, 9, 18, 39, 6, 12 }, k = 6
     *        Sorted result should be --> { 30, 18, 6, 12, 9, 22, 39, 45 }
     *
     * As shown above, this is a normal merge sort operation, except for the numbers
     * divisible by 'k' are first in the sequence.
     *
     * @param values    - input array to sort per definition above
     * @param k         - value k, such that all numbers divisible by this value are first
     */

    public void mergeSortDivisibleByKFirst(int[] values, int k) {

        // Protect against bad input values
        if (k == 0)  return;
        if (values.length <= 1)  return;

        mergeSortDivisibleByKFirst(values, k, 0, values.length-1);
    }

    private void mergeSortDivisibleByKFirst(int[] values, int k, int left, int right) {

        if (left >= right)
            return;

        int mid = left + (right - left) / 2;
        mergeSortDivisibleByKFirst(values, k, left, mid);
        mergeSortDivisibleByKFirst(values, k, mid + 1, right);
        mergeDivisbleByKFirst(values, k, left, mid, right);
    }

    /*
     * The merging portion of the merge sort, divisible by k first
     */

    private void mergeDivisbleByKFirst(int arr[], int k, int left, int mid, int right)
    {
        // YOUR CODE GOES HERE, THIS METHOD IS NO MORE THAN THE STANDARD MERGE PORTION
        // OF A MERGESORT, EXCEPT THE NUMBERS DIVISIBLE BY K MUST GO FIRST WITHIN THE
        // SEQUENCE PER THE DISCUSSION IN THE PROLOGUE ABOVE.
        //
        // NOTE: YOU CAN PROGRAM THIS WITH A SPACE COMPLEXITY OF O(1) OR O(N LOG N).
        // AGAIN, THIS IS REFERRING TO SPACE COMPLEXITY. O(1) IS IN-PLACE, O(N LOG N)
        // ALLOCATES AUXILIARY DATA STRUCTURES (TEMPORARY ARRAYS). IT WILL BE EASIER
        // TO CODE WITH A SPACE COMPLEXITY OF O(N LOG N), WHICH IS FINE FOR PURPOSES
        // OF THIS PROGRAMMING EXERCISES.

        int leftPointer = left;
        int rightPointer = mid + 1;
        int mergePlace = 0;
        // ptrs above for left and right subarrays

        int[] mergedArray = new int[right - left + 1];
        // joining two halves to hold merged result

        while(leftPointer <= mid && rightPointer <= right){
            // merging two halves making k-divisible nums come first
            if(arr[leftPointer] % k == 0 && arr[rightPointer] % k != 0){
                // left ptr points to k-divisible num and right ptr doesnt
                mergedArray[mergePlace++] = arr[leftPointer++];
            }
            else if(arr[rightPointer] % k == 0 && arr[leftPointer] % k != 0){
                // right ptr points to k-divisible num and left ptr doesnt
                mergedArray[mergePlace++] = arr[rightPointer++];
            }
            else if(arr[leftPointer] % k == 0){
                // both nums are k-divisible, add smaller num to mergedArray
                mergedArray[mergePlace++] = arr[leftPointer++];
            }
            else if(arr[rightPointer] % k == 0){
                mergedArray[mergePlace++] = arr[rightPointer++];
            }
            else {
                // last case: if neither num k-divisible, merge based on smaller num
                if(arr[leftPointer] <= arr[rightPointer]){
                    mergedArray[mergePlace++] = arr[leftPointer++];
                }
                else {
                    mergedArray[mergePlace++] = arr[rightPointer++];
                }
            }
        }
        while(leftPointer <= mid){
            // add any remaining elems from left subarray to merged
            mergedArray[mergePlace++] = arr[leftPointer++];
        }
        while(rightPointer <= right){
            // same as above for right subarray
            mergedArray[mergePlace++] = arr[rightPointer++];
        }
        for(int z = 0; z < mergedArray.length; z++){
            // copy merged back into og array
            arr[left + z] = mergedArray[z];
        }
    }


    /**
     * Method asteroidsDestroyed
     *
     * You are given an integer 'mass', which represents the original mass of a planet.
     * You are further given an integer array 'asteroids', where asteroids[i] is the mass
     * of the ith asteroid.
     *
     * You can arrange for the planet to collide with the asteroids in any arbitrary order.
     * If the mass of the planet is greater than or equal to the mass of the asteroid, the
     * asteroid is destroyed and the planet gains the mass of the asteroid. Otherwise, the
     * planet is destroyed.
     *
     * Return true if possible for all asteroids to be destroyed. Otherwise, return false.
     *
     * Example 1:
     *   Input: mass = 10, asteroids = [3,9,19,5,21]
     *   Output: true
     *
     * Explanation: One way to order the asteroids is [9,19,5,3,21]:
     * - The planet collides with the asteroid with a mass of 9. New planet mass: 10 + 9 = 19
     * - The planet collides with the asteroid with a mass of 19. New planet mass: 19 + 19 = 38
     * - The planet collides with the asteroid with a mass of 5. New planet mass: 38 + 5 = 43
     * - The planet collides with the asteroid with a mass of 3. New planet mass: 43 + 3 = 46
     * - The planet collides with the asteroid with a mass of 21. New planet mass: 46 + 21 = 67
     * All asteroids are destroyed.
     *
     * Example 2:
     *   Input: mass = 5, asteroids = [4,9,23,4]
     *   Output: false
     *
     * Explanation:
     * The planet cannot ever gain enough mass to destroy the asteroid with a mass of 23.
     * After the planet destroys the other asteroids, it will have a mass of 5 + 4 + 9 + 4 = 22.
     * This is less than 23, so a collision would not destroy the last asteroid.
     *
     * Constraints:
     *     1 <= mass <= 105
     *     1 <= asteroids.length <= 105
     *     1 <= asteroids[i] <= 105
     *
     * @param mass          - integer value representing the mass of the planet
     * @param asteroids     - integer array of the mass of asteroids
     * @return              - return true if all asteroids destroyed, else false.
     */

    public static boolean asteroidsDestroyed(int mass, int[] asteroids) {

        // CONSIDER USING ARRAYS.SORT()

        Arrays.sort(asteroids); // sort in ascending orderto prcess smallest first

        long currentMass = mass; // initial mass
        for (int a : asteroids) {
            // if current mass >= to asteroid's mass then asteroid can be destroyed
            // so increase mass by asteroid's mass
            if (currentMass >= a) {
                currentMass += a;
            } else {
                return false; // if current < asteroid's mass, cannot destroy steroid
            }
        }
        return true; // all destroyed

    }


    /**
     * Method numRescueSleds
     *
     * You are given an array people where people[i] is the weight of the ith person,
     * and an infinite number of rescue sleds where each sled can carry a maximum weight
     * of limit. Each sled carries at most two people at the same time, provided the
     * sum of the weight of those people is at most limit. Return the minimum number
     * of rescue sleds to carry every given person.
     *
     * Example 1:
     *    Input: people = [1,2], limit = 3
     *    Output: 1
     *    Explanation: 1 sled (1, 2)
     *
     * Example 2:
     *    Input: people = [3,2,2,1], limit = 3
     *    Output: 3
     *    Explanation: 3 sleds (1, 2), (2) and (3)
     *
     * Example 3:
     *    Input: people = [3,5,3,4], limit = 5
     *    Output: 4
     *    Explanation: 4 sleds (3), (3), (4), (5)
     *
     * @param people    - an array of weights for people that need to go in a sled
     * @param limit     - the weight limit per sled
     * @return          - the minimum number of rescue sleds required to hold all people
     */

    public static int numRescueSleds(int[] people, int limit) {

        // CONSIDER USING ARRAYS.SORT

        Arrays.sort(people);
        // sorts people to pair lightest and heaviest

        int left = 0; // ptrs for lightest (left) and heaviest (right)
        int right = people.length - 1;
        int sleds = 0;

        while (left <= right) { // keep checking for combos
            if (people[left] + people[right] <= limit) {
                // if lightest and heaviest can fit on sled, then paired and left ptr moved
                left++;
            }
            right--; // heaviest person gets on sled anyway
            sleds++;
        }

        return sleds; // total sleds needed to rescue everyone

    }

} // End Class ProblemSolutions

