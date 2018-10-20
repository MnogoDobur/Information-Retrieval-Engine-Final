
public class DocumentScore extends Object implements Comparable {

	private int id;
	private double score;

	public DocumentScore( int theId, double theScore ) {
		id= theId;
		score= theScore;
	}
	
	public int compareTo( Object ds1) {
		DocumentScore ds = (DocumentScore) ds1;
		if ( score < ds.score() )
			return -1;
		if (score > ds.score() )
			return 1;
		if ( id == ds.id() )
			return 0;
		else if ( id > ds.id() )
			return 1;
		else
			return -1;
	}

	public int id() { return id; }

	public double score() { return score; }
	
	public String toString() { return "( " + id + "/" + score + " )"; }
}