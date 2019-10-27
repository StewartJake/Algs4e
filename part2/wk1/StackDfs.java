public class StackDfs
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
        Stack<Integer> s = new Stack<Integer>;
        s.push(v);
        marked[v] = true;
        while (!s.isEmpty())
        {
            int w = s.pop()
            for (int x : G.Adj(w))
                if (!marked[x])
                {
                    s.push(x);
                    marked[x] = true;
                    edgeTo[x] = w;
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
