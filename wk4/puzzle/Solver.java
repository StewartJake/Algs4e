import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Queue;

public class Solver
{
    private int             numMoves    = 0;
    private Queue<Board>    bq          = new Queue<Board>();
    private boolean         solvable    = true;

    private class Node implements Comparable<Node>
    {
        private int     priority;
        private int     moves;
        private Board   board;
        private Node    prev;

        public Node(int moves, Board board, Node previous)
        {
            this.moves      = moves;
            this.board      = board;
            this.prev       = previous;
            this.priority   = board.manhattan() + this.moves;
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
        pq.insert(new Node(0, initial, null));
        twinQ.insert(new Node(0, twin, null));
        Node iniNode;
        Node twiNode;
        while (true)
        {
            iniNode = pq.delMin();
            twiNode = twinQ.delMin();
            initial = iniNode.board();
            twin    = twiNode.board();
            
            for (Board neighBoard : initial.neighbors())
                if (iniNode.prev == null || !neighBoard.equals(iniNode.prev.board()))
                    pq.insert(new Node(iniNode.moves + 1, neighBoard, iniNode));
            for (Board twinBoard : twin.neighbors())
                if (twiNode.prev == null || !twinBoard.equals(twiNode.prev.board()))
                    twinQ.insert(new Node(twiNode.moves + 1, twinBoard, twiNode));
            
            this.bq.enqueue(initial);
            if (initial.isGoal())
            {
                this.numMoves = iniNode.moves;
                break;
            }
            if (twin.isGoal())
            {
                this.solvable = false;
                this.numMoves = twiNode.moves;
                break;

            }
        }
    }


    public boolean isSolvable()
    {   return solvable;    }


    public int moves()
    {   return this.numMoves;  }


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