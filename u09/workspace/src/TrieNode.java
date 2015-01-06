import java.util.*;

/**
 * A node in a TrieSet stores elements whose String representation has been consumed completely and further 
 * prefixes to search objects by their remaining prefixes.
 * @author Thomas Stroeder
 * @param <E> The type of this TrieSet's elements.
 */
public class TrieNode<E> {

    /**
     * @return A TrieSet containing Integers for testing purposes.
     */
    public static TrieSet<Integer> createIntegerTestCase() {
        TrieNode<Integer> res = new TrieNode<Integer>();
        res.elements.add(null);
        TrieNode<Integer> oneNode = new TrieNode<Integer>();
        TrieNode<Integer> tenNode = new TrieNode<Integer>();
        tenNode.elements.add(10);
        TrieNode<Integer> twelveNode = new TrieNode<Integer>();
        twelveNode.elements.add(12);
        oneNode.prefixes.put("0", tenNode);
        oneNode.prefixes.put("2", twelveNode);
        TrieNode<Integer> twentyNode = new TrieNode<Integer>();
        twentyNode.elements.add(20);
        TrieNode<Integer> twoHundredNode = new TrieNode<Integer>();
        twoHundredNode.elements.add(200);
        TrieNode<Integer> twoHundredAndFiveNode = new TrieNode<Integer>();
        twoHundredAndFiveNode.elements.add(205);
        twentyNode.prefixes.put("0", twoHundredNode);
        twentyNode.prefixes.put("5", twoHundredAndFiveNode);
        res.prefixes.put("1", oneNode);
        res.prefixes.put("20", twentyNode);
        return new TrieSet<Integer>(res);
    }

    /**
     * The elements stored at this node (i.e., their String representation is completely consumed at this 
     * node).
     */
    private final Set<E> elements;

    /**
     * The remaining prefixes of elements stored in the TrieSet represented by this node and its 
     * successors.
     */
    private final TreeMap<String, TrieNode<E>> prefixes;

    /**
     * Creates a new TrieNode without any elements.
     */
    public TrieNode() {
        this.elements = new LinkedHashSet<E>();
        this.prefixes = new TreeMap<String, TrieNode<E>>();
    }

    /**
     * Adds the specified element to this Trie.
     * @param key The remaining String representation of the element.
     * @param element The element.
     * @return True if this Trie has been modified by adding the element (if the element was already 
     *         present, it is not added again). False otherwise.
     */
    public boolean add(String key, E element) {
        // IMPLEMENT ME
		return false;
    }

    /**
     * Removes all elements from this Trie.
     */
    public void clear() {
        this.prefixes.clear();
        this.elements.clear();
    }

    /**
     * @param key The remaining String representation of the object.
     * @param o The object.
     * @return True if the remaining TrieSet represented by this node and its successors contains the 
     *         specified object. False otherwise.
     */
    public boolean contains(String key, Object o) {
        if ("".equals(key)) {
            return this.elements.contains(o);
        }
        for (Map.Entry<String, TrieNode<E>> entry : this.prefixes.entrySet()) {
            if (key.startsWith(entry.getKey())) {
                return entry.getValue().contains(key.substring(entry.getKey().length()), o);
            }
        }
        return false;
    }

    /**
     * Removes the specified object from this Trie.
     * @param key The remaining String representation of the object.
     * @param o The object.
     * @return True if this Trie has been modified by the removal. False otherwise.
     */
    public boolean remove(String key, Object o) {
        if ("".equals(key)) {
            return this.elements.remove(o);
        }
        for (Map.Entry<String, TrieNode<E>> entry : this.prefixes.entrySet()) {
            if (key.startsWith(entry.getKey())) {
                return entry.getValue().remove(key.substring(entry.getKey().length()), o);
            }
        }
        return false;
    }

    /**
     * @return The elements stored at this node.
     */
    Set<E> getElements() {
        return this.elements;
    }

    /**
     * @return The remaining prefixes of elements stored in the TrieSet represented by this node and its 
     *         successors.
     */
    Map<String, TrieNode<E>> getPrefixes() {
        return this.prefixes;
    }

}
