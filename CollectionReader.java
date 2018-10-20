public abstract class CollectionReader {

	// Tests if more documents to read
	public boolean hasMoreDocuments() { return false; }

	// Fetches next document (assumes more to fetch)
	public IRDocument nextDocument() {
		return new IRDocument(1);	}

	// Fetches document with given id
	public IRDocument getDocument (int Id) {
		return new IRDocument(Id);	}
		
		
}