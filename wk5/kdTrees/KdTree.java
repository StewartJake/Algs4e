import java.util.LinkedList;
import java.util.Queue;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

public class KdTree
{
    private int     size;
    private Node    root;
    // private Queue<Node> pQ = new LinkedList<Node>();      //debugging

    public KdTree()
    {
        this.size = 0;
    }

    private class Node
    {
        private final boolean   vertical;
        private double          key;
        private Point2D         val;
        private Node            lebo;   // left-bottom
        private Node            rito;   // right-top
        private Node            prev;

        Node(Point2D p, Node prev)
        {
            if (prev == null || prev.vertical)
                this.key = p.x();
            else
                this.key = p.y();
            this.vertical = (prev == null) ? true : !prev.vertical;
            this.val = p;
        }

        public String toString()
        {
            StringBuilder s = new StringBuilder();
            s.append(val + "\n");
            s.append("Left "        + ((lebo == null) ? null : lebo.val)  + "\n");
            s.append("Right "       + ((rito == null) ? null : rito.val)  + "\n");
            s.append("Vertical "    + vertical  + "\n");
            return s.toString();
        }
    }

    public boolean isEmpty()
    {   return size == 0;   }


    public int size()
    {   return size;    }


    public void insert(Point2D p)
    {
        if (p == null)
            throw new IllegalArgumentException("Null point");
        root = put(root, p, root);
    }


    private Node put(Node x, Point2D p, Node prev)
    {
        if (x == null)
        {
            size++;
            // Node n = new Node(p, prev);
            // pQ.add(n);
            return new Node(p, prev);
        }
        if (p.equals(x.val))    return x;
        if (x.vertical)
        {
            if (p.x() >=    x.key)  x.rito = put(x.rito, p, x);
            if (p.x() <     x.key)  x.lebo = put(x.lebo, p, x);
        }
        else if (!x.vertical)
        {
            if (p.y() >=    x.key)  x.rito = put(x.rito, p, x);
            if (p.y() <     x.key)  x.lebo = put(x.lebo, p, x);
        }
        return x;
    }



    public boolean contains(Point2D p)
    {
        if (p == null)
            throw new IllegalArgumentException("Null point");
        Node x = root;
        while (x != null)
        {
            if (p.equals(x.val)) return true;
            if (x.vertical)
            {
                if      (p.x() >=   x.key)  x = x.rito;
                else if (p.x() <    x.key)  x = x.lebo;
            }
            else
            {
                if      (p.y() >=   x.key)  x = x.rito;
                else if (p.y() <    x.key)  x = x.lebo;
            }
        }
        return false;
    }


    public void draw()
    {
        // blank for now
    }


    public Iterable<Point2D> range(RectHV rect)
    {
        if (rect == null)
            throw new IllegalArgumentException("Rectangle undefined");
        Queue<Point2D> rQ = new LinkedList<Point2D>();
        range(root, rect, rQ);
        // Node x = root;
        // while (x != null)
        // {
        //     if (rect.contains(x.val))   rQ.add(x.val);
        //     if      (x.vertical)
        //     {
        //         if      (rect.xmax() < x.val.x())   x = x.lebo;
        //         else if (rect.xmax() > x.val.x())   x = x.rito;
        //         // else if (rect.xmin() < x.val.x() && rect.xmax() > x.val.x())
        //         // {}
        //     }
        //     else if (!x.vertical)
        //     {
        //         if      (rect.ymax() < x.val.y())   x = x.lebo;
        //         else if (rect.ymax() > x.val.y())   x = x.rito;
        //         // else if (rect.ymin() < x.val.y() && rect.ymax() > x.val.y())
        //         // {}
        //     }
        // }
        return rQ;
    }

    private void range(Node x, RectHV rect, Queue<Point2D> rQ)
    {
        if (x != null)
        {
            if (rect.contains(x.val))   rQ.add(x.val);
            if      (x.vertical)
            {
                if      (rect.xmax() <  x.key)   range(x.lebo, rect, rQ);
                else if (rect.xmax() >= x.key)   range(x.rito, rect, rQ);
                else if (rect.xmin() <  x.key && rect.xmax() > x.key)
                {
                    range(x.lebo, rect, rQ);
                    range(x.rito, rect, rQ);
                }
            }
            else    // !x.vertical
            {
                if      (rect.ymax() <  x.key)   range(x.lebo, rect, rQ);
                else if (rect.ymax() >= x.key)   range(x.rito, rect, rQ);
                else if (rect.ymin() <  x.key && rect.ymax() > x.key)
                {
                    range(x.lebo, rect, rQ);
                    range(x.rito, rect, rQ);
                }
            }
        }
    }



    public Point2D nearest(Point2D p)
    {
        if (p == null)
            throw new IllegalArgumentException("Null point");
        Point2D champ = (root == null) ? null : root.val;
        return nearest(root, champ, p);
    }

    private Point2D nearest(Node contender, Point2D champ, Point2D p)
    {
        if (contender != null)
        {
            if (contender.val.distanceSquaredTo(p)
                    < champ.distanceSquaredTo(p))
                champ = contender.val;
            if ((contender.lebo != null && contender.rito != null)
                    &&  contender.lebo.val.distanceSquaredTo(p) 
                    <   contender.rito.val.distanceSquaredTo(p))
            {
                if (contender.lebo.val.distanceSquaredTo(p)
                        < champ.distanceSquaredTo(p))
                    champ = nearest(contender.lebo, champ, p);
                if (contender.rito.val.distanceSquaredTo(p)
                        < champ.distanceSquaredTo(p))
                    champ = nearest(contender.rito, champ, p);
            }
            else
            {
                if (contender.rito != null 
                        && (contender.rito.val.distanceSquaredTo(p)
                            < champ.distanceSquaredTo(p)))
                    champ = nearest(contender.rito, champ, p);
                if (contender.lebo != null 
                        && (contender.lebo.val.distanceSquaredTo(p)
                            < champ.distanceSquaredTo(p)))
                    champ = nearest(contender.lebo, champ, p);
            }
        }
        return champ;
    }

    public static void main(String[] args)
    {
        KdTree kt   = new KdTree();
        RectHV rect = new RectHV(0.25, 0.125, 0.5625, 1.0); 
        // for (Node n : kt.pQ)
        //     System.out.println(n);
        // System.out.println(kt.range(rect));
        // System.out.println(kt.nearest(p0));
        Point2D A = new Point2D(0.3125, 0.75);
        Point2D B = new Point2D(0.1875, 0.8125);
        Point2D C = new Point2D(0.125,  0.5);
        Point2D D = new Point2D(0.9375, 0.1875);
        Point2D E = new Point2D(0.6875, 0.0625);
        Point2D F = new Point2D(0.375,  0.0);
        Point2D G = new Point2D(0.5,    0.625);
        Point2D H = new Point2D(1.0,    0.25);
        Point2D I = new Point2D(0.875,  0.4375);
        Point2D J = new Point2D(0.0625, 0.5625);
        kt.insert(A);
        kt.insert(B);
        kt.insert(C);
        kt.insert(D);
        kt.insert(E);
        kt.insert(F);
        kt.insert(G);
        kt.insert(H);
        kt.insert(I);
        kt.insert(J);
        System.out.println(rect);
        System.out.println(kt.range(rect));
    }
}
