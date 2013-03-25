package english;

import java.util.ArrayList;
import java.util.Collection;

public class DynamicProfiler
{
	
	
	//GLOBAL VARIABLES
	public static final int TEST_RUNS = 100;	//TODO: resize for better results
	public static String[] words;
	
	public static void main(String[] args)
	{
		//TODO: load the words...
		
		DynamicProfiler stdArrayList = new DynamicProfiler("Std. ArrayList", TEST_RUNS);
		for (int i = 0; i < TEST_RUNS; i++)
		{
			stdArrayList.assignCollection(new ArrayList<Double>());
			stdArrayList.run();
		}
		stdArrayList.printResults();


		DynamicProfiler ourArrayList = new DynamicProfiler("User ArrayList", TEST_RUNS);
		for (int i = 0; i < TEST_RUNS; i++)
		{
			ourArrayList.assignCollection(new ArrayListImpl<Double>());
			ourArrayList.run();
		}
		ourArrayList.printResults();

		
		
	}
	
	
	private String title;
	private Collection col;

	private int testRuns;
	private long[] fillTimes;
	private long[] findTimes;
	private int currentRun;
	
	public DynamicProfiler(String title, int testRuns)
	{
		this.title = title;
		this.col = null;
		
		this.testRuns = testRuns;
		this.fillTimes = new long[testRuns];
		this.findTimes = new long[testRuns];
		
		this.currentRun = 0;
		
		System.out.println(title+": ");
	}
	
	public void assignCollection(Collection col)
	{
		this.col = col;
	}
	
	public void run()
	{
		System.out.print((currentRun+1)+" ");
		
//		long fillTime, findTime, totalTime;
		
		fillTimes[currentRun] = fillCollection(col);
		findTimes[currentRun] = findCollection(col);						
		
		//Just outputs the time taken for each iteration of the test.
//		totalTime = fillTime + findTime;// + sortTime;
//		System.out.println("Fill Time: " + fillTime + ". TotalTime: "+totalTime+ ".");
		
		this.currentRun++;
	}
	
	public void printResults()
	{
		System.out.println();
		System.out.println("Average Fill Time: "+getAverageFillTime());
		System.out.println("Average Find Time: "+getAverageFindTime());
		System.out.println("-----------------------------------------");
	}

	public long getAverageFillTime()
	{
		long totalTime = 0;
		for (int i = 0; i < testRuns; i++)
			totalTime += fillTimes[i];
		
		return totalTime / testRuns;
	}

	public long getAverageFindTime()
	{
		long totalTime = 0;
		for (int i = 0; i < testRuns; i++)
			totalTime += findTimes[i];
		
		return totalTime / testRuns;
	}
	
	
	/**
	 * This gets the current time, fills the collection with random numbers
	 * and gets the time afterwards.  
	 * randArrayFill is the time it takes to fill the collection.
	 */
	private long fillCollection(Collection col) {
		
		long startTime, fillTime;
		
		startTime = System.nanoTime();
		for (int i =0; i<600000; i++) {	//TODO: how many elements should we add?
			col.add(Math.random());
		}
		fillTime = System.nanoTime() - startTime;
		
		return fillTime;
	}
	
	private long findCollection(Collection col) {
		
		long startTime, findTime;
		
		startTime = System.nanoTime();
		
		
		//WE NEED TO FIGURE OUT A GOOD WAY FOR TESTING THE CONTAINS METHOD
		//WE SHOULD WRITE A TEST FOR FINDING HERE.
		//IT SHOULD CHOOSE LIKE 1000 OBJECTS THAT ARE FOR SURE IN THE COLLECTION
		//AND MAYBE 500 THAT AREN'T.  
		
		for (int i=0; i<100; i++) {
		
			col.contains(i);
		}
		
		findTime = System.nanoTime() - startTime;
		return findTime;
	}
	
}
