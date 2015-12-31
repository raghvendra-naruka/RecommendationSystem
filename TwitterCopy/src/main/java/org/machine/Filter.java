package org.machine;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * this class filters the stop words
 * @author manuj singh
 *
 */
public class Filter {

	private static ArrayList<String> stopWords = new ArrayList<String>();
	

	public void createListOfStopWords(File stopWordsFile)
	{
		if(stopWordsFile.isFile()) {
			BufferedReader inputStream = null;
			try {
				inputStream = new BufferedReader(new FileReader(stopWordsFile));
				String line;
				while ((line = inputStream.readLine()) != null) {
					// splitting each line based on the space. so to seprate tokens
					String[] splited = line.split(" ");

					for(String word : splited)
					{
						// add each stopWord to the list
						stopWords.add(word);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			finally {
				if (inputStream != null) {
					try {
						inputStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		} 
		Collections.sort(stopWords);
	}
	
	public static boolean isThisAStopWord(String word)
	{
		int index = Collections.binarySearch(stopWords,word.toLowerCase());
		if(index < 0)
		{
			return false;
		}else
		{
			return true;
		}
	}
}
