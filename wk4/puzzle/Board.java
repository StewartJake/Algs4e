import java.util.Arrays;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Queue;


public class Board
{
    private static final int maxInt = Integer.MAX_VALUE;
    private int     N;
    private int     blankRow;
    private int     blankCol;
    private int[][] board;
    private int     manhattan = maxInt;
    private int     hamming = maxInt;


    public Board(int[][] tiles)
    {

        this.N          = tiles.length;
        this.board      = new int[N][N];

        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
            {
                board[i][j] = tiles[i][j];
                if (tiles[i][j] == 0)
                {
                    this.blankRow = i;
                    this.blankCol = j;
                }
            }
    }


    public String toString()
    {
        StringBuilder outStr = new StringBuilder();
        outStr.append(N + "\n");
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
                outStr.append(String.format("%2d ", board[i][j]));
            outStr.append("\n");
        }
        return outStr.toString();
    }


    public int dimension()
    {   return N;   }


    public int hamming()
    {
        if (this.hamming != maxInt)   return this.hamming;
        int count = 0;
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                if (board[i][j] != 0)
                    count += calcHamming(board[i][j], i, j);
        this.hamming = count;
        return count;
    }


    public int manhattan()
    {
        if (this.manhattan != maxInt) return this.manhattan;
        int sum = 0;
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
            {
                if (board[i][j] != 0)
                {
                    sum += calcManhattan(board[i][j], i, j);
                }
            }
        this.manhattan = sum;
        return sum;
    }

    private int calcManhattan(int tile, int r, int c)
    {
        int goalRow = (tile - 1) / N;
        int goalCol = (tile - 1) % N;
        int sum     = 0;
        
        sum += (goalRow - r < 0) ? (r - goalRow) : (goalRow - r);
        sum += (goalCol - c < 0) ? (c - goalCol) : (goalCol - c);
        return sum;
    }

    
    private int calcHamming(int tile, int r, int c)
    {   return (calcManhattan(tile, r, c) == 0) ? 0 : 1;    }


    public boolean isGoal()
    {   return (hamming() == 0);    }


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
        if (blankRow < N - 1)
            bq.enqueue(switchZero(blankRow + 1, blankCol));
        if (blankCol > 0)
            bq.enqueue(switchZero(blankRow, blankCol - 1));
        if (blankCol < N - 1)
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
        if (copy[N-1][0] != 0)
        {
            r1 = N-1;
            c1 = 0;
        }
        else
        {
            r1 = 0;
            c1 = N-1;
        }
        if (copy[0][0] != 0)
        {
            r2 = 0;
            c2 = 0;
        }
        else
        {
            r2 = N-1;
            c2 = N-1;
        }
        return switchTwo(r1, c1, r2, c2);
    }


    public static void main(String[] args)
    {
        int[][] bob     = {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
        int[][] goal    = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        int[][] veritas = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        Board b = new Board(bob);
        Board g = new Board(goal);
        Board v = new Board(veritas);
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
