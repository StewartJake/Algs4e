public class SET<Key extends Comparable<Key>>
{
    // generic SET API layout
    SET()
    void add(Key Key)
    boolean contains(Key key)
    void remove(Key key)
    int size()
    Iterator<Key> iterator()
}
