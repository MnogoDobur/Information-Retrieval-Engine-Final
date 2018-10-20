
public class MyPosting extends Object implements Posting {

	private int id;
	private int freq;
	
	public MyPosting (int anId, int theFreq) {
		id= anId;
		freq= theFreq;
	}
	
	public String toString() {
		return "("  + id + "," + freq + ")";
	}
	
	public int id() { return id; }
	
	public int freq() { return freq; }
	
}
