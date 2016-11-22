/*
 * File: 	RunDayTraderStrength.java
 * Purpose: This file is responsible for running an instance of DayTraderStrength.java class. 
 * 
 * Date: 	November 2016
 * Course: 	COSC 320 - Analysis of Algorithms
 * Project Page: https://people.ok.ubc.ca/yongg/teaching/cosc320/projects/project-design-and-implementation-tasks.pdf
 * 
 * Authors: (Group 6)
 * 			Barrett 
 * 			Brooke
 * 			Mark
 * 			Mitch
 * 			Zack
 */
public class RunDayTraderStrength {
	
	//SpeedTest Counter
	static int speedTestCounter = 0;
	
	//Helper Method: Array Printing
	public static void printArray(int[] n){
		System.out.print("[");
		for(int i=0;i<n.length;i++){
			//Space control, (maximum 4 = 3 + 1 for sign)
			if(n[i]>=0){System.out.print(" ");} //sign space for +
			if(Math.abs(n[i])< 10){
				System.out.print("  "); //1-digit
			}else if(Math.abs(n[i])< 100){
				System.out.print(" ");//2-digit
			}
			System.out.print(n[i]+",");
		}
		System.out.println("]");
	}

	//Helper Method: Array Printing Override
	public static void printArray(int[] n, int r, int c){
		System.out.println("Range: "+r+"; Cost: "+c+";");
		printArray(n);
	}
	
	/**
	 * speedTest
	 * This method executes the algorithms, based on the input parameters, and displays the times
	 * 
	 * @param dts - The instance of DayTraderStrength, to run the algorithms from.
	 * @param numRecords - The number of records for the algorithms to run.
	 * @param numEntries - In each record to calculate, this is the number of entries per record.
	 * 						Ex. 365 Entries per Record, means that a single record would represent a year's
	 * 							worth of day net results. Stated another way, numEntries and numRecords
	 * 							make up the lengths of a 2D array.
	 * @param runAlgorithmA - Yes or No for running the (very slow) Algorithm A for a given speed test.
	 */
	public static void speedTest(DayTraderStrength dts, int numRecords, int numEntries, boolean runAlgorithmA){
		//Print
		speedTestCounter++;
		if(runAlgorithmA){
			System.out.println("[Speed Test "+speedTestCounter+"]: Algorithm A and B, running "+numRecords+" Records @ "+numEntries+" Entries/Record: ");
		}else{
			System.out.println("[Speed Test "+speedTestCounter+"]: ONLY Algorithm B, running "+numRecords+" Records @ "+numEntries+" Entries/Record: ");
		}
		//Arrays for strength results from A and B
		int[] strengthsA = new int[numRecords];
		int[] strengthsB = new int[numRecords];
		
		//-----------------------------------------------
		//Algorithm A
		//-----------------------------------------------
		
		if(runAlgorithmA){
			//A) Start Time
			long startTimeA = System.currentTimeMillis();
					
			//Run Algorithm A
			for(int i=0; i < numRecords; i++){
				//create records, compute and save Algo A results
				int[] tmp = dts.createRecord(numEntries);
				strengthsA[i]=dts.algorithmAforStrength(tmp);
			}
			
			//A) End time, and print elapsed time
			long stopTimeA = System.currentTimeMillis();
			long elapsedTimeA = stopTimeA - startTimeA;
			System.out.println("\tAlgorithm A: O(n^3) Poor Time Complexity\n\t\tTime for "+numRecords+" Records @ "+numEntries+"/Record: "+(elapsedTimeA/1000.0)+" seconds.\n");
		}
		
		//-----------------------------------------------
		//Algorithm B
		//----------------------------------------------
		
		//B) Start Time
		long startTimeB = System.currentTimeMillis();
				
		//Run Algorithm B
		for(int i=0; i < numRecords; i++){
			//create records, compute and save Algo B results
			int[] tmp = dts.createRecord(numEntries);
			strengthsB[i]=dts.algorithmBforStrength(tmp);
		}
				
		//B) End time, and print elapsed time
		long stopTimeB = System.currentTimeMillis();
		long elapsedTimeB = stopTimeB - startTimeB;
		System.out.println("\tAlgorithm B: Maximum Subarray (Kadane, 1984)\n\t\tTime for "+numRecords+" Records @ "+numEntries+"/Record: "+(elapsedTimeB/1000.0)+" seconds.\n");
		
	}
	
	//Main Method
	public static void main(String[] args) {
		//Print
		System.out.println("File:\t\tRunDayTraderStrength.java\nPurpose:\tThis file is responsible for running an instance of DayTraderStrength.java class.\nDate:\t\tNovember 2016\nCourse:\t\tCOSC 320 - Analysis of Algorithms"); 
		System.out.println("Authors:\t(Group 6)\n\t\t-Barrett\n\t\t-Brooke\n\t\t-Mark\n\t\t-Mitch\n\t\t-Zack\n");
		
		//Create Instance
		DayTraderStrength dts = new DayTraderStrength(10,5);
		
		//Run Speed tests, based on criteria parameters
		//!@# WARNING) ALGO A IS NOW IN EFFECT, AND LARGE NUMBER OF RECORDS WILL TAKE FOREVER...
		System.out.println("Beginning Speed Tests:\n");
		speedTest(dts, 8000, 365, true); //8,000 case
		speedTest(dts, 500000, 365, false); //500,000 case
		speedTest(dts, 5000000, 365, false);// 5,000,000 case ('multi-million');
		
		//End
		System.out.println("\nEnd of Speed Tests.");
	}//main

}//class
