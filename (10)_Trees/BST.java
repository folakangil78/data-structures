/**
 * A simple binary search tree
 */
public class BST {

    /* Constants */
    private static final String LONGEST = "";
    private static final String SHORTEST = " ".repeat(1024);

    /** The entry point to the tree */
    private TreeNode root;
    /** Count of nodes in the tree */
    private int numberOfNodes;
    /** Longest and shortest words stored in the tree */
    private String longest;
    private String shortest;

    /** Default constructor */
    public BST() {
        this.root = null;
        this.numberOfNodes = 0;
        this.shortest = SHORTEST;
        this.longest = LONGEST;
    } // default constructor

    /**
     * Overloaded add to take a string, wrap it into a TreeNode object, and invoke
     * the principal method that adds a note to the tree.
     * 
     * @param word String to add, as a node, to the tree
     * 
     */
    public void add(String word) {
        this.add(new TreeNode(word));
    } // method add

    /**
     * method to add node to tree, tracking the last non-null node that parent is
     * set to, tracking that last non-null so
     * that we know where to attach node param
     * 
     * @param node
     */
    public void add(TreeNode node) {
        // Special case for an empty tree
        if (root == null) {
            root = node; // Set the new node as the root if the tree is empty
            numberOfNodes++; // Increment the count of nodes

            // Initialize longest and shortest based on the new node
            String word = node.getWord();
            longest = word;
            shortest = word.trim(); // Trim spaces for shortest
        } else {
            TreeNode current = root; // Start traversal from the root
            TreeNode parent = null; // parent functionality is holding correct position where to insert give node

            // Traverse tree to find correct position for new node
            while (current != null) {
                parent = current; // Caches last non-null as possible parent for node param

                if (node.getWord().compareTo(current.getWord()) < 0) { // Go left if word is less
                    current = current.getLeft(); // Cache current as left child b/c less
                } else if (node.getWord().compareTo(current.getWord()) > 0) { // Go right if word greater
                    current = current.getRight();
                } else {
                    current = null; // Assuming that word already in tree
                }
            } // at each while iteration, parent is set to current so that if/when
              // current=null, parent has the last populated node in the tree no matter what
              // that location denotes where node param needs to become a child at (depending
              // on less than or greater than in alphabet)

            // Assume parent is node to which noe should be made child to of
            if (node.getWord().compareTo(parent.getWord()) < 0) {
                parent.setLeft(node); // Node insertion as child of parent...
            } else {
                parent.setRight(node);
            }
            numberOfNodes++;

            // Update longest/shortest
            String word = node.getWord();
            if (word.length() > longest.length()) {
                longest = word;
            }

            // Trimming whitespace for shortest
            String trimmedWord = word.trim();
            if (trimmedWord.length() < shortest.length()) {
                shortest = trimmedWord;
            }
        }
    } // method add

    public int getNumberOfNodes() {
        return numberOfNodes;
    }

    /**
     * Verifies whether target is in tree and returns false if not
     * 
     * @param target
     * @return returns true if target is present in the tree and false otherwise
     */
    public boolean contains(String target) {
        TreeNode currentNode = this.root;
        boolean found = false;

        while (currentNode != null && found == false) {
            int comparison = target.compareTo(currentNode.getWord());

            if (comparison == 0) { // Target matches current node
                found = true;
            } else if (comparison < 0) { // Implies given target is smaller and accesses left child
                currentNode = currentNode.getLeft();
            } else { // Target is greater, accesses right child
                currentNode = currentNode.getRight();
            }
        }
        return found;
    }

    /**
     * Outputs text representation of object, traversing tree object and printing
     * string of nodes in order
     */
    public String toString() {
        TreeNode current = this.root;
        String result = "";

        while (current != null) {
            while (current.hasLeft()) {
                current = current.getLeft();
            } // Traverses to smallest node (leftest)

            while (current != null) { // Traverse from left to right
                if (result.isEmpty()) {
                    result += "";
                } else {
                    result += " "; // Adds space between populated nodes
                }
                result += current.getWord();

                if (current.hasRight()) { // Traverses to right if exists
                    current = current.getRight();

                    while (current.hasLeft()) { // Move to leftmost string of right sub
                        current = current.getLeft();
                    }
                } else {
                    current.getParent(); // Had to insert parent getter in TreeNode, used to move back up if no further
                                         // child
                }
            }
        }
        return result;
    }

    /**
     * Removes the node with target from the tree and returns it. If the node is not
     * present, the method will return null.
     * 
     * @param target String of node to be removed
     * @return Node or null if not found
     */
    public TreeNode remove(String target) {
        TreeNode removed = null;
        if (target != null && this.root != null) {
            removed = this.remove(target, this.root); // recursive call to overloaded remove()
            // Ensures that if root contains target string and is removed, then belowNode is
            // copied
            if (removed != null && removed == this.root) {
                this.root = removed; // In case the root changes
            }
        }
        return removed;
    }

