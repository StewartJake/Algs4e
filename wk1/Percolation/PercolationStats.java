import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats
{
	private double average;
	private Percolation testSite;

	public PercolationStats(int n, int trials)
	{
		double sumOfAverages = 0;
		for (int i = 0; i < trials; i++)
		{
			testSite = new Percolation(n);
			while (!testSite.percolates())
			{
				int rand_row = StdRandom.uniform(n) + 1;
				int rand_col = StdRandom.uniform(n) + 1;
				testSite.open(rand_row, rand_col);
			}
			sumOfAverages += (testSite.numberOfOpenSites() / (n*n));
		}
		average = sumOfAverages/trials;

	}

	public static void main(String[] args)
	{
		PercolationStats testStats;
		testStats = new PercolationStats(50, 1000);
		System.out.println(testStats.average);
	}
}
