package english;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class WordsParser
{
	Set<String> words;
	
	public WordsParser(String filename, int maxWords)
	{
		words = new HashSet<String>();
		Scanner scanner = null;
		
		try
		{
			File file = new File(filename);
			scanner = new Scanner(file);
			
			while (scanner.hasNext() && words.size() < maxWords)
			{
				words.add(scanner.next());
			}
		
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Error: file not found!");
		}
		finally
		{
			scanner.close();
//			System.out.println(words.size()+" words added.");
		}
	}
	
	public String[] toArray()
	{
		String[] array = new String[words.size()];
		
		Iterator<String> it = words.iterator();
		
		int index = 0;
		while (it.hasNext())
		{
			array[index] = it.next();
			index++;
		}
		
		return array;
	}
	
	public Set<String> getWords() { return words; }
}
