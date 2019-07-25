import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation
{
	private boolean[][] opens;
	private int[][] grid;
	private WeightedQuickUnionUF uf;
	private int size;
	
	public Percolation(int n)
	{
		uf = new WeightedQuickUnionUF(n*n);
		opens = new boolean [n][n];
		grid = new int [n][n];
		size = n;
		int counter = 0;
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < n; j++)
			{
				opens[i][j] = false;
				grid[i][j] = counter++;
				if (i == 0)	uf.union(grid[i][j], 0);
			}
		}
	}
	
	private void valid(int row, int col)
	{
		// note private; will already receive a zero indexed
		// row and col
		//if ((row == topper || row == footer) || col == topper || col == footer) return;
		if ((row > size || row < 0) || (col > size || col < 0))
		{
			throw new IllegalArgumentException("index is not between 1 and " + size);
		}

	}
	
	public void open(int row, int col)
	{
		valid(--row, --col);	//double duty | validate and decrement
		opens[row][col] = true;
		int dn_row = 1;
		int up_row = 1;
		int rt_col = 1;
		int lt_col = 1;
		if (row == 0) up_row=0;
		if (row == size-1) dn_row=0;
		if (col == 0) lt_col=0;
		if (col == size-1) rt_col=0;
			
		uf.union(grid[row + dn_row][col], grid[row][col]);
		opens[row + dn_row][col] = true;
		uf.union(grid[row - up_row][col], grid[row][col]);
		opens[row - up_row][col] = true;
		uf.union(grid[row][col + rt_col], grid[row][col]);
		opens[row][col + rt_col] = true;
		uf.union(grid[row][col - lt_col], grid[row][col]);
		opens[row][col - lt_col] = true;
	}

	public boolean isOpen(int row, int col)
	{
		valid(--row, --col);
		return opens[row][col];
	}

	public boolean isFull(int row, int col)
	{
		valid(--row, --col);
		return uf.connected(0, grid[row][col]);
	}

	public static void main(String[] args)
	{
		Percolation test = new Percolation(5);
		test.open(1,5);
		System.out.println(test.isFull(1,5));
		System.out.println(test.isOpen(3,5));
		test.open(3,5);
		System.out.println(test.isOpen(3,5));
	}
}
