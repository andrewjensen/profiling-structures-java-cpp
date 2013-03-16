package english;
import java.util.*;

import junit.framework.Assert;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class Test_LinkedListImpl {

	final static int SIZE = 10000;
	List<Integer> ll;
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
		
		//ll = new LinkedList<Integer>();
		ll = new LinkedListImpl<Integer>();
		
		for(int i=0; i < randNums.length; i++){
			ll.add(randNums[i]);
		}
		
		
	}

	@Test
	public void testLinkedListImpl() {
		Assert.assertNotNull(ll);
	} 
	

	@Test
	public void testAddE() {
		
		Assert.assertEquals(SIZE, ll.size());
	}

	@Test
	public void testContains() {
		
		for(int i=0; i < SIZE; i++){
			Assert.assertTrue(ll.contains(randNums[i]));
		}
		
	}
	
	@Test
	public void testStuff() {
		
		
		ll = new LinkedListImpl<Integer>();
		ll.add(new Integer(1));
		ll.add(new Integer(2));
		ll.add(new Integer(3));
		
		Assert.assertTrue(ll.contains(new Integer(1)));
		Assert.assertFalse(ll.contains(new Integer(999)));
		
	}

}
