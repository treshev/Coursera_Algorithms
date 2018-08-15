import java.util.Iterator;


public class Deque<Item> implements Iterable<Item> {

    private int size = 0;
    private Node first = null;
    private Node last = null;

    private class Node {
        Item item;
        Node previous = null;
        Node next = null;
    }

    private class MyIterator implements Iterator<Item> {

        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new java.util.NoSuchElementException();

            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
    }


    public boolean isEmpty()                 // is the deque empty?
    {
        return first == null;
    }

    public int size()                        // return the number of items on the deque
    {
        return size;
    }

    public void addFirst(Item newItem)          // add the Node to the front
    {
        if (newItem == null) throw new java.lang.IllegalArgumentException();

        Node newNode = new Node();
        newNode.item = newItem;
        newNode.next = first;

        if (first != null) {
            first.previous = newNode;
        }

        first = newNode;

        if (size == 0) {
            last = newNode;
        }
        size++;
    }

    public void addLast(Item item)           // add the Node to the end
    {
        if (item == null) throw new java.lang.IllegalArgumentException();

        Node newLastNode = new Node();
        newLastNode.item = item;

        if (last != null) {
            last.next = newLastNode;
            newLastNode.previous = last;

        } else {
            first = newLastNode;
        }
        last = newLastNode;
        size++;
    }

    public Item removeFirst() {
        if (isEmpty()) throw new java.util.NoSuchElementException();

        Node current = first;
        first = first.next;
        size--;
        if (size == 0) {
            last = null;
        }

        Item item = current.item;
        current = null;
        return item;
    }

    public Item removeLast() {
        if (isEmpty()) throw new java.util.NoSuchElementException();

        Node current = last;
        if (last.previous != null) {
            last.previous.next = null;
        }
        last = last.previous;
        size--;
        if (size == 0) {
            first = null;
            last = null;
        }
        Item item = current.item;
        current = null;
        return item;
    }

    public Iterator<Item> iterator() {
        return new MyIterator();
    }
}