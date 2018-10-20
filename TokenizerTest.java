
public class TokenizerTest {

  public static void main( String args[] )
	{
		Tokenizer t = new Tokenizer( ",.?\n\t" );
		t.setString( "tokenize, or not, that is the question? blah-blah phew" );
		while ( t.hasToken() ) {
			//System.out.println( t.nextToken() );
		}
	}


}