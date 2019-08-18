import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Queue;

public class Solver
{
    private int             moves       = 0;
    private Queue<Board>    bq          = new Queue<Board>();
    private boolean         solvable    = true;

    private class Node implements Comparable<Node>
    {
        private int     priority;
        private Board   board;
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
        MinPQ<Node> pq      = new MinPQ<Node>();
        MinPQ<Node> twinQ   = new MinPQ<Node>();
        Board       twin    = initial.twin();
        pq.insert(boardToNode(initial));
        twinQ.insert(boardToNode(twin));
        while (!initial.isGoal() && !twin.isGoal())
        {
            Board previous = initial;
            Board prevTwin = twin;
            initial = pq.delMin().board();
            twin    = twinQ.delMin().board();
            
            for (Board neighBoard : initial.neighbors())
                if (neighBoard != previous)
                    pq.insert(boardToNode(neighBoard));
            for (Board twinBoard : twin.neighbors())
                if (twinBoard != prevTwin)
                    twinQ.insert(boardToNode(twinBoard));
            
            this.bq.enqueue(initial);
            moves++;
        }
        this.solvable = (initial.isGoal()) ? true : false;
        moves--;    // to account for the initialization
    }


    private Node boardToNode(Board b)
    {
        int priority = this.moves + b.manhattan();
        Node testNode = new Node(priority, b);
        return testNode;
    }


    public boolean isSolvable()
    {   return solvable;    }


    public int moves()
    {   return this.moves;  }


    public Iterable<Board> solution()
    {   return this.bq; }


    public static void main(String[] args)
    {
        // create initial board from file
    In in = new In(args[0]);
    int n = in.readInt();
    int[][] tiles = new int[n][n];
    for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++)
            tiles[i][j] = in.readInt();
    Board initial = new Board(tiles);

    // solve the puzzle
    Solver solver = new Solver(initial);

    // print solution to standard output
    if (!solver.isSolvable())
        StdOut.println("No solution possible");
    else {
        StdOut.println("Minimum number of moves = " + solver.moves());
        for (Board board : solver.solution())
            StdOut.println(board);
    }
        // int n = 3;
        // int[][] john    = {{8,1,3},{4,0,2},{7,6,5}};
        // int[][] step4   = {{0,1,3},{4,2,5},{7,8,6}};
        // int[][] no      = {{1,2,3},{4,5,6},{8,7,0}};
        // Board b = new Board(step4);
        // Solver s = new Solver(b);
        // StdOut.println(s.isSolvable());
        // StdOut.println("Solved on move " + s.moves());
        // for (Board bd : s.bq)
        //     StdOut.println(bd.toString());
     }
}
