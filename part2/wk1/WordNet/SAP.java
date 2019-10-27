import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;

public class SAP
{
    private Digraph D;

    public SAP(Digraph G)
    {
        D = new Digraph(G);
    }

    public int length(int v, int w)
    {
        Bag<Integer> iterV = new Bag<>();
        Bag<Integer> iterW = new Bag<>();
        iterV.add(v);
        iterW.add(w);
        return length(iterV, iterW);
    }

    public int ancestor(int v, int w)
    {
        Bag<Integer> iterV = new Bag<>();
        Bag<Integer> iterW = new Bag<>();
        iterV.add(v);
        iterW.add(w);
        return ancestor(iterV, iterW);
    }

    public int length(Iterable<Integer> v, Iterable<Integer> w)
    {   return calcBfs(v, w, true); }

    public int ancestor(Iterable<Integer> v, Iterable<Integer> w)
    {   return calcBfs(v, w, false);   }
    
    private int calcBfs(Iterable<Integer> v, Iterable<Integer> w, boolean returnLen)
    {  
        checkInput(v);
        checkInput(w);

        int                         length      = -1;
        int                         ancestor    = -1;
        int                         min         = Integer.MAX_VALUE;
        BreadthFirstDirectedPaths   bfsV        = new BreadthFirstDirectedPaths(D, v);
        BreadthFirstDirectedPaths   bfsW        = new BreadthFirstDirectedPaths(D, w);

        for (int i = 0; i < D.V(); i++)
        {
            if (bfsV.hasPathTo(i) && bfsW.hasPathTo(i))
            {
                length = bfsV.distTo(i) + bfsW.distTo(i);
                if (length < min)
                {
                    min         = length;
                    ancestor    = i;
                }
            }
        }
        if (!returnLen)                 return ancestor;
        if (min == Integer.MAX_VALUE)   return length;
        return min;
    }

    private void checkInput(Iterable<Integer> ins)
    {
        if (ins == null) throw new IllegalArgumentException("Null input detected");
        for (Integer i : ins)
        {
            if (i == null)
                throw new IllegalArgumentException("Illegal entry in input iterable");
            if (i.intValue() < 0 || i.intValue() > D.V())
                throw new IllegalArgumentException("Input out of bounds");
        }
    }

    public static void main(String[] args)
    {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        Bag<Integer> v = new Bag<>();
        v.add(7);
        v.add(12);
        Bag<Integer> w = new Bag<>();
        w.add(9);
        w.add(null);
        System.out.println(sap.length(v, w));
        System.out.println(sap.ancestor(v, w));
    }
}
