package parsing;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;

import rankedretrieval.Posting;

public class Parser {

	public static void main(String[] args) {
		Parser p = new Parser();
		String sText = p.parseFile("data/coffee/reut2-001x29.txt");
		p.tokenize(sText);
	}

	/**
	 * Extracts the text from a given text file described by its full path name
	 * (sFile).
	 * Reads the file line-by-line and returns the text as a String variable.
	 * 
	 * @param sFile
	 * @return
	 */
	public String parseFile(String sFile) {
		String sLine = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader(sFile));
			sLine = br.readLine();
			tokenize(sLine);
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sLine;
	}

	/**
	 * Extracts tokens from a text String (sText) given as input.
	 * Uses a list of delimiters to split the text in tokens.
	 * Returns the tokens as an ArrayList<String>
	 * 
	 * @param sLine
	 * @return
	 */
	private ArrayList<String> tokenize(String sText) {
		ArrayList<String> arTokens = new ArrayList<String>();
		String DELIM = " ,.!()/\""; // Splitting characters
		StringTokenizer st = new StringTokenizer(sText, DELIM);
		while (st.hasMoreTokens()) {
			String sToken = st.nextToken();
			// System.out.println(sToken);
			arTokens.add(sToken);
		}
		return arTokens;
	}

	/**
	 * Return tokens and TFs in a HashMap: hTokens
	 * 
	 * @param sText: the text to be tokenized
	 * @return HashMap of Tokens and TFs
	 */
	private HashMap<String, Integer> tokenizeWithTF(String sText) {
		HashMap<String, Integer> hTokens = new HashMap<String, Integer>();
		String DELIM = " ,.!()/\""; // Splitting characters
		StringTokenizer st = new StringTokenizer(sText, DELIM);
		while (st.hasMoreTokens()) {
			String sToken = st.nextToken();
			if (hTokens.containsKey(sToken)) {
				int TF = hTokens.get(sToken);
				TF = TF + 1;
				hTokens.put(sToken, TF);
			} else {
				hTokens.put(sToken, 1);
			}
		}
		return hTokens;
	}

	/**
	 * Takes as input a file and return its tokens in an ArrayList<String>
	 */
	public ArrayList<String> parseAndTokenizeFile(String sFile) {
		ArrayList<String> arTokens = null;
		String sLine = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader(sFile));
			sLine = br.readLine();
			arTokens = tokenize(sLine);
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return arTokens;
	}

	/**
	 * Takes as input a file and return its tokens in an HashMap<String, Integer>
	 */
	public HashMap<String, Integer> parseAndTokenizeFileWithTF(String sFile) {
		HashMap<String, Integer> hTokens = null;
		String sLine = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader(sFile));
			sLine = br.readLine();
			hTokens = tokenizeWithTF(sLine);
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return hTokens;
	}

	public List<String> Postfix(List<String> infixTokens) {
		Map<String, Integer> precedence = new HashMap<>();
		precedence.put("NOT", 3);
		precedence.put("AND", 2);
		precedence.put("OR", 1);
		precedence.put("(", 0);
		precedence.put(")", 0);

		List<String> output = new ArrayList<>();
		Stack<String> operatorStack = new Stack<>();

		for (String token : infixTokens) {
			if (token.equals("(")) {
				operatorStack.push(token);
			} else if (token.equals(")")) {
				while (!operatorStack.isEmpty()) {
					String operator = operatorStack.pop();
					if (operator.equals("("))
						break;
					output.add(operator);
				}
			} else if (precedence.containsKey(token)) {
				while (!operatorStack.isEmpty()) {
					String currentOperator = operatorStack.peek();
					if (precedence.get(currentOperator) > precedence.get(token)) {
						output.add(operatorStack.pop());
					} else {
						break;
					}
				}
				operatorStack.push(token);
			} else {
				output.add(token.toLowerCase());
			}
		}

		while (!operatorStack.isEmpty()) {
			output.add(operatorStack.pop());
		}

		return output;
	}

}
