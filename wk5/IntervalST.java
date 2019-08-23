public class IntervalST<Key extends Comparable<Key>, Value>
{
    IntervalST()
    {}


    void put(Key lo, Key hi, Value val)
    {}


    Value get(Key lo, Key hi)
    {}


    void delete(Key lo, Key hi)
    {}
    

    Iterable<Value> intersects(Key lo, Key hi)
    {}


}

