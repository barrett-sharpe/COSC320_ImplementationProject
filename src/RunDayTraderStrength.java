import java.util.Random;

public class RunDayTraderStrength {

	public static void main(String[] args) {
		// Print intro
		System.out.println("File:\t\tRunDayTraderStrength.java\nPurpose:\tThis file is responsible for running an instance of DayTraderStrength.java class.\nDate:\t\tNovember 2016\nCourse:\t\tCOSC 320 - Analysis of Algorithms"); 
		System.out.println("Authors:\t(Group 6)\n\t\t-Barrett\n\t\t-Brooke\n\t\t-Mark\n\t\t-Mitch\n\t\t-Zach\n");
		
		Random rdm = new Random();
		
		DayTraderStrength dts = new DayTraderStrength();
		
		
		int [] profits = new int[1000];
		
		for (int i = 0; i < 1000; i++) {
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
