import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;


public class RandomizedQueue<Item> implements Iterable<Item>
{
	private Item[] a= (Item[]) new Object[1];
	private int tail = 0;

	public RandomizedQueue(){}

	public boolean isEmpty()
	{	return (tail == 0);	}

	public int size()
	{	return tail;	}	

	private void resize(int capacity)
	{
		Item[] copy = (Item[]) new Object[capacity];
		int copyCount = 0;
		for (int i = 0; i < a.length; i++)
			if (a[i] != null)
				copy[copyCount++] = a[i];
		tail = copyCount;
		a = copy;
	}

	public void enqueue(Item item)
	{
		if (item == null)
			throw new IllegalArgumentException(
					"Cannot add null");
		if (tail == a.length) resize(a.length*2);
		a[tail++] = item;
	}
	
	private Item randomEntry(boolean remove)
	{
		if (isEmpty())
			throw new NoSuchElementException(
					"This queue is empty.");
		int n = StdRandom.uniform(tail);
		Item item = a[n];
		if (remove) 
		{
			if (tail == a.length/4) 
				resize(a.length/2);
			a[n] = a[tail-1];	// move tail here
			a[tail--] = null;	// make tail null instead
		}
		return item;
	}
		
	public Item dequeue() {	return randomEntry(true);	}

	public Item sample() {	return randomEntry(false);	}

	public Iterator<Item> iterator()
	{	return new RandomIterator();	}

	private class RandomIterator implements Iterator<Item>
	{
		private Item[] copy = (Item[]) new Object[tail];
		private int iter = 0;
		private int visited = 0;

		public RandomIterator() {}
		
		public boolean hasNext()
		{	return visited == tail;	}

		public Item next()
		{
			if (!hasNext())
				throw new NoSuchElementException(						"There is no next entry.");
			if (hasNext())
			{
				iter = StdRandom.uniform(tail);
				visited++;
			}
			Item item = a[iter];
			return item;
		}

		public void remove()
		{
			throw new UnsupportedOperationException(
				"The remove() method is not supported");
		}
	}

	public static void main(String[] args)
	{
		RandomizedQueue<String> rq = new RandomizedQueue<String>();

		StdOut.println("true:           " + rq.isEmpty());
		StdOut.println("0:              " + rq.size());
		rq.enqueue("test");
		StdOut.println("false:          " + rq.isEmpty());
		StdOut.println("1:              " + rq.size());
		for (int i= 0; i < 20; i++)
			rq.enqueue("test" + i);
		StdOut.println("21:             " + rq.size());
		String randEntry = rq.dequeue();
		StdOut.println("20:             " + rq.size());
		StdOut.println(randEntry);
		StdOut.println("20:             " + rq.size());
		StdOut.println(rq.sample());
		StdOut.println("20:             " + rq.size());
		for (String s : rq)
			StdOut.println(s);
	}
}
