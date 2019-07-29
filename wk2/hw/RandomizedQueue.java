import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;


public class RandomizedQueue<Item> implements Iterable<Item>
{
	private Item[] a= (Item[]) new Object[1];
	private int head = 0;
	private int tail = 1;
	private int size = 0;

	public RandomizedQueue(){}

	public boolean isEmpty()
	{	return (a[head] == null);	}

	public int size()
	{	return size;	}	

	private void resize(int capacity)
	{
		Item[] copy = (Item[]) new Object[capacity];
		int copyCount = 0;
		for (int i = head; i < tail; i++)
			if (a[i] != null)
				copy[copyCount++] = a[i];
		head = 0;
		tail = copyCount;
		a = copy;
	}

	public void enqueue(Item item)
	{
		if (item == null)
			throw new IllegalArgumentException(
					"Cannot add null");
		if (tail == size) resize(size*2);
		else a[head++] = item;
		size++;
	}
	
	private Item randomEntry(boolean remove)
	{
		if (isEmpty())
			throw new NoSuchElementException(
					"This queue is empty.");
		int n = StdRandom.uniform(size);
		Item item = a[n];
		if (remove)
		{
			a[n] = null;
			size--;
		}
		return item;
	}
		
	public Item dequeue() {	return randomEntry(true);	}

	public Item sample() {	return randomEntry(false);	}

	public Iterator<Item> iterator()
	{	return new RandomIterator();	}

	private class RandomIterator implements Iterator<Item>
	{
		private int[] visited = new int[size];
		private int count = 0;

		public RandomIterator() {}
		
		public boolean hasNext()
		{	return count != size;	}

		public Item next()
		{
			if (!hasNext())
				throw new NoSuchElementException(
						"There is no next entry.");
			Item entry = null;
			while (hasNext() && entry != null)
			{
				int n = StdRandom.uniform(size);
				entry = a[n];
				visited[count++] = n;
			}
			return entry;
		}

		public void remove()
		{
			throw new UnsupportedOperationException(
				"The remove() method is not supported");
		}
	}

	public static void main(String[] args){}
}
