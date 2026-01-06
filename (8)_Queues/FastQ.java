public class FastQ {

    private String[] array;
    private int size;
    private int used;
    private int front;
    private int back;

    private static final int DEFAULT_SIZE = 4;

    /** Full constructor */
    public FastQ(int size) {
        if (size < 1) {
            size = DEFAULT_SIZE;
        }
        this.size = size;
        this.array = new String[this.size];
        this.used = 0;
        this.front = 0;
        this.back = 0;
    } // full constructor

    /** Default constructor */
    public FastQ() {
        this(DEFAULT_SIZE);
    } // default constructor

    /**
     * Method to add given string param in queue and update back pointer accordingly
     * 
     * @param string for element to be added
     * @return whether or not element was added based on space in queue
     */
    public boolean add(String string) {
        boolean success = (this.used < this.array.length); // as long as num of elements is less than length, string can
                                                           // be added

        if (success == true) {
            this.array[this.back] = string; // queue insertion here
            this.back++;

            if (this.back == this.array.length) { // checks if this.back reached end of array and intializing to 0 based
                                                  // on addition
                this.back = 0; // basically sets back to beginning to add elements there
            }

            this.used++;
        }
        return success;
    } // method add

    /**
     * Method to remove the front element from queue and updating front pointer
     * based on element afterward
     * making sure to decrement used number of elements in queue and ony conducting
     * operation if there's at least one used
     * 
     * @return element that was removed
     */
    public String remove() {
        String removed = "";

        if (this.used > 0) {
            removed = this.array[this.front]; // sets front for removal if at least one element in queue
            this.array[this.front] = null; // removing...
            this.front++; // moves front to element after

            if (this.front == this.array.length) {
                this.front = 0; // same as add check for setting front to beginning once reached queue end
            }

            this.used--;
        }

        return removed;
    } // method remove

} // class FastQ