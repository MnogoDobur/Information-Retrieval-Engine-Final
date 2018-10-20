/*
 * GenericFileReader.java
 *
 * Created on 10 March 2005, 14:48
 */

import java.io.*;
/**
 *
 * @author Administrator
 */
public class GenericFileReader {
    
    private String strInputFileName;
    private FileReader fileReader;
    private BufferedReader objBufferedReader;
    /** Creates a new instance of GenericFileReader */
    public GenericFileReader(String theInputFileName) {
        
        strInputFileName = theInputFileName;      
        try { fileReader = new FileReader(strInputFileName); }
        catch (FileNotFoundException e) {
          System.err.println
             ("GenericFileReader can't find input file: " + strInputFileName);
          e.printStackTrace ( );
        }
        objBufferedReader = new BufferedReader(fileReader);
        
    }
    
    public String[] getSGMLDocumentContents(){
        String[] theReturnArray = new String[2];
        String line = "";
        String strFileTitle = "";
        String strFileContents = "";
        //Ignore the first 2 lines
        int lineCounter = 1;
        try {
            while ((line = objBufferedReader.readLine()) != null){
                if (lineCounter==2) {
                    String res;
                    strFileTitle=" [";
                    int f=line.indexOf('>',0);
                    if (f == -1)
                        res=" ";
                    else {
                        int g=line.indexOf('<', f);
                        if (g == -1)
                            res=" ";
                        else {
                            res=line.substring(f+1, g);
                        }
                    }
                    strFileTitle=strFileTitle + res + "]";
                }
                else if (lineCounter==3)
                    strFileTitle= line +strFileTitle;
                else if (lineCounter>3)
                    strFileContents += line + "\n";
               lineCounter++;
            }
            
            theReturnArray[0] = SGMLUtilities.cleanDocumentSGML(strFileTitle);
            theReturnArray[1] = SGMLUtilities.cleanDocumentSGML(strFileContents.substring(2,strFileContents.length()-1));
            
            return theReturnArray;
        }
        catch (IOException e) {
            e.printStackTrace ( );
        }
        return null;
    }
    
    public String getNextLine(){
        try {
            return objBufferedReader.readLine ( );
        }
        catch (IOException e) {
            e.printStackTrace ( );
        }
        return null;
    }
    
}
