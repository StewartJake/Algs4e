public class QuickFindUF
{
	private int[] id;

	public QuickFindUF(int N)
	{
		id = new int[N];
		for (int i = 0; i < N; i++)
			id[i] = i;
	}

	public boolean connected(int p, int q)
	{	return id[p] == id[q];	}

	public void union(int p, int q)
	{
		int pid = id[p];
		int qid = id[q];
		for (int i = 0; i < id.length; i++)
			if (id[i] == pid) id[i] = qid;
	}
	
	public static void main(String[] args)
	{
		int[] a = {1,2,3,4,5,6,7};
		QuickFindUF qf1 = new QuickFindUF(a.length);
		qf1.union(0, 2);
		qf1.union(0, 4);
		qf1.union(5, 6);
		System.out.println(qf1.connected(0, 6));
		qf1.union(2, 5);
		System.out.println(qf1.connected(0, 6));
	}
}
