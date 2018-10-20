import java.io.*;
import java.util.Arrays;

public final class DirectoryCollectionReader extends CollectionReader {

	private int count, cfiles;
	private String filenames [], files [];
	private File theSourceDirectory, theDestinationDirectory, currentFile;
	private String theDatabase;

	public DirectoryCollectionReader( String theSource, String theDB, String theDest)
	{
		theSourceDirectory= new File ( theSource );
		theDatabase= theDB;
		theDestinationDirectory= new File ( theDest );
		if ( theSourceDirectory.exists() && theSourceDirectory.isDirectory()
			 &&
			 theDestinationDirectory.exists() && theDestinationDirectory.isDirectory()
			 && ! (new File ( theDestinationDirectory, theDB ).exists() ) ) {

			filenames= theSourceDirectory.list();
                        Arrays.sort(filenames);
			files= new String [ filenames.length ];
			cfiles= 0;
			count= 0;
		}
		else {
			System.out.println( " Source, DBname, or Destination problem" );
			System.exit (0);
		}
	}

//	public DirectoryCollectionReader( String theDB, String theDest)
//	{
//	}

	// Tests if more documents to read
	public boolean hasMoreDocuments() {

		while ( cfiles < filenames.length ) {
			currentFile = new File ( theSourceDirectory, filenames[cfiles] );
			if ( currentFile.exists() && currentFile.isFile() ) 
				return true;
			cfiles++;
		}

		return false;
	}

	// Fetches next document (assumes more to fetch)
	public IRDocument nextDocument() {
		FileDocument theFileDocument = new FileDocument( currentFile, count );
		files [ count ] = filenames[ cfiles ];
		count++; cfiles++;
		return theFileDocument;	
	}

	// Fetches document with given id
	public IRDocument getDocument (int id) {
		if ( id >= 0 && id < count) {
			currentFile = new File ( theSourceDirectory, files[id] );
			if ( currentFile.exists() && currentFile.isFile() ) 
				return new FileDocument( currentFile, id );
		}
		return (FileDocument) null;
	}

}