package org.machine;
/**
 * 
 * @author manuj singh
 *
 */
import java.util.HashSet;

/**
 *  
 *  This class represesents the vocabulary of all the tokens present in all the training documents
 * @version      
 *        1.0, 28 Sep 2014  
 * @author          
 *       Manuj Singh
 */
public class Vocabulary {
	
	/**
	 * this will contain all the unique tokens in the documents
	 * HashSet does not allow duplicates
	 */
	public static HashSet<String> vocab = new HashSet<String>();
	
	
	/**
	 * this method will add token to the vocabulary
	 * @param token to add
	 */
	public void addToken(String token)
	{
		vocab.add(token);
	}
	
	/**
	 * this will return total no of tokens in the vocab
	 * @return size of the vocabulary
	 */
	public static int getTotalTokensInVocab()
	{
		return vocab.size();
	}
	
	public static String[] getVocabularyArray()
	{
		String[] Vocabulary = new String[vocab.size()]; 
		vocab.toArray(Vocabulary);
		return Vocabulary;
	}
	
	
	// this is iterator for the vocab
	
//	 for ( HashSetIterator i = vocab.begin(); !i.atEnd(); i.advance() )
//	      System.out.println( i.get() );
//	    System.out.println();
	
}
