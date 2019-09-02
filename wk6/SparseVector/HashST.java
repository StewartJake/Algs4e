public class HashST<Key, Value>
{
    private int     m       = 30001;
    private Key[]   keys    = (Key[])   new Object[m];
    private Value[] vals    = (Value[]) new Object[m];

    public HashST() {}


    private int hash(Key key)
    {   return (key.hashCode() & 0x7fffffff) % m;   }

    
    public void put(Key key, Value val)
    {
        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % m)
            if (keys[i].equals(key))
                break;
        keys[i] = key;
        vals[i] = val;
    }


    public Value get(Key key)
    {
        for (int i = hash(key) ; keys[i] != null; i = (i + 1) % m)
            if (keys[i].equals(key))
                return vals[i];
        return null;
    }
    

    public boolean contains(Key key)
    {   return get(key) != null;    }

    // Edit later
    
    public Key[] keys()
    {
        Key[] copy = (Key[]) new Object[m];
        for (int i = 0; i < m; i++)
        {
            if (keys[i] == null)    break;
            else                    copy[i] = keys[i];
        }
        return copy;
    }


    public static void main(String[] args)
    {
        HashST<Integer, Double> v = new HashST<Integer, Double>();
        v.put(110, 9.9);
        v.put(293, 5.5);
        v.put(429, 6.3);
        v.put(532, 3.4);
        System.out.println(v.get(429));

    }

}
