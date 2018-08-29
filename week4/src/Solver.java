import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.Date;

public class Solver
{
    private int moves;
    private ArrayList<Board> output = new ArrayList<>();
    private MinPQ<MyBoard> pq;

    class MyBoard implements Comparable
    {
        private final Board b;
        private final int iter;


        public MyBoard(Board board, int iteration)
        {
            b = board;
            iter = iteration;
        }

        @Override
        public int compareTo(Object obj) {
            MyBoard object = (MyBoard)obj;
//            System.out.println("=========");
//            System.out.print(this.b);
//            System.out.println("This iter = " + this.iter);
//            System.out.println("This b.hamming() = " + this.b.hamming());
//            System.out.println("DIFF");
//            System.out.print(object.b);
//            System.out.println("This iter = " + object.iter);
//            System.out.println("This b.hamming() = " + object.b.hamming());
//            System.out.println();
//            System.out.println("RESULT = " + Integer.compare(this.b.hamming()+this.iter, object.b.hamming()+object.iter));
//            System.out.println("=========");
            return Integer.compare(this.b.hamming()+this.iter, object.b.hamming()+object.iter);
        }
    }

    public Solver(Board initial)           // find a solution to the initial board (using the A* algorithm)
    {
        if (initial == null || initial.dimension() < 1 || initial.dimension() > 128)
            throw new IllegalArgumentException();
        long dateIncert = 0;
        long dateGetMin = 0;
        long dateGoal = 0;
        long dateNeig = 0;


        moves = 0;
        pq = new MinPQ<>();
        pq.insert(new MyBoard(initial, 0));

        MyBoard current = pq.delMin();
        output.add(current.b);

        long startGoal = new Date().getTime();
        long gStart = new Date().getTime();
        while (!current.b.isGoal())
        {
            dateGoal+=new Date().getTime()-startGoal;
            long dateN = new Date().getTime();
            Iterable<Board> neig = current.b.neighbors();
            dateNeig += new Date().getTime() - dateN;
            for (Board board : neig)
            {
                long start = new Date().getTime();
                pq.insert(new MyBoard(board, current.iter+1));
                dateIncert += new Date().getTime()-start;
            }
            long startd = new Date().getTime();
            current = pq.delMin();
            dateGetMin += new Date().getTime()-startd;
//            System.out.println("MIN");
//            System.out.print(current.b);
//            System.out.println("hamming:" +current.b.hamming() + " iter:" + current.iter + " SUM: "+(current.b.hamming()+current.iter));
//            System.out.println("---");
//            output.add(current.b);
            startGoal = new Date().getTime();
        }
        moves = current.iter;
        System.out.println("Insert = " + dateIncert);
        System.out.println("Delete = " + dateGetMin);
        System.out.println("isGoal = " + dateGoal);
        System.out.println("Naigbors = " + dateNeig);
        System.out.println("While = " + (new Date().getTime()-gStart));


    }

    public boolean isSolvable()            // is the initial board solvable?
    {
        return true;
    }

    public int moves()                     // min number of moves to solve initial board; -1 if unsolvable
    {
        return moves;
    }

    public Iterable<Board> solution()      // sequence of boards in a shortest solution; null if unsolvable
    {
        if (isSolvable())
        {
            return output;
        }
        return null;
    }

    public static void main(String[] args) // solve a slider puzzle (given below)
    {

    }
}
