import java.util.ArrayList;

public class Board
{
    private final int[][] board;
    private ArrayList<Board> neighborsList;
    private int zeroI;
    private int zeroJ;
    private final int hamming;
    private final int manhattan;
    private final int n;

    public Board(int[][] blocks)
    {
        n = blocks.length;
        int[][] newBoardArray = new int[n][n];
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                newBoardArray[i][j] = blocks[i][j];
                if (blocks[i][j] == 0)
                {
                    zeroI = i;
                    zeroJ = j;
                }
            }
        }
        this.board = newBoardArray;
        this.hamming = culculateHamming();
        this.manhattan = culculateManhattan();
    }

    public int dimension()                 // board dimension n
    {
        return this.n;
    }

    private int culculateHamming()
    {
        int count = 0;
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

    public int hamming()                   // number of blocks out of place
    {
        return this.hamming;
    }

    private int culculateManhattan()                 // sum of Manhattan distances between blocks and goal
    {
        int res = 0;
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

    public int abs(int a)
    {
        return (a < 0) ? -a : a;
    }

    public int manhattan()                 // sum of Manhattan distances between blocks and goal
    {

        return this.manhattan;
    }

    public boolean isGoal()                // is this board the goal board?
    {
        return hamming == 0;
    }

    public Board twin()                    // a board that is obtained by exchanging any pair of blocks
    {
        int[][] newBoardArray = new int[n][n];
        for (int i = 0; i < n; i++)
        {
            System.arraycopy(this.board[i], 0, newBoardArray[i], 0, n);
        }

        if (newBoardArray[0][0] != 0 && newBoardArray[0][1] != 0)
        {
            int tmp = newBoardArray[0][0];
            newBoardArray[0][0] = newBoardArray[0][1];
            newBoardArray[0][1] = tmp;
        }
        else
        {
            int tmp = newBoardArray[n - 1][n - 1];
            newBoardArray[n - 1][n - 1] = newBoardArray[n - 1][n - 2];
            newBoardArray[n - 1][n - 2] = tmp;
        }
        return new Board(newBoardArray);
    }

    public boolean equals(Object y)        // does this board equal y?
    {
        if (y.getClass() != Board.class) return false;
        Board yBoard = (Board) y;
        if ((this.zeroI != yBoard.zeroI) || (this.zeroJ != yBoard.zeroJ)) return false;

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
            if (zeroI > 0) neighborsList.add(swap(zeroI - 1, zeroJ, zeroI, zeroJ));
            if (zeroI < n - 1) neighborsList.add(swap(zeroI + 1, zeroJ, zeroI, zeroJ));
            if (zeroJ > 0) neighborsList.add(swap(zeroI, zeroJ - 1, zeroI, zeroJ));
            if (zeroJ < n - 1) neighborsList.add(swap(zeroI, zeroJ + 1, zeroI, zeroJ));
        }
        return neighborsList;
    }

    private Board swap(int newi, int newj, int oldi, int oldj)
    {
        int[][] newBoardArray = new int[n][n];
        for (int i = 0; i < n; i++)
        {
            newBoardArray[i] = this.board[i].clone();
        }
        newBoardArray[oldi][oldj] = newBoardArray[newi][newj];
        newBoardArray[newi][newj] = 0;
        return new Board(newBoardArray);
    }

    public String toString()               // string representation of this board (in the output format specified below)
    {
        StringBuilder sb = new StringBuilder();
        sb.append(n).append("\n");
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                sb.append(String.format("%2d ", board[i][j]));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) // unit tests (not graded)
    {
    }
}
