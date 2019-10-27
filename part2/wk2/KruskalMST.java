import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.UF;

public class KruskalMST
{
    private Queue<Edge> mst = new Queue<>();

    public KruskalMST(EdgeWeightedGraph g)
    {
        MinPQ<Edge> pq = new MinePQ<>();
        for (Edge e : g.edges())
            pq.insert(e);
        UF uf = new UF(g.V());
        while (!pq.isEmpty() && mst.size < G.V() - 1)
        {
            Edge e = pq.delMin();
            int v = e.either();
            int w = e.other(v);
            if (!uf.connected(v, w))
            {
                uf.union(v, w);
                mst.enqueue(e);
            }
        }
    }

    public Iterable<Edge> edges()
    {   return mst; }
}
