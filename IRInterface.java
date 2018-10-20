import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.event.*;
import java.io.*;

//public class IRInterface extends JFrame
//all code modified @Nicola Dowsland
public class IRInterface extends JFrame 
{
	private QueryInterface qi;
	private RetrievedInterface ri;
	private DocumentDisplayer dd;
	private String theQuery;
	private String  titles[];
	private FileDocument  docs[];
	
	public IRInterface( String title, SearchEngine se, CollectionReader cr)
	{
		super(title);
		theQuery=null;
		qi= new QueryInterface("Querying Interface", se, cr );
		dd= new DocumentDisplayer("Document Displayer");
		ri=new RetrievedInterface("Retrieved Documents",se,cr);
		ri.setDocumentDisplayer( dd );
		qi.setRetrievedInterface(ri);
		//same width as TutoringInterface
		setSize(852, 600);
		Container cp= this.getContentPane();
                cp.setLayout(new GridBagLayout());
                GridBagConstraints g = new GridBagConstraints();
                g.gridx=0;
                g.gridy=0;
                g.gridwidth=2;
                g.weightx=1;
                g.fill=GridBagConstraints.HORIZONTAL;
                cp.add(qi,g);
                g.gridx=0;
                g.gridy=1;
                g.gridwidth=1;
                g.fill=GridBagConstraints.BOTH;
                cp.add(ri,g);
                g.gridx=1;
                g.gridy=1;
                g.weighty=1;
                g.fill=GridBagConstraints.BOTH;
                cp.add(dd,g);
		show();	
	}
	
	public void showRetrieved()
	{
		ri.showRetrieved();
	}
	
	public void clearRetrieved()
	{
		ri.clearRetrieved();
	}
	
class QueryInterface extends JPanel 
{

	private SearchEngine theSearchEngine;
	private DirectoryCollectionReader theReader;
	private RetrievedInterface theRetrieved;
	
	private JTextField queryText;
	private JButton goButton;
	
	
	public QueryInterface( String title, SearchEngine se,
						   CollectionReader cr)
	{

		super();
		theSearchEngine= se;
		theReader= (DirectoryCollectionReader) cr;
		
		this.setBackground(Color.black);
		this.setLayout(new BorderLayout());
		JTextField titleField=new JTextField();
		titleField.setFont(new Font("Monospaced",Font.BOLD,14));
		titleField.setText(title);
		titleField.setEditable(false);
		this.add(titleField,BorderLayout.NORTH);
				
		JPanel inner=new JPanel();
		inner.setBackground(Color.black);
		inner.setLayout(new FlowLayout());		
				
		JPanel fields=new JPanel();
		fields.setBackground(Color.black);
		JPanel text=new JPanel();
		text.setBackground(Color.black);
		text.setLayout ( new BorderLayout() );
		JTextField enterQueryText= new JTextField("ENTER QUERY TERMS:");
		enterQueryText.setEditable(false);
		text.add( enterQueryText,"North" );
		queryText= new JTextField(15);
		text.add( queryText,"South" );
		fields.add(text,"North");
		inner.add(fields);
		
		JPanel button=new JPanel();
		button.setBackground(Color.black);
		goButton= new JButton( "SEARCH" );
		goButton.setMaximumSize(new Dimension(100,25));
		button.add(goButton);
		inner.add( button);
		
		this.add(inner,"Center");
		
		ButtonHandler handler = new ButtonHandler();
		goButton.addActionListener( handler );	
	}

	private class ButtonHandler implements ActionListener {
	
		public void actionPerformed( ActionEvent e )
		{
			theQuery=queryText.getText();
			if ( theQuery == null )
			{
				JOptionPane.showMessageDialog
					(null, 
					"Input query terms then press the <SEARCH> button",
					"Query Error", 
					JOptionPane.ERROR_MESSAGE);
			}
			else
			{		
				JOptionPane.showMessageDialog( null,"Query terms entered:\n"+theQuery,"New query accepted",JOptionPane.INFORMATION_MESSAGE );
				Query newQuery= new Query ( theQuery );
				theRetrieved.clearRetrieved();
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
                                showRetrieved();
			}
		}
	}
	
