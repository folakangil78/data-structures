class ReverseString {
    public static void main(String[] args) {
        String reversed_word = reverse("Chicago");
        System.out.println(reversed_word);
    }

    public static String reverse(String word) {
        String[] initialWordArray = new String[word.length()];
        for (int i = 0; i < word.length(); i++) {
            initialWordArray[i] = String.valueOf(word.charAt(i));
        }

        String[] reversed_word_array = new String[word.length()];
        int arrayCount = 0;
        for (int i = word.length() - 1; i >= 0; i--) {
            reversed_word_array[arrayCount] = initialWordArray[i];
            arrayCount++;
        }
        String reversed_result = "";
        for (int i = 0; i < reversed_word_array.length; i++) {
            reversed_result += reversed_word_array[i];
        }
        return reversed_result;
    }

    /**
     * javadoc format
     */

    /*
     * block format
     */

    // in-line comments
}