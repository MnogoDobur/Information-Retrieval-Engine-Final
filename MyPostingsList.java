import java.util.*;

public class MyPostingsList extends Object implements PostingsList {

	public ArrayList theList;
	
	public MyPostingsList () {
		theList = new ArrayList(1);
	}
	
	public void addPosting(Posting thePosting) {
		theList.add( thePosting );
	}
	
	public PostingsIterator postingsIterator() {
		return new MyPostingsIterator ( this );
	}
	
	public String toString() {
	
		String aString = "[";
		MyPostingsIterator mypi = (MyPostingsIterator) postingsIterator();
		while ( mypi.morePostings() ) {
			aString += (MyPosting) mypi.getPosting();
			if (mypi.morePostings() ) 
				aString += "  ";
		}
		aString += "]";
		return aString;
	}
	
	public List listOf() { return theList; }
}