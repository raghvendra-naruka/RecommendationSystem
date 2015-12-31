package org.machine;


import java.util.HashMap;

/**
 * this class implements the Ham, i.e all the properties if the doc is Ham
  * @version      
 *        1.0, 28 Sep 2014  
 * @author          
 *       Manuj Singh
 */
public class HamClass {
	
	private final static String KEY_FOR_TOTAL_NO_OF_TOKENS_IN_HAM_TEXT = "totalNoOFTokens";
	
	/**
	 * this represnts total no of emails that belong to ham in the traning set
	 */
	private static double totalDocsBelongingToHam ;
	
	/**
	 * this defines the prior probabilty of the ham class
	 * i.e  totalDocsBelongingToHam/totalDocsInTheTraningSet
	 */
	private static double priorOfHamClass;
	
	/**
	 * this will contain all the tokens which are present in all the docs that
	 * belong to ham class, with their count as value
	 * hashMap<Token,count>
	 */
	private static HashMap<String ,Integer> hamText = new HashMap<String, Integer>();

	/**
	 * this will store the conditional probabilty of all the tokens present in the Vocabulary against the ham class
	 * i.e P(t|ham) 
	 */
	private static HashMap<String ,Double> conditioanlProbablityForTokenInHamClass = new HashMap<String, Double>();
	
	/**
	 * this returns the total no of tokens in the hamtext so far
	 * it is stored in a special key in the hamtext( hasmap) and is increased every time a token is added to the text
	 */
	public static int getTotalNoOFTokensInHamText()
	{
		return hamText.get(KEY_FOR_TOTAL_NO_OF_TOKENS_IN_HAM_TEXT);
	}
	
	/**
	 * @return total docs belonging to ham
	 */
	public double getTotalDocsBelongingToHam() {
		return totalDocsBelongingToHam;
	}
	
	public void setTotalDocsBelongingToHam(int totalHamDocs) {
		totalDocsBelongingToHam = totalHamDocs;
	}
	
	public static double getPriorOfHamClass() {
		return priorOfHamClass;
	}
	
	/**
	 * this will set the prior of the ham class
	 * @param totalNoOFDocsInTraningSet these are the total no. of the emails in the traning set
	 */
	public void setPriorOfHamClass(int totalNoOFDocsInTraningSet) {
		priorOfHamClass = (double)getTotalDocsBelongingToHam()/(double)totalNoOFDocsInTraningSet;
	}
	
	public HashMap<String, Integer> getText() {
		return hamText;
	}
	
	/**
	 * this will add the given token to the hashmap 
	 * if the token is new it will add and set the value of count for this tokesn as 1
	 * if the token already exist then it will add 1 to the previous value, basically increasing its count
	 * @param token
	 */
	public void addTokenToHamText(String token) 
	{
		
		// check if the token already exists
		// then just update the count
		if(hamText.containsKey(token))
		{
			int oldCount = hamText.get(token);
			int newCount = oldCount + 1;
			hamText.put(token,newCount);
		}
		// otherwise add the new token with count as 1
		else
		{
			hamText.put(token,1);
		}
		
		//update the totalNoOfTokens in the text
		if(hamText.containsKey(KEY_FOR_TOTAL_NO_OF_TOKENS_IN_HAM_TEXT))
		{
			int oldCount = hamText.get(KEY_FOR_TOTAL_NO_OF_TOKENS_IN_HAM_TEXT);
			int newCount = oldCount + 1;
			hamText.put(KEY_FOR_TOTAL_NO_OF_TOKENS_IN_HAM_TEXT,newCount);
		}else
		{
			hamText.put(KEY_FOR_TOTAL_NO_OF_TOKENS_IN_HAM_TEXT,1);
		}
		
	}
	
	/**
	 * @param token whose occureance we have to find in the hamText
	 * @return this returns the total occurence of the given token in the ham text 
	 */
	public static int getTotalOccurenceOFtokenInHamText(String token)
	{
		// if the token is present in the text then return its value
		if(hamText.containsKey(token))
		{
			return hamText.get(token);
		}
		// otherwise just return 0
		else
		{
			return 0;
		}
	}
	
	/**
	 * this will add conditional probabilty of the given token for the ham class
	 * @param token
	 * @param valueOfProb
	 */
	
	public static void addContionalProbabiltyForHam(String token,double valueOfProb)
	{
		conditioanlProbablityForTokenInHamClass.put(token,valueOfProb);
	}
	
	/**
	 * 
	 * @param token
	 * @return this will return the conditioanlProbabilty for the ham class of the given token
	 */
	public static double getContionalProbabiltyForHam(String token)
	{
				// if the token is present in the text then return its value
				if(conditioanlProbablityForTokenInHamClass.containsKey(token))
				{
					return  conditioanlProbablityForTokenInHamClass.get(token);
				}
				// otherwise just return 1
				else
				{
					return 1;
				}
	}
	
	public static void printprobforham()
	{
		HashMap<String , Double> maxList = new HashMap();
		
		
		while(maxList.size() != 11)
		{
				String temp = findMax(maxList);
			
				System.out.println(temp + "   :  " + conditioanlProbablityForTokenInHamClass.get(temp));
				maxList.put(temp, conditioanlProbablityForTokenInHamClass.get(temp));
			
		}
//		
//		for(String s: conditioanlProbablityForTokenInHamClass.keySet())
//		{
//			System.out.println(s + "   :  " + conditioanlProbablityForTokenInHamClass.get(s));
//		}
	}
	
	private static String findMax(HashMap<String, Double> x)
	{
		double max = Double.MIN_VALUE; 

		String maxkey = null;
		
		for(String s: conditioanlProbablityForTokenInHamClass.keySet())
		{
			if(!x.containsKey(s))
			{
				if(conditioanlProbablityForTokenInHamClass.get(s) > max)
				{
					max = conditioanlProbablityForTokenInHamClass.get(s);
					maxkey = s;
				}
			}
		}
		return maxkey;
	}
	
	
	public static void printProbHam(String token)
	{
		// if the token is present in the text then return its value
		// if the token is present in the text then return its value
		if(conditioanlProbablityForTokenInHamClass.containsKey(token))
		{
			System.out.println("prob of token in ham "+conditioanlProbablityForTokenInHamClass.get(token));
		}
		// otherwise just return 1 which means we are discarding this value as Log(1) will be zero, while calcualtinf the sumofLogs
		else
		{
			System.out.println("token not found");
		}
	}


	
	
}
