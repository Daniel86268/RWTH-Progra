import java.util.*;

/**
 * @author Thomas Stroeder
 * @param <E> Element type.
 * Iterator through a functional set.
 */
public class FunctionalSetIterator<E> implements Iterator<E> {

    /**
     * The current simple functional set. It is always an add set with an unused (i.e., not contained in 
     * the set named "used" below) element or an empty set.
     */
    private SimpleFunctionalSet<E> current;

    /**
     * The most recent element this iterator has returned.
     */
    private E recentElem;

    /**
     * Flag indicating whether the remove operation is applicable (needed since elements may be null, so 
     * we cannot just check whether recentElem is null).
     */
    private boolean removable;

    /**
     * The functional set to which the current simple functional set belongs.
     */
    private final FunctionalSet<E> set;

    /**
     * A set of already seen elements (in add or remove sets).
     */
    private final Set<Object> used;

    /**
     * @param functionalSet The functional set containing the simple functional set to iterate over.
     * @param head The head of the simple functional set to iterate over.
     */
    public FunctionalSetIterator(
        FunctionalSet<E> functionalSet,
        SimpleFunctionalSet<E> head)
    {
        this.current = head;
        this.recentElem = null;
        this.removable = false;
        this.set = functionalSet;
        this.used = new FunctionalSet<Object>();
        this.forwardToNextUnusedSet();
    }

    @Override
    public boolean hasNext() {
        return !(this.current instanceof EmptySet);
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

    @Override
    public void remove() {
        if (this.removable) {
            this.set.remove(this.recentElem);
            this.removable = false;
        } else {
            throw new IllegalStateException(
                "The next method has not been called before this remove operation!");
        }
    }

    /**
     * Forwards the current set to the next remaining set which is no remove set and which is no add set 
     * with an already used element. During forwarding, used objects are added to the corresponding set.
     */
    private void forwardToNextUnusedSet() {
        boolean loop = true;
        while (loop) {
            loop = false;
            while (this.current instanceof RemoveSet) {
                this.used.add(((RemoveSet<E>) this.current).getObject());
                this.current = this.current.getRemainingSet();
            }
            if (this.current instanceof AddSet
                && this.used.contains(((AddSet<E>) this.current).getElement()))
            {
                loop = true;
                this.current = this.current.getRemainingSet();
            }
        }
    }

}
