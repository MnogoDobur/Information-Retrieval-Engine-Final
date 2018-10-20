import java.util.*;

public class MyWordEntry extends Object implements WordEntry {

	private int theCount;
	private int theFreq;
	private MyPostingsList thePostings;
	private String theWord;

	public MyWordEntry ( String aWord) {
		theCount= 0;
		theFreq= 0;
		theWord= aWord;
		thePostings= new MyPostingsList();
	}

	public int count() { return theCount; }

	public int totFreq() { return theFreq; }

	public String word() { return theWord; }

	public PostingsIterator postingsIterator() {
		return thePostings.postingsIterator();
	}

	public List getPostings() { return thePostings.listOf(); }

	public void addPosting (Posting thePosting) {
		thePostings.addPosting( thePosting );
		theCount++;
		MyPosting theMyPosting= (MyPosting) thePosting;
		theFreq += theMyPosting.freq();
	}
			
	public String toString() {
		String aString = "";
		aString += theWord + " (df/" + theFreq + ") => "
				 + thePostings.toString() + "\n";
		return aString;
	}

}
