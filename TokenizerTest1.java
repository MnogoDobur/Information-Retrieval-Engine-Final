
public class TokenizerTest1 {

  public static void main( String args[] )
	{
		Tokenizer t = new Tokenizer( ", .?\n\t!" );
		Stopper s = new Stopper();
		Stemmer st = new Stemmer();
		t.setString( "tokenize cats, or not, that dog and boys and is the the question? blah-blah a phew" );
		s.setUpstream( t );
		st.setUpstream( s );
		while ( st.hasToken() ) {
			//System.out.println( st.nextToken() );
		}
	}

}