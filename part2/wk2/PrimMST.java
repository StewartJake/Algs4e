public class PrimMST
{
    private Edge[] edgeTo;
    private double[] distTo;
    private boolean[] marked;
    private IndexMinPQ<Double> pq;

    public PrimMST(EdgeWeightedGraph g)
    {
        edgeTo = new Edge[g.V()];
        distTo = new double[g.V()];
        marked = new boolean[g.V()];
        for (int v = 0; v < g.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        pq = new IndexMinPQ<Double>(g.V());

        distTo[0] = 0.0;
        pq.insert(0, 0.0);
        while (!pq.isEmpty())
            visit(g, pq.delMin());
    }

    private void visit(EdgeWeightedGraph G, int v)
    {
        marked[v] = true;
        for (Edge e : g.adj(v))
        {
            int w = e.other(v);
            if (marked[w]) continue;
            if (e.weight() < distTo[w])
            {
                edgeTo[w] = e;
                distTo[w] = e.weight();
                if (pq.contains(w)) pq.change(w, distTo[w]);
                else                pq.insert(w, distTo[w]);
            }
        }
    }

    public Iterable<Edge> edges()
    {   
        Bag<Edge> b = new Bag<>();
        for (int i = 0; i < edgeTo.length; i++)
            b.add(edgeTo[i]);
        return b;
    }

    public double weight()
    {
        double w;
        for (Edge e : edges())
            w += e.weight();
        retrun w;
    }
}

