package english;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class Test_SetImpl
{
	public static final int NUMBERS_TO_ADD = 20;
	
	public static Set<Integer> set;
	
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
		set = new SetImpl<Integer>();
	}

	@After
	public void tearDown() throws Exception
	{
	}

	@Test
	public void testSetImpl()
	{
		assertEquals(set.size(), 0);
		
		for (int i = 0; i < NUMBERS_TO_ADD; i++)
			assertFalse(set.contains(i));
	}

	@Test
	public void testAdd()
	{
		int expectedSize = 0;
		for (int i = 0; i < NUMBERS_TO_ADD; i++)
		{
			set.add(i);
			expectedSize++;
			assertEquals(set.size(), expectedSize);
			assertTrue(set.contains(i));
		}
		
		assertEquals(set.size(), NUMBERS_TO_ADD);
		
		Set local = set;
		
//		System.out.print(set.toString());
	}

}
