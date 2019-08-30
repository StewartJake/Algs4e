public class SeparateChainingHashST<Key, Value>
{
    private int M = 97;
    private Node[] st = new Node[M];

    private static class Node
    {
        private Object  key;
        private Object  val;
        private Node    next;
    }

    public Value get(Key key)
    {
        int i = hash(key);
        for (Node x = st[i]; x != null; x = x.next)
            if (key.equals(x.key)) return (Value) x.val;
        return null;
    }
}
