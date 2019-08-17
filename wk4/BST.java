public class BST<Key extaneds Comparable<Key>, Value>
{
    private Node root;


    private class Node
    {
        private int count;
        private Key key;
        private Value val;
        private Node left, right;

        public Node(Key key, Value val)
        {
            this.key = key;
            this.value = val;
        }
    }


    public void put(Key key, Value val)
    {   root = put(root, key, val); }


    private Node put(Node x, Key key, Value val)
    {
        if (x == null)  return new Node(key, val);
        int cmp = key.compareTo(x.key);
        if      (cmp < 0)
            x.left  = put(x.left,   key, val);
        else if (cmp > 0)
            x.right = put(x.right,  key, val);
        else
            x.val = val;
        x.count = 1 + size(x.left) + size(x.right);
        return x;
    }

    
    public int size()
    {   return size(root);  }


    private int size(Node x)
    {
        if (x == null) return 0;
        return x.count;
    }

    
    public Value get(Key key)
    {
        Node x = root;
        while (x != null)
        {
            int cmp = key.compareTo(x.key);
            if      (cmp > 0)   x = x.right;
            else if (cmp < 0)   x = x.left;
            else                return x.val;
        }
        return null;
    }    


    public Key floor(key key)
    {
        Node x = floor(root, key);
        if (x == null)  return null;
        return x.key;
    }


    private Node floor(Node x, Key key)
    {
        if (x == null)  return null;
        int cmp = key.compareTo(x.key);
        if (cmp ==  0)  return x;
        if (cmp <   0)  return floor(x.left, key);

        Node t = floor(x.right, key);
        if (t != null)  return t;
        else            return x;
    }

 
   public int rank(Key key)
   {    return rank(key, root); }


   private int rank(Key key, Node x)
   {
     if (x == null) return 0;
      
    public void delete(Key key)
    {       }


    public Iterable<key> iterator()
    {       }

}
