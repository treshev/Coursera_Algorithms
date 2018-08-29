import edu.princeton.cs.algs4.MinPQ;

public class Solver
{
    private int moves;
    private MinPQ<Board> pq;

    public Solver(Board initial)           // find a solution to the initial board (using the A* algorithm)
    {
        if (initial == null || initial.dimension() < 1 || initial.dimension() > 128)
            throw new IllegalArgumentException();

        pq = new MinPQ<>();
        pq.insert(initial);
        Board current = pq.delMin();
        moves++;
        while (current.isGoal())
        {
            for (Board board : current.neighbors())
            {
                pq.insert(board);
            }
            current = pq.delMin();
            moves++;
        }

    }

    public boolean isSolvable()            // is the initial board solvable?
    {
        return false;
    }

    public int moves()                     // min number of moves to solve initial board; -1 if unsolvable
    {
        return moves;
    }

    public Iterable<Board> solution()      // sequence of boards in a shortest solution; null if unsolvable
    {
        if (isSolvable())
        {
            return null;
        }
        return null;
    }

    public static void main(String[] args) // solve a slider puzzle (given below)
    {

    }
}
