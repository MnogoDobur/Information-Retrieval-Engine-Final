public class IRDocument {
	protected int ident;
	
	public IRDocument(int anId) {
		ident= anId;
	}

	public String title() { return "title " + ident; }

	public String text() { return title() + "\n" + "text body"; }

	public int id() { return ident; }

	public String toString() { return text(); }

}