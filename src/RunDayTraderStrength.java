import java.util.Random;

public class RunDayTraderStrength {

	public static void main(String[] args) {
		// Print intro
		System.out.println("File:\t\tRunDayTraderStrength.java\nPurpose:\tThis file is responsible for running an instance of DayTraderStrength.java class.\nDate:\t\tNovember 2016\nCourse:\t\tCOSC 320 - Analysis of Algorithms"); 
		System.out.println("Authors:\t(Group 6)\n\t\t-Barrett\n\t\t-Brooke\n\t\t-Mark\n\t\t-Mitch\n\t\t-Zach\n");
		
		Random rdm = new Random();
		
		DayTraderStrength dts = new DayTraderStrength();
		
		int entries = 10000000;
		
		int [] profits = new int[entries];
		
		for (int i = 0; i < entries; i++) {
			profits[i] = rdm.nextInt(1000000) - 500000;
		}
		
//		System.out.println("Starting A");
//		long astart = System.currentTimeMillis();
//		int a = dts.algorithmAforStrength(profits);
//		long afinish = System.currentTimeMillis();
//		long atime = afinish-astart;
//		System.out.printf("Algorithm A (for %d random entries) = %d\n(calculated in %d milliseconds)\n", entries, a, atime);
		
		System.out.println("\nStarting B");
		long bstart = System.currentTimeMillis();
		int b = dts.algorithmBforStrength(profits);
		long bfinish = System.currentTimeMillis();
		long btime = bfinish-bstart;
		System.out.printf("Algorithm B (for %d random entries) = %d\n(calculated in %d milliseconds)", entries, b, btime);
	}
}
