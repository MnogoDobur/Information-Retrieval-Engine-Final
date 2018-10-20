
public class MyWord extends Object implements Word {

	private String token;
	private int id, freq;
	
	public MyWord (String theToken, int theId, int theFreq ) {
		token = theToken;
		id= theId;
		freq= theFreq;
	}
	
	public String wordOf() { return token; }
	
	public int id() { return id; }
	
	public int freq() { return freq; }
	
	public String toString() {
		return "word( " + token + "," + id + "," + freq + " )";
	}
	
}
	