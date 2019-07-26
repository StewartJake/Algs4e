import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation
{
	private boolean[][] opens;
	private WeightedQuickUnionUF uf;
	private int size;
	
	public Percolation(int n)
	{
		uf = new WeightedQuickUnionUF(n*n);
		opens = new boolean[n][n];
		size = n;
		int counter = 0;
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < n; j++)
			{
				opens[i][j] = false;
				counter++;
			}
		}
	}
	
	private void valid(int row, int col)
	{
		//  will receive a one indexed row and col
		//if ((row == topper || row == footer) || col == topper || col == footer) return;
		if ((row > size || row < 1) || (col > size || col < 1))
		{
			throw new IllegalArgumentException("index is not between 1 and " + size);
		}

	}
	
	public void open(int row, int col)
	{
		valid(row, col);
		opens[row - 1][col -1] = true;
		int currentId = indToId(row, col);
		int otherId;
		if (row == 1) 
			uf.union(0, currentId); // this connects to a virtual topper
		else if (row == size)
			uf.union(size + 1, currentId); // virtual footer
		if (row != size  && isOpen((row + 1),col))
		{
			otherId = indToId((row + 1),col);
			uf.union(otherId, currentId);
		}
		if (row != 1 && isOpen((row - 1),col))
		{
			otherId = indToId((row - 1),col);
			uf.union(otherId, currentId);
		}
		if (col != size && isOpen(row, (col + 1)))
		{	
			otherId = indToId(row,(col + 1));
			uf.union(otherId, currentId);
		}
		if (col != 1 && isOpen(row,(col - 1)))
		{
			otherId = indToId(row,(col - 1));
			uf.union(otherId, currentId);
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
		return (uf.connected(0, indToId(row, col))) && isOpen(row, col);
	}
	
	public int numberOfOpenSites()
	{
		int counter = 0;
		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				if (opens[i][j] == true) counter++;
		return counter;
	}
	
	private int indToId(int row, int col)
	{
		return --row*size + --col;
	}

	public boolean percolates()
	{
		return uf.connected(0,6);
	}

	public static void main(String[] args)
	{
		Percolation test = new Percolation(5);
		test.open(1,5);
		test.open(2,5);
		test.open(5,5);
		//int counter = 0;
		//for (int i = 0; i < test.size; i++)
		//	for (int j = 0; j < test.size; j++)
		//	{
		//		System.out.println(counter + "    " +test.uf.find(counter));
		//		counter++;
		//	}
		System.out.println(test.percolates());
		test.open(3,5);
		test.open(4,5);
		System.out.println(test.percolates());
	}
}
