import java.util.Random;

public class RunDayTraderStrength {

	public static void main(String[] args) {
		// Print intro
		System.out.println("File:\t\tRunDayTraderStrength.java\nPurpose:\tThis file is responsible for running an instance of DayTraderStrength.java class.\nDate:\t\tNovember 2016\nCourse:\t\tCOSC 320 - Analysis of Algorithms"); 
		System.out.println("Authors:\t(Group 6)\n\t\t-Barrett\n\t\t-Brooke\n\t\t-Mark\n\t\t-Mitch\n\t\t-Zach\n");
		
		DayTraderStrength dts = new DayTraderStrength();
		
		System.out.println("Algorithm A:\n");
		int [] profitsForA = dailyPerformanceGenerator(3000);
		long astart = System.currentTimeMillis();
		int a = dts.algorithmAforStrength(profitsForA);
		long afinish = System.currentTimeMillis();
		long atime = afinish-astart;
		System.out.printf("Algorithm A (for %d random entries) = %d\n(calculated in %d milliseconds)\n", profitsForA.length, a, atime);
		
		System.out.println("\nAlgorithm B:\n");
		long bstart = System.currentTimeMillis();
		int b = dts.algorithmBforStrength(profitsForA);
		long bfinish = System.currentTimeMillis();
		long btime = bfinish-bstart;
		System.out.printf("Algorithm B (for the same %d entries as algorithm A) = %d\n(calculated in %d milliseconds)\n", profitsForA.length, b, btime);
		
		System.out.println("\nFurthermore...");
		for (int i = 10000; i <= 10000000; i = i*10) {
			int [] profits = dailyPerformanceGenerator(i);
			bstart = System.currentTimeMillis();
			b = dts.algorithmBforStrength(profits);
			bfinish = System.currentTimeMillis();
			btime = bfinish-bstart;
			System.out.printf("Algorithm B against %d random entries = %d\nCalculated in %d milliseconds\n", i, b, btime);
		}
	}
	
	public static int [] dailyPerformanceGenerator(int entryCount) {
		Random rdm = new Random();
		int [] profits = new int[entryCount];
		for (int i = 0; i < entryCount; i++) {
			profits[i] = rdm.nextInt(1000000) - 500000;
		}
		return profits;
	}
}
