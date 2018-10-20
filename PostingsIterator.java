
public interface PostingsIterator {
	public abstract boolean morePostings();
	public abstract Posting getPosting();
}