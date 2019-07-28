public class ArrayStackOfStrings
{
	private String[] s = new String[1];
	private int n;

	public boolean isEmpty()
	{	return s[0] == null;	}


	public String pop()
	{	
		String item = s[--n];
		s[n] = null;
		return item;
	}


	public void push(String str)
	{	s[n++] = str;	}
}
