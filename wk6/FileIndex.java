public class FileIndex
{
    public static void main(String[] args)
    {
        ST<String, SET<File>> st = new ST<String, SET<Files>>();

        for (String filename : args) {
            File file = new File(filename);
            In in = new File(filename);
            while (!in.isEmpty())
            {
                String key = in.readString();
                if (!st.contians(key))
                    st.put(key, new SET<File>());
                SET<File> set = st.get(key);
                set.add(file);
            }
        }

        while (!StdIn.isEmpty())
        {
            String query = StdIn.readString();
            StdOut.println(st.get(query));
        }
    }
}

