
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.event.*;
import java.io.*;

public class OldIRInterface extends JFrame {

	private QueryInterface qi;
	private DocumentDisplayer dd;

	public OldIRInterface( String title, SearchEngine se, CollectionReader cr)
	{
		super( title );

		final JDesktopPane theDesktop = new JDesktopPane();
		getContentPane().add( theDesktop );

		qi= new QueryInterface("Querying", se, cr );
		dd= new DocumentDisplayer("Display Document");
		qi.setDocumentDisplayer( dd );

		theDesktop.add( qi );
		theDesktop.add( dd );
		setSize(600, 500);
		show();	
	}	
}

class QueryInterface extends JInternalFrame {

	private SearchEngine theSearchEngine;
	private DirectoryCollectionReader theReader;
	private DocumentDisplayer theDisplayer;
	
	private JTextField queryText;
	private JButton goButton;
	private JList theJList;
	private String theQuery;
	
	private String  titles[];
	private FileDocument  docs[];

	public QueryInterface( String title, SearchEngine se,
						   CollectionReader cr)
	{

		super( title, true, true, true, true);

		theSearchEngine= se;
		theReader= (DirectoryCollectionReader) cr;
		
		Container c= getContentPane();
		c.setLayout ( new FlowLayout() );
		
		queryText= new JTextField( "Enter query here");
		c.add( queryText );
		theQuery= null;
	
		goButton= new JButton( "GO" );
		c.add( goButton );
		
		theJList= new JList();
		theJList.setVisibleRowCount( 10 );
		theJList.setSelectionMode(
			ListSelectionModel.SINGLE_SELECTION );
		c.add( new JScrollPane ( theJList ) );
		
		TextFieldHandler handlerText = new TextFieldHandler();
		queryText.addActionListener( handlerText );
		ButtonHandler handler = new ButtonHandler();
		goButton.addActionListener( handler );
		
		theJList.addListSelectionListener(
			new ListSelectionListener () {
				public void valueChanged( ListSelectionEvent e )
				{
					theDisplayer.setDocument
							( docs[ theJList.getSelectedIndex() ] );
				}
			}
		);

		setSize(500, 400);
		setOpaque( true );
		show();

	}
	
	private class TextFieldHandler implements ActionListener {
		public void actionPerformed( ActionEvent e )
		{
			theQuery= e.getActionCommand();
		}
	}
	
	private class ButtonHandler implements ActionListener {
	
		public void actionPerformed( ActionEvent e )
		{
			if ( theQuery == null )
			{
				JOptionPane.showMessageDialog
					(null, 
					"Input query and press Enter, then GO",
					"Query Error", 
					JOptionPane.ERROR_MESSAGE);
			}
			else
			{		
				JOptionPane.showMessageDialog( null, theQuery );
				Query newQuery= new Query ( theQuery );
				java.util.List results= theSearchEngine.queryIt( newQuery );
				theQuery= null;
				titles= new String [ results.size() ];
				docs= new FileDocument[ results.size() ];
				java.util.Iterator iter = results.iterator();
				for( int i=0; i<docs.length; i++) {
					docs[i]= (FileDocument) theReader.getDocument
									( 
										((DocumentScore) iter.next()).id() 
									);
					titles[i]= docs[i].title();
				}
				theJList.setListData( titles );
			}
		}
	}

	public void setDocumentDisplayer( DocumentDisplayer dd)
	{
		theDisplayer= dd;
	}
}

class DocumentDisplayer extends JInternalFrame {

	private JEditorPane docDisplay;

	public DocumentDisplayer( String title )
	{
		super( title, true, true, true, true );
		
		Container c = getContentPane();
		
		docDisplay= new JEditorPane();
		docDisplay.setEditable( false );
		c.add( new JScrollPane( docDisplay ),
			BorderLayout.CENTER );

		setSize(500, 400);
		setOpaque( true );
		show();
	}
	
	public void setDocument( FileDocument doc)
	{
		try {
			docDisplay.setPage( doc.file() );
		}
		catch ( IOException io ) {
			JOptionPane.showMessageDialog( this,
				"Error retrieving document",
				"Bad filename",
				JOptionPane.ERROR_MESSAGE );
		}
	}
}