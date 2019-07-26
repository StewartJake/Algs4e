import java.lang.Math;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats
{
	private double average;
	private Percolation testSite;
	private double[] listOfAverages;
	private int numTrials;

	public PercolationStats(int n, int trials)
	{
		listOfAverages = new double [trials];
		numTrials = trials;
		for (int i = 0; i < trials; i++)
		{
			testSite = new Percolation(n);
			while (!testSite.percolates())
			{
				int rand_row = StdRandom.uniform(n) + 1;
				int rand_col = StdRandom.uniform(n) + 1;
				testSite.open(rand_row, rand_col);
			}
			listOfAverages[i] = (double)(testSite.numberOfOpenSites() / (double)(n*n));
		}
	}

	public double mean()
	{
		return StdStats.mean(listOfAverages, 0, numTrials);
	}

	public double stddev()
	{
		return StdStats.stddev(listOfAverages);
	}

	public double confidenceLo()
	{
		return mean() - 1.96 * stddev() / Math.sqrt(numTrials);
	}

	public double confidenceHi()
	{
		return mean() + 1.96 * stddev() / Math.sqrt(numTrials);
	}

	public static void main(String[] args)
	{
		PercolationStats testStats;
		testStats = new PercolationStats(50, 1000);
		System.out.println(testStats.confidenceLo());
		System.out.println(testStats.confidenceHi());
	}
}
