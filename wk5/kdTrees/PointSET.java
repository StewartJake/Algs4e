import java.util.TreeSet;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class PointSET
{
    private TreeSet<Point2D> ps;

    public PointSET()
    {
        this.ps = new TreeSet<Point2D>();
    }


    public boolean isEmpty()
    {   return ps.isEmpty();    }


    public int size()
    {   return ps.size();   }


    public void insert(Point2D p)
    {   ps.add(p);  }


    public boolean contains(Point2D p)
    {   return ps.contains(p);  }


    public void draw()
    {
        //StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 5);
        StdDraw.setYscale(0, 5);
        for (Point2D p : ps)
            p.draw();
        StdDraw.show();
    }



    // public Iterable<Point2D> range(RectHV rect)
    // {}


    // public Point2D nearest(Point2D p)
    // {}


    public static void main(String[] args)
    {
        Point2D on = new Point2D(1.0, 1.0);
        Point2D tw = new Point2D(2.0, 2.0);
        Point2D th = new Point2D(3.0, 3.0);
        PointSET ps = new PointSET();
        ps.insert(on);
        ps.insert(tw);
        ps.insert(th); 
        ps.draw();
    }
}

