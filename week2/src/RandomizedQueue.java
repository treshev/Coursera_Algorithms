import java.util.Iterator;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue implements Iterable {

    class MyIterator implements Iterator<Item> {

        private Item current = dataSet[0];

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
            throw new UnsupportedOperationException();
        }
    }

    private Item[] dataSet = new Item[1];
    private int size = 0;

    public boolean isEmpty()                 // is the deque empty?
    {
        return size == 0;
    }

    public int size()                        // return the number of items on the deque
    {
        return size;
    }

    private void resize(int newSize) {
        Item[] newDataSet = new Item[newSize];
        for (int i = 0; i < newSize && i < dataSet.length; i++) {
            newDataSet[i] = dataSet[i];
        }
//        System.arraycopy(dataSet, 0, newDataSet, 0, dataSet.length);
        dataSet = newDataSet;
    }

    public void enqueue(Item item) {
        if (item == null) throw new java.lang.IllegalArgumentException();
        if (size == dataSet.length) {
            resize(2 * dataSet.length);
        }
        dataSet[size++] = item;
    }

    public Item dequeue()                    // remove and return a random item
    {
        int index = StdRandom.uniform(size);
        Item delItem = dataSet[index];
        dataSet[index] = dataSet[--size];
        dataSet[size] = null;
        if (size > 2 && size == dataSet.length / 4) {
            resize(dataSet.length / 2);
        }
        return delItem;

    }

    public Item sample()                     // return a random item (but do not remove it)
    {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        int index = StdRandom.uniform(size);
        return dataSet[index];
    }

    public Iterator<Item> iterator() {
        return new MyIterator();
    }


    public static void main(String[] args) {
        RandomizedQueue queue = new RandomizedQueue();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(new Item(i));
        }

        System.out.println(queue.sample().item);
        System.out.println(queue.sample().item);
        System.out.println(queue.sample().item);

        System.out.println("Queue size before = " + queue.size());
        for (int i = 0; i < 10; i++) {
            queue.dequeue();
        }
        System.out.println("Queue size after = " + queue.size());

        Iterator<Item> iterator = queue.iterator();
        while (iterator.hasNext()) {
            Item item = iterator.next();
            System.out.printf("%s  prev = %s      next = %s\n", item.item, (item.previous != null) ? item.previous.item : "Null",
                    (item.next != null) ? item.next.item : "Null");
        }

    }
}