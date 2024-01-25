package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final int N;
    private final int top;
    private final int bottom;
    private int numOpenSites;
    private boolean[][] grid;
    private WeightedQuickUnionUF sites;

    /**
     * Transform from 2D to 1D.
     */
    private int transform(int row, int col) {
        return row * N + col;
    }

    /**
     * Union with open neighbors.
     */
    private void unionOpenNeighbor(int row1, int col1, int row2, int col2) {
        if (row2 < 0 || row2 >= N || col2 < 0 || col2 >= N) {
            return;
        }
        if (!grid[row2][col2]) {
            return;
        }

        sites.union(transform(row1, col1), transform(row2, col2));
    }

    /**
     * Create N-by-N grid, with all sites initially blocked.
     */
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }

        this.N = N;
        numOpenSites = 0;
        top = N * N;
        bottom = N * N + 1;
        grid = new boolean[N][N];
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                grid[i][j] = false;
            }
        }
        sites = new WeightedQuickUnionUF(N * N + 2);

        for (int j = 0; j < N; ++j) {
            sites.union(top, transform(0, j));
        }
        for (int j = 0; j < N; ++j) {
            sites.union(bottom, transform(N - 1, j));
        }
    }

    /**
     * Open the site (row, col) if it is not open already.
     */
    public void open(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N) {
            throw new IndexOutOfBoundsException();
        }

        if (grid[row][col]) {
            return;
        }
        grid[row][col] = true;
        numOpenSites++;
        unionOpenNeighbor(row, col, row - 1, col);
        unionOpenNeighbor(row, col, row + 1, col);
        unionOpenNeighbor(row, col, row, col - 1);
        unionOpenNeighbor(row, col, row, col + 1);
    }

    /**
     * Is the site (row, col) open?
     */
    public boolean isOpen(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N) {
            throw new IndexOutOfBoundsException();
        }
        return grid[row][col];
    }


    /**
     * Is the site (row, col) full?
     */
    public boolean isFull(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N) {
            throw new IndexOutOfBoundsException();
        }

        if (!grid[row][col]) {
            return false;
        }
        return sites.connected(top, transform(row, col));
    }

    /**
     * Number of open sites.
     */
    public int numberOfOpenSites() {
        return numOpenSites;
    }

    /**
     * Does the system percolate?
     */
    public boolean percolates() {
        return sites.connected(top, bottom);
    }

    /**
     * Use for unit testing (not required).
     * Lazy to import required Java libraries for unit testing,
     * so I just printed out......
     */
    public static void main(String[] args) {
        Percolation percolation = new Percolation(3);
        percolation.open(0, 0);
        percolation.open(1, 1);
        percolation.open(2, 1);
        if (!percolation.isFull(0, 0)) {
            System.out.println("isFull(0, 1) should return true, but returned false.");
        }
        if (percolation.percolates()) {
            System.out.println("percolates() should return false, but returned true.");
        }
        if (percolation.numberOfOpenSites() != 3) {
            System.out.println("numberOfOpenSites() should return 3, but returned "
                    + percolation.numberOfOpenSites() + "."
            );
        }

        percolation.open(1, 0);
        if (!percolation.percolates()) {
            System.out.println("percolates() should return true, but returned false.");
        }
    }
}
