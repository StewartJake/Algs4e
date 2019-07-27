import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation
{
	private boolean[][] opens;
	private final WeightedQuickUnionUF uf;
	private final int size;
	private int opennedSites;
	private final int header;
	private final int footer;
	
	public Percolation(int n)
	{
		header = n*n;
		footer = n*n + 1;
		if (n <= 0)
			throw new IllegalArgumentException("Arg n must be greater than zero");
		uf = new WeightedQuickUnionUF(n*n + 2);
		opens = new boolean[n][n];
		size = n;
		opennedSites = 0;
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < n; j++)
				opens[i][j] = false;
		}
	}
	
	private void valid(int row, int col)
	{
		//  will receive a one indexed row and col
		if ((row > size || row < 1) || (col > size || col < 1))
		{
			throw new IllegalArgumentException("index is not between 1 and " + size);
		}

	}
	
	public void open(int row, int col)
	{
		valid(row, col);
		if (!opens[row - 1][col - 1])
		{
			opens[row - 1][col -1] = true;
			opennedSites++;
		}
		int currentId = indToId(row, col);
		int otherId;
		if (row == 1) 
			uf.union(header, currentId);
		else if (row == size)
			uf.union(footer, currentId);
		// pesky corner cases
		if (size == 1)
			;
		else if(size == 2)
		{
			if (row == 1 && col == 1)
			{
				if (isOpen(row, col+1)) uf.union(currentId, indToId(row, col+1));
				if (isOpen(row+1, col)) uf.union(currentId, indToId(row+1, col));
			}
			if (row == 1 && col == size)
			{
				if (isOpen(row, col-1)) uf.union(currentId, indToId(row, col-1));
				if (isOpen(row+1, col)) uf.union(currentId, indToId(row+1, col));
			}
			if (row == size && col == 1)
			{
				if (isOpen(row, col+1)) uf.union(currentId, indToId(row, col+1));
				if (isOpen(row-1, col)) uf.union(currentId, indToId(row-1, col));
			}
			if (row == size && col == size)
			{
				if (isOpen(row, col-1)) uf.union(currentId, indToId(row, col-1));
				if (isOpen(row-1, col)) uf.union(currentId, indToId(row-1, col));
			}
		}
		else
		{
			if (row != size  && isOpen((row + 1), col))
			{
				otherId = indToId((row + 1), col);
				uf.union(otherId, currentId);
			}
			if (row != 1 && isOpen((row - 1), col))
			{
				otherId = indToId((row - 1), col);
				uf.union(otherId, currentId);
			}
			if (col != size && isOpen(row, (col + 1)))
			{	
				otherId = indToId(row, (col + 1));
				uf.union(otherId, currentId);
			}
			if (col != 1 && isOpen(row, (col - 1)))
			{
				otherId = indToId(row, (col - 1));
				uf.union(otherId, currentId);
			}
		}
	
	}

	public boolean isOpen(int row, int col)
	{
		valid(row, col);
		return opens[row-1][col-1];
	}

	public boolean isFull(int row, int col)
	{
		valid(row, col);
		// a site that is connected to th header must also be open
		// no need to check here
		return uf.connected(header, indToId(row, col));
	}
	
	public int numberOfOpenSites()
	{
		return opennedSites;
	}
	
	private int indToId(int row, int col)
	{
		return --row*size + --col;
	}

	public boolean percolates()
	{
		return uf.connected(header, footer);
	}

	public static void main(String[] args)
	{
		Percolation test = new Percolation(5);
		test.open(1, 5);
		test.open(2, 5);
		test.open(5, 5);
		System.out.println(test.percolates());
		test.open(3, 5);
		test.open(4, 5);
		System.out.println(test.percolates());
	}
}
