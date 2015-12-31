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
import java.util.ArrayList;
import org.bigdata.TwitterDataAnalysis.*;

public class TestFile {
	
	/**
	 * this represents the test file
	 */
	private String tweet;
	
	/**
	 * this will contain all the  tokens present in the testDocument
	 */
	public  ArrayList<String> tokenList = new ArrayList<String>();
	
	public TestFile(String  tweet)
	{
		this.tweet = tweet;
	}
	
	public void ExtractTokens()
	{
		
		String[] splited = this.tweet.split(" ");

		for(String token : splited)
		{
			if(Twitter.filterStopWords.equalsIgnoreCase("y"))
			{
				// if this is not a stop word only then add it to the vocab
				if(!Filter.isThisAStopWord(token))
				{
					// add each token to the tokenList
					tokenList.add(token);
				}
			}else
			{
				// add each token to the tokenList
				tokenList.add(token);
			}
			
		}
	}
	
			
	
	public double calculateBayesForSpam()
	{
		System.out.println("prior of spam class "+SpamClass.getPriorOfSpamClass());
		double sumOfLogs = Math.log(SpamClass.getPriorOfSpamClass());
		for(String token : tokenList)
		{
			System.out.println("Prob of token for "+ token + " in spam " +Math.log(SpamClass.getContionalProbabiltyForSpam(token)));
			sumOfLogs += Math.log(SpamClass.getContionalProbabiltyForSpam(token));
		}
		
		return sumOfLogs;
	}
	
	public double calculateBayesForHam()
	{
		System.out.println("prior of ham class "+HamClass.getPriorOfHamClass());
		double sumOfLogs = Math.log(HamClass.getPriorOfHamClass());
		for(String token : tokenList)
		{
			System.out.println("Prob of token for "+ token + " in ham " + Math.log(HamClass.getContionalProbabiltyForHam(token)));
			sumOfLogs += Math.log(HamClass.getContionalProbabiltyForHam(token));
		}
		
		return sumOfLogs;
	}
}
