package booleanretrieval;

import java.util.ArrayList;

import parsing.Parser;
import utils.FileFunctions;

public class RunnerBoolean {

	private InvertedIndex buildIndex(ArrayList<String> arFiles) {
		System.out.println(">>>>> building the Inverted Index");
		// Add each file to the Inverted Index
		InvertedIndex index = new InvertedIndex();
		Parser p = new Parser();
		for (int i = 0; i < arFiles.size(); i++) { // For each file
			String sFile = arFiles.get(i);
			int nDocID = i + 1;
			ArrayList<String> arTokens = p.parseAndTokenizeFile(sFile); // Parse and tokenize the file
			for (int j = 0; j < arTokens.size(); j++) {
				String sToken = arTokens.get(j);
				index.insert(sToken, nDocID);
			}
		}
		// Print the Inverted Index
		// index.print();
		return index;
	}

	private Integer[] search(String sTermA, String sTermB, InvertedIndex index) {
		System.out.println(">>>>> Search A and B");
		Search s = new Search();
		return s.AandB(sTermA, sTermB, index);
	}

	private Integer[] search(String sTermA, String sTermB, String sTermC, InvertedIndex index) {
		System.out.println(">>>>> Search A and B and C");
		Search s = new Search();
		return s.AandBandC(sTermA, sTermB, sTermC, index);
	}

	public void BooleanAnd(ArrayList<String> corpus, InvertedIndex index) {
		String sTermA = "the";
		String sTermB = "oil";
		String sTermC = "tanker";
		long l3 = System.currentTimeMillis();
		// Integer[] docIDs = r.search(sTermA, sTermB, index); // Search the Index
		Integer[] docIDs = this.search(sTermA, sTermB, sTermC, index); // Search the Index
		long l4 = System.currentTimeMillis();
		System.out.println((l4 - l3) / 1000 + " sec, found: " + docIDs.length + " results");
		if (docIDs != null && docIDs.length > 0) {
			for (int i = 0; i < docIDs.length; i++)
				System.out.println(
						"Result: Document " + docIDs[i] + " file " + corpus.get(docIDs[i] - 1));
		}
		
	}

	public static void main(String[] args) {
		String sCorpusFolder = "reuters/data";
		// String sIndexFile = "myindex";
		RunnerBoolean r = new RunnerBoolean();
		FileFunctions F = new FileFunctions();
		// ====== 1. Obtain the list of all Documents (files) ======
		ArrayList<String> arFiles = F.getFiles(sCorpusFolder);
		// ====== 2. Build the Inverted Index ======
		long l1 = System.currentTimeMillis();
		InvertedIndex index = r.buildIndex(arFiles); // Build the Index
		// index.write("index.ser");
		long l2 = System.currentTimeMillis();
		System.out.println((l2 - l1) / 1000 + " sec");
		// ====== 3. Search the Inverted Index (Boolean retrieval) ======

		// r.BooleanAnd(arFiles, index);

		// ====== 4. Boolean Not ======
		// System.out.println(">>>>> Search A or B");
		Search s = new Search();
		// Integer[] res = s.AorB("the", "tanker", index);
		// System.out.println("Number of DOCS: " + res.length);

		// System.out.println(">>>>> Search A not B");
		// Integer[] notResult = s.AnotB("the", "tanker", index);
		// System.out.println("Number of DOCS: " + notResult.length);
		System.out.println(">>>>> Search Not A ");
		Integer[] notResult = s.NotA("tanker", index);
		System.out.println("Number of DOCS: " + notResult.length);

		/*
		 * // 5. Write the Inverted Index to DISK
		 * index.write(sIndexFile);
		 * // 6. Read back the Inverted Index to MEMORY
		 * InvertedIndex index2 = new InvertedIndex();
		 * index.load(sIndexFile);
		 * index2.print();
		 */
	}

}
