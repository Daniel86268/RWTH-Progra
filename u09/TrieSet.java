import java.lang.reflect.*;
import java.util.*;

/**
 * A TrieSet stores prefix Strings in a search tree to find its elements.
 * @author Thomas Stroeder
 * @param <E> The type of this TrieSet's elements.
 */
public class TrieSet<E> implements Set<E> {

    /**
     * Test program.
     * @param args Ignored.
     */
    public static void main(String[] args) {
        TrieSet<Integer> intSet = TrieNode.createIntegerTestCase();
        /*
         * iterator test should yield:
         * null
         * 10
         * 12
         * 20
         * 200
         * 205
         */
        Iterator<Integer> it = intSet.iterator();
        while (it.hasNext()) {
            Integer element = it.next();
            System.out.println(element);
            if (element == null || element % 2 != 0) {
                it.remove();
            }
        }
        // toString() test should yield: {10, 12, 20, 200}
        System.out.println(intSet);
        // retainAll() test should yield: {10, 200}
        Set<Integer> otherSet = new LinkedHashSet<Integer>();
        otherSet.add(3);
        otherSet.add(10);
        otherSet.add(200);
        intSet.retainAll(otherSet);
        System.out.println(intSet);
        // add() test should not throw an exception
        intSet.clear();
        otherSet.clear();
        Random gen = new Random();
        for (int i = 0; i < 100000; i++) {
            Integer v = gen.nextInt(300) + 1;
            if (gen.nextInt(3) > 0) {
                intSet.add(v);
                otherSet.add(v);
            } else {
                final int size = otherSet.size();
                if (size > 0 && gen.nextInt(3) > 0) {
                    v = gen.nextInt(size);
                    for (Integer e : otherSet) {
                        v--;
                        if (v <= 0) {
                            v = e;
                            break;
                        }
                    }
                }
                intSet.remove(v);
                otherSet.remove(v);
            }
            if (!intSet.equals(otherSet)) {
                throw new IllegalStateException("The two sets are not equal!");
            }
        }
    }

    /**
     * The root node of the Trie storing the elements of this set.
     */
    private final TrieNode<E> root;

    /**
     * Creates an empty TrieSet.
     */
    public TrieSet() {
        this.root = new TrieNode<E>();
    }

    /**
     * Creates a TrieSet containing the elements in the specified collection.
     * @param c The collection.
     */
    public TrieSet(Collection<? extends E> c) {
        this();
        this.addAll(c);
    }

    /**
     * Creates a TrieSet with the specified node as root.
     * @param node The root of the Trie.
     */
    protected TrieSet(TrieNode<E> node) {
        this.root = node;
    }

    @Override
    public boolean add(E e) {
        return this.root.add(e == null ? "" : e.toString(), e);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean res = false;
        for (E element : c) {
            res |= this.add(element);
        }
        return res;
    }

    @Override
    public void clear() {
        this.root.clear();
    }
    
    @Override
    public boolean contains(Object o) {
        return this.root.contains(o == null ? "" : o.toString(), o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!this.contains(o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Set) {
            Set<?> s = (Set<?>)o;
            return this.containsAll(s) && s.containsAll(this);
        } else if (o instanceof Collection) {
            Collection<?> c = (Collection<?>)o;
            if (c.containsAll(this)) {
                Set<Object> check = new LinkedHashSet<Object>();
                for (Object element : c) {
                    if (check.contains(element)) {
                        return false;
                    }
                    check.add(element);
                    if (!this.contains(element)) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int res = 101;
        for (E element : this) {
            res += element.hashCode() * 17;
        }
        return res;
    }

    @Override
    public boolean isEmpty() {
        return this.iterator().hasNext();
    }

    @Override
    public Iterator<E> iterator() {
        // IMPLEMENT ME
    }

    @Override
    public boolean remove(Object o) {
        return this.root.remove(o == null ? "" : o.toString(), o);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean res = false;
        for (Object o : c) {
            res |= this.remove(o);
        }
        return res;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        // IMPLEMENT ME
    }

    @Override
    public int size() {
        int res = 0;
        for (Iterator<E> it = this.iterator(); it.hasNext(); it.next()) {
            res++;
        }
        return res;
    }

    @Override
    public Object[] toArray() {
        Object[] res = new Object[this.size()];
        int index = 0;
        for (E element : this) {
            res[index++] = element;
        }
        return res;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T>T[] toArray(T[] a) {
        int size = this.size();
        T[] res = a;
        if (a.length < size) {
            res = (T[])Array.newInstance(a.getClass(), this.size());
        }
        int index = 0;
        for (E element : this) {
            try {
                res[index++] = (T)element;
            } catch (ClassCastException e) {
                throw new ArrayStoreException(
                    "Element " + element + " cannot be cast to type of specified array!"
                );
            }
        }
        if (res.length > index) {
            res[index] = null;
        }
        return res;
    }

}
