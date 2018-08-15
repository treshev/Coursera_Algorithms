import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        System.out.println(k);

        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();

        while (!StdIn.isEmpty()) {
            String str = StdIn.readString();
            randomizedQueue.enqueue(str);
        }

        if (k > randomizedQueue.size()) {
            k = randomizedQueue.size();
        }

        for (int i = 0; i < k; i++) {
            System.out.println(randomizedQueue.dequeue());
        }

    }

}