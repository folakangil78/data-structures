public class TrainLine {

    /** The name of the trainline */
    private String name;
    /** Points to the first station in the trainline */
    private TrainStation head;
    /** Points to the last station in the trainline */
    private TrainStation tail;
    /** Keeps a running tally of train stations in the trainline */
    private int numberOfStations;

    /** Full constructor */
    public TrainLine(String name, TrainStation head) {
        this.name = name;
        this.head = head;
        this.numberOfStations = 0;
        if (this.head != null) {
            // If head is not null, there is one station in the line
            this.numberOfStations = 1;
        }
        // At initialization head and tail point to the same station even if null
        this.tail = null;
    } // full constructor

    /** Basic constructor */
    public TrainLine(String name) {
        this(name, null);
    } // basic constructor

    /**
     * Creates a new station with the given name and adds it to the end of the line.
     */
    public void add(String name) {
        // Create the new station to add
        TrainStation station_to_insert = new TrainStation(name);
        // Determine where to place the new station
        if (this.head == null) {
            // Trainline is empty, make new station the head of the line
            this.head = station_to_insert;
        } else {
            // When there is a head station already, add the new station after the last
            // station in the line.
            this.tail.setNext(station_to_insert);
        }
        // The new station becomes the tail station of the line
        this.tail = station_to_insert;
        // Update station count
        this.numberOfStations++;
    } // method add

    /** Returns the number of stations in the line >= 0 */
    public int getNumberOfStations() {
        return numberOfStations;
    } // method getNumberOfStations

    /*
     * METHOD STUBS TO ENSURE CODE COMPILES. YOU WILL HAVE TO REWRITE THIS CODE TO
     * MATCH THE SPECIFICATIONS AND ALSO YOU'LL HAVE TO WRITE METHOD isEmpty.
     */

    /**
     * Method to determine if the trainline object has the given train station name
     * specified by param
     * sets current station to beginning of line and iterates through train line
     * with getNext()
     * only returning false if after breaking out of loop because after last station
     * is null, no 'name' station is found
     * 
     * @param name
     * @return boolean value indicating whether or not station of given name is
     *         within line
     */
    public boolean contains(String name) {
        TrainStation current = head; // sets current to beginning of trainline
        while (current != null) {
            if (current.getName().equals(name)) { // compares current to name param and returns true if same
                return true;
            }
            current = current.getNext(); // uses getNext to iterate through line and compare names of stataions
        }
        return false;
    }

    /**
     * Method to locate index of station with param name in line
     * same algorithm as contains() but uses index variable to keep track of index
     * should a match be found
     * 
     * @param name
     * @return index of given station with name
     */
    public int indexOf(String name) {
        int index = 0; // variable to keep track of index of stations in line, increments after each
                       // iteration in case match is found
        TrainStation current = head;

        while (current != null) {
            if (current.getName().equals(name)) {
                return index; // same logic as contains() but increments index after failing if condition to
                              // see if matches name
            }
            current = current.getNext();
            index++; // tracks index of next station in line in case if-comparison with name param
                     // renders true
        }
        return -1; // station not found
    }

    /**
     * Method to return names of stations in line in reverse order on separate lines
     * uses prepending of getName outputs to insert next station name to beginning
     * of result string rather than after (as you would generally do) to reverse
     * 
     * @return string of reversed list of stations on line
     */
    public String reverseList() {
        TrainStation current = head; // Start from the head of the train line
        String result = ""; // Initialize the result string

        while (current != null) {
            result = current.getName() + "\n" + result; // adds station name to beginning of result string instead of
                                                        // after, reversing output of iterations
            current = current.getNext(); // Move to the next station
        }
        return result; // Return the reversed list as a string
    }

    /**
     * Method to check whether the trainline actually has any stations inside it
     * returns false if there are actual named stations within line
     * 
     * @return whether or not list is empty
     */
    public boolean isEmpty() {
        return head == null; // whether beginning of train line has station initialized
    }

    /**
     * Method to insert string station name at index of position + 1
     * @param station_name
     * @param position_before_insertion to denote the index before position for insertion location
     */
    public void insert(String station_name, int position_before_insertion) {
        if (position_before_insertion < 0 || position_before_insertion > this.numberOfStations) { // makes sure to use this keyword to imply numberofstations as part of current trainstation obj
            throw new IndexOutOfBoundsException("outside of train station.");
        } // throws exception if int param is out of bounds

        TrainStation station_to_insert = new TrainStation(station_name);

        if (position_before_insertion == 0) { // insert station at beginning if pos is 0
            station_to_insert.setNext(this.head);
            this.head = station_to_insert;
        } else {
            // iterate to position before insertion
            TrainStation current = this.head;
            for (int i = 0; i < position_before_insertion - 1; i++) {
                current = current.getNext();
            }
            station_to_insert.setNext(current.getNext());
            current.setNext(station_to_insert);
        }

        if (position_before_insertion == this.numberOfStations) { // basically checks if pos is last station in trainline, and if the case, updates tail
            this.tail = station_to_insert;
        }

        numberOfStations++;
    }

    /**
     * Method to output TrainLine of cached stations in snake-like format
     * snake-like format intends for lines of less than 80 characters printing but
     * also
     * looping right below the last station on the previous line,
     * making sure to output each successive station backwards (as per the concept
     * of a loop)
     */
    public String toString() {
        // making sure to use this keyword to refer to this specific instance of head
        // field of this specific trainstation object
        TrainStation current = this.head;

        String output = "";

        int current_char_count = 0;
        int max_chars = 80;

        // caching arrows in separate string vars to avoid magic values
        String arr_backward = "<--";
        String arr_down = "|";
        String arr_end = "--+";
        String arr_forward = "-->";

        boolean move_downward = false;

        TrainStation[] stations = new TrainStation[numberOfStations];

        int index = 0;
        // basically inserting stations in stations array
        while (current != null) {
            stations[index++] = current;
            current = current.getNext();
        }

        index = 0;
        while (index < stations.length) {
            String name_of_station = stations[index].getName();
            int length_of_name = name_of_station.length();

            if (move_downward == false) {
                // progressing here for lines that require forward printing (not looping)
                if (current_char_count + length_of_name + 3 <= max_chars) {
                    output += name_of_station;
                    current_char_count += length_of_name;

                    if (index < stations.length - 1) {
                        output += arr_forward;
                        current_char_count += 3;
                    } else {
                        output += arr_end;
                    }
                } else {
                    // inserts ending arrow formatting if printing another station name exceeds 80
                    // char count
                    output += arr_end + "\n";
                    output += arr_down + "\n";
                    current_char_count = 0;
                    move_downward = true;
                }
            } else {
                // printing downward part of loop
                current_char_count = length_of_name;
                output += name_of_station;
                index++;

                // *tries* to deal with printing backwards from end of prev line
                if (index < stations.length) {
                    output += arr_backward;
                    while (index < stations.length
                            && current_char_count + stations[index].getName().length() + 3 <= max_chars) { // only proceeds if we are still within stations array and 
                                //the total chars required plus the length of the name of the station is less than 80
                        output += stations[index].getName();
                        current_char_count += stations[index].getName().length();

                        if (index < stations.length - 1) {
                            current_char_count += 3;
                            output += arr_backward;
                        }
                        index++;
                    }
                    if (index < stations.length) { // only inserts ending symbol if reached the end of stations array
                        output += arr_end + "\n" + arr_down + "\n";
                    }
                    move_downward = false;
                }
            }
            index++;
        }

        return output;
    }

    /*******************************************************************************
     * DO NOT REMOVE TESTS FROM THE CODE BELOW. YOU MAY **ADD** YOUR OWN TESTS BUT *
     * YOU MAY NOT REMOVE ANY OF THE EXISTING TEST CODE. *
     ******************************************************************************/
    public static void main(String[] args) {
        // A few station names
        String[] stationNames = { "Howard", "Jarvis", "Morse",
                "Loyola", "Granville", "Thorndale" };
        // A populated trainline
        TrainLine redLineSB = new TrainLine("Red Line SB");
        for (String station : stationNames) {
            redLineSB.add(station);
        }
        // An empty trainline
        TrainLine brownLineSB = new TrainLine("Brown Line SB");
        // A random station name
        String randomName = "Oak Park";
        // Guard tests
        redLineSB.indexOf(null);
        redLineSB.contains(null);
        // Test indexOf on existing values
        boolean indexOfTestExisting = true;
        for (int i = 0; i < stationNames.length; i++) {
            indexOfTestExisting = (indexOfTestExisting && (redLineSB.indexOf(stationNames[i]) == i));
        }
        // Test indexOf for non existing station
        boolean indexOfTestNotExisting = (redLineSB.indexOf(randomName) == -1);
        // Test indexOf on empty line
        boolean indexOfTestingEmpty = (brownLineSB.indexOf(stationNames[0]) == -1);
        // Test contains for existing stations
        boolean containsTestExisting = true;
        for (String station : stationNames) {
            containsTestExisting = (containsTestExisting && redLineSB.contains(station));
        }
        // Test contains for non existing values
        boolean containsTestNonExisting = (!redLineSB.contains(randomName));
        // Test reverse list
        String expectedReverseList = "";
        for (int i = stationNames.length - 1; i >= 0; i--) {
            expectedReverseList = expectedReverseList + stationNames[i] + "\n";
        }
        boolean reverseListTest = redLineSB.reverseList().equals(expectedReverseList);
        // Reporting strings
        final String PASS = "Pass";
        final String FAIL = "Fail";
        String reportIndexOfTestExisting = (indexOfTestExisting) ? PASS : FAIL;
        String formatIndexOfTestExisting = "\n\nindexOf test for existing values: ......... %s";
        String reportIndexOfTestNonExisting = (indexOfTestNotExisting) ? PASS : FAIL;
        String formatIndexOfTestNonExisting = "\nindexOf test for non existing values: ..... %s";
        String reportIndexOfTestEmpty = (indexOfTestingEmpty) ? PASS : FAIL;
        String formatIndexOfTestEmpty = "\nindexOf test for empty object: ............ %s";
        String reportContaisTestExisting = (containsTestExisting) ? PASS : FAIL;
        String formatContainsTestExisting = "\ncontains test for existing values: ........ %s";
        String reportContainsTestNonExisting = (containsTestNonExisting) ? PASS : FAIL;
        String formatContainsTestNonExisting = "\ncontains test for non existing values: .... %s";
        String reportReverseListTest = (reverseListTest) ? PASS : FAIL;
        String formatReverseListTest = "\nreverseList test: ......................... %s\n\n";
        System.out.printf(formatIndexOfTestExisting, reportIndexOfTestExisting);
        System.out.printf(formatIndexOfTestEmpty, reportIndexOfTestEmpty);
        System.out.printf(formatIndexOfTestNonExisting, reportIndexOfTestNonExisting);
        System.out.printf(formatContainsTestExisting, reportContaisTestExisting);
        System.out.printf(formatContainsTestNonExisting, reportContainsTestNonExisting);
        System.out.printf(formatReverseListTest, reportReverseListTest);
        // ----------- YOU MAY ADD YOUR OWN TESTS BELOW THIS COMMENT LINE
        // ---------------

        redLineSB.insert("MONKEYBUSINESS", 3);
        redLineSB.insert("TEXAS", 5);
        redLineSB.insert("VIRGINIA", 2);
        redLineSB.insert("ARLINGTON", 4);
        System.out.println("insertion was successful");
        System.out.println();

        System.out.println(redLineSB.toString());

    } // method main
} // class TrainLine