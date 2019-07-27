import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats
{
	private static final double Z_SCORE_95 = 1.96;
	private final int numTrials;
	private final double avg;
	private final double stdDev;

	public PercolationStats(int n, int trials)
	{
		double[] listOfAverages;
		Percolation testSite;
		if (n <= 0 || trials <= 0)
			throw new IllegalArgumentException("Both arguments must be greater than zero");
		listOfAverages = new double [trials];
		numTrials = trials;
		for (int i = 0; i < trials; i++)
		{
			testSite = new Percolation(n);
			while (!testSite.percolates())
			{
				int randRow = StdRandom.uniform(n) + 1;
				int randCol = StdRandom.uniform(n) + 1;
				testSite.open(randRow, randCol);
			}
			listOfAverages[i] = (double) (testSite.numberOfOpenSites()) / (n*n);
		}
		avg = StdStats.mean(listOfAverages, 0, trials);
		stdDev = StdStats.stddev(listOfAverages);
	}

	public double mean()
	{
		return avg;
	}

	public double stddev()
	{
		return stdDev;
	}

	public double confidenceLo()
	{
		return avg - Z_SCORE_95 * stdDev / Math.sqrt(numTrials);
	}

	public double confidenceHi()
	{
		return avg + Z_SCORE_95 * stdDev / Math.sqrt(numTrials);
	}

	public static void main(String[] args)
	{
		int n = Integer.parseInt(args[0]);
		int trials = Integer.parseInt(args[1]);
		PercolationStats testStats;
		testStats = new PercolationStats(n, trials);
		System.out.printf("%-24s", "mean");
		System.out.print("= " + testStats.mean() + "\n");
		System.out.printf("%-24s", "stddev");
		System.out.print("= " + testStats.stddev() + "\n");
		System.out.printf("%-24s", "95% confidence interval");
		System.out.print("= [" + testStats.confidenceLo() 
				+", " + testStats.confidenceHi() + "]\n");
	}
}
