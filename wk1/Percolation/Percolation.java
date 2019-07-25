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
				//sets all the top row to a virtual topper root
				if (i == 0)	uf.union(0, counter);
				counter++;
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
		int currentId = indToId(row, col, true);
		if (row == 0) uf.union(0, currentId) // this connects to a virtual topper
		int otherId;
		int dn_row = 1;
		int up_row = 1;
		int rt_col = 1;
		int lt_col = 1;
		if (row == 0) up_row=0;
		if (row == size-1) dn_row=0;
		if (col == 0) lt_col=0;
		if (col == size-1) rt_col=0;
			
		if (isOpen((row + dn_row),col))
		{
			otherId = indToId((row + dn_row),col, true);
			uf.union(otherId, currentId);
		}
		if (isOpen((row - up_row),col))
		{
			otherId = indToId((row - up_row),col, true);
			uf.union(otherId, currentId);
		}
		if (isOpen(row, (col + rt_col)))
		{	
			otherId = indToId(row,(col + rt_col), true);
			uf.union(otherId, currentId);
		}
		if (isOpen(row,(col - lt_col)))
		{
			otherId = indToId(row,(col - lt_col), true);
			uf.union(otherId, currentId);
		}
	
	}

	public boolean isOpen(int row, int col)
	{
		valid(--row, --col);
		return opens[row][col];
	}

	public boolean isFull(int row, int col)
	{
		valid(--row, --col);
		return (uf.connected(0, indToId(row, col, true));
	}
	
	public int numberOfOpenSites()
	{
		int counter = 0;
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				if (opens[i][j] == true) counter++;
		return counter;
	}
	
	private int indToId(int row, int col, boolean isReduced)
	{
		row = (isReduced) ? row : row--;
		col = (isReduced) ? col : col--;
		return row*size + col;
	}
	public static void main(String[] args)
	{
		Percolation test = new Percolation(5);
		System.out.println(test.uf.connected(1,5));
		test.uf.union(1,5);
		//int counter = 0;
		//for (int i = 0; i < test.size; i++)
		//	for (int j = 0; j < test.size; j++)
		//	{
		//		System.out.println(counter + "    " +test.uf.find(counter));
		//		counter++;
		//	}
		System.out.println(test.uf.connected(1,5));
		System.out.println(test.uf.connected(1,2));
	}
}
