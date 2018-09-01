import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Date;

public class Client
{
    public static void main(String[] args)
    {
        System.out.println("Working Directory = " +
                System.getProperty("user.dir"));

        // create initial board from file
//        In in = new In(args[0]);
        String fileName = "week4/test/notDone/puzzle4x4-45.txt";
        In in = new In(fileName);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        System.out.println(fileName);
        System.out.println(initial);
        System.out.println();

        // solve the puzzle
        Date start = new Date();

        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else
        {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution()) {
                StdOut.println(board);
            }
        }
        System.out.println("Spend = " + (new Date().getTime()-start.getTime())/1000);
    }
}
