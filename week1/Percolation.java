import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final int globalN;
    private int oCount = 0;
    private boolean[][] mass;
    private final WeightedQuickUnionUF uf;

    public Percolation(int n) {
        if (n <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        globalN = n;
        mass = new boolean[n + 1][n + 1];
        uf = new WeightedQuickUnionUF(n * n + 2);
    }

    private int getIndex(int row, int col) {
        return globalN * (row - 1) + col;
    }

    public void open(int row, int col) {
        if (row < 1 || col < 1) {
            throw new java.lang.IllegalArgumentException();
        }

        mass[row][col] = true;
        oCount++;
        int cur = getIndex(row, col);
        if (row > 1 && isOpen(row - 1, col)) {
            uf.union(cur, getIndex(row - 1, col));
        } else if (row < globalN && isOpen(row + 1, col)) {
            uf.union(cur, getIndex(row + 1, col));
        } else if (row == 1) {
            uf.union(0, getIndex(row, col));
        }

        if (row == globalN) {
            uf.union(getIndex(row, col), globalN);
        }
        if (col > 1 && isOpen(row, col - 1)) {
            uf.union(cur, getIndex(row, col - 1));
        } else if (col < globalN && isOpen(row, col + 1)) {
            uf.union(cur, getIndex(row, col + 1));
        }
    }

    public boolean isOpen(int row, int col) {
        if (row < 1 || col < 1) throw new java.lang.IllegalArgumentException();
        return mass[row][col];
    }

    public boolean isFull(int row, int col) {
        if (row < 1 || col < 1) throw new java.lang.IllegalArgumentException();
        return isOpen(row, col) && uf.connected(0, getIndex(row, col));
    }

    public int numberOfOpenSites() {
        return oCount;
    }

    public boolean percolates() {
        return uf.connected(0, globalN * globalN + 1);
    }
}