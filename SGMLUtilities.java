/*
 * SGMLUtilities.java
 *
 * Created on 13 March 2005, 10:51
 */

import java.io.*;
/**
 *
 * @author Administrator
 */
public class SGMLUtilities {
    
    /** Creates a new instance of SGMLUtilities */
    public SGMLUtilities() {
    }
    
    public static void writeQueryToSGML(String strTheFileName, String strTheQuery){
        PrintWriter printWriter = null;

        try {
            //printWriter = new PrintWriter( new FileOutputStream (API.STR_LEMUR_QUERY_DIR + strTheFileName + ".sgml"), true);
        }
        catch (Exception e) {
            System.err.println ("SGMLUtilities can't use output file: " + strTheFileName);
        }

        printWriter.println("<DOC>");        
        printWriter.println("<DOCNO> 1 </DOCNO>");
        
        //Write the query text
        printWriter.println(strTheQuery);
        
        printWriter.println("</DOC>");
        
    }
    
    public static String cleanDocumentSGML(String strInputString){
        return strInputString.replaceAll("<.*?>","");
    }
    
}
