import java.util.ArrayList;

public class DayTraderStrength {
	
	public int algorithmAforStrength(int [] profits)
	{
		int max = 0;
		
		for (int i = 0; i < profits.length; i++)
		{
			for (int j = i; j < profits.length; j++)
			{
				int sum = 0;
				for (int k = i; k <= j; k++)
				{
					sum += profits[k];
				}
				max = Math.max(max, sum);
			}
		}
		
		return max;
	}
	
	public int algorithmBforStrength(int [] profits)
	{
		// First, we get a condensed profits list
		ArrayList<Integer> condensedProfits = simplify(profits);
		
		if (condensedProfits.get(0) < 0)
		{
			/*****
			 * If the first element in condensedProfits is negative
			 * then because of the way the simplifying method works
			 * we know that the this first value is the only value
			 * and it represents the best day the trader had, even
			 * if it still wasn't a profitable day.
			 */ 
			return condensedProfits.get(0);
		}
		
		// We know condensedProfits will always starts with the combined
		// performance of the first set of consecutive profitable days.
		
		// Best So Far (bsf)
		int bsf = condensedProfits.get(0);
		
		// Current Consecutive Performance (ccp)
		int ccp = bsf;
		
		// ArrayList index
		int i = 0;
		
		// Just so this doesn't need to be recalculated with each iteration.
		int iStop = condensedProfits.size() - 2;
		
		// Begin Loop
		while (i < iStop)
		{
			// Because of the structure of condensedProfits, we know that
			// the next 2 elements will be a negative followed by a positive
			int neg = condensedProfits.get(i+1);
			int pos = condensedProfits.get(i+2);
			
			if (pos > ccp)
			{
				if (pos + neg > 0) // i.e. pos > -neg
				{
					if (ccp + neg > 0) // i.e. ccp > -neg
					{
						if (ccp > neg + pos)
						{
							if (pos > ccp + neg)
							{
								ccp += pos + neg;
								bsf = Math.max(bsf, ccp);
							}
						}
						else
						{
							if (pos > ccp + neg)
							{
								ccp += pos + neg;
								bsf = Math.max(bsf, ccp);
							}
						}
					}
					else // ccp <= -neg
					{
						if (ccp > neg + pos)
						{
							if (pos > ccp + neg)
							{
								ccp = pos;
								bsf = Math.max(bsf, ccp);
							}
						}
						else // ccp < neg + pos
						{
							if (pos > ccp + neg)
							{
								ccp = pos;
								bsf = Math.max(bsf, ccp);
							}
						}
					}
				}
				else // pos <= -neg
				{
					if (ccp + neg > 0) // i.e. ccp > -neg
					{
						if (ccp > neg + pos)
						{
							if (pos > ccp + neg)
							{
								
							}
							else
							{
								
							}
						}
						else // ccp <= neg + pos
						{
							if (pos > ccp + neg)
							{
								
							}
							else
							{
								
							}
						}
					}
					else // ccp <= -neg
					{
						if (ccp > neg + pos)
						{
							if (pos > ccp + neg)
							{
								ccp = pos;
								bsf = Math.max(bsf, ccp);
							}
							else
							{
								
							}
						}
						else
						{
							if (pos > ccp + neg)
							{
								
							}
							else
							{
								
							}
						}
					}
				}
			}
			else // pos <= ccp
			{
				if (pos + neg > 0) // i.e. pos > -neg
				{
					if (ccp + neg > 0) // i.e. ccp > -neg
					{
						if (ccp > neg + pos)
						{
							if (pos > ccp + neg)
							{
								ccp += pos + neg;
								bsf = Math.max(bsf, ccp);
							}
							else
							{
								ccp += pos + neg;
								bsf = Math.max(bsf, ccp);
							}
						}
						else
						{
							if (pos > ccp + neg)
							{
								
							}
							else
							{
								
							}
						}
					}
					else // ccp <= -neg
					{
						if (ccp > neg + pos)
						{
							if (pos > ccp + neg)
							{
								
							}
							else
							{
								
							}
						}
						else
						{
							if (pos > ccp + neg)
							{
								
							}
							else
							{
								
							}
						}
					}
				}
				else // pos <= -neg
				{
					if (ccp + neg > 0) // i.e. ccp > -neg
					{
						if (ccp > neg + pos)
						{
							if (pos > ccp + neg)
							{
								bsf = Math.max(bsf, ccp);
								ccp += pos + neg;
							}
							else
							{
								bsf = Math.max(bsf, ccp);
								ccp += pos + neg;
							}
						}
						else
						{
							if (pos > ccp + neg)
							{
								
							}
							else
							{
								
							}
						}
					}
					else // ccp <= -neg
					{
						if (ccp > neg + pos)
						{
							if (pos > ccp + neg)
							{
								bsf = Math.max(bsf, ccp);
								ccp = pos;
							}
							else
							{
								
							}
						}
						else
						{
							if (pos > ccp + neg)
							{
								
							}
							else
							{
								
							}
						}
					}
				}
			}
			
			// Iterate
			i = i + 2;
		}
		
		return Math.max(bsf, ccp);
	}

	/*****
	 * Profits Array Simplification
	 * @param profits -> the array of the day traders daily performance
	 * @return A simplified list where the first entry is the sum
	 * 			of the first positive profit day with all consecutive
	 * 			days of positive profit after, followed by the sum
	 * 			of the first negative profit day with all consecutive
	 * 			days of negative profit after, then alternating between
	 * 			sums of consecutive positive days and consecutive negative
	 * 			days until all elements of the original profits array
	 * 			have been traced through.
	 */
	public ArrayList<Integer> simplify(int [] profits)
	{
		
		ArrayList<Integer> simplifiedProfits = new ArrayList<Integer>();
		
		int i = 0;
		int leastNegative = Integer.MIN_VALUE;
		
		// ignore any starting days with non-positive profits
		while (profits[i] <= 0 && i < profits.length)
		{
			leastNegative = Math.max(leastNegative, profits[i]);
			i++;
		}
		
		// If they were not all negative profit days...
		if (i < profits.length)
		{
			// Start the first entry to simplifiedProfits with the first profitable day.
			int curVal = profits[i];
			
			boolean lastWasPositive = true;
			
			// Move to the next entry of profits
			i++;
			
			/***************
			 * MAIN LOOP
			 * Here, for each iteration of the loop if the next element of profits
			 * is the same sign (pos/neg) as the last, then its value is added
			 * to the current entry of simplifiedProfits, if not, it starts
			 * the next entry of simplifiedProfits.
			 */
			while (i < profits.length)
			{
				// Noting if this next element is non-negative.
				boolean nextIsPositive = profits[i] >= 0;
				
				// If the next element has the same sign as the last element...
				if (nextIsPositive == lastWasPositive)
				{
					// its value is added to the current entry of the simplifiedProfits list.
					curVal += profits[i];
				}
				// Else, it's not the same element...
				else
				{
					// So, the current simplified entry is added to the list,
					simplifiedProfits.add(curVal);
					
					// the next element starts the next current entry,
					curVal = profits[i];
					
					// and the (pos/neg) boolean is switched.
					lastWasPositive = !lastWasPositive;
				}
				
				i++;
			}
			// Add the final entry to simplifiedProfits
			simplifiedProfits.add(curVal);
		}
		else
		{
			// Else, the trader never made a profit.
			// In this case, the least negative single day
			// is their best
			simplifiedProfits.add(leastNegative);
		}

		return simplifiedProfits;
	}
}
