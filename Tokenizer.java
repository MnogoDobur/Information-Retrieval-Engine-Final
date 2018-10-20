import java.util.*;

public class Tokenizer implements TokenChainMember {

	private String theDelims;
	private StringTokenizer st;
	private boolean hasNextToken;
	private String theNextToken;
        private boolean special=false;

	public Tokenizer( String delims )
	{
		theDelims= delims;
	}

	public void setString ( String theString )
	{
		st= new StringTokenizer( theString, theDelims, true );
		theNextToken= nextInternalToken();
	}

	public void setUpstream(TokenChainMember tcm)
	{
	}

	public boolean hasToken( )
	{
		return hasNextToken; 
	}

	public String nextToken()
	{
		String retToken= theNextToken;
		theNextToken = nextInternalToken();
		return retToken;	
	}
        
        public void setSpecialQueryProcessing(boolean f) { special= f; }
	
	String nextInternalToken() {

		hasNextToken= false;
		while ( !hasNextToken && ( st.hasMoreTokens() ) ) {
			theNextToken= st.nextToken();
			if ( theNextToken.length() == 1 && theDelims.indexOf( theNextToken ) != -1) {
                            if (special) {
                                /* if (theNextToken.compareTo("+") == 0 ) {
                                    theNextToken= "+++++";
                                    hasNextToken=true;
                                } else */
                                if (theNextToken.compareTo("$") == 0 ) {
                                    theNextToken= "$" + st.nextToken();
                                    hasNextToken=true;
                                }
                            }
			}
			else {
				theNextToken= theNextToken.toLowerCase();
				hasNextToken= true;
			}
		};
		if ( hasNextToken ) {
			return theNextToken;
		}
		else
			return null;
	}
}