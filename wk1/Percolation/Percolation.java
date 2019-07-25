import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation
{
	private int[][] grid;
	private WeightedQuickUnionUF uf;
	private int size;
	
	public Percolation(int n)
	{
		uf = new WeightedQuickUnionUF(n*n);
		grid = new int [n][n];
		size = n;
		int counter = 0;
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < n; j++)
			{
				grid[i][j] = counter;
				counter++;
			}
		}
	}

	public void open(int row, int col)
	{
		row--;		// convert from 1,1 notation
		col--;		// to 0 based
		if ((row > size || row < 0) || (col > size || col < 0))
		{
			throw new IllegalArgumentException("index is not between 1 and " + size);
		}
		int dn_row = 1;
		int up_row = 1;
		int rt_col = 1;
		int lt_col = 1;
		if (row == 0) up_row=0;
		if (row == size-1) dn_row=0;
		if (col == 0) lt_col=0;
		if (col == size-1) rt_col=0;
			
		uf.union(grid[row + dn_row][col], grid[row][col]);
		uf.union(grid[row - up_row][col], grid[row][col]);
		uf.union(grid[row][col + rt_col], grid[row][col]);
		uf.union(grid[row][col - lt_col], grid[row][col]);
	}
	public static void main(String[] args)
	{
		Percolation test = new Percolation(5);
		test.open(1,5);
	
	}
}
