
public class Stopper implements TokenChainMember {

	private Tokenizer theTokenizer;
	private String s;
	private String stops[] = { "and", "the", "a", "of", "on", "in", "to", "with" };

	public void setUpstream(TokenChainMember tcm)
	{
		theTokenizer = (Tokenizer) tcm;
	}

	public boolean hasToken( )
	{
		while ( theTokenizer.hasToken() ) {
			s= theTokenizer.nextToken();
			if ( !stopword() ) return true;
		}
		return false;
	}

	public String nextToken()
	{
		return s;	
	}
	
	private boolean stopword()
	{
		for(int i=0; i<stops.length; i++) {
			if (s.equals( stops[i] ) )
				return true;
		}
		return false;
	}
}