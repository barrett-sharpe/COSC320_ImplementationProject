import java.util.ArrayList;

public class DayTraderStrength {
	
//	public int algorithmAforStrength(int [] profits) {
//		int sum = 0;
//		int curmax = 0;
//		
//		for(int i=1;i<profits.length;i++){
//			for(int n=0;n+i<profits.length;n++){
//				sum = sumSubArray(n, n+i, profits);
//				if (curmax < sum)
//					curmax = sum;
//			}
//		}
//		return curmax;
//	}
//	
//	private int sumSubArray(int a,int b, int[] arr) {
//		int sum = 0;	
//		for(int i=a;i<b;i++) {
//			sum = sum + arr[i];
//		}
//		return sum;
//	}
	
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

//	public int algorithmBforStrength(int [] profits)
//	{
//		// Get simplified profits (sequential positive and negative days are added together).
//		ArrayList<Integer> condensedProfits = simplify(profits);
//		
//		// First entry is always positive, so the potentialMax is set to that.
//		int potentialMax = condensedProfits.get(0);
//		
//		int lastMax = 0;
//		
//		/***************
//		 * MAIN LOOP
//		 * For each entry of condensedProfits (alternating between positive and negative),
//		 * if the next negative and positive combine to be an improvement on potentialMax (i.e. are > 0)
//		 * AND the current potentialMax is greater than the absolute value of the next negative,
//		 * then combining all three get you the highest possible potentialMax so far.
//		 * If the next negative and positive combine to less than 0, then the consecutive chain is broken
//		 * and the current potentialMax is stored away (lastMax) to be checked against the next potentialMax
//		 * either when the next consecutive chain is broken, or we reach the end of the condensedProfits
//		 * list. If potentialMax > lastMax, lastMax gets the value of potentialMax. Then, finally, we return
//		 * the greater of lastMax or potentialMax.
//		 */
//		
//		int i = 0;
//		
//		// Only need to go through the loop if there is a next negative and positive left to check.
//		while (i < condensedProfits.size() - 2)
//		{
//			// Setting quick-to-reference integers for next two values (since we always start with a positive,
//			// and then alternate, we can know which is which).
//			int neg = condensedProfits.get(i+1);
//			int pos = condensedProfits.get(i+2);
//			
//			// If the two together will improve potentialMax...
//			if (neg + pos >= 0)
//			{
//				// and if max + neg > 0...
//				if (potentialMax + neg > 0)
//				{
//					// then max + neg + pos is a greater consecutive sum than either max or pos alone.
//					potentialMax += neg + pos;
//				}
//				// else max + neg <= 0. So pos alone is at least as high as current max,
//				else
//				{
//					potentialMax = pos;
//				}
//			}
//			// neg is more negative than pos is positive.
//			else
//			{
//				// make lastMax the greater of itself or potentialMax
//				lastMax = Math.max(lastMax, potentialMax);
//				potentialMax = pos;
//			}
//			
//			// iterate by two (so we are looking at the next neg/pos pair, if both exist)
//			i = i + 2;
//		}
//		
//		return Math.max(lastMax, potentialMax);
//	}
	
	public int algorithmBforStrength(int [] profits)
	{
		// Get simplified profits (sequential positive and negative days are added together).
		ArrayList<Integer> condensedProfits = simplify(profits);
		
		// First entry is always positive, so the potentialMax is set to that.
		int potentialMax = condensedProfits.get(0);
		
		int lastMax = 0;
		
//		int otherPossibleMax = 0;
		
		/***************
		 * MAIN LOOP
		 * For each entry of condensedProfits (alternating between positive and negative),
		 * if the next negative and positive combine to be an improvement on potentialMax (i.e. are > 0)
		 * AND the current potentialMax is greater than the absolute value of the next negative,
		 * then combining all three get you the highest possible potentialMax so far.
		 * If the next negative and positive combine to less than 0, then the consecutive chain is broken
		 * and the current potentialMax is stored away (lastMax) to be checked against the next potentialMax
		 * either when the next consecutive chain is broken, or we reach the end of the condensedProfits
		 * list. If potentialMax > lastMax, lastMax gets the value of potentialMax. Then, finally, we return
		 * the greater of lastMax or potentialMax.
		 */
		
		int i = 0;
		
		// Only need to go through the loop if there is a next negative and positive left to check.
		while (i < condensedProfits.size() - 2)
		{
			// Setting quick-to-reference integers for next two values (since we always start with a positive,
			// and then alternate, we can know which is which).
			int neg = condensedProfits.get(i+1);
			int pos = condensedProfits.get(i+2);
			
			if (neg + pos >= 0 || potentialMax + neg >= 0)
			{
				if (neg + pos >= 0 && potentialMax + neg > 0)
				{
					potentialMax += neg + pos;
				}
				else if (potentialMax + neg > 0)
				{
					if (potentialMax + neg + pos > pos)
					{
						potentialMax += neg + pos;
					}
				}
				else
				{
					potentialMax = pos;
				}
			}
			else
			{
				// make lastMax the greater of itself or potentialMax
				lastMax = Math.max(lastMax, potentialMax);
				potentialMax = pos;
			}
			
			// iterate by two (so we are looking at the next neg/pos pair, if both exist)
			i = i + 2;
		}
		
		return Math.max(lastMax, potentialMax);
	}

	public ArrayList<Integer> simplify(int [] profits)
	{
		
		ArrayList<Integer> simplifiedProfits = new ArrayList<Integer>();
		
		int i = 0;
		
		// ignore any starting days with non-positive profits
		while (profits[i] <= 0 && i < profits.length)
		{
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
		// Else, the trader never made a profit. simplifiedProfits list = { 0 }.
		else
		{
			simplifiedProfits.add(0);
		}

		return simplifiedProfits;
	}
}
