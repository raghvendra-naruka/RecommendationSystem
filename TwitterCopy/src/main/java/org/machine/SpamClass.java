package org.machine;


import java.util.HashMap;

/**
 * this class implements the Spam, i.e all the properties if the doc is spam
  * @version      
 *        1.0, 28 Sep 2014  
 * @author          
 *       Manuj Singh
 */
public class SpamClass {
	
	private final static String KEY_FOR_TOTAL_NO_OF_TOKENS_IN_SPAM_TEXT = "totalNoOFTokens";
	
	/**
	 * this represnts total no of emails that belong to spam in the traning set
	 */
	private static double totalDocsBelongingToSpam ;
	
	/**
	 * this defines the prior probabilty of the spam class
	 * i.e  totalDocsBelongingToSpam/totalDocsInTheTraningSet
	 */
	private static double priorOfSpamClass;
	
	/**
	 * this will contain all the tokens which are present in all the docs that
	 * belong to spam class, with their count as value
	 * hashMap<Token,count>
	 */
	private static HashMap<String ,Integer> spamText = new HashMap<String, Integer>();

	/**
	 * this will store the conditional probabilty of all the tokens present in the Vocabulary against the Spam class
	 * i.e P(t|spam) 
	 */
	private static HashMap<String ,Double> conditioanlProbablityForTokenInSpamClass = new HashMap<String, Double>();
	
	/**
	 * this returns the total no of tokens in the spamtext so far
	 * it is stored in a special key in the spamTExt( hasmap) and is increased every time a token is added to the text
	 */
	public static int getTotalNoOFTokensInSpamText()
	{
		return spamText.get(KEY_FOR_TOTAL_NO_OF_TOKENS_IN_SPAM_TEXT);
	}
	
	/**
	 * @return total docs belonging to spam
	 */
	public double getTotalDocsBelongingToSpam() {
		return totalDocsBelongingToSpam;
	}

	public void setTotalDocsBelongingToSpam(int totalSpamDocs) {
		totalDocsBelongingToSpam = totalSpamDocs;
	}

	public static double getPriorOfSpamClass() {
		return priorOfSpamClass;
	}

	/**
	 * this will set the prior of the spam class
	 * @param totalNoOFDocsInTraningSet these are the total no. of the emails in the traning set
	 */
	public void setPriorOfSpamClass(int totalNoOFDocsInTraningSet) {
		priorOfSpamClass = (double)getTotalDocsBelongingToSpam()/(double)totalNoOFDocsInTraningSet;
	}

	public HashMap<String, Integer> getText() {
		return spamText;
	}

	/**
	 * this will add the given token to the hashmap 
	 * if the token is new it will add ans set the value of value for this tokesn as 1
	 * if the token already exist then it will add 1 to the previous value, basically increasing its count
	 * @param token
	 */
	public void addTokenToSpamText(String token) 
	{
		
		// check if the token already exists
		// then just update the count
		if(spamText.containsKey(token))
		{
			int oldCount = spamText.get(token);
			int newCount = oldCount + 1;
			spamText.put(token,newCount);
		}
		// otherwise add the new token with count as 1
		else
		{
			spamText.put(token,1);
		}
		
		//update the totalNoOfTokens in the text
		if(spamText.containsKey(KEY_FOR_TOTAL_NO_OF_TOKENS_IN_SPAM_TEXT))
		{
			int oldCount = spamText.get(KEY_FOR_TOTAL_NO_OF_TOKENS_IN_SPAM_TEXT);
			int newCount = oldCount + 1;
			spamText.put(KEY_FOR_TOTAL_NO_OF_TOKENS_IN_SPAM_TEXT,newCount);
		}else
		{
			spamText.put(KEY_FOR_TOTAL_NO_OF_TOKENS_IN_SPAM_TEXT,1);
		}
		
	}
	
	/**
	 * @param token whose occureance we have to find in the spamText
	 * @return this returns the total occurence of the given token in the spam text 
	 */
	public static int getTotalOccurenceOFtokenInSpamText(String token)
	{
		// if the token is present in the text then return its value
		if(spamText.containsKey(token))
		{
			return spamText.get(token);
		}
		// otherwise just return 0
		else
		{
			return 0;
		}
	}
	
	/**
	 * this will add conditional probabilty of the given token for the spam class
	 * @param token
	 * @param valueOfProb
	 */
	
	public static void addContionalProbabiltyForSpam(String token,double valueOfProb)
	{
		conditioanlProbablityForTokenInSpamClass.put(token,valueOfProb);
	}
	
	/**
	 * 
	 * @param token
	 * @return this will return the conditioanlProbabilty for the spam class of the given token
	 */
	public static double getContionalProbabiltyForSpam(String token)
	{
				// if the token is present in the text then return its value
				if(conditioanlProbablityForTokenInSpamClass.containsKey(token))
				{
					return  conditioanlProbablityForTokenInSpamClass.get(token);
				}
				// otherwise just return 1 which means we are discarding this value as Log(1) will be zero, while calcualtinf the sumofLogs
				else
				{
					return 1;
				}
	}
	public static void printprobforspam()
	{
HashMap<String , Double> maxList = new HashMap();
		
		
		while(maxList.size() != 10)
		{
				String temp = findMax(maxList);
			
				System.out.println(temp + "   :  " + conditioanlProbablityForTokenInSpamClass.get(temp));
				maxList.put(temp, conditioanlProbablityForTokenInSpamClass.get(temp));
			
		}
//		for(String s: conditioanlProbablityForTokenInSpamClass.keySet())
//		{
//			System.out.println(s + "   :  " + conditioanlProbablityForTokenInSpamClass.get(s));
//		}
	}
	
	private static String findMax(HashMap<String, Double> x)
	{
		double max = Double.MIN_VALUE; 

		String maxkey = null;
		
		for(String s: conditioanlProbablityForTokenInSpamClass.keySet())
		{
			if(!x.containsKey(s))
			{
				if(conditioanlProbablityForTokenInSpamClass.get(s) > max)
				{
					max = conditioanlProbablityForTokenInSpamClass.get(s);
					maxkey = s;
				}
			}
		}
		return maxkey;
	}
	
	
	
	public static void printProb(String token)
	{
		// if the token is present in the text then return its value
		if(conditioanlProbablityForTokenInSpamClass.containsKey(token))
		{
			System.out.println("prob of token in spam "+conditioanlProbablityForTokenInSpamClass.get(token));
		}
		// otherwise just return 1 which means we are discarding this value as Log(1) will be zero, while calcualtinf the sumofLogs
		else
		{
			System.out.println("token not found");
		}
	}
	
}
