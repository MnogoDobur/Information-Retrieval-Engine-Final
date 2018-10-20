import java.util.*;

public class MyWordEntryTest {

	public static void main ( String args[] ) {
	
		MyInvertedFile inv = new MyInvertedFile ();
		
		String doc0[] = { "cat", "dog", "mat", "cat", "white" };
		String doc1[] = { "mat" , "cat" };
		String doc2[] = { "white" , "white"};
		
		inv.addEntries( 0, Arrays.asList( doc0 ) );
		inv.addEntries( 1, Arrays.asList( doc1 ) );
		inv.addEntries( 2, Arrays.asList( doc2 ) );
		
		/*
		inv.addPosting(new MyWord ("cat", 0, 2) );
		inv.addPosting(new MyWord ("dog", 0, 1) );
		inv.addPosting(new MyWord ("mat", 0, 1) );
		inv.addPosting(new MyWord ("white", 0, 1) );
		inv.addPosting(new MyWord ("cat", 1, 1) );			
		inv.addPosting(new MyWord ("mat", 1, 1) );
		inv.addPosting(new MyWord ("white", 2, 2) );
		*/
	
		
		//System.out.print(inv);
		System.exit(0);
		
	}
}
