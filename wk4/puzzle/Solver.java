import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Queue;

public class Solver
{
    private int moves = 0;
    private Queue<Board> bq = new Queue<Board>();

    private class Node implements Comparable<Node>
    {
        private int     priority;
        private Board   board;
        private Node    parent  = null;
        private Node    left    = null;
        private Node    right   = null;

        public Node(int priority, Board board)
        {
            this.priority    = priority;
            this.board       = board;
        }


        public Board board()
        {   return this.board;  }


        public int compareTo(Node that)
        {
            if      (this.priority < that.priority) return -1;
            else if (this.priority > that.priority) return  1;
            else                                    return  0;
        }
    }

    public Solver(Board initial)
    {
        if (initial == null)
            throw new IllegalArgumentException("Illegitimate Board");
        MinPQ<Node> pq = new MinPQ<Node>();
        pq.insert(boardToNode(initial));
        while (!initial.isGoal())
        {
            initial = pq.delMin().board();
            for (Board neighBoard : initial.neighbors())
                pq.insert(boardToNode(neighBoard));
            this.bq.enqueue(initial);
            moves++;
        }
        moves--;    // to account for the initialization
    }


    private Node boardToNode(Board b)
    {
        int priority = this.moves + b.manhattan();
        Node testNode = new Node(priority, b);
        return testNode;
    }


    public boolean isSolvable()
    {return false;}


    public int moves()
    {   return this.moves;  }


    public Iterable<Board> solution()
    {   return this.bq; }


    public static void main(String[] args)
    {
        int n = 3;
        int[][] john    = {{8,1,3},{4,0,2},{7,6,5}};
        int[][] step4   = {{0,1,3},{4,2,5},{7,8,6}};
        Board b = new Board(step4);
        Solver s = new Solver(b);
        StdOut.println("Solved on move " + s.moves());
        for (Board bd : s.bq)
            StdOut.println(bd.toString());
     }
}
