package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    // Length of the square grid "gridLength * gridLength"
    private final int gridLength;
    // Array representing indexes of all sites (either it's open or blocked)
    private  boolean[] sites;
    // Number of open sites
    private int openSites;
    // Weighted quick union find data structure
    private  WeightedQuickUnionUF uf;

    // Create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N <= 0)
            throw new IllegalArgumentException("N must be positive");
        gridLength = N;
        sites = new boolean[N * N];
        openSites = 0;
        uf = new WeightedQuickUnionUF(N * N + 2); // +2 for virtual top and bottom nodes
        for (int i = 0; i < N * N ; i++) {
            sites[i] = false; // All sites are initially blocked
        }
    }

    private int xyTo1D(int row, int col) {
        return row * gridLength + col;
    }

    private void validate(int row, int col) {
        if (row < 0 || col < 0 || row > gridLength - 1 || col > gridLength - 1) {
            throw new IndexOutOfBoundsException("Index is out of range");
        }
    }

    // Open the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col); // Check if row and col are valid indices
        if (isOpen(row, col))
            return; // If site is already open, do nothing
        else {
            openSites += 1; // Increment number of open sites
            sites[xyTo1D(row, col)] = true; // Mark site as open
            if (row == 0) {
                uf.union(0, xyTo1D(row, col));
            }
            if (row == gridLength - 1) {
                uf.union(gridLength * gridLength + 1, xyTo1D(row, col));
            }
            if (row > 0 && isOpen(row - 1, col)) {
                uf.union(xyTo1D(row, col), xyTo1D(row - 1, col));
            }
            if (row < gridLength - 1 && isOpen(row + 1, col)) {
                uf.union(xyTo1D(row, col), xyTo1D(row + 1, col));
            }
            if (col > 0 && isOpen(row, col - 1)) {
                uf.union(xyTo1D(row, col), xyTo1D(row, col - 1));
            }
            if (col < gridLength - 1 && isOpen(row, col + 1)) {
                uf.union(xyTo1D(row, col), xyTo1D(row, col + 1));
            }
        }

    }

    public boolean isOpen(int row, int col) {
        validate(row, col); // Check if row and col are valid indices
        return sites[xyTo1D(row, col)];
    }

    public boolean isFull(int row, int col) {
        validate(row, col);
        return uf.connected(0, xyTo1D(row, col));
    }

    public int numberOfOpenSites() {
        return openSites;
    }

    public boolean percolates() {
        return uf.connected(0, gridLength * gridLength + 1);
    }

    public static void main(String[] args) {

    }

    // Other methods omitted for brevity

}
