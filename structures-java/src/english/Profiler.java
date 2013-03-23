package english;
import java.util.*;
public class Profiler {
	
	
	//GLOBAL VARIABLES
	static Collection col;
	
	
	public static void main(String[] args) {

		final long TEST_RUNS = 50;
		long masterTime = 0, averageTime = 0;
		
		for(int k=0; k<TEST_RUNS; k++){		
			col = new ArrayList<Double>();
			
			long fillTime, findTime, totalTime;

			
			fillTime = fillCollection(col);
			findTime = findCollection(col);						
			
			//Just outputs the time taken for each iteration of the test.
			totalTime = fillTime + findTime;// + sortTime;
			System.out.println("Fill Time: " + fillTime + ". TotalTime: "+totalTime+ ".");
			masterTime +=totalTime;
		}
		
		averageTime = masterTime / TEST_RUNS;
		System.out.println("average Total Time: "+averageTime);
	}
	
	
	/*
	 * This gets the current time, fills the collection with random numbers
	 * and gets the time afterwards.  
	 * randArrayFill is the time it takes to fill the collection.
	 */
	static long fillCollection(Collection col){
		
		long startTime, fillTime;
		
		startTime = System.nanoTime();
		for(int i =0; i<3000000; i++){
			col.add(Math.random());
		}
		fillTime = System.nanoTime() - startTime;
		
		return fillTime;
	}
	
	static long findCollection(Collection col){
		
		long startTime, findTime;
		
		startTime = System.nanoTime();
		
		
		//WE NEED TO FIGURE OUT A GOOD WAY FOR TESTING THE CONTAINS METHOD
		//WE SHOULD WRITE A TEST FOR FINDING HERE.
		//IT SHOULD CHOOSE LIKE 1000 OBJECTS THAT ARE FOR SURE IN THE COLLECTION
		//AND MAYBE 500 THAT AREN'T.  
		
		for(int i=0; i<100; i++){
		
			col.contains(i);
		}
		
		findTime = System.nanoTime() - startTime;
		return findTime;
	}	
	
}
