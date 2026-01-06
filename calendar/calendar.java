import java.io.Console;

public class calendar {
  public static void main(String[] args){
    
    printCalendar(31,6);
    
  }

  public static String padded(int n, int width) {
    String s = "" + n;
    for (int i = s.length(); i < width; i++) {
      s = " " + s;
    }
    return s;
  }

  /**
   * method to print formatted calendar of a singular month
   * @param numberOfDays total in given month
   * @param firstSunday date of first sunday of month
   */
  public static void printCalendar(int numberOfDays, int firstSunday) {
    // top calendar label array
    String[] daysofWeek = new String[7];
    daysofWeek[0] = "Sun";
    daysofWeek[1] = "Mon";
    daysofWeek[2] = "Tue";
    daysofWeek[3] = "Wed";
    daysofWeek[4] = "Thu";
    daysofWeek[5] = "Fri";
    daysofWeek[6] = "Sat";
    System.out.print("  " + daysofWeek[0]);
    for(int i = 1; i < daysofWeek.length; i++) {
      System.out.print("    " + daysofWeek[i]);
    }
    System.out.print(" ");
    System.out.println();

    // top/bottom lines in calendar
    System.out.print('+');
    for (int i = 0; i < 7; i++) {
      System.out.print("------+");
    }
    System.out.println();



    int white_space = (7 + 6 * (8-firstSunday));
    
    for (int i = 0; i < white_space; i++) {
      System.out.print(" ");
    }
    System.out.print('1');
  }
}