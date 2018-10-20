import java.util.*;

public class MyInvertedFile extends Object implements InvertedFile {

	private HashMap invertedFileMap;
	private int theSize;
	private int theTotalFreq;
	private int nDocs;
        private int numberOfPostings;
        private int[] maxtf;
	
	private String currentToken, thisToken;
	private int freq;
	
	public MyInvertedFile( ) {
		invertedFileMap = new HashMap();
		theSize= 0;
		theTotalFreq= 0;
		nDocs= 0;
	}
	
        public int getMaxtf(int i){
            return maxtf[i];
        }
        
	public void addPosting (Word theWord) {

		WordEntry theLookUp = lookupEntry ( theWord.wordOf() );
		if (theLookUp != null) {
		}
		else {
			theLookUp = new MyWordEntry ( theWord.wordOf() );
			addEntry ( theLookUp );
			theSize++;
		};
		MyWord theMyWord = (MyWord) theWord;
                numberOfPostings++;
		theLookUp.addPosting ( 
			new MyPosting ( theMyWord.id(), theMyWord.freq() ) );
		theTotalFreq= theTotalFreq + theMyWord.freq();
	}
	
	public WordEntry lookupEntry (String theStringWord) {
	
		if (invertedFileMap.containsKey ( theStringWord ) )
			return (WordEntry) invertedFileMap.get ( theStringWord );
		else
			return null;
	}
	
	public InvertedFileIterator invertedFileIterator() {
		return null;
	}
	
	void addEntry (WordEntry theWordEntry) {
	
		invertedFileMap.put( theWordEntry.word() , theWordEntry );
		
	}
	
	void addEntries ( int id, List theList) {
		
		nDocs++;
		Collections.sort( theList );
		Iterator iter = theList.iterator();
		if ( iter.hasNext() ) {
			currentToken= (String) iter.next();
			freq= 1;
		};
		while ( iter.hasNext() ) {
			thisToken= (String) iter.next();
			if ( currentToken.equals( thisToken ) ) {
				freq++;
			}
			else {
				addPosting ( new MyWord( currentToken, id, freq ) );
				currentToken= thisToken;
				freq= 1;
			}
		};
		addPosting ( new MyWord( currentToken, id, freq ) );
	}
	//////
	public String toString() {
            String theString = "Inverted File\n";
               Iterator aMapIterator = invertedFileMap.values().iterator();

		
		while ( aMapIterator.hasNext() )
			theString += (WordEntry) aMapIterator.next(); 

                
		theString += "Total docs : " + nDocs + "\n" + "\n";
                theString += "\n" + "Inverted File Statistics" +"\n";
                theString += "Number of documents = "+ nDocs + "\n";
                theString += "Total Number of index terms extracted from documents = " + theTotalFreq+"\n";
		theString += "Number of Posting Lists= " +theSize+"\n";
                theString += "Total number of postings= " +numberOfPostings+"\n";
                theString += "Average number of postings per posting list= " + (double)numberOfPostings/theSize+"\n";
                return theString;
	}
	
	public int nDocs() { return nDocs; }
	
}