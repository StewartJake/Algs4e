import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Dequeue<Item> implements Iterable<Item>
{
	private Node first, last;
	private int count = 0;

	public Dequeue(){}


	private class Node
	{
		Item item;
		Node next;
	}


	public boolean isEmpty()
	{
		return (last == null && first == null);
	}


	public int size()
	{	return count;	}

	
	private void checkFirst()
	{
		if (count == 0)
		{
			first = new Node();
			last = first;
		}
	}


	public void addFirst(Item item)
	{
		if (item == null)
			throw new IllegalArgumentException("You cannot add a null");
		checkFirst();
		if (count == 0)
			first.item = item;
		else
		{
			Node oldFirst = first;
			first = new Node();
			first.item = item;
			first.next = oldFirst;
		}
		count++;
	}


	public void addLast(Item item)
	{
		if (item == null)
			throw new IllegalArgumentException("You cannot add a null");
		checkFirst();
		if (count == 0)
			last.item = item;
		else
		{
			Node oldLast = last;
			last = new Node();
			last.item = item;
			last.next = null;
			oldLast.next = last;
		}
		count++;
	}


	public Item removeFirst()
	{
		if (isEmpty())
			throw new NoSuchElementException("The dequeue is empty");
		Item item = first.item;
		first = first.next;
		count--;
		return item;
	}


	public Item removeLast()
	{
		if (isEmpty())
			throw new NoSuchElementException("The dequeue is empty");
		Item item = last.item;
		Node x = first;
		while (x.next.next != null) {	x = x.next;	}
		x.next = null;
		last = x;
		count--;
		return item;
	}

	
	public Iterator<Item> iterator() 
	{
		return new DequeueIterator();
	}


	private class DequeueIterator implements Iterator<Item>
	{
		private Node iter;

		public DequeueIterator() {	iter = first;	}

		public boolean hasNext() {	return iter != null;	}
		public Item next()
		{
			Item item = iter.item;
			if (hasNext())	iter = iter.next;
			else		iter = first;
			return item;
		}
		public void remove()
		{
			throw new UnsupportedOperationException("The remove() method is not supported");
		}
	}
	public static void main(String[] args)
	{
		Dequeue<String> dq = new Dequeue<String>();
		Dequeue<String> dq2 = new Dequeue<String>();
		StdOut.println("true: " + dq.isEmpty());
		StdOut.println("0:    " + dq.size());
		dq.addFirst("test");
		StdOut.println("false: " + dq.isEmpty());
		StdOut.println("1:    " + dq.size());
		for (int i = 0; i < 10; i++)
			dq.addFirst("test" + i);
		for (int i = 10; i < 20; i++)
			dq.addFirst("test" + i);
		for (int i = 0; i < 10; i++)
			dq2.addLast("test" + i);
		for (int i = 10; i < 20; i++)
			dq2.addLast("test" + i);
		StdOut.println("21:    " + dq.size());
		StdOut.println("20:    " + dq2.size());
		String word1 = dq.removeFirst();
		String word2 = dq.removeLast();
		StdOut.println("test19:   " + word1);
		StdOut.println("test:   " + word2);
		String word3 = dq2.removeFirst();
		String word4 = dq2.removeLast();
		StdOut.println("test0:   " + word3);
		StdOut.println("test19:   " + word4);
		for (String s : dq2)
			StdOut.println(s);
	}
}

				

