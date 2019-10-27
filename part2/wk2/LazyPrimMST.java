import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;

public class LazyPrimMST
{
    private boolean[] marked;   // MST vertices
    private Queue<Edge> mst;    // MST edges
    private MinPQ<Edge> pq;

    public LazyPrimMST(WeightedGraph g)
    {
        pq = new MinPQ<Edge>();
        mst = new Queue<Edge>();
        marked = new boolean[g.V()];
        visit(g, 0);            // assume G is connected

        while (!pq.isEmpty() && mst.size() < g.V() - 1)
        {
            Edge e = pq.delMin();
            int v = e.either();
            int w = e.other(v);
            if (marked[v] && marked[w]) continue;
            mst.enqueue(e);
            if (!marked[v]) visit(g, v);
            if (!marked[w]) visit(g, w);
        }
    }

    private void visit(WeightedGraph g, int v)
    {
       marked[v] = true;
       for (Edge e : g.adj(v))
           if (!marked[e.other(v)])
               pq.insert(e);
    }
}
