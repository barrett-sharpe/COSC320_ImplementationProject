/*
 * Day Trader Strength
 * 
 * Purpose:
 * 
 * COSC 320 - Analysis of Algorithms
 * Project Page: https://people.ok.ubc.ca/yongg/teaching/cosc320/projects/project-design-and-implementation-tasks.pdf
 * November, 2016
 * 
 * Written By (Group 6):
 * - Barrett 
 * - Brooke
 * - Mark
 * - Mitch
 * - Zack
 */
public class DayTraderStrength {
	
	//Constants, to control the range of createRecord() results    
	public Integer RANGE = 120;
	public Integer COST = 60;
		
	//Constructors
	public DayTraderStrength(){	};
	public DayTraderStrength(int range, int cost){ RANGE = range; COST = cost;	};
	
	//Methods
	/**
	 * createRecord
	 * This method was provided to us by Dr. Yong Gao, and serves to generate an array of random integer values representing the trader's daily trade results (either a PROFIT (>0) , or a LOSS (<0) ).
	 * 
	 * @param int numRecords
	 * @return int[]
	 */
	public int[] createRecord(int numEntries){    
		long seed = System.currentTimeMillis();  
		java.util.Random rand = new java.util.Random(seed);    
		int[] a = new int[numEntries];    
		for(int i = 0; i < numEntries; i++){        
			a[i] = rand.nextInt(RANGE) - COST;
		}      
		 return a;
	}
	
	/**
	 * algorithmAforStrength
	 * This method is the straightforward cubic time algorithm to compute the performance of the trader over all possible time periods, and then take the maximum of these values as the strength.
	 * Maximum Subarray Problem: https://en.wikipedia.org/wiki/Maximum_subarray_problem
	 * 
	 * @param int[] profits
	 * @return int strength
	 */
	public int algorithmAforStrength(int[] profits){
		//Vars
		int sum = 0;
		int curmax = 0;
		
		for(int i=1;i<profits.length;i++){
			for(int n=0;n+i<profits.length;n++){
				sum =sumSubArray(n,n+i,profits);
				if (curmax < sum)
					curmax = sum;
			}
		}
		return curmax;
	}
	
	/**
	 * algorithmBforStrength
	 * This method is the 'more efficient algorithm' to compute the performance of the trader over all possible time periods, and then take the maximum of these values as the strength.
	 * 
	 * @param int[] profits
	 * @return int strength
	 */
	public int algorithmBforStrength(int[] profits){
		//Variables
		int sum = 0;
		int maximum = 0;
		//For
		for(int i=0;i<profits.length;i++){
			sum = Math.max(0, (sum + profits[i]));
			maximum = Math.max(maximum, sum);
		}
		return maximum;
	}
	
	//Method to sum subarray of an array
	private int sumSubArray(int a,int b, int[] arr) {
		int sum = 0;
		
		for(int i=a;i<b;i++) {
			sum = sum + arr[i];
		}
		return sum;
	}
	
	//Auto-generated getters and setters
	public Integer getRANGE() {	return RANGE;	}
	public void setRANGE(Integer rANGE) {	RANGE = rANGE;	}
	public Integer getCOST() {	return COST;	}
	public void setCOST(Integer cOST) {		COST = cOST;	}
	
}
