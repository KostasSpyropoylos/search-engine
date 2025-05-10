package rankedretrieval;

import java.util.Comparator;

public class CustomComparator implements Comparator<ScoreDoc> 
{
	public int compare(ScoreDoc s1, ScoreDoc s2) {
		if (s1.getScore() == s2.getScore())
			return 0;
		else if (s1.getScore() < s2.getScore())
			return 1; 
		else 
			return -1;
	}
}