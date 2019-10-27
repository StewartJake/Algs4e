public class Outcast
{
    private WordNet wn;

    public Outcast(WordNet wordnet)
    {
        this.wn = wordnet;
    }

    public String outcast(String[] nouns)
    {
        int     maxDist = Integer.MIN_VALUE;
        int     sum;
        String  outcast = null;
        for (int i = 0; i < nouns.length; i++)
        {
            sum = 0;
            for (int j = 0; j < nouns.length; j++)
                sum += wn.distance(nouns[i], nouns[j]);
            if (sum > maxDist)
            {
                maxDist = sum;
                outcast = nouns[i];
            }
        }
        return outcast;
    }

    public static void main(String[] args)
    { /* no unit tests atm */   }

}
