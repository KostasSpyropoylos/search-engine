package utils;

import java.io.File;
import java.util.ArrayList;

public class FileFunctions {
	/** Recursively check the contents (files/folders) of input directory: dir
	 * 	If a file with extension "txt" is found, print is full name.	
	 * @param dir
	 */
	private void getDirectoryContents(File dir, ArrayList<String> arFileNames) 
	{				
		File[] files = dir.listFiles();
		for (int i=0 ; i < files.length ; i++) {
			File f = files[i];
			if (f.isDirectory()) {
				getDirectoryContents(f, arFileNames);
			}
			else {
				if (f.getName().endsWith("txt")) {
					String sFName = f.getAbsolutePath();
					arFileNames.add(sFName);
				}
			}
		} 		
	}

	public ArrayList<String> getFiles(String sCorpusFolder) {
		System.out.println(">>>>> Obtain the documents to be indexed");
		File fCorpusFolder = new File(sCorpusFolder);		
		// 1. Get a list with all files to be Indexed. This list is: ArrayList<String> arFiles
		ArrayList<String> arFiles = new ArrayList<String>();
		getDirectoryContents(fCorpusFolder, arFiles);
		// 2. Create a MAPPING between files and DocumentIDs
		int nCntTotalFiles = arFiles.size();
		//for (int i=0 ; i < arFiles.size() ; i++)
		//System.out.println("File: "+(i+1)+"/"+nCntTotalFiles+" "+arFiles.get(i));
		return arFiles;
	}
}
