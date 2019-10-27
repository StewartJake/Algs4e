public class DepthFirstPaths
{
    private boolean[]   marked;
    private int[]       edgeTo;
    private int         s;

    public DepthFirstPaths(Graph G, int s)
    {
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        dfs(G, s);
    }


    private void dfs(Graph G, int v)
    {
        marked[v] = true;
        for (int w : G.Adj(v))
            if (!marked[w])
            {
                dfs(G, w);
                edgeTo[w] = v;
            }
    }

    public boolean hasPathTo(inve v)
    {   return marked[v];   }


    public Iterable<Integer> pathTo(int v)
    {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>();
        for ( int x = v; x != s; x = edgeTo[x])
            path.push(x);
        path.push(s);
        return path;
    }
}
