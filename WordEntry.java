
public interface WordEntry {
	public abstract int count();
	public abstract String word();
	public abstract PostingsIterator postingsIterator();
	public abstract void addPosting (Posting thePosting);
}
