import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class MyStack<Item> 
{
	private Node first = null;

	private class Node
	{
		Item item;
		Node next;
	}

	public boolean isEmpty()
	{	return first == null;	}

	public Item pop()
	{
		Item item = first.item;
		first = first.next;
		return item;
	}

	public void push(Item nextWord)
	{
		Node oldFirst = first;
		first = new Node();
		first.item = nextWord;
		first.next = oldFirst;
	}

	public static void main(String[] args)
	{
		//MyStack stack = new MyStack();
		//while (!StdIn.isEmpty())
		//{
		//	String s = StdIn.readString();
		//	if (s.equals("-")) StdOut.print(stack.pop());
		//	else		   stack.push(s);
		//}
	}
}

