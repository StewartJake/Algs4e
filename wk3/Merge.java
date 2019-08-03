public class Merge
{
    private static Comparable[] aux;

    public static void sort(Comparable[] a)
    {
        aux = new Comparable[a.length];
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable[] a, int lo, int hi)
    {
        if (hi <= lo) return;
        int mid = lo + (hi - lo)/2;
        sort(a, lo, mid);
        sort(a, mid + 1, hi);
        merge(a, lo, mid, hi);
    }

    private static void merge(Comparable[] a, int lo, int mid, int hi)
    {
        int i = lo;
        int j = mid + 1;

        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];

        for (int k = lo; k <= hi; k++)
        {
            if      (i > mid)               a[k] = a[j++];
            else if (j > hi)                a[k] = a[i++];
            else if (less(aux[j], aux[i]))  a[k] = a[j++];
            else                            a[k] = a[i++];
        }
    }

    private static boolean less(Comparable v, Comparable w)
    {   return v.compareTo(w) < 0;  }
}
