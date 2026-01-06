public class Person implements Comparable<Person>, SillyActions {

    private static final String DEFAULT_LAST_NAME = "LNU";
    private static final String DEFAULT_FIRST_NAME = "FNU";
    private static final int DEFAULT_YEAR_BORN = 1800;

    private String firstName;
    private String lastName;
    private int yearBorn;

    public Person(String firstName, String lastName, int yearBorn) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.yearBorn = yearBorn;
    }

    public Person(String firstName) {
        this(firstName, DEFAULT_LAST_NAME, DEFAULT_YEAR_BORN);
    }

    /** Default constructor */
    public Person() {
        this(DEFAULT_FIRST_NAME, DEFAULT_LAST_NAME, DEFAULT_YEAR_BORN);
    }

    /**
     * Implements the Comparable interface to determine the relative order of two
     * persons based on their age.
     * 
     */
    public int compareTo(Person other) {
        return other.getYearBorn() - this.yearBorn;
    } // method compareTo

    @Override
    public String toString() {
        return "Person [firstName=" + firstName + ", lastName=" + lastName + ", yearBorn=" + yearBorn + "]";
    } // method toString

    //------------------- AUTO GENERATED METHODS ------------------------------
    
    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public int getYearBorn() {
        return this.yearBorn;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setYearBorn(int yearBorn) {
        this.yearBorn = yearBorn;
    }

    //------------------- SILLY ACTIONS METHODS ------------------------------
    // Important note: declaring methods as non-static not only to mirror declarations in SillyActions interface but because
    // non-static methods being declared in the interface implies that they can be overriden and/or implemented in different ways 
    // by the implementing class
    // Declaring one as static in the inteface would imply that the method is solely intended for utility/operation purposes and doesn't
    // need to be elaborated on by implementing class

    /**
    * Make a random sound. Actually, produce a random word and print it (no need to
    * find how to make an actual sound)
    */
    public void makeRandomSound(){
        int randomIndex = (int) (Math.random() * PoemWords.words.length); 
        System.out.println("Randomized audio: " + PoemWords.words[randomIndex]); // gets random integer and matches to corresponding index of poem words array
    }

    /**
     * Perform a silly dance by describing it as a sequence of a few steps left,
     * right, backwards, and forward
     */
    public void performSillyDance(){
        String[] steps = {"left", "right", "backwards", "forwards"};
        StringBuilder dance = new StringBuilder("Silly dance sequence: "); // W3Schools for StringBuilder implementation
        for (int i = 0; i < 7; i++) { // iteration length determines how many steps, red - magic value, use random itself for random object
            int randomStep = (int) (Math.random() * steps.length); // takes randomized index of steps array for next step and appends to string
            dance.append(steps[randomStep]).append(" ");
        }
        System.out.println(dance.toString().trim());
    }

    /** Recite the alphabet backwards (but forget one letter) */
    public String reciteAlphabetBackwards(){
        String reverse_alphabet = "ZYXWVUTSRQPONMLKJIHGFEDCBA";
        int missingIndex = (int) (Math.random() * reverse_alphabet.length());
        return reverse_alphabet.substring(0, missingIndex) + reverse_alphabet.substring(missingIndex + 1); // concatenates substrings of first half to 
        // randomIndex (exclusive) and second half which is one after random index till the end
    }

    /** Count to ten in an unusual way (maybe skip a number) */
    public void countToTenWeirdly() {
        int skip = (int) (Math.random() * 10) + 1; // skipping random number bw 1 and 10
        System.out.println("Counting to ten in an unusual way:");
        for (int i = 1; i <= 10; i++) { // starting iteration at 1 and ending at 10 to correspond with output, consider magic val change
            if (i != skip) {
                System.out.print(i);
            }
        }
        System.out.println();
    }

    /**
     * Create a whimsical poem about a topic of your choice. Could be a few words at
     * random, as long as string topic is included in the first line
     */
    public String createWhimsicalPoem(String topic){
        StringBuilder poem = new StringBuilder("A whimsical poem about " + topic + "!\n");
        for (int i = 0; i < 10; i++) { // iterates for how many words wanted in poem, magic value! consider randomizing
            int randomIndex = (int) (Math.random() * PoemWords.words.length); // ensures that random function traverses all of poem words array
            poem.append(PoemWords.words[randomIndex]).append(" ");
        }
        return poem.toString();
    }

    /** Produce numbers for the state lottery */
    public void winStateLottery(){
        System.out.print("6 lottery numbers: ");
        for (int i = 0; i < 6; i++) { // why specifying 6 - magic value, 50 as well
            int number = (int) (Math.random() * 50) + 1; // Choosing 50 as the upper limit for my lottery numbers was arbitrary
            // adding one after the multiplication is to ensure that the 50 is inclusive
            System.out.print(number + " ");
        }
        System.out.println();
    }

    /** Main method to test implemented methods from SillyActions
     * Invocation of methods goes from bottom to top in this java file, starting with winStateLottery()
     * Primary concept here is that you need to create an instance of the Person class to then invoke the non-static methods
     * This is done by creating an instance of Person class, or a Person object
     * The main method by default is static, implying that it solely belongs to this Person class
     * Methods from SillyActions belong to objects/instances of the class because they have inter-class declarations
     */
    public static void main(String[] args) {
        // comments for main implementation in above javadoc
        Person person = new Person();

        person.winStateLottery();
        System.out.println();

        String poem = person.createWhimsicalPoem("fast cars");
        System.out.println(poem);
        System.out.println();

        person.countToTenWeirdly();
        System.out.println();

        String alphabet = person.reciteAlphabetBackwards();
        System.out.println("Reversing the alphabet and forgetting one letter: \n" + alphabet);
        System.out.println();

        person.performSillyDance();
        System.out.println();

        person.makeRandomSound();
    }
}