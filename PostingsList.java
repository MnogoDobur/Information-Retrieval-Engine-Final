
public interface PostingsList {
	public abstract void addPosting(Posting thePosting);
	public abstract PostingsIterator postingsIterator();
}