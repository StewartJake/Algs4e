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
    private int     manhattan;
    private int     hamming;


    public Board(int[][] tiles)
    {
        int count       = 1;
        this.N          = tiles.length;
        this.goal       = new int[N][N];
        this.board      = tiles;
        this .manhattan = this.manhattan();
        this.hamming    = this.hamming();

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
            bq.enqueue(switchZero(blankRow - 1, blankCol));
        if (blankRow < N)
            bq.enqueue(switchZero(blankRow + 1, blankCol));
        if (blankCol > 0)
            bq.enqueue(switchZero(blankRow, blankCol - 1));
        if (blankCol < N)
            bq.enqueue(switchZero(blankRow, blankCol + 1));
        return bq; 
    }


    private Board switchZero(int r, int c)
    {   return switchTwo(r, c, blankRow, blankCol); }


    private Board switchTwo(int r1, int c1, int r2, int c2)
    {
        int[][] nb = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                nb[i][j] = board[i][j];
        int temp = nb[r1][c1];
        nb[r1][c1] = board[r2][c2];
        nb[r2][c2] = temp;
        Board neighbor = new Board(nb);
        return neighbor;
    }


    public Board twin()
    {   
        int[][] copy = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                copy[i][j] = board[i][j];
        int r1;
        int c1;
        int r2;
        int c2;
        if (copy[1][0] != 0)
        {
            r1 = 1;
            c1 = 0;
        }
        else
        {
            r1 = 0;
            c1 = 1;
        }
        if (copy[2][1] != 0)
        {
            r2 = 2;
            c2 = 1;
        }
        else
        {
            r2 = 1;
            c2 = 2;
        }
        return switchTwo(r1, c1, r2, c2);
    }


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
        StdOut.println(b.twin());
    }
}
