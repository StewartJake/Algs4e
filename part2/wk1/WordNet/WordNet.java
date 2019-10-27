import java.util.HashMap;
import java.util.Map;
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedCycle;
import edu.princeton.cs.algs4.In;

public class WordNet
{
    private Map<String, Bag<Integer>>   nouns   = new HashMap<>();
    private Map<Integer, String>        ids     = new HashMap<>();  
    private SAP                         wn;

    public WordNet(String synets, String hypernyms)
    {
        In synF = new In(synets);
        while (synF.hasNextLine())
        {
            String      line    = synF.readLine();
            String[]    fields  = line.split(",");
            String[]    words   = fields[1].split(" ");
            int         id      = Integer.parseInt(fields[0]);
            for (int i = 0; i < words.length; i++)
            {
                if (!nouns.containsKey(words[i]))
                {
                    Bag<Integer> b = new Bag<>();
                    nouns.put(words[i], b);
                }
                Bag<Integer> tempBag = nouns.get(words[i]);
                tempBag.add(id);
            }
            ids.put(id, fields[1]);
        }
        Digraph g = new Digraph(ids.size());

        In hypF = new In(hypernyms);
        while (hypF.hasNextLine())
        {
            String      line    =   hypF.readLine();
            String[]    fields  =   line.split(",");
            int         v       =   Integer.parseInt(fields[0]);
            int         w;
            for (int i = 1; i < fields.length; i++)
            {
                w = Integer.parseInt(fields[i]);
                g.addEdge(v, w);
            }
        }
        if (!isDAG(g))
            throw new IllegalArgumentException("The file does not represent a DAG");
        wn = new SAP(g);
    }

    public Iterable<String> nouns()
    {   return nouns.keySet();   }

    public boolean isNoun(String word)
    {
        if (word == null)   throw new IllegalArgumentException("Word is null");
        return nouns.containsKey(word);
    }

    public int distance(String nounA, String nounB)
    {
        // if (!isNoun(nounA) || !isNoun(nounB))
        //     throw new IllegalArgumentException("One of the arguments is invalid");
        nounInputCheck(nounA);
        nounInputCheck(nounB);
        Bag<Integer> a = nouns.get(nounA);
        Bag<Integer> b = nouns.get(nounB);
        return wn.length(a, b);
    }

    public String sap(String nounA, String nounB)
    {
        // if (!isNoun(nounA) || !isNoun(nounB))
        //     throw new IllegalArgumentException("One of the arguments is invalid");
        nounInputCheck(nounA);
        nounInputCheck(nounB);
        Bag<Integer> a = nouns.get(nounA);
        Bag<Integer> b = nouns.get(nounB);
        int i = wn.ancestor(a, b);
        return ids.get(i);
    }

    private void nounInputCheck(String word)
    {
        if(!isNoun(word))
            throw new IllegalArgumentException("One of the arguments is outside of the set");
    }

    private boolean isDAG(Digraph g)
    {
        // check for cycles
        DirectedCycle checkCycle = new DirectedCycle(g);
        if (checkCycle.hasCycle())  return false;
        
        // check roots
        int roots = 0;
        for (int i = 0; i < g.V(); i++)
            if (!g.adj(i).iterator().hasNext()) roots++;
        return roots == 1;
    }

    public static void main(String[] args)
    {
        String synset       = args[0];
        String hypernym     = args[1];
        // String nounA        = "stinker";
        // String nounB        = "thing";
        WordNet wn = new WordNet(synset, hypernym);
        // System.out.println(wn.distance(nounA, nounB));
    }
}
