package english;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeSet;


public class DynamicProfiler
{
	public static final int TEST_RUNS = 10;
	
	public static void main(String[] args)
	{
		if (args.length != 1)
		{
			System.out.println("USAGE: DynamicProfiler testNumber (1-8)");
			return;
		}
		
		DynamicProfiler std = null;
		DynamicProfiler ours = null;
		
		int test = Integer.parseInt(args[0]);
		switch (test)
		{
		case 1:
			std = new DynamicProfiler("Std. ArrayList", TEST_RUNS);
			for (int i = 0; i < TEST_RUNS; i++)
			{
				std.assignCollection(new ArrayList<Double>());
				std.run();
			}
			std.printResults();
			break;
		case 2:
			ours = new DynamicProfiler("User ArrayList", TEST_RUNS);
			for (int i = 0; i < TEST_RUNS; i++)
			{
				ours.assignCollection(new ArrayListImpl<Double>());
				ours.run();
			}
			ours.printResults();
			break;
		case 3:
			std = new DynamicProfiler("Std. LinkedList", TEST_RUNS);
			for (int i = 0; i < TEST_RUNS; i++)
			{
				std.assignCollection(new LinkedList<Double>());
				std.run();
			}
			std.printResults();
			break;
		case 4:
			ours = new DynamicProfiler("User LinkedList", TEST_RUNS);
			for (int i = 0; i < TEST_RUNS; i++)
			{
				ours.assignCollection(new LinkedListImpl<Double>());
				ours.run();
			}
			ours.printResults();
			break;
		case 5:
			std = new DynamicProfiler("Std. Set", TEST_RUNS);
			for (int i = 0; i < TEST_RUNS; i++)
			{
				std.assignCollection(new HashSet<Double>());
				std.run();
			}
			std.printResults();
			break;

		case 6:
			ours = new DynamicProfiler("User Set", TEST_RUNS);
			for (int i = 0; i < TEST_RUNS; i++)
			{
				ours.assignCollection(new SetImpl<Double>());
				ours.run();
			}
			ours.printResults();
			break;

		case 7:
			std = new DynamicProfiler("Std. SortedSet", TEST_RUNS);
			for (int i = 0; i < TEST_RUNS; i++)
			{
				std.assignCollection(new TreeSet<Double>());
				std.run();
			}
			std.printResults();
			break;

		case 8:
			ours = new DynamicProfiler("User SortedSet", TEST_RUNS);
			for (int i = 0; i < TEST_RUNS; i++)
			{
				ours.assignCollection(new SortedSetImpl<Double>());
				ours.run();
			}
			ours.printResults();
			break;
		default:
			break;
		}
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
		
		System.out.println(title+": \n");
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
		System.out.println();
		System.out.println("Average Add Time (nanoseconds): "+getAverageFillTime());
		System.out.println("Average Find Time (nanoseconds): "+getAverageFindTime());
//		System.out.println("-----------------------------------------");
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
		
			col.contains(i+0.0);
		}
		
		findTime = System.nanoTime() - startTime;
		return findTime;
	}
	
}
