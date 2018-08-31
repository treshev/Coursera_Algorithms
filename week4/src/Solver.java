import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.Collections;

public class Solver
{
    private final MyBoard finalNode;
    private boolean isSolve = true;

    private static class MyBoard implements Comparable<MyBoard>
    {
        private final Board board;
        private final MyBoard predecessorItem;
        private final int iter;


        public MyBoard(Board current, MyBoard predecessor, int iteration)
        {
            board = current;
            predecessorItem = predecessor;
            iter = iteration;
        }

        @Override
        public int compareTo(MyBoard object)
        {
            if (this == object) return 0;

            if (this.board.manhattan() + this.iter == object.board.manhattan() + object.iter)
            {
                return Integer.compare(this.board.manhattan(), object.board.manhattan());
            }
            return Integer.compare(this.board.manhattan() + this.iter,
                    object.board.manhattan() + object.iter);
        }
    }

    public Solver(Board initial)           // find a solution to the initial board (using the A* algorithm)
    {
        if (initial == null || initial.dimension() < 1 || initial.dimension() > 128)
            throw new IllegalArgumentException();

        MinPQ<MyBoard> pq = new MinPQ<>();
        MyBoard initialBoard = new MyBoard(initial, null, 0);
        pq.insert(initialBoard);

        MinPQ<MyBoard> pqTwin = new MinPQ<>();
        pqTwin.insert(new MyBoard(initial.twin(), null, 0));

        MyBoard current = pq.delMin();
        MyBoard twin = pqTwin.delMin();

        while (!current.board.isGoal() && !twin.board.isGoal())
        {
            setNeighbors(pq, current);
            current = pq.delMin();

            setNeighbors(pqTwin, twin);
            twin = pqTwin.delMin();
        }

        if (current.board.isGoal())
        {
            finalNode = current;
        }
        else
        {
            finalNode = initialBoard;
            isSolve = false;
        }
    }

    private void setNeighbors(MinPQ<MyBoard> pq, MyBoard current)
    {
        Iterable<Board> neig = current.board.neighbors();
        for (Board board : neig)
        {
            if (current.predecessorItem == null || !board.equals(current.predecessorItem.board))
            {
                pq.insert(new MyBoard(board, current, current.iter + 1));
            }
        }
    }

    public boolean isSolvable()            // is the initial board solvable?
    {
        return isSolve;
    }

    public int moves()                     // min number of moves to solve initial board; -1 if unsolvable
    {
        return finalNode.iter;
    }

    public Iterable<Board> solution()      // sequence of boards in a shortest solution; null if unsolvable
    {
        ArrayList<Board> finalList = new ArrayList<>();
        MyBoard current = finalNode;
        if (isSolvable())
        {
            while (current.predecessorItem != null)
            {
                finalList.add(current.board);
                current = current.predecessorItem;
            }
            finalList.add(current.board);
            Collections.reverse(finalList);
        }
        return finalList;
    }

    public static void main(String[] args) // solve a slider puzzle (given below)
    {
        System.out.println("Empty");
    }
}
