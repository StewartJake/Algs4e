import StdIn;

public class main(String args[])
{
    MinPQ<Transaction> pq = new MinPq<transaction>();

    while (StdIn.hasNextLine())
    {
        String line = StdIn.readLine();
        Transaction item = new Transaction(line);
        pq.insert(item);
        if (pq.size()) > M)
            pq.delMin();
    }
}
