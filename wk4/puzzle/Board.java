import java.util.Arrays;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Queue;

public class Board
{
    private int     N;
    private int     blankRow;
    private int     blankCol;
    private int[][] board;
    private int[][] goal;
    private int[]   goal1;
    private int[]   board1;


    public Board(int[][] tiles)
    {
        int count   = 1;
        this.N      = tiles.length;
        this.goal   = new int[N][N];
        this.board  = tiles;
        this.goal1  = new int[N*N];

        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                if (tiles[i][j] == 0)
                {
                    this.blankRow = i;
                    this.blankCol = j;
                    break;
                }

        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                goal[i][j] = count++;
        goal[N - 1][N - 1] = 0;
    }


    public String toString()
    {
        String outStr ="" + N + "\n";
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j <N; j++)
                outStr += board[i][j] + " ";
            outStr += "\n";
        }
        return outStr;
    }


    public int dimension()
    {   return N;   }


    public int hamming()
    {
        int count = 0;
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                if (board[i][j] != goal[i][j] && board[i][j] != 0)
                    count++;
        return count;
    }


    public int manhattan()
    {
        int sum = 0;
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
            {
                // might have to speed this up
                if (goal[i][j] != 0)
                {
                    int current = goal[i][j];
                    for (int x = 0; x < N; x++)
                        for (int y = 0; y < N; y++)
                            if (board[x][y] == current)
                            {
                                sum += (x-i < 0) ? (i-x) : (x-i);
                                sum += (y-j < 0) ? (j-y) : (y-j);
                            }
                }
            }
        return sum;
    }


    public boolean isGoal()
    {   return Arrays.deepEquals(this.board, this.goal);    }


    public boolean equals(Object y)
    {
        if (y == this)  return true;
        if (y == null)  return false;

        if (this.getClass() != y.getClass())
            return false;

        Board that = (Board) y;
        if (this.N != that.N)   return false;
        if (!Arrays.deepEquals(this.board, that.board))   return false;
        return true;
    }
            

    public Iterable<Board> neighbors()
    {
        Queue<Board> bq = new Queue<Board>();
        if (blankRow > 0)
            bq.enqueue(switchTwo(blankRow - 1, blankCol));
        if (blankRow < N)
            bq.enqueue(switchTwo(blankRow + 1, blankCol));
        if (blankCol > 0)
            bq.enqueue(switchTwo(blankRow, blankCol - 1));
        if (blankCol < N)
            bq.enqueue(switchTwo(blankRow, blankCol + 1));
        return bq; 
    }


    private Board switchTwo(int r, int c)
    {
        int[][] nb = this.board.clone();
        int temp = nb[r][c];
        nb[r][c] = 0;//board[blankRow][blankCol];
        nb[blankRow][blankCol] = temp;
        this.blankRow = r;
        this.blankCol = c;
        Board neighbor = new Board(nb);
        return neighbor;
    }


//     public Board twin()
//     {}
// 

    public static void main(String[] args)
    {
        int n = 3;
        int[][] bob     = {{8,1,3},{4,0,2},{7,6,5}};
        int[][] goal    = {{1,2,3},{4,5,6},{7,8,0}};
        int[][] veritas = {{1,2,3},{4,5,6},{7,8,0}};
        Board b = new Board(bob);
        Board g = new Board(goal);
        Board v = new Board(veritas);
        Board z = v;
        StdOut.println(b);
        StdOut.println(b.dimension());
        StdOut.println(b.hamming());
        StdOut.println(b.manhattan());
        StdOut.println(v.equals(b));
        StdOut.println(b.isGoal());
        StdOut.println(g.isGoal());
        for (Board nei : b.neighbors())
            StdOut.println(nei);
    }
}
