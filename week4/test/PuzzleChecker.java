/******************************************************************************
 *  Compilation:  javac-algs4 PuzzleChecker.java
 *  Execution:    java-algs4 PuzzleChecker filename1.txt filename2.txt ...
 *  Dependencies: Board.java Solver.java
 *
 *  This program creates an initial board from each filename specified
 *  on the command line and finds the minimum number of moves to
 *  reach the goal state.
 *
 *  % java-algs4 PuzzleChecker puzzle*.txt
 *  puzzle00.txt: 0
 *  puzzle01.txt: 1
 *  puzzle02.txt: 2
 *  puzzle03.txt: 3
 *  puzzle04.txt: 4
 *  puzzle05.txt: 5
 *  puzzle06.txt: 6
 *  ...
 *  puzzle3x3-impossible: -1
 *  ...
 *  puzzle42.txt: 42
 *  puzzle43.txt: 43
 *  puzzle44.txt: 44
 *  puzzle45.txt: 45
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PuzzleChecker
{


    public static List<String> listFilesForFolder(final File folder)
    {
        ArrayList<String> filesList = new ArrayList<>();
        for (final File fileEntry : folder.listFiles())
        {
            if (fileEntry.isDirectory())
            {
                listFilesForFolder(fileEntry);
            }
            else if (fileEntry.isFile() && fileEntry.getName().contains("puzzle") && fileEntry.getName().endsWith(".txt"))
            {
                filesList.add(fileEntry.getName());
            }
        }
        return filesList;
    }

    public static void main(String[] args)
    {
        System.out.println("Working Directory = " +
                System.getProperty("user.dir"));

        long globalStart = new Date().getTime();
        for (int iter = 0; iter < 1; iter++)
        {
            if (args.length == 0)
            {
                for (String filename : listFilesForFolder(new File("test/done")))
                {
                    // read in the board specified in the filename
                    In in = new In(filename);
                    int n = in.readInt();
                    int[][] tiles = new int[n][n];
                    for (int i = 0; i < n; i++)
                    {
                        for (int j = 0; j < n; j++)
                        {
                            tiles[i][j] = in.readInt();
                        }
                    }
                    long start = new Date().getTime();
                    // solve the slider puzzle
                    System.out.print(filename);
                    Board initial = new Board(tiles);
                    Solver solver = new Solver(initial);
                    StdOut.println(": " + solver.moves() + " : " + (new Date().getTime() - start));
                }
            }
            else
            {
                // for each command-line argument
                for (String filename : args)
                {

                    // read in the board specified in the filename
                    In in = new In(filename);
                    int n = in.readInt();
                    int[][] tiles = new int[n][n];
                    for (int i = 0; i < n; i++)
                    {
                        for (int j = 0; j < n; j++)
                        {
                            tiles[i][j] = in.readInt();
                        }
                    }

                    Board initial = new Board(tiles);
                    Solver solver = new Solver(initial);
                    StdOut.println(filename + ": " + solver.moves());

                }
            }
            long current = new Date().getTime();
            System.out.println("\nGLOBAL TIME = " + (current - globalStart) / 1000 + "sec. (" + (current - globalStart) + ")");
        }
        long current = new Date().getTime();
        System.out.println("\nGLOBAL TIME = " + (current - globalStart) / 1000 + "sec. (" + (current - globalStart) + ")");
    }
}