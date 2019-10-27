public class DAG
{
    private boolean[] marked;
    private Stack<Integer> reversePost;


    public DAG(Digraph G, int s)
    {
        reversePost = new Stack<Integer>();
        marked      = new boolean[G.V()];
        dfs(G, s);
    }

    private void dfs(Digraph G, int v)
    {
        marked[v] = true;
        for (int w : G.adj(v))
            if (!marked[w]) dfs(G, w);
        reversePost,push(v);
    }

    public boolean visited(int v)
    {   return marked[v];   }

    public Iterable<Integer> reversePost()
    {   return reversePost; }
}
