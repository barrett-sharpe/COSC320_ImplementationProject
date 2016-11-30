import java.util.Random;

public class RunDayTraderStrength {

	public static void main(String[] args) {
		// Print intro
		System.out.println("File:\t\tRunDayTraderStrength.java\nPurpose:\tThis file is responsible for running an instance of DayTraderStrength.java class.\nDate:\t\tNovember 2016\nCourse:\t\tCOSC 320 - Analysis of Algorithms"); 
		System.out.println("Authors:\t(Group 6)\n\t\t-Barrett\n\t\t-Brooke\n\t\t-Mark\n\t\t-Mitch\n\t\t-Zach\n");
		
		Random rdm = new Random();
		
		DayTraderStrength dts = new DayTraderStrength();
		
//		int [] a = {-1, -3, -2, 7, -7, 54, -41, 8, -2, -4, -6, -30}; // 10
//		
//		int x = dts.algorithmAforStrength(a);
//		int y = dts.algorithmBforStrength(a);
//		
//		System.out.println("\n\n\n***********\n\nActual Result: 18");
//		
//		System.out.println("Algorithm A: " + x);
//		
//		System.out.println("Algorithm B: " + y + "\n\n*************");
		
		
		int [] profits = new int[30];
		
		for (int i = 0; i < 30; i++) {
			profits[i] = rdm.nextInt(1000000) - 500000;
		}
		
		System.out.println("Starting A");
		long astart = System.currentTimeMillis();
		int a = dts.algorithmAforStrength(profits);
		long afinish = System.currentTimeMillis();
		long atime = afinish-astart;
		System.out.println("Algorithm A (10000) = " + a + " (calculated in " + atime + " milliseconds)");
		
		System.out.println("\nStarting B");
		long bstart = System.currentTimeMillis();
		int b = dts.algorithmBforStrength(profits);
		long bfinish = System.currentTimeMillis();
		long btime = bfinish-bstart;
		System.out.println("Algorithm B (10000) = " + b + " (calculated in " + btime + " milliseconds)");
	}

}
