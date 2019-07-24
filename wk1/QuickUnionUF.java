public class QuickUnionUF
{
	private int[] id;

	public QuickUnionUF(int N)
	{
		id = new int [N];
		for (int i = 0; i < N; i++) id[i] = i;
	}

	private int root(int i)
	{
		while(i != id[i]) i = id[i];
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

	public static void main(String[] args)
	{
		int[] a = {1,2,3,4,5,6,7};
		QuickUnionUF qf1 = new QuickUnionUF(a.length);
		qf1.union(0, 2);
		qf1.union(0, 4);
		qf1.union(5, 6);
		System.out.println(qf1.connected(0, 6));
		qf1.union(2, 5);
		System.out.println(qf1.connected(0, 6));
	}
}
