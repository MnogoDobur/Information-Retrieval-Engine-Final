/*
 * PreviewPane.java
 *
 * Created on 13 March 2005, 11:41
 */

import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;
/**
 *
 * @author Administrator
 */
public class PreviewPane extends JTextPane {
    
    private StyledDocument doc;
    private Style titleStyle;
    private Style bodyStyle;
    private String theTitle;
    
    /** Creates a new instance of PreviewPane */
    public PreviewPane(){
        //setPreferredSize(new Dimension(Parameters.DOC_DISPLAY_HOR, Parameters.DOC_DISPLAY_VERT));
        doc = getStyledDocument();
        setEditable(false);
        
        // Set up styles
        titleStyle = addStyle("titleStyle", null);
        StyleConstants.setBold(titleStyle, true);
        
        bodyStyle = addStyle("bodyStyle", null);
        StyleConstants.setBold(bodyStyle, false);
        setBorder(BorderFactory.createEmptyBorder(4,4,4,4));
        
    }
    
    public void updateText(FileDocument fd){

            //Clear text area
            setText("");

                
                theTitle = fd.title();
                try{
                    StyleConstants.setBold(titleStyle, true);
                    doc.insertString(doc.getLength(), theTitle + "\n\n", titleStyle);
                    doc.insertString(doc.getLength(), fd.text(), bodyStyle);
                }
                catch (BadLocationException ble) {
                    System.err.println("Couldn't insert initial text into text pane.");
                }
                setCaretPosition(0);
                /* try {
                    setCaretPosition(0);
                    Rectangle r = modelToView(0);
                    if ( r != null) {
                        scrollRectToVisible(r);
                    } else {
                        System.out.println("Null position\n");
                    }
                } catch (BadLocationException e ) { // should never be called
                    System.out.println("Failed to locate beginning of text: " + e);
                } */
    
    }
    
    public void updateText(String str){

            //Clear text area
            setText("");
                try{
                    StyleConstants.setBold(titleStyle, true);
                    doc.insertString(doc.getLength(), str, titleStyle);
                }
                catch (BadLocationException ble) {
                    System.err.println("Couldn't insert initial text into text pane.");
                }
                setCaretPosition(0);
                /* try {
                    setCaretPosition(0);
                    Rectangle r = modelToView(0);
                    if ( r != null) {
                        scrollRectToVisible(r);
                    } else {
                        System.out.println("Null position\n");
                    }
                } catch (BadLocationException e ) { // should never be called
                    System.out.println("Failed to locate beginning of text: " + e);
                } */
    
    }
    
    public String getDocTitle(){
         return theTitle + "";        
    }
    
}
