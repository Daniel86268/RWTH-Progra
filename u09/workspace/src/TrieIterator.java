import java.util.*;

public class TrieIterator<E> implements Iterator<E> {

	private TrieNode<E> current;

	@Override
	public boolean hasNext() {
		return !(this.current == null);
	}

	@Override
	public E next() {
		if (this.hasNext()) {
			E elem = ((AddSet<E>) this.current).getElement();
			this.used.add(elem);
			this.recentElem = elem;
			this.removable = true;
			this.current = this.current.getRemainingSet();
			this.forwardToNextUnusedSet();
			return elem;
		} else {
			throw new NoSuchElementException();
		}
	}

	public void remove() {

	}

}