import java.util.LinkedList;
import java.util.Queue;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

public class KdTree
{
    private int     size;
    private Node    root;
    private Node    peek;
    // private Queue<Node> pQ = new LinkedList<Node>();      // debugging

    public KdTree()
    {
        this.size = 0;
    }

    private class Node
    {
        private final boolean   vertical;
        private int             count;
        private double          key;
        private Point2D         val;
        private Node            lebo;   // left-bottom
        private Node            rito;   // right-top
        private Node            prev;

        Node(Point2D p, Node prev)
        {
            if (prev == null || !prev.vertical)
                this.key    = p.x();
            else
                this.key    = p.y();
            this.vertical   = (prev == null) ? true : !prev.vertical;
            this.val        = p;
            this.prev       = prev;
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

    private Node peek()
    {   return peek;    }


    public boolean isEmpty()
    {   return size(root) == 0;   }


    public int size()
    {   return size(root);    }


    private int size(Node x)
    {   return (x == null) ? 0 : x.count;   }


    public void insert(Point2D p)
    {
        if (p == null)
            throw new IllegalArgumentException("Null point");
        root = put(root, p, null);
    }


    private Node put(Node x, Point2D p, Node prev)
    {
        if (x == null)
        {
            x = new Node(p, prev);
            peek = x;
        }
        else if (p.equals(x.val))    x = x;
        else if (x.vertical)
        {
            if      (p.x() >=    x.key)         x.rito = put(x.rito, p, x);
            else /* if (p.x() <     x.key) */   x.lebo = put(x.lebo, p, x);
        }
        else if (!x.vertical)
        {
            if      (p.y() >=    x.key)         x.rito = put(x.rito, p, x);
            else /* if (p.y() <     x.key) */   x.lebo = put(x.lebo, p, x);
        }
        x.count = 1 + size(x.lebo) + size(x.rito);
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
                if      (p.x() >=   x.key)          x = x.rito;
                else /* if (p.x() <    x.key) */    x = x.lebo;
            }
            else
            {
                if      (p.y() >=   x.key)          x = x.rito;
                else /* if (p.y() <    x.key) */    x = x.lebo;
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
        return rQ;
    }

    private void range(Node x, RectHV rect, Queue<Point2D> rQ)
    {
        if (x != null)
        {
            if (rect.contains(x.val))   rQ.add(x.val);
            if      (x.vertical)
            {
                if      (rect.xmax() < x.key)   range(x.lebo, rect, rQ);
                else if (rect.xmin() > x.key)   range(x.rito, rect, rQ);
                else if (rect.xmin() <=  x.key && rect.xmax() >= x.key)
                {
                    range(x.lebo, rect, rQ);
                    range(x.rito, rect, rQ);
                }
            }
            else    // !x.vertical
            {
                if      (rect.ymax() < x.key)   range(x.lebo, rect, rQ);
                else if (rect.ymin() > x.key)   range(x.rito, rect, rQ);
                else if (rect.ymin() <=  x.key && rect.ymax() >= x.key)
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
                    <= champ.distanceSquaredTo(p))
                champ = contender.val;
            if ((contender.lebo != null && contender.rito != null)
                    &&  contender.lebo.val.distanceSquaredTo(p) 
                    <=   contender.rito.val.distanceSquaredTo(p))
            {
                // if (contender.lebo.val.distanceSquaredTo(p)
                //         <= champ.distanceSquaredTo(p))
                    champ = nearest(contender.lebo, champ, p);
                // if (contender.rito.val.distanceSquaredTo(p)
                //          <= champ.distanceSquaredTo(p))
                    champ = nearest(contender.rito, champ, p);
            }
            else
            {
                // if (contender.rito != null 
                //         && (contender.rito.val.distanceSquaredTo(p)
                //             <= champ.distanceSquaredTo(p)))
                    champ = nearest(contender.rito, champ, p);
                // if (contender.lebo != null 
                //         && (contender.lebo.val.distanceSquaredTo(p)
                //             <= champ.distanceSquaredTo(p)))
                    champ = nearest(contender.lebo, champ, p);
            }
        }
        return champ;
    }

    public static void main(String[] args)
    {
        // KdTree kt   = new KdTree();
        // RectHV rect = new RectHV(0.5, 0.25, 0.75, 1.0); 
        // for (Node n : kt.pQ)
        //     System.out.println(n);
        // Point2D A = new Point2D(0.7,    0.2);
        // Point2D B = new Point2D(0.5,    0.4);
        // Point2D C = new Point2D(0.2,    0.3);
        // Point2D D = new Point2D(0.4,    0.7);
        // Point2D E = new Point2D(0.9,    0.6);
        // Point2D F = new Point2D(0.571,  0.926);
        // Point2D G = new Point2D(0.5,    0.625);
        // Point2D H = new Point2D(1.0,    0.25);
        // Point2D I = new Point2D(0.875,  0.4375);
        // Point2D J = new Point2D(0.0625, 0.5625);
        // Point2D A  = new Point2D(1.0,   0.25);
        // Point2D B  = new Point2D(0.0,   0.0);
        // Point2D C  = new Point2D(0.25,  1.0);
        // Point2D D  = new Point2D(0.25,  0.0);
        // Point2D E  = new Point2D(0.75,  0.0);
        // Point2D F  = new Point2D(0.75,  0.75);
        // Point2D G  = new Point2D(0.0,   0.5);
        // Point2D H  = new Point2D(1.0,   0.75);
        // Point2D I  = new Point2D(0.0,   0.25);
        // Point2D J  = new Point2D(0.5,   0.0);
        // kt.insert(A);
        // kt.insert(B);
        // kt.insert(C);
        // kt.insert(D);
        // kt.insert(E);
        // kt.insert(F);
        // kt.insert(G);
        // kt.insert(H);
        // kt.insert(I);
        // kt.insert(J);
        // System.out.println(rect);
        // System.out.println(kt.range(rect));
        // System.out.println(kt.nearest(F));
        // System.out.println(kt.size());
    }
}
