package rankedretrieval;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class InvertedIndexTF {

	HashMap<String, ArrayList<Posting>> h;

	public InvertedIndexTF() {
		h = new HashMap<String, ArrayList<Posting>>();
	}

	public void insert(String sTerm, int nDocID, float fTF) {
		if (h == null) return;
		ArrayList<Posting> a = null;
		if (h.containsKey(sTerm)) { // term already exists, just add the Posting to Postings list
			a = h.get(sTerm);
			// check if the Posting for nDocID already exists (it shouldn't, but...)
			boolean bExists = false;
			for (int i=0 ; i < a.size() ; i++) {
				if (a.get(i).getDocID() == nDocID)
					bExists = true;
			}
			if (!bExists) {
				Posting p = new Posting(nDocID, fTF);
				a.add(p);				
			}
		}
		else { // terms does not exist, create new Postings list
			a = new ArrayList<Posting>();
			Posting p = new Posting(nDocID, fTF);
			a.add(p);
			h.put(sTerm, a);
		}
	}

	public ArrayList<Posting> get(String sTerm)
	{
		if (h == null) return null;
		ArrayList<Posting> a = h.get(sTerm);
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
				ArrayList<Posting> a = h.get(sIndexTerm);
				for (int i=0 ; i < a.size() ; i++)
					System.out.print(a.get(i).getDocID()+","+a.get(i).getTF()+" ");
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
