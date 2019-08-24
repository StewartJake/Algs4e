import edu.princeton.cs.algs4.Point2D;

public class KdTree
{
    Node    root;

    public KdTree()
    {
        this.size = 0;
        this.root = null;
    }

    private class Node
    {
        private int     count;
        private boolean vertical = true;
        private double  key;
        private Point2D val;
        private Node    prev;
        private Node    lebo = null;   // left-bottom
        private Node    rito = null;   // right-top

        Node(Node previous, Point2D p)
        {
            if (previous == null)
                this.prev = null;
            else
            {
                this.prev = previous;
                if (previous.vertical)
                {
                    this.vertical = false;
                    this.key = p.x();
                }
                else
                    this.key = p.y();
            }
            this.val = p;
        }
    }

    public boolean isEmpty()
    {   return size == 0;   }


    public int size()
    {
        if (root == null)    return 0;
        return x.count;
    }


    public void insert(Point2D p)
    {
            
    }

    private Node put(Node query, Point2D p)
    {
        if (query == null) return new Node(null, p);
        if (query.vertical)
        {
            if      (query.val.x > p.val.x) (n.rito;
            else if (query.val.x < p.val.x) n.lebo;
            else                            n;
        }
        if (!n.vertical)
        {
            if      (query.val.y > n.val.y) return n.rito;
            else if (query.val.y < n.val.y) return n.lebo;
            else                            return n; 


    public boolean contains(Point2D p)
    {return false;}


    public void draw()
    {}


    // public Iterable<Point2D> range(RectHV rect)
    // {}


    // public Point2D nearest(Point2D p)
    // {}


    public static void main(String[] args)
    {}
}
