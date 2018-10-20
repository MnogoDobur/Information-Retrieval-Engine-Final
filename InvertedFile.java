public interface InvertedFile {
	public abstract void addPosting (Word theWord);
	public abstract WordEntry lookupEntry (String theStringWord);
	public abstract InvertedFileIterator invertedFileIterator();
	public abstract int nDocs();
}
