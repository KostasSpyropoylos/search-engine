package booleanretrieval;

import java.util.ArrayList;

public class Search {

	public Search() {
	}

	public Integer[] AandBandC(String aTerm, String bTerm, String cTerm, InvertedIndex index) {
		Integer[] tempRes1 = AandB(aTerm, bTerm, index);
		if (tempRes1.length == 0)
			return null;
		Integer[] tempRes2 = AandB(cTerm, tempRes1, index);
		return tempRes2;
	}

	public Integer[] AandB(String aTerm, String bTerm, InvertedIndex index) {
		ArrayList<Integer> listA = index.get(aTerm);
		ArrayList<Integer> listB = index.get(bTerm);
		System.out.println(listA.size() + " " + listB.size());
		if (listA == null || listB == null)
			return null;
		ArrayList<Integer> res = new ArrayList<Integer>(Math.min(listA.size(), listB.size()));
		// find the commons IDs in the two lists
		int a = 0, b = 0;
		while (a < listA.size() && b < listB.size()) {
			int nDocIdA = listA.get(a);
			int nDocIdB = listB.get(b);
			if (nDocIdA < nDocIdB)
				a++;
			else if (nDocIdB < nDocIdA)
				b++;
			else {
				res.add(nDocIdA);
				a++;
				b++;
			}
		}
		return (Integer[]) res.toArray(new Integer[res.size()]);
	}

	public Integer[] AandB(String aTerm, Integer[] listB, InvertedIndex index) {
		ArrayList<Integer> listA = index.get(aTerm);
		System.out.println(listA.size());
		if (listA == null || listB == null)
			return null;
		ArrayList<Integer> res = new ArrayList<Integer>(Math.min(listA.size(), listB.length));
		// find the commons IDs in the two lists
		int a = 0, b = 0;
		while (a < listA.size() && b < listB.length) {
			int nDocIdA = listA.get(a);
			int nDocIdB = listB[b];
			if (nDocIdA < nDocIdB)
				a++;
			else if (nDocIdB < nDocIdA)
				b++;
			else {
				res.add(nDocIdA);
				a++;
				b++;
			}
		}
		return (Integer[]) res.toArray(new Integer[res.size()]);
	}

	public Integer[] AorB(String aTerm, String bTerm, InvertedIndex index) {
		ArrayList<Integer> listA = index.get(aTerm);

		ArrayList<Integer> listB = index.get(bTerm);
		System.out.println("list A Size:" + listA.size() + " list B Size:" + listB.size());
		if (listA == null || listB == null)
			return null;
		ArrayList<Integer> res = new ArrayList<Integer>(Math.min(listA.size(), listB.size()));
		// find the IDs that exist on either of the lists
		res.addAll(listA);
		for (int x : listB) {
			if (!res.contains(x))
				res.add(x);
		}

		return (Integer[]) res.toArray(new Integer[res.size()]);
	}

	public Integer[] AnotB(String aTerm, String bTerm, InvertedIndex index) {
		ArrayList<Integer> listA = index.get(aTerm);

		ArrayList<Integer> listB = index.get(bTerm);
		System.out.println("list A Size:" + listA.size() + " list B Size:" + listB.size());
		if (listA == null || listB == null)
			return null;
		ArrayList<Integer> res = new ArrayList<Integer>();
		// find the IDs that exist on either of the lists
		for (int x : listA) {
			if (!listB.contains(x)) {
				res.add(x);
			}
		}

		return (Integer[]) res.toArray(new Integer[res.size()]);
	}

}
