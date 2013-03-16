package english;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Random;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class Test_SortedSetImpl {

	final static int SIZE = 100000;
	Set<Integer> ss;
	static Integer[] randNums;
	static Random rand;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	
		rand = new Random();
		randNums = new Integer[SIZE];
		for(int i=0; i < SIZE; i++){
			randNums[i] = rand.nextInt();
		}
	}

	@Before
	public void setUp() throws Exception {
	
		//ss = new SortedSet<Integer>();
		ss = new SortedSetImpl<Integer>();
		
		for(int i=0; i < randNums.length; i++){
			ss.add(randNums[i]);
		}
	
	}


	@Test
	public void testAdd() {
		
		Assert.assertEquals(SIZE, ss.size());
	}

	@Test
	public void testContains() {

		for(int i=0; i < SIZE; i++){
			Assert.assertTrue(ss.contains(randNums[i]));
		}
	
	}

}
