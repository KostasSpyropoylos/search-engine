package rankedretrieval;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import parsing.Parser;
import utils.FileFunctions;

public class RunnerRanked {
	// The folder containing the input data (the collection or the Corpus)
	private String sCorpusFolder = "reuters/data";
	// The number of documents in the collection
	private int nDocs;
	// The number of terms in each document (the document length)
	private int[] length;
	
	private InvertedIndexTF buildIndex(ArrayList<String> arFiles) {
		System.out.println(">>>>> building the Inverted Index (with TF)");
		// Add each file to the Inverted Index
		InvertedIndexTF index = new InvertedIndexTF();
		Parser p = new Parser();
		for (int i=0 ; i < arFiles.size() ; i++) { // For each file
			String sFile = arFiles.get(i);
			int nDocID = i+1; 
			// Parse and tokenize the file
			HashMap<String, Integer> hTokens = p.parseAndTokenizeFileWithTF(sFile);
			//Then, convert this HashMap to ArrayList<Posting> and return it
			Iterator<String> it = hTokens.keySet().iterator();
			while (it.hasNext()) {
				String sToken = it.next();
				int TF = hTokens.get(sToken);
				index.insert(sToken, nDocID, TF);
				// Store the file's length
				length[i] += TF;
			}						
		}
		return index;
	}


	public static void main(String[] args) 
	{
		RunnerRanked r = new RunnerRanked();
		FileFunctions F = new FileFunctions();
		// ====== 1. Obtain the list of all Documents (files) ======
		ArrayList<String> arFiles = F.getFiles(r.sCorpusFolder);
		r.nDocs = arFiles.size();
		r.length = new int[r.nDocs];
		// ====== 2. Build the Inverted Index ======
		long l1 = System.currentTimeMillis();		
		InvertedIndexTF index = r.buildIndex(arFiles); // Build the Index
		long l2 = System.currentTimeMillis();
		System.out.println((l2-l1)/1000 + " sec");
		// Print the Inverted Index		
		index.print();
		// ====== 3. Search the Inverted Index (Ranked retrieval) ======
		Search s = new Search(r.nDocs, r.length, index);
		String[] query = {"oil", "money"};
		s.cosine(query, 10/*=k*/);		

		
		// 5. Write the Inverted Index to DISK
		// index.write(sIndexFile);
		// 6. Read back the Inverted Index to MEMORY
		// InvertedIndex index2 = new InvertedIndex();
		// index.load(sIndexFile);
		// index2.print();
				
	}

}
