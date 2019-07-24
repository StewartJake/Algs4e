public class Percolation
{
	private int[][] grid;
	
	public Percolation(int n)
	{
		grid = new int [n][n];
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
	public static void main(String[] args)
	{
		Percolation test = new Percolation(5);
		System.out.println(test.grid[4][4]);
	}
}
