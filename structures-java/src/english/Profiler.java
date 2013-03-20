package english;
import java.util.*;
public class Profiler {

	public static void main(String[] args) {

		final long TEST_RUNS = 5;
		long masterTime = 0, averageTime = 0;
		
		for(int k=0; k<TEST_RUNS; k++){
		
			List<Double> arr = new ArrayList<Double>();
			
			
			long fillTime, collectionFillTime, presortedTime, totalTime, sortTime;
			
			
			fillTime = fillCollection(arr);
			sortTime = sortCollection(arr);
			
			//WE SHOULD WRITE A TEST FOR FINDING HERE.
			//IT SHOULD CHOOSE LIKE 1000 OBJECTS THAT ARE FOR SURE IN THE COLLECTION
			//AND MAYBE 500 THAT AREN'T.  
			
			
			
			
			
			
			
			/*
			 * Just outputs the time taken for each iteration of the test.
			 */
			totalTime = fillTime + sortTime;
			System.out.println("Fill Time: " + fillTime + ". Sort Time: "+ sortTime + ". TotalTime: "+totalTime+ ".");
			masterTime +=totalTime;
		}
		
		averageTime = masterTime/TEST_RUNS;
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
	
	
	/*
	 * This gets the time for sorting the collection.
	 * Returns -1 if we aren't actually sorting the collection.  
	 */
	static long sortCollection(Collection col){
		long startTime, sortTime;
		
		startTime = System.nanoTime();
		
		//THIS ONLY WORKS ON LIST<T> ...SO WHAT DO WE WANT TO DO FOR THIS TESTING?
		//WE COULD ONLY USE IT ON LIST OBJECTS, THAT WOULD BE FINE.
		//Collections.sort(col);
		
		sortTime = System.nanoTime() - startTime;
		if(sortTime < 2000)
			sortTime = -1;
		
		return sortTime;
	}
	
	
	
	
}