	public void setRetrievedInterface( RetrievedInterface ri)
	{
		theRetrieved= ri;
	}

}//end class QueryInterface


class RetrievedInterface extends JPanel 
{

	private SearchEngine theSearchEngine;
	private DirectoryCollectionReader theReader;
	private DocumentDisplayer theDisplayer;

	private JList theJList;
	private String theQuery;
	private String openingText[];
	
	public RetrievedInterface( String title, SearchEngine se,
						   CollectionReader cr)
	{

		super();
		theSearchEngine= se;
		theReader= (DirectoryCollectionReader) cr;
		this.setBackground(Color.black);
		this.setLayout(new BorderLayout());
		JTextField titleField=new JTextField();
		titleField.setFont(new Font("Monospaced",Font.BOLD,14));
		titleField.setText(title);
		titleField.setEditable(false);
		this.add(titleField,BorderLayout.NORTH);
		
			
		theJList= new JList();
		theJList.setVisibleRowCount( 4);
		openingText=new String[1];
		openingText[0]="No query processed yet";
		theJList.setListData(openingText);
		theJList.setSelectionMode(
			ListSelectionModel.SINGLE_SELECTION );

		theJList.addListSelectionListener(
			new ListSelectionListener () {
				public void valueChanged( ListSelectionEvent e )
				{
					try
					{
						theDisplayer.setDocument( docs[ theJList.getSelectedIndex() ] );
					}catch(ArrayIndexOutOfBoundsException arr)
					{}
				}
			}
		);
		this.add( new JScrollPane(theJList),BorderLayout.CENTER);
	}
	
	public void showRetrieved()
	{
		theJList.setListData( titles );
	}
	
	public void clearRetrieved()
	{
		theJList.setListData( openingText );
	}
	
	public void setDocumentDisplayer( DocumentDisplayer dd)
	{
		theDisplayer= dd;
	}
	
}
	
class DocumentDisplayer extends JPanel 
{

	private PreviewPane docDisplay;

	public DocumentDisplayer( String title )
	{
		super();
		this.setBackground(Color.darkGray);
		this.setLayout(new BorderLayout());
		JTextField titleField=new JTextField();
		titleField.setFont(new Font("Monospaced",Font.BOLD,14));
		titleField.setText(title);
		titleField.setEditable(false);
		//this.add(titleField,BorderLayout.NORTH);
		
		//JPanel inner=new JPanel();
		//inner.setBackground(Color.black);
		//inner.setLayout ( new FlowLayout() );
		
		//JTextField display=new JTextField("Text of Retrieved Document Selected:");
		//display.setFont(new Font("Monospaced",Font.PLAIN,12));
		//display.setEditable(false);
		//inner.add(display,BorderLayout.NORTH);
		
		docDisplay= new PreviewPane();
		docDisplay.setSize(200,600);
		docDisplay.setEditable( false );
		docDisplay.updateText("No Document Selected");
		//inner.add( new JScrollPane( docDisplay ));
		//this.add(inner,BorderLayout.CENTER);
                
                setLayout(new GridBagLayout());
                GridBagConstraints g = new GridBagConstraints();
                g.gridx=0;
                g.gridy=0;
                g.weightx=1;
                g.fill=GridBagConstraints.HORIZONTAL;
                add(titleField,g);
                g.gridx=0;
                g.gridy=1;
                g.gridwidth=1;
                g.weighty=1;
                g.fill=GridBagConstraints.BOTH;
                add(new JScrollPane( docDisplay ),g);
	}
	
	public void setDocument( FileDocument doc)
	{
		docDisplay.updateText( doc);
	}
	
}//end class DocumentDisplayer
}
