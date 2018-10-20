import java.util.*;

public class SearchEngine {

	private Weighter weighter;

	public SearchEngine 
		( Weighter theWeighter ) {
		 weighter= theWeighter;
	}

	public List queryIt ( Query theQuery ) {
		return weighter.scoreIt (theQuery );
	}
		 

}