package english;
import java.util.ArrayList;
import java.util.Collections;
public class Profiler {

	public static void main(String[] args) {

		final long TEST_RUNS = 5;
		long masterTime = 0, averageTime = 0;
		
		for(int k=0; k<TEST_RUNS; k++){
		
			ArrayList<Double> arr = new ArrayList<Double>();
			
			
			long startTime, collectionFillTime, presortedTime, totalTime, sortedTime;
			
			/*
			 * This gets the current time, fills the collection with random numbers
			 * and gets the time afterwards.  
			 * randArrayFill is the time it takes to fill the collection.
			 */
			startTime = System.nanoTime();
			for(int i =0; i<3000000; i++){
				arr.add(Math.random());
			}
			collectionFillTime = System.nanoTime() - startTime;
			
			
			
			//WE SHOULD WRITE A TEST FOR FINDING HERE.
			//IT SHOULD CHOOSE LIKE 1000 OBJECTS THAT ARE FOR SURE IN THE COLLECTION
			//AND MAYBE 500 THAT AREN'T.  
			
			
			
			
			/*
			 * This gets the time for sorting the collection.  
			 */
			presortedTime = System.nanoTime();
			Collections.sort(arr);
			sortedTime = System.nanoTime() - presortedTime;
			
			/*
			 * Just outputs the time taken for each iteration of the test.
			 */
			totalTime = System.nanoTime()-startTime;
			System.out.println("Fill Time: " + collectionFillTime + ". Sort Time: "+ sortedTime + ". TotalTime: "+totalTime+ ".");
			masterTime +=totalTime;
		}
		
		averageTime = masterTime/TEST_RUNS;
		System.out.println("average Total Time: "+averageTime);
	}
}
