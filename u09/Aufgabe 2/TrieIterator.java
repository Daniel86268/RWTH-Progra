import java.util.*;



public class TrieIterator<E> implements Iterator<E>{

	private TrieNode<E> current;

	
	@Override
    public boolean hasNext() {
        return !(this.current == null);
    }
	
	public TrieNode<E> next() {
	
	
	}


}