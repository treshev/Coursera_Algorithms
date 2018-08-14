import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private static final double CONFIDENCE_95 = 1.96;
    private final double globalTrials;
    private final double[] pointCount;
    private double meanValue = -1;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new java.lang.IllegalArgumentException();
        }

        pointCount = new double[trials];
        this.globalTrials = trials;

        for (int i = 0; i < trials; i++) {
            double currentCount = 0;
            Percolation per = new Percolation(n);
            while (!per.percolates()) {
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);
                if (!per.isOpen(row, col)) {
                    per.open(row, col);
                    currentCount++;
                }
            }
            pointCount[i] = currentCount / (n * n);
        }
    }

    private double getMeanValue() {
        if (meanValue == -1) {
            meanValue = StdStats.mean(pointCount);
        }
        return meanValue;
    }

    public double mean() {
        return getMeanValue();
    }

    public double stddev() {
        return StdStats.stddev(pointCount);
    }

    public double confidenceLo() {
        return getMeanValue() - (CONFIDENCE_95 * stddev() / Math.sqrt(globalTrials));
    }

    public double confidenceHi() {
        return getMeanValue() + (CONFIDENCE_95 * stddev() / Math.sqrt(globalTrials));
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int iter = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(n, iter);
        StdOut.println("mean                     = " + ps.mean());
        StdOut.println("stddev                   = " + ps.stddev());
        StdOut.println("95 % confidence interval = [" + ps.confidenceLo() + ", " + ps.confidenceHi() + "]");
    }
}
