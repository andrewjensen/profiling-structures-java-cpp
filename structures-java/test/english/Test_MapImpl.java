package english;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class Test_MapImpl
{
	
	public static Map<String, Integer> map;

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
		map = new MapImpl<String, Integer>();
	}

	@After
	public void tearDown() throws Exception
	{
	}

	@Test
	public void testPut()
	{
		assertEquals(map.size(), 0);
		
		map.put("bill", 5);
		map.put("jim", 6);
		
		assertEquals(2, map.size());
		
//		System.out.println(map.toString());
		
		map.put("bill", 10);
		map.put("crazy", 2000);
		
		assertEquals(3, map.size());
		
//		System.out.println(map.toString());

		map.put("a", 1);
		map.put("b", 1);
		map.put("c", 1);
		map.put("d", 1);
		map.put("e", 1);
		map.put("f", 1);
		map.put("g", 1);
		map.put("h", 1);
		map.put("i", 1);
		map.put("j", 1);

		System.out.println(map.toString());
		
		assertEquals(13, map.size());
	}
	

	@Test
	public void testContainsKey()
	{
		map.put("a", 5);
		map.put("b", 6);
		
		assertTrue(map.containsKey("a"));
		assertTrue(map.containsKey("b"));
		assertFalse(map.containsKey("c"));
	}

}
