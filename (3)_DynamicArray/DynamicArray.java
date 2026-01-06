/**
 * A simple class to demonstrate dynamic behavior with arrays. Objects of this
 * class store strings in an array that grows to match the demand for storage.
 * 
 * The class is based on an underlying string array. Objects can be initialized
 * to any size; otherwise they'll be initialized to the default size. For
 * example,
 * 
 * DynamicArray da1 = new DynamicArray(10);
 * 
 * will have initially room for 10 strings, while
 * 
 * DynamicArray da2 = new DynamicArray();
 * 
 * will have initially room for 4 strings.
 */
public class DynamicArray {

    /** Default size for underlying array */
    private static final int DEFAULT_SIZE = 4;

    /** underlying array for class */
    private String[] foundation;

    /** number of elements currently in array */
    private int size;

    /**
     * Full constructor. Initializes the underlying array to the specified size. The
     * size must be a positive, non zero value. Otherwise, the constructor uses the
     * default size value.
     */
    public DynamicArray(int size) {
        size = (size > 0) ? size : DEFAULT_SIZE;
        this.foundation = new String[size];
        this.size = 0; // initially no elements are added
    } // full constructor

    // array-based constructor for testing
    public DynamicArray(String[] data) {
        this.foundation = data;
        this.size = data != null ? data.length : 0;
    }
    /** Default constructor */
    public DynamicArray() {
        this(DEFAULT_SIZE);
    }

    /**
     * Method to see if given string exists in foundation[]
     * 
     * @param target = string to search for
     * @return true if  string is found, otherwise false
     */
    public boolean contains(String target) {
        if (target == null) return false;
        for (int i = 0; i < size; i++) {
            if (target.equals(foundation[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method to get string at specific index
     * 
     * @param index the position to retrieve the string from
     * @return string at specified index or null if index invalid
     */
    public String get(int index) {
        if (index < 0 || index >= size) {
            return null; // invalid index
        }
        return foundation[index];
    }

    /**
     * Method removing string at specific index and returning
     * 
     * @param index position to remove  string from
     * @return  removed string or null if index is invalid
     */
    public String remove(int index) {
        if (index < 0 || index >= size) {
            return null; // (condition for invalid index)
        }
        String removed = foundation[index];
        delete(index); // shift array down and delete element at index
        return removed;
    }

    /**
     * Method removing value from array at specific index
     * 
     * @param index --> position to delete the string from
     */
    public void delete(int index) {
        if (index < 0 || index >= size) {
            return; // condition should the index be invalid
        }
        for (int i = index; i < size - 1; i++) {
            foundation[i] = foundation[i + 1];
        } // shift elements left overwriting removed element
        foundation[size - 1] = null; 
        size--; // decrement array size and clear end element
    }

    /**
     * Method inserting string param into array, allowing for required resize
     * 
     * @param string to insert into foundation
     */
    public void insert(String string) {
        if (size == foundation.length) {
            resize(); // call resize() if array gets full
        }
        foundation[size++] = string; // adds element at end with next available position
    }

    /**
     * Method resizing array when gets full - doubling array size
     */
    private void resize() {
        int newSize = foundation.length * 2;
        String[] newFoundation = new String[newSize];
        for (int i = 0; i < foundation.length; i++) {
            newFoundation[i] = foundation[i]; // copy elements from oldFoundation to new array
        }
        foundation = newFoundation; 
    }

    /** Test code */
    public static void main(String[] args) {
        final String PASS = "Pass";
        final String FAIL = "Fail";
        final String NON_EXISTING = "COBOL";
        // Test data
        String[] testData = { "Java", "Python", "C", "C++", "Fortran" };
        DynamicArray test = new DynamicArray(testData);
        DynamicArray tset = new DynamicArray(null);
        // Naive testing - I am ashamed to do this but I need
        // to keep things simple for now.
        String testContainsNullTarget = (!test.contains(null)) ? PASS : FAIL;
        String testContainsEmptyData = (!tset.contains("Java")) ? PASS : FAIL;
        String testContainsExisting = (test.contains(testData[1])) ? PASS : FAIL;
        String testContainsNonExisting = (!test.contains(NON_EXISTING)) ? PASS : FAIL;
        String testGetNegative = (test.get(-1) == null) ? PASS : FAIL;
        String testGet = (test.get(0).equals(testData[0])) ? PASS : FAIL;
        String testGetOutOfBounds = (test.get(testData.length + 1) == null) ? PASS : FAIL;
        String testRemove = (testData[1].equals(test.remove(1))) ? PASS : FAIL;
        String testRemoveNull = (tset.remove(1) == null) ? PASS : FAIL;
        String testRemoveOutOfBounds = (test.remove(testData.length + 1) == null) ? PASS : FAIL;
        System.out.printf("\nTest for contains(null): ............... %s", testContainsNullTarget);
        System.out.printf("\nTest for contains on null foundation: .. %s", testContainsEmptyData);
        System.out.printf("\nTest for contains (existing): .......... %s", testContainsExisting);
        System.out.printf("\nTest for contains (non existing): ...... %s", testContainsNonExisting);
        System.out.printf("\nTest for get(-1): ...................... %s", testGetNegative);
        System.out.printf("\nTest for get(0): ....................... %s", testGet);
        System.out.printf("\nTest for get(out of bounds): ........... %s\n", testGetOutOfBounds);
        System.out.printf("\nTest for remove(1): .................... %s", testRemove);
        System.out.printf("\nTest for remove(null): ................. %s", testRemoveNull);
        System.out.printf("\nTest for remove(out of bounds): ........ %s\n\n", testRemoveOutOfBounds);
        // If all is good, these two statements will not crash the program
        test.insert("Pascal");
        test.insert("Basic");
        System.out.println("Insert test: " + test.contains("Pascal")); // should return true
        System.out.println("Insert test: " + test.contains("Basic")); // should return true
    } // method main

} // class DynamicArray