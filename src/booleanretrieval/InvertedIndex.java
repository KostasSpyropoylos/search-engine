package booleanretrieval;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class InvertedIndex {

	HashMap<String, ArrayList<Integer>> h;

	public InvertedIndex() {
		h = new HashMap<String, ArrayList<Integer>>();
	}

	public void insert(String sTerm, int nDocID)
	{
		if (h == null) return;
		ArrayList<Integer> a = null;
		if (h.containsKey(sTerm)) {
			a = h.get(sTerm);
			if (!a.contains(nDocID))
				a.add(nDocID);
		}
		else {
			a = new ArrayList<Integer>();
			a.add(nDocID);
			h.put(sTerm, a);
		}
	}

	public ArrayList<Integer> get(String sTerm)
	{
		if (h == null) return null;
		ArrayList<Integer> a = h.get(sTerm);
		return a;
	}

	public void print() {
		System.out.println("call print");
		if (h != null) {
			Set<String> indexterms = h.keySet();
			Iterator<String> iter = indexterms.iterator();
			System.out.println(iter.hasNext());
			while (iter.hasNext()) {
				String sIndexTerm = iter.next();
				System.out.print("IndexTerm: "+sIndexTerm+" -> ");
				ArrayList<Integer> a = h.get(sIndexTerm);
				for (int i=0 ; i < a.size() ; i++)
					System.out.print(a.get(i)+",");
				System.out.println();
			}
		}
		else 
			System.out.println("Index is empty");
	}	


	public void write(String sFile) {		
		/*FileOutputStream fos;
		ObjectOutputStream oos;
		try {
			fos = new FileOutputStream(sFile);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(h);
			oos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {		
			e.printStackTrace();
		}*/
	}

	public void load(String sFile) {
		/*FileInputStream fis;
		ObjectInputStream ois;
		try {
			fis = new FileInputStream(sFile);
			ois = new ObjectInputStream(fis);
			h = (HashMap<String, ArrayList<Integer>>) ois.readObject();
			ois.close();
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {		
			e.printStackTrace();		
		}
		catch (ClassNotFoundException e) {		
			e.printStackTrace();
		}*/
	}

}
