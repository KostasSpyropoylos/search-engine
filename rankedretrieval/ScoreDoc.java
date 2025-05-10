package rankedretrieval;

public class ScoreDoc  {
	private int nDocID;
	private float score;
	
	public ScoreDoc(int nDocID, float score) {
		this.nDocID = nDocID;
		this.score = score;
	}
	
	public int getDocID() {
		return nDocID;
	}
	public void setDocID(int nDocID) {
		this.nDocID = nDocID;
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}	
}
