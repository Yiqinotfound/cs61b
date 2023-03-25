package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private int[] x;
    private int t;
    private boolean validate(int N, int T) {
        if (N <= 0 || T <= 0) {
            return false;
        } else
            return true;
    }

    public PercolationStats(int N, int T, PercolationFactory pf) {
        t = T;
        if (validate(N, T)) {
            throw new java.lang.IllegalArgumentException();
        }
        x = new int[N];
        for (int i = 0; i < T; i++) {
            Percolation p = pf.make(N);
            while (!p.percolates()) {
                int randomRow;
                int randomCol;
                while (true) {
                     randomRow = StdRandom.uniform(N);
                     randomCol = StdRandom.uniform(N);
                    if (!p.isOpen(randomRow, randomCol)) {
                        break;
                    }
                }
                p.open(randomRow, randomCol);
            }
            x[i] = p.numberOfOpenSites();
        }

    }

    public double mean() {
        return StdStats.mean(x);
    }

    public double stddev() {
        double sum = 0;
        double mu = mean();
        for (int i = 0; i < t; i ++) {
            sum += (x[i] - mu)*(x[i] - mu);
        }
        return sum / (t - 1);
    }

    public double confidenceLow() {
        return mean() - (1.96*stddev())/Math.sqrt(t);

    }

    public double confidenceHigh() {
        return mean() + (1.96*stddev())/Math.sqrt(t);

    }

}
