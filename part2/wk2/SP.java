public class SP
{
    private int[] distTo;
    private DirectedEdge[] edgeTo;

    public SP(EdgeWeightedDigraph g, int s)
    
    public double distTo(int v)
    {   return distTo[v];   }
    
    public Iterable <DirectedEdge> pathTo(int v)
    {
        Stack<DirectedEdge> path = new Stack<DirectedEdge>();
        for (DirectedEdge e = edgeTo[v]; e != null; e=edgeTo[e.from()])
            path.push(e);
        return path;
    }

    private void relax(DirectedEdge e)
    {
        int v = e.from();
        int w = e.to();
        if (distoTo[w] > distTo[v] + e.weight())
        {
            distTo[w] = distTo[v] + e.weight();
            edgeTo[w] = e;
        }
    }

    boolean hasNegativeCycle()
    {   return false;   }

    Iterable <DirectedEdge> negativeCycle()
    {}

    public static void main(String[] args)
    {
        SP sp = new SP(G, s);
        for (int i = 0; i < G.V(); v++)
        {
            StdOut.printf("%d to %d (%.2f): ", s, i, sp.distTo(i));
            for (DirectedEdge e : sp.pathTo(i))
                StdOut.print(e + " ");
            StdOut.println();
        }
}
