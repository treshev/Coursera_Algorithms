import java.util.ArrayList;
import java.util.Iterator;

import static java.lang.Math.abs;

public class Board
{
    private final int[][] board;
    private ArrayList<Board> neighborsList;

    public Board(int[][] blocks)           // construct a board from an n-by-n array of blocks
    // (where blocks[i][j] = block in row i, column j)
    {
        int n = blocks.length;
        int[][] newBoardArray = new int[n][n];
        for (int i = 0; i < n; i++)
        {
            newBoardArray[i] = blocks[i].clone();
        }
        this.board = newBoardArray;
    }

    public int dimension()                 // board dimension n
    {
        return this.board.length;
    }

    public int hamming()                   // number of blocks out of place
    {
        int count = 0;
        int n = dimension();
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (board[i][j] != 0 && board[i][j] != i * n + (j + 1))
                {
                    count++;
                }
            }
        }
        return count;
    }

    public int manhattan()                 // sum of Manhattan distances between blocks and goal
    {
        int res = 0;
        int n = dimension();
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (board[i][j] != 0 && board[i][j] != i * n + (j + 1))
                {
                    int x = board[i][j];
                    int si = (x - 1) / n;
                    int sj = abs(x - si * n - 1);
                    res += abs(i - si) + abs(j - sj);
                }
            }
        }
        return res;
    }

    public boolean isGoal()                // is this board the goal board?
    {
        return hamming() == 0;
    }

    public Board twin()                    // a board that is obtained by exchanging any pair of blocks
    {
        return null;
    }

    public boolean equals(Object y)        // does this board equal y?
    {
        if (!(y instanceof Board)) return false;
        Board yBoard = (Board) y;
        int n = dimension();
        if (yBoard.dimension() != n) return false;

        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                if (board[i][j] != yBoard.board[i][j])
                {
                    return false;
                }
            }
        }
        return true;
    }

    public Iterable<Board> neighbors()     // all neighboring boards
    {
        if (neighborsList == null)
        {
            neighborsList = new ArrayList<>();
            int n = dimension();
            int iZero = -1;
            int jZero = -1;
            for (int i = 0; i < n; i++)
            {
                for (int j = 0; j < n; j++)
                {
                    if (board[i][j] == 0)
                    {
                        iZero = i;
                        jZero = j;
                        break;
                    }
                }
            }
            if (iZero > 0) neighborsList.add(swap(iZero - 1, jZero, iZero, jZero));
            if (iZero < n - 1) neighborsList.add(swap(iZero + 1, jZero, iZero, jZero));
            if (jZero > 0) neighborsList.add(swap(iZero, jZero - 1, iZero, jZero));
            if (jZero < n - 1) neighborsList.add(swap(iZero, jZero+1, iZero, jZero));
        }
        return neighborsList;
    }

    private Board swap(int newi, int newj, int oldi, int oldj)
    {
        int n = dimension();
        if (!(newi == oldi && newj == oldj) && newi >= 0 && newi < n && newj >= 0 && newj < n)
        {
            int[][] newBoardArray = new int[n][n];
            for (int i = 0; i < n; i++)
            {
                newBoardArray[i] = this.board[i].clone();
            }
            Board newBoard = new Board(newBoardArray);
            newBoard.board[oldi][oldj] = newBoard.board[newi][newj];
            newBoard.board[newi][newj] = 0;
            return newBoard;
        }
        return null;
    }

    public String toString()               // string representation of this board (in the output format specified below)
    {
        StringBuilder sb = new StringBuilder();
        int n = dimension();
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                sb.append(board[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) // unit tests (not graded)
    {
        int n = 2;
        int[][] arr = new int[n][n];
        int item = 9;
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                arr[i][j] = item--;
            }
        }
        arr[0][0]=1;
        arr[0][1]=0;
        arr[1][0]=3;
        arr[1][1]=2;
//        arr[n - 1][n - 1] = 0;
        Board b = new Board(arr);
        System.out.println(b);
        System.out.println(b.dimension());
        System.out.println(b.hamming());
        System.out.println(b.manhattan());
        System.out.println(b.isGoal());
        System.out.println(b.equals(new Board(arr)));

        for (Board neighbor : b.neighbors())
        {
            System.out.println(neighbor);
        }
    }
}
