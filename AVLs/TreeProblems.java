/*
 * *** Francis Olakangil / Section 001 ***
 *
 * This java file contains several simple tree problems that need to be
 * codified. These routines  must use the TreeMap and TreeSet library
 * classes from the Java Collection Framework.
 *
 */

import java.util.*;

public class TreeProblems {

  /**
   * Method different()
   *
   * Given two TreeSets of integers, return a TreeSet containing all elements
   * that are NOT in both sets. In other words, return a TreeSet of all the
   * elements that are in one set but not the other.
   */
  
  public static Set<Integer> different(Set<Integer> setA, Set<Integer> setB) {

    // INSERT CODE HERE - DO NOT FORGET TO PLACE YOUR NAME ABOVE
    //
    // This can be done numerous ways, but once such will only that
    // *several* lines of code. Hint: create two temporary TreeSets and utilize the
    // methods retainAll(), addAll(), and removeAll(). But in the end, get something to work.

    // creating copies of A and B params to change
    Set<Integer> onlyInA = new TreeSet<>(setA);
    Set<Integer> onlyInB = new TreeSet<>(setB);

    // storing common elements
    Set<Integer> common = new TreeSet<>(setA);
    common.retainAll(setB);

    // removing common elements from A and B sets
    onlyInA.removeAll(common);
    onlyInB.removeAll(common);

    // combining remaining unique elements
    onlyInA.addAll(onlyInB);

    // creates copies to change, finds common elems using retainAll method
    // removes common elements from either set
    // combines remaining unique elems
    return onlyInA;
  }


  /**
   * Method removeEven()
   *
   * Given a treeMap with the key as an integer, and the value as a String,
   * remove all <key, value> pairs where the key is even. 
   */

  public static void removeEven(Map<Integer, String> treeMap) {
    Set<Integer> evenKeys = new HashSet<>();
    // storing even keys
    for (Integer key : treeMap.keySet()) {
      if (key % 2 == 0) {
        evenKeys.add(key);
      }
    }

    // removing even keys
    for (Integer key : evenKeys) {
      treeMap.remove(key);
    }
    // collects even keys first to avoid modifying map while iterating over
    // iterating over stored even keys and removing from given treeMap param
  }


  /**
   * Method treesEqual()
   *
   * Given two treeMaps, each with the key as an integer, and the value as a String,
   * return a boolean value indicating if the two trees are equal or not.
   */

  public boolean treesEqual(Map<Integer, String> tree1,Map<Integer, String> tree2 ) {
    return tree1.equals(tree2); // only need to check if both maps identical
    // equals fxn directly compares
  }

} // end treeProblems class
