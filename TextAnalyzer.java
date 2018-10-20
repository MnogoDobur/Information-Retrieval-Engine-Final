import java.util.*;

public class TextAnalyzer {

	private Tokenizer theTokenizer;
	private Stopper theStopper;
	private Stemmer theStemmer;

	public TextAnalyzer(Tokenizer t, Stopper s, Stemmer st) {

		theTokenizer= t;
		theStopper= s;
		theStemmer= st;

		theStopper.setUpstream( theTokenizer );
		theStemmer.setUpstream( theStopper );

	}
        ///
        public TextAnalyzer(Tokenizer t, Stopper s) {

		theTokenizer= t;
		theStopper= s;

		theStopper.setUpstream( theTokenizer );

	}
        /////
        public void setSpecialQueryProcessing(boolean f) {
            theTokenizer.setSpecialQueryProcessing(f);
        }

	public List analyzeDocument ( IRDocument theDocument) {

		String theToken;
                theTokenizer.setString ( theDocument.text() );
		ArrayList theList = new ArrayList();
		while ( theStemmer.hasToken() ) {
                        theToken= theStemmer.nextToken();
                        if (theToken != null)
                            theList.add( theStemmer.nextToken() );
		};
		return theList;
	}

}