    /**
     * Overloaded method to deal with diff child cases, removing node starting at
     * belownode
     * 
     * @param target    String of node to be removed
     * @param belowNode Node below node-to-be-removed to start removal at
     * @return Node deleted or null
     */
    public TreeNode remove(String target, TreeNode belowNode) {
        TreeNode parent = null;
        TreeNode current = belowNode; // Starts removal at child of node-to-be-removed
        TreeNode removedNode = null;

        boolean found = false;

        while (current != null && !found) { // Traverses to find target
            int comparison = target.compareTo(current.getWord());

            if (comparison == 0) { // Target found
                removedNode = current;
                found = true;
            } else if (comparison < 0) { // Target is less than current node alphabet
                parent = current;
                current = current.getLeft(); // Traverses down left child
            } else { // Target is greater than current node alphabet
                parent = current;
                current = current.getRight(); // Traverses down right child
            }
        }

        // Assuming target now found...
        if (found) {
            // Node has no children below it
            if (!current.hasLeft() && !current.hasRight()) { // No children check
                if (parent == null) { // If root check
                    belowNode = null;
                    this.root = null; // Resets node if needed
                } else if (parent.getLeft() == current) {
                    parent.setLeft(null);
                } else {
                    parent.setRight(null);
                }
            }
            // One child
            else if (current.hasLeft() || current.hasRight()) {
                TreeNode child;
                if (current.hasLeft()) { // locates child of target/current
                    child = current.getLeft();
                } else {
                    child = current.getRight();
                }

                if (parent == null) { // Root case
                    belowNode = child;
                    this.root = child; // Copies child into root to delete root if current
                } else if (parent.getLeft() == current) {
                    parent.setLeft(child);
                } else {
                    parent.setRight(child); // Updates node-to-be-removed with child below
                }
            }
            // Two children
            else {
                // Find in-order successor - smallest node in right subtree
                TreeNode successor = current.getRight();
                TreeNode parent_of_successor = current;

                while (successor.hasLeft()) {
                    parent_of_successor = successor;
                    successor = successor.getLeft(); // Initializes to smaller value
                }

                current.setWord(successor.getWord()); // Replacing current node-to-be-removed with successor (assumes
                                                      // successor been found in right subtree)

                if (parent_of_successor.getLeft() == successor) {
                    parent_of_successor.setLeft(successor.getRight()); // Removes successor and reinitializes to right
                                                                       // child (won't have left child b/c successor is
                                                                       // smallest value)
                    // Removes need for additional recursive call to remove() for successor
                } else {
                    parent_of_successor.setRight(successor.getRight()); // Or no children and successor is right child
                                                                        // itself
                }
            }
        }
        return removedNode;
    }
}

// in class implementation notes - keep for reference

// boolean found = false;
// cursor = root
// parent = null
// while (cursor != null !found)
// parent = cursor
// if cursor.getWord().equals(target) (compareto)
// break?
// found = true instead of break
// if target.compareto(cursor.getWord()) < 90
// cursor = cursor.getLeft
// else
// curosr = cursor.getRight
// // either cursor is at target or target not present in tree, can use boolean
// to figure out which option is the case at end of while loop
// if (found) {
// //cursor is node to delete, but also need to initiate parent varible at
// beginning to store parent
// // now you have parent is parent of cursor
// if cursor.getcountChildren = 0 {
// if parent.getLeft != null {
// parent.left = null
// } else {
// parent.right = null
// }
// } // done deleting node with 0 children
// else if cursor.countChildren = 1 {
// if parent.getLeft != null {
// if cursor.getLeft != null {
// parent.left = current.left
// } else {
// parent.left = cursor.right
// }
// } else if cursor.getleft {
// p.right = c.left
// } else {
// p.right = c.right
// }
// } // done with possible one child
// else {//countchildren = 2
// //cursor has two children, where cursor is node to delete
// //look at tree with cursor.right as root
// // find successor - look to code and diagram on paper
// // cursor.setWord = successor.getWord
// // basically replacing node to be removed with two children with leftmost
// node of right substree, but when replacing
// // node, you are copying that leftmost node to slot of node you want to
// remove, so you need to remove the copy of that leftmost node
// // (the original in the slot you took it from to copy to the slot of node you
// want to remove)
// // you basically calling remove() again to delete extra copy, which is
// deleting node with one child, visualize tree diagram with example of removing
// // "now" by replacing now with "of", but have duplicate "of", so you need to
// call remove() method again on string of "of" on tree to delete;
// // the basic recursive call:
// remove (string) {
// remove (string, this.root) // calls overloaded method in first remove()
// method
// }
// remove (string, node) { // node is entry point
// on initial call: this.root
// if node-to-delete has 2 children, we call again to remove(successor.getWord,
// nodeToDelete.rightChild)
// }
// } // basically gonna have to make a second call to overloaded remove method
// of remove(successor.getWord, nodetodelete.rightChild)
// // where successor is always leftmost node (smallest) of right subtree of
// rode you want to remove
// }
