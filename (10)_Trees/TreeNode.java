public class TreeNode implements Comparable<TreeNode> {

    /** The data payload of the node */
    private String word;
    /** Its left and right pointers */
    private TreeNode left;
    private TreeNode right;
    private TreeNode parent;


    /**
     * Basic constructor creates a simple node with a payload and two null children.
     * 
     * @param word the data we store in the node.
     */
    public TreeNode(String word) {
        this.word = word;
        this.left = null;
        this.right = null;
    } // basic constructor

    /**
     * Implementation of Comparable using the nodes' content strings as basis for
     * comparison. If the 
     */
    public int compareTo(TreeNode other) {
        int result = 1;
        if (other != null)
            result = this.getWord().compareTo(other.getWord());
        return result;
    } // method compareTo

    public String toString() {
        return this.getWord();
    } // method toString

    /** Accesors and mutators */

    public boolean hasLeft() {
        return this.left != null;
    }

    public boolean hasRight() {
        return this.right != null;
    }

    public String getWord() {
        return word;
    }

    public TreeNode getRight() {
        return this.right;
    }

    public TreeNode getLeft() {
        return this.left;
    }

    public void setLeft(TreeNode node) {
        this.left = node;
    }

    public void setRight(TreeNode node) {
        this.right = node;
    }

    public TreeNode getParent() {
        return this.parent;
    }
    
    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int countChildren() {
        int totalCount = 0;

        if (this.hasLeft()) {
            totalCount++;
        }
        if (this.hasRight()) {
            totalCount++;
        }
        return totalCount; // Increments count by checking presence of left/right childs
    }
} // class TreeNode