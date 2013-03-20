package english;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ArrayListImpl<E> implements List<E>
{
	public static final int INITIAL_ARRAY_LENGTH = 10;
	public static final int GROW_FACTOR = 2;
	
	private E[] elements;
	private int size;
	
	public ArrayListImpl()
	{
		size = 0;
		
		//See http://stackoverflow.com/a/2924453
		elements = (E[]) new Object[INITIAL_ARRAY_LENGTH];
	}
	
	@Override
	public boolean add(E item)
	{
		System.out.println("add("+item+")");
		
		if (size == elements.length)
			resizeArray();
		
		elements[size] = item;
		size++;
		
		return true;
	}

	@Override
	public boolean contains(Object item)
	{
		for (int i = 0; i < size; i++)
			if (elements[i] == item)
				return true;
		
		return false;
	}

	@Override
	public E get(int index)
	{
		return elements[index];
	}

	@Override
	public int size() { return this.size; }

	@Override
	public boolean isEmpty() { return this.size == 0; }

	private void resizeArray()
	{
		int newSize = elements.length * GROW_FACTOR;
		
		System.out.println("resizing to "+newSize+" elements.");
		
		E[] newArray = (E[]) new Object[newSize];
		
		for (int i = 0; i < elements.length; i++)
			newArray[i] = elements[i];
		
		elements = newArray;
	}

	///////////////////////////////////////////////////////////////
	// 
	// THE FOLLOWING METHODS ARE NOT IMPLEMENTED IN THIS RESEARCH.
	// 
	///////////////////////////////////////////////////////////////
	
	

	@Override
	public void add(int arg0, E arg1)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(Collection<? extends E> arg0)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(int arg0, Collection<? extends E> arg1)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void clear()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean containsAll(Collection<?> arg0)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public int indexOf(Object arg0)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public Iterator<E> iterator()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public int lastIndexOf(Object arg0)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<E> listIterator()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<E> listIterator(int arg0)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean remove(Object arg0)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public E remove(int arg0)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean removeAll(Collection<?> arg0)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean retainAll(Collection<?> arg0)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public E set(int arg0, E arg1)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public List<E> subList(int arg0, int arg1)
	{
		throw new UnsupportedOperationException();
	}

	public Object[] toArray()
	{
		throw new UnsupportedOperationException();
	}

	public <T> T[] toArray(T[] arg0)
	{
		throw new UnsupportedOperationException();
	}

}
