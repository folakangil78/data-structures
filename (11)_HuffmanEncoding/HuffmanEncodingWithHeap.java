// import java.util.ArrayList;

public class HuffmanEncodingWithHeap {
    /**
     * Method to construct forest of HuffmanNodes representing symbols and their
     * frequencies.
     * 
     * @param frequencies represents array where index = ascii value of char of
     *                    given symbol
     * @return a minheap of huffmanNode objects replacing forest arraylist
     */
    public MinHeap<HuffmanNode> buildForest(int[] frequencies) {
        MinHeap<HuffmanNode> forest = new MinHeap<>();
        for (int asciiCode = 0; asciiCode < frequencies.length; asciiCode++) { // each index presumably represents ascii
                                                                               // of char
            if (frequencies[asciiCode] > 0) { // taken from in-class tutorial sesh
                // creates huffmanNode object char with freq>0
                HuffmanNode newNode = new HuffmanNode((char) asciiCode, frequencies[asciiCode]);
                forest.insert(newNode);
                // adds to minheap
            }
        }
        return forest;
    }

    /**
     * Method to construct Huffman tree from assembled minheap forest
     * 
     * @param forest is a minheap of huffmanNode objs
     * @return root of huffman tree
     */
    public HuffmanNode buildTree(MinHeap<HuffmanNode> forest) {
        while (forest.size() > 1) { // construct tree by removing nodes with smallest frqs and adding combination to
                                    // heap
            HuffmanNode node1 = forest.removeMin();
            HuffmanNode node2 = forest.removeMin();
            // removing two nodes with smalles freqs.

            // creates freq-only node by combining freqs of removed node1Andnode2
            HuffmanNode newNode = new HuffmanNode('\0', node1.getFrequency() + node2.getFrequency());
            // need to insert 0-escape sequence filler for huffmanNode constructor b/c needs
            // char param
            // needed to compile code with nodes that are null-chars

            newNode.setLeft(node1);
            newNode.setRight(node2);

            forest.insert(newNode);
        }

        // returnsReturn the last node in forest (shud be root)
        return forest.removeMin();
    }
}
