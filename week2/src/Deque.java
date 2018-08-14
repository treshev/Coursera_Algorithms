import java.util.Iterator;
import java.util.LinkedList;

class Item {
    Object item;
    Item previous = null;
    Item next = null;

    Item(Object value) {
        item = value;
    }
}

public class Deque implements Iterable {


    class MyIterator implements Iterator<Item> {

        private Item current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new java.util.NoSuchElementException();
            Item item = current;
            current = item.next;
            return item;
        }

        @Override
        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
    }

    private LinkedList<Item> array = new LinkedList<>();
    private Item first = null;
    private Item last = null;

    public boolean isEmpty()                 // is the deque empty?
    {
        return first == null;
    }

    public int size()                        // return the number of items on the deque
    {
        return array.size();
    }

    public void addFirst(Item newItem)          // add the Item to the front
    {
        if (newItem == null) throw new java.lang.IllegalArgumentException();
        if (array.size() > 0) {
            Item current_first = array.get(0);
            current_first.previous = newItem;
            newItem.next = current_first;
        }
        array.add(0, newItem);
        first = newItem;
    }

    public void addLast(Item newLastItem)           // add the Item to the end
    {
        if (newLastItem == null) throw new java.lang.IllegalArgumentException();
        if (array.size() > 0) {
            Item current_last = array.get(array.size() - 1);
            current_last.next = newLastItem;
            newLastItem.previous = current_last;
        } else {
            first = newLastItem;
        }
        array.add(newLastItem);
        last = newLastItem;
    }

    public Item removeFirst() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        Item current = first;
        array.remove(0);
        if (array.size() == 0) {
            first = null;
            last = null;
        } else
            first = array.get(0);
        return current;
    }

    public Item removeLast() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        Item current = last;
        if (last.previous != null) {
            last.previous.next = null;
        }
        array.remove(array.size() - 1);
        if (!isEmpty() && array.size() > 0)
            last = array.get(array.size() - 1);
        else {
            first = null;
            last = null;
        }
        return current;
    }

    public Iterator<Item> iterator() {
        return new MyIterator();
    }

    public static void main(String[] args) {
        //From the head
        System.out.println("From the head");
        Deque queue = new Deque();
        for (int i = 0; i < 10; i++) {
            queue.addFirst(new Item(i));
        }
        System.out.println("Queue size before = " + queue.size());
        for (int i = 0; i < 10; i++) {
            queue.removeFirst();
        }

        //From the tail
        System.out.println("From the tail");
        queue = new Deque();
        for (int i = 0; i < 10; i++) {
            queue.addLast(new Item(i));
        }

        System.out.println("Queue size before removing = " + queue.size());
        for (int i = 0; i < 10; i++) {
            queue.removeLast();
        }

        //From both side
        queue = new Deque();
        for (int i = 0; i < 10; i++) {
            queue.addFirst(new Item(i));
            queue.addLast(new Item(i));
        }

        System.out.println("Queue size before = " + queue.size());
        for (int i = 0; i < 10; i++) {
            queue.removeLast();
            queue.removeFirst();
        }

        System.out.println("Queue size after = " + queue.size());

        for (Item item : (Iterable<Item>) queue) {
            System.out.printf("%s  prev = %s      next = %s\n", item.item, (item.previous != null) ? item.previous.item : "Null",
                    (item.next != null) ? item.next.item : "Null");
        }

    }
}