
public class Query extends IRDocument {

	private String theText;

	public Query( String queryText ) {
		super ( 1 );
		theText= queryText;
	}

	public String title() { return "query"; }

	public String text() { return theText; }
}