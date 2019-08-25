import java.util.LinkedList;
import java.util.Queue;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

public class KdTree
{
    int     size;
    Node    root;
    Queue<Node> pQ = new LinkedList<Node>();      //debugging

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
        root = put(root, p, root);
    }


    private Node put(Node x, Point2D p, Node prev)
    {
        if (x == null)
        {
            size++;
            Node n = new Node(p, prev);
            pQ.add(n);
            return n; //new Node(p, prev);
        }
        if (p.equals(x.val))    x.val = p;
        if (x.vertical)
        {
            if (p.x() > x.key)  x.rito = put(x.rito, p, x);
            if (p.x() < x.key)  x.lebo = put(x.lebo, p, x);
        }
        if (!x.vertical)
        {
            if (p.y() > x.key)  x.rito = put(x.rito, p, x);
            if (p.y() < x.key)  x.lebo = put(x.lebo, p, x);
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
    {
    }


    public Iterable<Point2D> range(RectHV rect)
    {
        Queue<Point2D> rQ = new LinkedList<Point2D>();
        contains(root, rect, rQ);
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

    private void contains(Node x, RectHV rect, Queue<Point2D> rQ)
    {
        if (x != null)
        {
            if (rect.contains(x.val))   rQ.add(x.val);
            if      (x.vertical)
            {
                if      (rect.xmax() < x.val.x())   contains(x.lebo, rect, rQ);
                else if (rect.xmax() > x.val.x())   contains(x.rito, rect, rQ);
                else if (rect.xmin() <=  x.val.x() && rect.xmax() >= x.val.x())
                {
                    contains(x.lebo, rect, rQ);
                    contains(x.rito, rect, rQ);
                }
            }
            else if (!x.vertical)
            {
                if      (rect.ymax() < x.val.y())   contains(x.lebo, rect, rQ);
                else if (rect.ymax() > x.val.y())   contains(x.rito, rect, rQ);
                else if (rect.ymin() < x.val.y() && rect.ymax() > x.val.y())
                {
                    contains(x.lebo, rect, rQ);
                    contains(x.rito, rect, rQ);
                }
            }
        }
    }



    public Point2D nearest(Point2D p)
    {
        Point2D champ = root.val;
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
        Point2D p0  = new Point2D(0.0, 0.0);
        Point2D p1  = new Point2D(1.0, 1.0);
        Point2D p2  = new Point2D(2.0, 2.0);
        Point2D p3  = new Point2D(3.0, 3.0);
        Point2D p4  = new Point2D(4.0, 4.0);
        Point2D p5  = new Point2D(5.0, 5.0);
        KdTree kt   = new KdTree();
        RectHV rect = new RectHV(-1.0, -1.0, 1.0, 1.0); 
        kt.insert(p5);
        kt.insert(p2);
        kt.insert(p4);
        kt.insert(p3);
        //kt.insert(p0);
        kt.insert(p1);
        for (Node n : kt.pQ)
            System.out.println(n);
        System.out.println(kt.contains(p3));
        System.out.println(kt.contains(p2));
        System.out.println(kt.range(rect));
        System.out.println(kt.nearest(p0));
    }
}
