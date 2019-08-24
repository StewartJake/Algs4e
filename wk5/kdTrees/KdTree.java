import edu.princeton.cs.algs4.Point2D;

public class KdTree
{
    int     size;
    Node    root;

    public KdTree()
    {
        this.size = 0;
    }

    private class Node
    {
        private boolean vertical;
        private double  key;
        private Point2D val;
        private Node    lebo;   // left-bottom
        private Node    rito;   // right-top
        private Node    prev;

        Node(Point2D p, Node prev)
        {
            if (prev == null || prev.vertical)
                this.key = p.x();
            else
                this.key = p.y();
            this.vertical = (prev == null) ? true : !prev.vertical;
            this.val = p;
        }
    }

    public boolean isEmpty()
    {   return size == 0;   }


    public int size()
    {   return size;    }


    public void insert(Point2D p)
    {
        root = put(root, p, root);
    }


    private Node put(Node x, Point2D p, Node prev)
    {
        if (x == null)
        {
            size++;
            return new Node(p, prev);
        }
        if (p.equals(x.val))    x.val = p;
        if (x.vertical)
        {
            if (p.x() > x.key)  x = put(x.rito, p, x);
            if (p.x() < x.key)  x = put(x.lebo, p, x);
        }
        if (!x.vertical)
        {
            if (p.y() > x.key)  x = put(x.rito, p, x);
            if (p.y() < x.key)  x = put(x.lebo, p, x);
        }
        return x;
    }



    public boolean contains(Point2D p)
    {
        Node x = root;
        while(x != null)
        {
            if (p.equals(x.val)) return true;
            if (x.vertical)
            {
                if      (p.x() > x.val.x())  x = x.rito;
                else if (p.x() < x.val.x())  x = x.lebo;
            }
            else
            {
                if      (p.y() > x.val.y())  x = x.rito;
                else if (p.y() < x.val.y())  x = x.lebo;
            }
        }
        return false;
    }


    public void draw()
    {}


    // public Iterable<Point2D> range(RectHV rect)
    // {}


    // public Point2D nearest(Point2D p)
    // {}


    public static void main(String[] args)
    {
        Point2D p0 = new Point2D(0.0, 0.0);
        Point2D p1 = new Point2D(1.0, 1.0);
        Point2D p2 = new Point2D(2.0, 2.0);
        Point2D p3 = new Point2D(3.0, 3.0);
        Point2D p4 = new Point2D(4.0, 4.0);
        Point2D p5 = new Point2D(5.0, 5.0);
        KdTree kt = new KdTree();
        kt.insert(p0);
        kt.insert(p2);
        System.out.println(kt.contains(p3));
        System.out.println(kt.contains(p2));
    }
}
