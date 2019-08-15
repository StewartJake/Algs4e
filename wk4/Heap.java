public class Heap
{
    public static void sort(Comparable[] pq)
    {
        int N = pq.length;
        for (int k = N/2; k >= 1; k--)
            sink(a, k, N);
        while (N > 1)
        {
            exch(a, 1, N--);
            sink(a, 1, N);
        }
    }
    private static void sink(Compararable[] pq, int k, int N)
    {
        while (2*k <= N)
        {
            int j = 2*k;
            if (j < N && less(pq[j], pq[j + 1]))    j++;
            if (!less(pq[k], pqj])) break;
            exch(pq[k], pq[j]);
            k = j;
        }
    }

    private static boolean less(Comparable[] pq, int i, int j)
    {   return pq[--i].compareTo(pq[--j]) < 0;  }

    private static void exch(Comparable[] pq, int i, int j)
    {
        Key temp = pq[--i];
        pq[i] = pq[--j];
        pq[j] = pq[i];
    }
}
