import java.util.*;
import java.awt.event.*;
import javax.swing.*;

public class Driver {

  public static void main( String args[] )
	{

		DirectoryCollectionReader aReader;
		FileDocument aDocument;
                int numberDocuments=0;
               
                aReader = new DirectoryCollectionReader("C:\\Users\\yo\\Desktop\\IR_Engine\\IR_Engine\\IRDocuments", "Document Collection", "C:\\");
                //aReader = new DirectoryCollectionReader("//Users//Stewart//NetBeansProjects//DocumentsB", "Document Collection", "//Users//Stewart");
		//aReader = new DirectoryCollectionReader("c:\\IRDocuments", "blah", "c:\\");
                //aReader = new DirectoryCollectionReader("H:\\NetBeansProjects\\CourseworkResources\\Documents", "Document Collection", "H:\\");
		while ( aReader.hasMoreDocuments() ) {
			
                  
                    aDocument = (FileDocument) aReader.nextDocument();
                        numberDocuments++;
			//System.out.println( aDocument.toString() );
		};
		
		Tokenizer t = new Tokenizer( ", .?\n\t!-()\"\';:_`!*&#" );
		Stopper s = new Stopper();
                TextAnalyzer ta;
                
               
               int selectedOption = JOptionPane.showConfirmDialog(null, 
                                  "Do you want to use Porter's stemming?", 
                                  "Choose", 
                                  JOptionPane.YES_NO_OPTION); 
                if(selectedOption == JOptionPane.YES_OPTION){
                    Stemmer st = new Stemmer();
                    st.setStemmingPerformed(true); 
                     ta= new TextAnalyzer( t, s, st ); 
                }
                else{
                     Stemmer st = new Stemmer();
                    st.setStemmingPerformed(false); 
                     ta= new TextAnalyzer( t, s, st ); 
                }

		MyInvertedFile myIV = new MyInvertedFile( );
		for(int i=0; i<numberDocuments; i++) {
			aDocument = (FileDocument) aReader.getDocument ( i );
			List l = ta.analyzeDocument( aDocument );
			myIV.addEntries( i, l );
			//System.out.println( aDocument.toString() );
			//System.out.println( l );
			
		};
		System.out.println( myIV );
		//Query aQuery = new Query( "dog cat" );
		Weighter aWeighter = new Weighter( myIV, ta, 1);
		SearchEngine search = new SearchEngine( aWeighter );
		//List results = search.queryIt ( aQuery );
		//System.out.println("Query Results " + results);
		IRInterface theIR= new IRInterface("Search Engine", 
				search, aReader);
		theIR.addWindowListener(
			new WindowAdapter() {
				public void windowClosing( WindowEvent e )
				{
					System.exit(0);
				}
			}
		);
//		aDocument = (FileDocument) aReader.getDocument ( 5 );
//		System.out.println( aDocument.toString() );
	}


}