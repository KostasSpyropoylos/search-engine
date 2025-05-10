package rankedretrieval;

public class Posting {
	private int nDocID;
	private float fTF;
	
	public Posting(int nDocID, float fTF) {
		this.nDocID = nDocID;
		this.fTF	= fTF;
	}
	
	public int getDocID() {
		return nDocID;
	}
	public void setDocID(int nDocID) {
		this.nDocID = nDocID;
	}
	public float getTF() {
		return fTF;
	}
	public void setTF(float fTF) {
		this.fTF = fTF;
	}
}
