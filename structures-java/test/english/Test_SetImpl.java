package english;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class Test_SetImpl
{
	public static final int NUMBERS_TO_ADD = 20;
	
	public static Set<String> set;
	
	public static String[] words;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		words = new WordsParser("words.txt", 100).toArray();
		
//		System.out.println("Words:");
//		for (String word: words)
//			System.out.println(word);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{
	}

	@Before
	public void setUp() throws Exception
	{
		set = new SetImpl<String>();
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
//		int expectedSize = 0;
//		for (int i = 0; i < NUMBERS_TO_ADD; i++)
//		{
//			set.add(i);
//			expectedSize++;
//			assertEquals(set.size(), expectedSize);
//			assertTrue(set.contains(i));
//		}
//		
//		assertEquals(set.size(), NUMBERS_TO_ADD);
		
		int expectedSize = 0;
		for (int i = 0; i < words.length; i++)
		{
			set.add(words[i]);
			expectedSize++;
			assertEquals(set.size(), expectedSize);
			assertTrue(set.contains(words[i]));
		}
		
		assertEquals(set.size(), words.length);
		
		Set local = set;
		
//		System.out.print(set.toString());
	}

}
