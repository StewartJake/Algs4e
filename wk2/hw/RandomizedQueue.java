import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;


public class RandomizedQueue<Item> implements Iterable<Item>
{
	private Node first, last;
	private int size = 0;

	private class Node
	{
		Item item;
		Node next;
	}

	public RandomizedQueue(){}


	public boolean isEmpty()
	{	return (first == null);	}


	public int size()
	{	return size;	}


	public void enqueue(Item item)
	{
		oldLast = last;
		last = new Node();
		last.item = item;
		last.next = null;
		if (isEmpty()) first = last;
		else oldLast.next = last;
	}

	public Item dequeue()
	{
		return;
	}	
