import java.io.*;
import javax.swing.*;
import javax.swing.text.*;


public final class FileDocument extends IRDocument {
	private File theFile;
	private JEditorPane pane;
	private String s;
        private String[] res;
	
	public FileDocument (File aFile, int anId )
	{
		super ( anId );
		theFile= aFile;
                GenericFileReader theReader = new GenericFileReader(aFile.toString());
                res= theReader.getSGMLDocumentContents();
	}
        
        public String title() {
            return res[0];
        }
        
        public String text() {
            return res[1];
        }

	/* public String title() { 
            String t="no title";
            try {
                    pane= new JEditorPane( "file:\\\\\\" + theFile );
		}
		catch (IOException io ) {
			System.out.println( " URL file error");
			System.exit (0);
		}
		
		Document d = pane.getDocument();
		try {
			s= d.getText( 0, d.getLength() );
		}
		catch (BadLocationException ble) {
			System.out.println( " Bad Loc error");
			System.exit (0);
		}
                int left=0;
                int right;
                for (int i=0; i<3;i++) {
                    right=s.indexOf('\n',left);
                    if ( right != -1)
                        t=s.substring(left,right);
                    left=right+1;
                }
		return t;	
            }

	public String text() {
	
		try {
			pane= new JEditorPane( "file:\\\\\\" + theFile );
		}
		catch (IOException io ) {
			System.out.println( " URL file error");
			System.exit (0);
		}
		
		Document d = pane.getDocument();
		try {
			s= d.getText( 0, d.getLength() );
		}
		catch (BadLocationException ble) {
			System.out.println( " Bad Loc error");
			System.exit (0);
		}
		return s;				
	} */
	
	public int id() { return ident; }

	public String toString() { return text(); }
	
	public String file() { return "file:\\\\\\" + theFile; }

}