import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeSet;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class PointSET
{
    private TreeSet<Point2D>    ps;


    public PointSET()
    {
        this.ps = new TreeSet<Point2D>();
    }


    public boolean isEmpty()
    {   return ps.isEmpty();    }


    public int size()
    {   return ps.size();   }


    public void insert(Point2D p)
    {   
        if (p == null)
            throw new IllegalArgumentException("Null point");
        ps.add(p);
    }


    public boolean contains(Point2D p)
    {
        if (p == null)
            throw new IllegalArgumentException("Null point");
        return ps.contains(p);
    }


    public void draw()
    {
        for (Point2D p : ps)
            p.draw();
        StdDraw.show();
    }


    public Iterable<Point2D> range(RectHV rect)
    {
        if (rect == null)
            throw new IllegalArgumentException("Null rectangle");
        Queue<Point2D> rangeQ = new LinkedList<Point2D>();
        for (Point2D p : ps)
            if (rect.contains(p))
                rangeQ.add(p);
        return rangeQ;
    }


    public Point2D nearest(Point2D p)
    {
        if (p == null)
            throw new IllegalArgumentException("Null point");
        double  champDist   = Double.POSITIVE_INFINITY;
        Point2D champ = null;
        for (Point2D q : ps)
            if (p.distanceSquaredTo(q) < champDist)
            {
                champDist   = p.distanceSquaredTo(q);
                champ       = q;
            }
        return champ;
    }


    public static void main(String[] args)
    {
        Point2D on = new Point2D(1.0, 1.0);
        Point2D tw = new Point2D(2.0, 2.0);
        Point2D th = new Point2D(3.0, 3.0);
        PointSET ps = new PointSET();
        ps.insert(on);
        ps.insert(tw);
        ps.insert(th);
        RectHV rect = new RectHV(0.0, 0.0, 4.3, 4.9);
        System.out.println(ps.range(rect));
        System.out.println(ps.nearest(new Point2D(0.0, 0.0)));
        ps.draw();
    }
}
