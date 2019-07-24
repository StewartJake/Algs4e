public class QuickUnionPathCompUF1
{
	private int[] id;

	public QuickUnionPathCompUF1(int N)
	{
		id = new int [N];
		for (int i = 0; i < N; i++) id[i] = i;
	}

	private int root(int i)
	{
		while(i != id[i]) i = id[i];
		int root = i;
		while(i != id[i]) id[i] = root;
		return i;
	}

	public boolean connected(int p, int q)
	{
		return root(p) == root(q);
	}

	public void union(int p, int q)
	{
		int i = root(p);
		int j = root(q);
		id[i] = j;
	}
}
