import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public static void main(String[] args)
{
	StackofStrings stack = new StackOfStrings();
	while (!StdIn.isEmpty())
	{
		String s = StdIn.readString();
		if (s.equals("-")) StdOut.print(stack.pop());
		else		   stack.push(s);
	}
}
