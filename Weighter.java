import java.util.*;

public class Weighter {

	private double scores[];
	private InvertedFile inv;
	private TextAnalyzer ta;
        
        private int theWf;

	public Weighter ( InvertedFile theInv,
					  TextAnalyzer theTA, int wf ) {
		inv= theInv;
		ta= theTA;
                theWf= wf;
	}
        
	public Weighter ( InvertedFile theInv,
					  TextAnalyzer theTA) {
		inv= theInv;
		ta= theTA;
                theWf=1;
	}

	public double queryWeight( WordEntry we ) {
		return 1.0;
	}
	private WordEntry we;
	public double documentWeight( Posting p) {
                double res;
                MyPosting mp = (MyPosting) p;
                MyInvertedFile minv = (MyInvertedFile)inv;
                //System.out.println("id " + mp.id() + "freq " + mp.freq() + "docFreq ");
                res=1.0;
               // double wordCnt = (double)we.count();
              //  double idf = Math.log10((double)inv.nDocs()/wordCnt);
               // double normTF = (double)mp.freq()/(double)minv.getMaxtf(mp.id());
              //  double tf = (double)mp.freq();
                
               // switch(theWf){
               //     case 1:res=1.0;break;//default weighting coordination lvl matching
               //     case 2:res=normTF;break;//normalised tf
               //     case 3:res=idf;break;//idf
               //     case 4:res=normTF*idf;break;//normalised tf*idf
                 //   case 5:res=tf;break;//tf
                //    default:res=1.0;
               // }
                
                if ( theWf==1 ) // co-ordination level matching
                    res=1.0;
                else // default is co-ordination level matching
                    res= 1.0;
                return res;
	}

	public List scoreIt ( Query theQuery ) {
		double qWeight, dWeight;
                boolean firstFlag=true;
		String queryWord;
		WordEntry we;
		MyPostingsIterator pi;
		Posting post;
		//System.out.println("ScoreIt 1");
		scores= new double [ inv.nDocs() ];
		for (int i=0; i<scores.length; i++) {
			scores[i]= 0;
		}
                
                ta.setSpecialQueryProcessing(true);
		List qt = ta.analyzeDocument ( theQuery );
                ta.setSpecialQueryProcessing(false);
                
		Iterator it = qt.iterator();
		while ( it.hasNext() ) {
			queryWord= (String) it.next();
                        if (firstFlag) {
                            firstFlag= false;
                            if (queryWord.charAt(0) == '$') {
                                theWf= Integer.valueOf(queryWord.substring(1)).intValue();
                            }
                        }
			//System.out.println("Query term " + queryWord );
			we= inv.lookupEntry( queryWord );
			if ( we == null ) 
				continue;
			//System.out.println("Word Entry " + (MyWordEntry )we );
			qWeight= queryWeight( we );
			pi= (MyPostingsIterator) we.postingsIterator();
			while ( pi.morePostings() ) {
				post= (Posting) pi.getPosting();
				dWeight= documentWeight( post );
				scores[ post.id() ] += qWeight*dWeight;
			}
		}
		ArrayList scorepairs = new ArrayList();
		for(int i=0; i<scores.length; i++) {
			if ( scores[i] != 0.0 ) {
				scorepairs.add( new DocumentScore( i, scores[i] ) );
			}
		}
		Collections.sort( scorepairs, Collections.reverseOrder() );		
		return scorepairs;		
	}
}