package rankedretrieval;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Search {

	private int nDocs;	// number of docs in the collection
	private int[] length;	// lengths of docs (=number of tokens per doc) 
	private InvertedIndexTF index; 	// the Inverted Index

	public Search(int collectionSize, int[] length, InvertedIndexTF index) {
		this.nDocs = collectionSize;
		this.index = index;
		this.length = new int[length.length];
		for (int i=0 ; i < length.length ; i++)
			this.length[i] = length[i];
	}

	/** Computes the cosine similarity for a given query 
	 * @param query
	 * @param k
	 * @return
	 */
	public ArrayList<Integer> cosine(String[] query, int k) {
		// initialize array with scores
		ArrayList<ScoreDoc> scores = new ArrayList<ScoreDoc>(this.nDocs);	
		for (int i=0 ; i < this.nDocs ; i++) {
			scores.add(new ScoreDoc(i, 0));
		}
		for (String t: query) {
			float Wtq = 1; // Set the weight for this query term: TF_t,q
			ArrayList<Posting> postlist = index.get(t);
			for (int i=0 ; i < postlist.size() ; i++) {
				Posting p = postlist.get(i);
				int nDocID 	= p.getDocID();
				float TF 	= p.getTF();
				float Wtd	= TF;// Set the weight for the document term: TF_d,q
				ScoreDoc s = scores.get(nDocID);
				s.setScore(s.getScore() + Wtq * Wtd);	
			}
		}
		// Divide scores with length to obtain cosine similarity
		for (int i=0 ; i < scores.size() ; i++) {
			ScoreDoc s = scores.get(i);
			s.setScore(s.getScore() / this.length[i]);
		}			
		// Sort the scores
		Collections.sort(scores, new CustomComparator());	
		
		for (int i=0 ; i < k ; i++) {
			System.out.println(scores.get(i).getDocID()+","+scores.get(i).getScore());
		}
		System.out.println();
		// return the top-k from: scores
		ArrayList<Integer> topk = new ArrayList<Integer>(k);

		return topk;
	}
	
}
