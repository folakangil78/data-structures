/**
 * A simple linked list object. Its nodes are defined as an inner class.
 * 
 * A -> null ... the middle node is A
 * 
 * When the SimpleLinkedList object is:
 * 
 * A -> B --> null ... the middle node is also A
 * 
 * When the SimpleLinkedList object is:
 * 
 * A -> B --> C --> null ... the middle node is B
 * 
 * When the SimpleLinkedList object is:
 * 
 * A -> B --> C --> D --> null ... the middle node is also B
 * 
 * When the SimpleLinkedList object is:
 * 
 * A -> B --> C --> D --> E --> null ... the middle node is C, etc
 * 
 * 
 * 2) Write a method called invert that returns the inverted version of the
 * present SimpleLinkedList object. For example, if the current object is
 * 
 * A --> B --> C --> D --> E --> null
 * 
 * method invert should return the object
 * 
 * E --> D --> C --> B --> A --> null.
 * 
 * Remember, no arrays or arraylists or any other data structure except for
 * SimpleLinkedList.
 * 
 */

public class SimpleLinkedList {

    /**
     * Inner class for Node. Node fields can be accessed directly, for simplicity of
     * code. This is an intentional violation of the Pact.
     */
    class Node {
        String data;
        Node next;

        /** Simple string representation for Node */
        public String toString() {
            return this.data;
        } // method Node.toString
    } // inner class Node

    /** The only field in class SimpleLinkedList */
    Node head;

    /**
     * Add a new node to the linked list
     */
    public void add(String data) {
        Node newNode = new Node();
        newNode.data = data;
        if (this.head == null) {
            this.head = newNode;
        } else {
            // Traverse the list to find the last node
            Node current = this.head;
            while (current.next != null) {
                current = current.next;
            }
            // current is now the last node in the list
            current.next = newNode;
        }
    } // method add

    /**
     * Find and return the middle node of the linked list.
     * Used two-pointer strategy to traverse through list once with fast pointer
     * moving twice as
     * fast as slow pointer
     * odd-len = middle -> center
     * even-len = middle -> first of middle pair
     * 
     * Instructional note: Remember, no arrays or arraylists or any other data
     * structure except for
     * SimpleLinkedList.
     * 
     * @return middle node of simple linked list obj
     */
    public Node findMiddle() {
        Node faster_cursor = this.head; // two nodes at once
        Node slower_cursor = this.head; // one node at once

        while (faster_cursor != null && faster_cursor.next != null) { // end traversal when fster_cursor reaches null
            faster_cursor = faster_cursor.next.next; // 2x speed movement
            if (faster_cursor != null) { // Move slow pointer only if fast has not reached the end
                slower_cursor = slower_cursor.next;
                // only moving slower_cursor if fast_cursor is within list ensures slower moves
                // at only half the nodes that fast traverses over, ending up at middle
            }
        }
        return slower_cursor;
    } // method SimpleLinkedList.findMiddle

    /**
     * Invert a linked list.
     * Reversing given linked list by traversing once with three different ptrs -
     * current, prev, and next
     * Uses three pointers to reverse pointer b/w each node
     * Head ptr then set to initial tail (new beginning)
     * 
     * Instructional note: Remember, no arrays or arraylists or any other data
     * structure except for
     * SimpleLinkedList. For this method you may NOT use method SimpleLinkedList.add
     * 
     * @return a reversed linked list
     */
    public SimpleLinkedList invert() {
        Node current_ptr = this.head;
        Node prev = null;
        Node next = null;

        while (current_ptr != null) {
            next = current_ptr.next; // caching subsequent node to flip its pointer
            current_ptr.next = prev; // basically flipping ptr of current node to node behind it (first iteration
                                     // will reverse ptr to null b/c it'll be the end)
            prev = current_ptr; // allows subsequent iterations to point to node it just passed
            current_ptr = next;
        }

        this.head = prev; // sets start of linked list to last current_node, which will be last node in
                          // list

        return this;
    } // method SimpleLinkedList.invert

    /** String representation for the simple linked list */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.head != null) {
            Node current = this.head;
            while (current != null) {
                sb.append(String.format("%s", current.data));
                current = current.next;
            }
        }
        return sb.toString();
    } // method SimpleLinkedList.toString

    /**
     * Driver/test code. This is some of the most embarassing code I've ever written
     * but it works. Learn from it and do not write code that bad! :-)
     */
    public static void main(String[] args) {
        SimpleLinkedList demo = new SimpleLinkedList();
        boolean test1 = demo.findMiddle() == null;
        demo.add("A");
        boolean test2 = demo.findMiddle().data.equals("A");
        demo.add("B");
        boolean test3 = demo.findMiddle().data.equals("A");
        demo.add("C");
        boolean test4 = demo.findMiddle().data.equals("B");
        demo.add("D");
        demo.add("E");
        boolean test5 = demo.findMiddle().data.equals("C");
        boolean success = test1 && test2 && test3 && test4 && test5;
        if (success) {
            System.out.println("Method findMiddle works as specified.");
        } else {
            System.out.println("Method findMiddle not working as specified.");
        }
        String left = demo.toString();
        String right = demo.invert().toString();
        boolean test10 = left.length() == right.length();
        for (int i = 0; i < left.length(); i++) {
            test10 = test10
                    && left.charAt(i) == right.charAt(right.length() - 1 - i);
        }
        if (test10) {
            System.out.println("Method invert works as specified.");
        } else {
            System.out.println("Method invert not working as specified.");
        }
    } // method SimpleLinkedList.main

} // class SimpleLinkedList
