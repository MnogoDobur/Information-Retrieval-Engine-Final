import java.util.*;

public class MyPostingsIterator extends Object implements PostingsIterator {

	ListIterator theIterator;
	
	public MyPostingsIterator( MyPostingsList tpl) {
		theIterator = tpl.theList.listIterator();
	}
	
	public boolean morePostings() {
		return (theIterator.hasNext());
	}
	
	public Posting getPosting() {
		return (Posting) theIterator.next();
	}
}
