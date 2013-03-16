package english;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class Test_ArrayListImpl
{
	public static final int NUMBERS_TO_ADD = 20;
	
	public static List<Integer> list;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{
	}

	@Before
	public void setUp() throws Exception
	{
		list = new ArrayListImpl<Integer>();
	}

	@After
	public void tearDown() throws Exception
	{
	}

	@Test
	public void testArrayListImpl()
	{
		System.out.println("testing constructor...");
		assertEquals(list.size(), 0);
	}

	@Test
	public void testAdd()
	{
		System.out.println("testing add...");
		assertEquals(list.size(), 0);
		//This is just to be explicit that we are testing the right number.
		int expectedSize = 0;
		for (int i = 0; i < NUMBERS_TO_ADD; i++)
		{
			list.add(i);
			expectedSize++;
			assertEquals(list.size(), expectedSize);
		}
	}

	@Test
	public void testContains()
	{
		testAdd();
		
		for (int i = 0; i < NUMBERS_TO_ADD; i++)
			assertTrue(list.contains(i));
		
		for (int j = NUMBERS_TO_ADD; j < (NUMBERS_TO_ADD*2); j++)
			assertFalse(list.contains(j));
		
		
	}
}
