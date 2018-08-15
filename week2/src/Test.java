import java.text.NumberFormat;
import java.util.Iterator;

public class Test {
    public static void main(String[] args) {
        testQueue();
//        testRandomizedQueue();
    }

    private static void testRandomizedQueue() {
        RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();

        System.out.println("1. Size = " + queue.size());
        System.out.println("Is empty = " + queue.isEmpty());

        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
        }

        for (Integer integer : queue) {
            System.out.println(integer.toString());
        }

        System.out.println("##############");
        System.out.println("##############");
        for (Integer integer : queue) {
            System.out.println(integer.toString());
        }

//        System.out.println("2. Size = " + queue.size());
//        System.out.println("Is empty = " + queue.isEmpty());
//
//        for (int i = 0; i < 50; i++) {
//            queue.dequeue();
//        }
//
//        for (Integer integer : queue) {
//            System.out.println(integer.toString());
//        }
//
//        System.out.println("3. Size = " + queue.size());
//        System.out.println("Is empty = " + queue.isEmpty());
//
//        for (int i = 0; i < 50; i++) {
//            queue.enqueue(i);
//        }
//
//
//
//        System.out.println("4. Size = " + queue.size());
//        System.out.println("Is empty = " + queue.isEmpty());
//
//        for (int i = 0; i < 150; i++) {
//            queue.dequeue();
//        }
//
//        System.out.println("5. Size = " + queue.size());
//        System.out.println("Is empty = " + queue.isEmpty());
//
//        for (Integer integer : queue) {
//            System.out.println(integer.toString());
//        }
//
////        System.out.println(queue.sample());
////        System.out.println(queue.sample());
////        System.out.println(queue.sample());
//
//        Iterator<Integer> iterator = queue.iterator();
//        while (iterator.hasNext()) {
//            Integer node = iterator.next();
//            System.out.printf("%s", node);
//        }
    }

    private static void testQueue() {
        System.out.println("From the head");

        showMemeory();
        Deque<Integer> queue = new Deque<Integer>();
        for (int i = 200000; i > 0; i--) {
            queue.addLast(i);
        }
        showMemeory();

        for (int i = 200000; i > 0; i--) {
            queue.removeLast();
        }

        showMemeory();

//        for (Integer integer : queue) {
//            System.out.println(integer.toString());
//        }
//
//        queue.removeFirst();
//        System.out.println("################################");
//        for (Integer integer : queue) {
//            System.out.println(integer.toString());
//        }
    }

    private static void showMemeory() {
        Runtime runtime = Runtime.getRuntime();
        NumberFormat format = NumberFormat.getInstance();

        StringBuilder sb = new StringBuilder();
        long maxMemory = runtime.maxMemory();
        long allocatedMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();

        sb.append("free memory: " + format.format(freeMemory) + "\n");
//        sb.append("allocated memory: " + format.format(allocatedMemory) + "\n");
//        sb.append("max memory: " + format.format(maxMemory ) + "\n");
        sb.append("total free memory: " + format.format((freeMemory + (maxMemory - allocatedMemory)) / 1024) + "\n");
        System.out.println();
        System.out.println("################################");
        System.out.println(sb);
    }
}
