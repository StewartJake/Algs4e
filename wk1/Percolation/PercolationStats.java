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
		if (n <= 0 || trials <= 0)
			throw new IllegalArgumentException("Both arguments must be greater than zero");
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
		int n = Integer.parseInt(args[0]);
		int trials = Integer.parseInt(args[1]);
		PercolationStats testStats;
		testStats = new PercolationStats(n, trials);
		System.out.printf("%-24s","mean");
		System.out.print("= " + testStats.mean() + "\n");
		System.out.printf("%-24s","stddev");
		System.out.print("= " + testStats.stddev() + "\n");
		System.out.printf("%-24s","95% confidence interval");
		System.out.print("= [" + testStats.confidenceLo() 
				+", " + testStats.confidenceHi() + "]\n");
	}
}
