import java.util.Iterator;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] dataSet = (Item[]) new Object[2];
    private int tail = 0;

    private class MyIterator implements Iterator<Item> {
        private int i = tail;
        final private int[] order = StdRandom.permutation(tail);

        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new java.util.NoSuchElementException();
            int index = order[--i];
            return dataSet[index];
        }
    }

    public boolean isEmpty()                 // is the deque empty?
    {
        return tail == 0;
    }

    public int size()                        // return the number of items on the deque
    {
        return tail;
    }

    private void resize(int newSize) {
        Item[] newDataSet = (Item[]) new Object[newSize];
        int i = 0;
        int j = 0;
        while (i < newSize && j < dataSet.length) {
            if (dataSet[j] != null) {
                newDataSet[i] = dataSet[j];
                i++;
            }
            j++;
        }
        dataSet = newDataSet;
    }


    public void enqueue(Item item) {
        if (item == null) throw new java.lang.IllegalArgumentException();

        if (tail == dataSet.length) {
            resize(2 * dataSet.length);
        }
        dataSet[tail++] = item;
    }

    public Item dequeue()                    // remove and return a random item
    {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        int index = StdRandom.uniform(tail);
        Item delNode = dataSet[index];
        dataSet[index] = dataSet[--tail];
        dataSet[tail] = null;
        if (tail > 2 && tail == dataSet.length / 4) {
            resize(dataSet.length / 2);
        }
        return delNode;

    }

    public Item sample()                     // return a random item (but do not remove it)
    {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        int index = StdRandom.uniform(tail);
        return dataSet[index];
    }

    public Iterator<Item> iterator() {
        return new MyIterator();
    }

}