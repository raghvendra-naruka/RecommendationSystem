package org.machine;
/**
 *
 * @author manuj singh
 *
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.bigdata.TwitterDataAnalysis.*;

/**
 * this class is a utility class and it creates the vocabulary
 * @version      
 *        1.0, 28 Sep 2014  
 * @author          
 *       Manuj Singh
 */
public class Utility {

	public static int TOTALDOCS;
	private File[] traningSpamFiles;
	private File[] traningHamFiles;

	public Utility(File[] traningSpamFiles,File[] traningHamFiles)
	{
		this.traningSpamFiles = traningSpamFiles;
		this.traningHamFiles = traningHamFiles;
	}

	public void setTOTALDOCS(int tOTALDOCS) {
		TOTALDOCS = tOTALDOCS;
	}


	/**
	 * this method will create the vocabulary from the spam files as well as ham files
	 * this method will also intialize the spam and ham classes
	 */
	public void createVocabulary()
	{
		Vocabulary vocab = new Vocabulary();

		// creating object of spam class
		SpamClass spam = new SpamClass();
		spam.setTotalDocsBelongingToSpam(this.traningSpamFiles.length);
		spam.setPriorOfSpamClass(Utility.TOTALDOCS);

		// creating vocab from spam files
		CreateFromSpam(vocab,traningSpamFiles,spam);

		// creating object of spam class
		HamClass ham = new HamClass();
		ham.setTotalDocsBelongingToHam(this.traningHamFiles.length);
		ham.setPriorOfHamClass(Utility.TOTALDOCS);

		// creating from traning ham files
		CreateFromHam(vocab,traningHamFiles,ham);
	}


	/**
	 * this method will create the vocab from spam files
	 * @param vocab
	 * @param spam , files which are all spam
	 */
	private void CreateFromSpam(Vocabulary vocab,File[] spamFiles,SpamClass spamObj)
	{
		for (File f : spamFiles) {
			if(f.isFile()) {
				BufferedReader inputStream = null;
				try {
					inputStream = new BufferedReader(new FileReader(f));
					String line;
					while ((line = inputStream.readLine()) != null) {
						// splitting each line based on the space. so to seprate tokens
						String[] splited = line.split(" ");

						for(String token : splited)
						{
							
							if(Twitter.filterStopWords.equalsIgnoreCase("y"))
							{
								// if this is not a stop word only then add it to the vocab
								if(!Filter.isThisAStopWord(token))
								{
									// add each token to the vocabulary
									vocab.addToken(token);
									// also add each token to the spam class hashmap this will keep count of the occurence of each token
									spamObj.addTokenToSpamText(token);
								}
							}else
							{
								// add each token to the vocabulary
								vocab.addToken(token);
								// also add each token to the spam class hashmap this will keep count of the occurence of each token
								spamObj.addTokenToSpamText(token);
							}
							

							
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
		}
	}

	/**
	 * this will create the vocab from ham files
	 * @param vocab
	 * @param ham files which are all hams
	 */
	private void CreateFromHam(Vocabulary vocab,File[] hamFiles,HamClass hamObj)
	{
		for (File f : hamFiles) {
			if(f.isFile()) {
				BufferedReader inputStream = null;
				try {
					inputStream = new BufferedReader(new FileReader(f));
					String line;
					while ((line = inputStream.readLine()) != null) {
						// splitting each line based on the space. so to seprate tokens
						String[] splited = line.split(" ");
						// add each token to the vocabulary
						for(String token : splited)
						{
							if(Twitter.filterStopWords.equalsIgnoreCase("y"))
							{
							// if this is not a stop word only then add it to the vocab
								if(!Filter.isThisAStopWord(token))
								{
									// add each token to the vocabulary
									vocab.addToken(token);
									
									// also add each token to the ham class hashmap this will keep count of the occurence of each token
									hamObj.addTokenToHamText(token);
								}
							}else
							{
								// add each token to the vocabulary
								vocab.addToken(token);
								
								// also add each token to the ham class hashmap this will keep count of the occurence of each token
								hamObj.addTokenToHamText(token);
							}
							
							
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
		}
	}

}
