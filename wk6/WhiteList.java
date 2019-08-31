public class WhiteList
{
    public static void main(String[] args)
    {
        SET<String> set = new SET<String>();
        In in = new IN(args[0]);
        while (!in.isEmpty())
            set.add(in.readString());


        while (!StdIn.isEmpty())
        {
            String word = StdIn.readString();
            if (set.contains(word))
                STDOut.println(word);
        }
    }
}

