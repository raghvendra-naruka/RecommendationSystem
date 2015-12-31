package org.machine;


import java.util.HashSet;

/**
 *  
 *  This class implemnts Bayes Classification
 * @version      
 *        1.0, 28 Sep 2014  
 * @author          
 *       Manuj Singh
 */
public class BayesClassification {
	
	 public int laplaceSmoothing = 3;
	
	/**
	 * this method will create conditional probability for all the tokens in the vocab for spam class
	 * i.e P(t|Spam)
	 */
	public void IntializeConditionalProbForSpamClass()
	{
		int totalTokenInSpam = SpamClass.getTotalNoOFTokensInSpamText();
		int totalTokenInVocab = Vocabulary.getTotalTokensInVocab();
		
		HashSet<String> vocab = Vocabulary.vocab;
		
		// Calculate prob for each token in the spam class
		for ( String token : vocab)
		{
			int totalOcurrenceOfTokenInSpam = SpamClass.getTotalOccurenceOFtokenInSpamText(token);
			double condProb = (double)(totalOcurrenceOfTokenInSpam + laplaceSmoothing)/(double)(totalTokenInSpam + totalTokenInVocab);
			
			// add this propb to the token
			SpamClass.addContionalProbabiltyForSpam(token,condProb);
		}
		
		
	}
	
	/**
	 * this method will create conditional probability for all the tokens in the vocab for ham class
	 * i.e P(t|Spam)
	 */
	public void IntializeConditionalProbForHamClass()
	{
		int totalTokenInHam = HamClass.getTotalNoOFTokensInHamText();
		int totalTokenInVocab = Vocabulary.getTotalTokensInVocab();
		
		HashSet<String> vocab = Vocabulary.vocab;
		
		// Calculate prob for each token in the spam class
		for ( String token : vocab)
		{
			int totalOcurrenceOfTokenInHam = HamClass.getTotalOccurenceOFtokenInHamText(token);
			double condProb = (double)(totalOcurrenceOfTokenInHam + laplaceSmoothing)/(double)(totalTokenInHam + totalTokenInVocab);
			
			// add this prob to the token
			HamClass.addContionalProbabiltyForHam(token,condProb);
		}
		
		
	}
	
}
