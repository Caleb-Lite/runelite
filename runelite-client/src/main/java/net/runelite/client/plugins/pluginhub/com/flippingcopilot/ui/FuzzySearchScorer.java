package net.runelite.client.plugins.pluginhub.com.flippingcopilot.ui;

import net.runelite.client.plugins.pluginhub.com.flippingcopilot.model.ItemIdName;
import org.apache.commons.text.similarity.JaroWinklerDistance;
import org.apache.commons.text.similarity.LongestCommonSubsequence;
import org.apache.commons.text.similarity.SimilarityScore;

import javax.inject.Singleton;
import java.util.function.ToDoubleFunction;

@Singleton
public class FuzzySearchScorer
{

	// can be swapped, but i found jaro-winkler to do well considering the variable length of inputs
	// whereas levenshtein biases toward strings of same len, regardless of overlap
	private final SimilarityScore<Double> baseAlgorithm = new JaroWinklerDistance();

	public Double score(String query, String itemName)
	{
		query = query.toLowerCase().replace('-', ' ');
		itemName = itemName.toLowerCase().replace('-', ' ');

		// we raise the score for longest substring of a word, scoring within [0,1]
		String[] queryWords = query.split(" ");
		String[] itemWords = itemName.split(" ");
		double lcsScore = 0.0;
		for (String queryWord : queryWords)
		{
			for (String itemWord : itemWords)
			{
				int lcsLen = new LongestCommonSubsequence().longestCommonSubsequence(queryWord, itemWord).length();
				lcsScore = Math.max(lcsScore, ((double) lcsLen) / queryWord.length());
			}
		}

		// and also raise the score for string "closeness", but strongly prefer high closeness, scoring within [-0.5,0.5]
		double proximityScore = Math.log10(10 * baseAlgorithm.apply(query, itemName)) - 0.5;

		// subtract 1.0 to filter out low-scoring results
		return lcsScore + proximityScore - 1.0;
	}

	public ToDoubleFunction<ItemIdName> comparator(String query)
	{
		// We do this so that for example the items "Anti-venom ..." are still at the top
		// when searching "anti venom"
		return item -> score(
			query.toLowerCase().replace('-', ' '),
			item.getName().toLowerCase().replace('-', ' ')
		);
	}

}
