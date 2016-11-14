/*
 * Run Day Trader Strength
 * 
 * Purpose: To run the Day Trader Strength 
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
public class RunDayTraderStrength {
	
	//Array printing method
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

	//Array printing method
	public static void printArray(int[] n, int r, int c){
		System.out.println("Range: "+r+"; Cost: "+c+";");
		printArray(n);
	}
	
	//Speed Test
	public static void speedTest(DayTraderStrength dts, int numRecords, int numEntries){
		
		//Start time
		long startTime = System.currentTimeMillis();
		
		//Compute numRecords records
		int[] strengths = new int[numRecords];
		for(int i=0; i < numRecords; i++){
			//create records, and compute algoA and algoB
			int[] tmp = dts.createRecord(numEntries);
			dts.algorithmAforStrength(tmp); //don't save, just run for now. A not implemented.
			strengths[i] = dts.algorithmBforStrength(tmp);
		}
		
		//Print strengths[0] to confirm
		//System.out.println("Strength[0]: "+strengths[0]);
		
		//End time
		long stopTime = System.currentTimeMillis();
	    long elapsedTime = stopTime - startTime;
	    System.out.println("________________________________\nTime for "+numRecords+" Records @ "+numEntries+"/Record: "+(elapsedTime/1000.0)+" seconds.\n________________________________");
	}
	
	//Main Method
	public static void main(String[] args) {
		//Create Instance
		DayTraderStrength dts = new DayTraderStrength(10,5);
		
		//Test Output
		/*----------------------------------------------------------------------
		System.out.println("Testing DTS Methods...");
		//Create 4 records
		System.out.println("Creating Records:");
		for(int j=0;j<4;j++){
			printArray(dts.createRecord(20),dts.getRANGE(),dts.getCOST());
			dts.setRANGE(dts.getRANGE()+50);
			dts.setCOST(dts.getCOST()+25);
		}
		------------------------------------------------------------------------*/
		
		//Create A Record
		int[] r1 = dts.createRecord(50);
		System.out.println("Trader A:");
		printArray(r1);
		System.out.println("Trader A) A-Strength is: "+dts.algorithmAforStrength(r1));
		System.out.println("Trader A) B-Strength is: "+dts.algorithmBforStrength(r1));
		
		//Speed tests, based on criteria
		System.out.println("\nBeginning Speed Tests:");
		speedTest(dts, 8000, 365); //8,000 case
		speedTest(dts, 500000, 365); //500,000 case
		speedTest(dts, 5000000, 365);// 5,000,000 case ('multi-million');
	}//main

}//class
