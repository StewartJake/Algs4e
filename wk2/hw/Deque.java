import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item>
{
    private Node first, last;
    private int count;

	public Deque()
    {
        count = 0;
    }

	private class Node
	{
		Item item;
		Node next;
        Node prev;
	}

	public boolean isEmpty()
	{
		// return (last == null && first == null);
        return (count == 0);
	}

	public int size()
	{	return count;	}
	
	public void addFirst(Item item)
	{
		if (item == null)
			throw new IllegalArgumentException("You cannot add a null");
		if (isEmpty())
        {
            first = new Node();
			first.item = item;
            last = first;
        }
		else
		{
			Node oldFirst = first;
			first = new Node();
			first.item = item;
			first.next = oldFirst;
            oldFirst.prev = first;
		}
		count++;
	}

	public void addLast(Item item)
	{
		if (item == null)
			throw new IllegalArgumentException("You cannot add a null");
		if (isEmpty())
        {
            last = new Node();
			last.item = item;
            first = last;
        }
		else
		{
			Node oldLast = last;
			last = new Node();
			last.item = item;
			last.next = null;
			oldLast.next = last;
            last.prev = oldLast;
		}
		count++;
	}

	public Item removeFirst()
	{
		if (isEmpty())
			throw new NoSuchElementException("The dequeue is empty");
		Item item = first.item;
        if (size() > 1) 
        {
            Node oldFirst = first;
            first = first.next;
            first.prev = null;
            oldFirst.next = null;
        }
        else
        {
            first = null;
            last = null;
        }
        count--;
		return item;
	}
	
    public Item removeLast()
	{
		if (isEmpty())
			throw new NoSuchElementException("The dequeue is empty");
        Item item = last.item;
        if (size() > 1)
        {
            Node oldLast = last;
            last = last.prev;
            last.next = null;
            oldLast.prev = null;
        }
        else
        {
            last = null;
            first = null;
        }
		count--;
		return item;
	}
	
	public Iterator<Item> iterator() 
	{
		return new DequeIterator();
	}


	private class DequeIterator implements Iterator<Item>
	{
		private Node iter;

		public DequeIterator() 
        {	
            if (first == null)  iter = last;
            else                iter = first;	
        }

		public boolean hasNext() {	return iter != null;	}
		public Item next()
		{
            if (!hasNext())    
                throw new NoSuchElementException("There is no next.");
			Item item = iter.item;
			if (hasNext())	iter = iter.next;
			return item;
		}

		public void remove()
		{
			throw new UnsupportedOperationException("The remove() method is not supported");
		}
	}
	public static void main(String[] args)
	{
		// Deque<String> dq = new Deque<String>();
		// Deque<String> dq2 = new Deque<String>();
		// StdOut.println("true: " + dq.isEmpty());
		// StdOut.println("0:    " + dq.size());
		// dq.addFirst("test");
		// StdOut.println("false: " + dq.isEmpty());
		// StdOut.println("1:    " + dq.size());
		// for (int i = 0; i < 10; i++)
		// 	dq.addFirst("test" + i);
		// for (int i = 10; i < 20; i++)
		// 	dq.addFirst("test" + i);
		// for (int i = 0; i < 10; i++)
		// 	dq2.addLast("test" + i);
		// for (int i = 10; i < 20; i++)
		// 	dq2.addLast("test" + i);
		// StdOut.println("21:    " + dq.size());
		// StdOut.println("20:    " + dq2.size());
		// String word1 = dq.removeFirst();
		// String word2 = dq.removeLast();
		// StdOut.println("test19:   " + word1);
		// StdOut.println("test:   " + word2);
		// String word3 = dq2.removeFirst();
		// String word4 = dq2.removeLast();
		// StdOut.println("test0:   " + word3);
		// StdOut.println("test19:   " + word4);
		// for (String s : dq2)
		// 	StdOut.println(s);
        Deque<Integer> deque = new Deque<Integer>();
        deque.addLast(1);
        StdOut.println(deque.iterator());
	}
}

				

