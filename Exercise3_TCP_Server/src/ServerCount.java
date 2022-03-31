
public class ServerCount {
	public String Counter(String textReceive)
	{
		// differentiate when to count as a word
		int words = textReceive.split("\\s+|,\\s*|\\.\\s*").length;	
		// count the word
		String result = Integer.toString(words);
		return result;
	}
}
