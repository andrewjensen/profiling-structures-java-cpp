package english;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class SetImpl<E> implements Set<E>
{
	public static final int INITIAL_CAPACITY = 16;
	public static final double LOAD_FACTOR = 0.75;
	public static final int GROW_FACTOR = 2;
	
	private Object[] hashTable;
	private int size;
	private double loadFactor;
	
	public SetImpl()
	{
		//TODO: finish the implementation.
		//joey made a change here
		
		this(INITIAL_CAPACITY, LOAD_FACTOR);
	}
	
	public SetImpl(int initialCapacity, double loadFactor)
	{
		
		//asfibafglib
		
		this.size = 0;
		this.loadFactor = loadFactor;

		//This is hard!  See here for a solution:
		// http://stackoverflow.com/a/530289
		
		
		hashTable = new Object[initialCapacity];
		for (int i = 0; i < hashTable.length; i++)
			hashTable[i] = new Chain<E>();
	}
	
	
	@Override
	public boolean add(E item)
	{
		if (contains(item))
			return false;
		
		if (loadFactor * (size+1) > hashTable.length)
			resizeHashTable();

		Chain<E> chain = getChain(item);
		
		ChainNode<E> node = chain.head;
		while (node.next != null)
			node = node.next;
		
		node.next = new ChainNode<E>(item);
		size++;
		return true;
	}

	@Override
	public boolean contains(Object item)
	{
		Chain<E> chain = getChain(item);
		
		for (ChainNode<E> node = chain.head; node != null; node = node.next)
			if (node.value == item)
				return true;
		
		return false;
	}

	@Override
	public int size() { return size; }

	@Override
	public boolean isEmpty() { return size == 0; }

	private void resizeHashTable()
	{	
		//This is old now.
		Object[] oldHashTable = hashTable;

		//Create the new table and fill it with chains.
		hashTable = new Object[hashTable.length * GROW_FACTOR];
		for (int i = 0; i < hashTable.length; i++)
			hashTable[i] = new Chain<E>();
		
		//Move the elements from the old table into the new table
		
		for (int i = 0; i < oldHashTable.length; i++)
		{
			//Chain<E> chain = getChain()
		}
	}
	
	private Chain<E> getChain(Object item)
	{
		int location = item.hashCode() % hashTable.length;
		Chain<E> chain = (Chain<E>) hashTable[location];
		return chain;
	}
	
	private class Chain<E>
	{
		private ChainNode<E> head;
		private ChainNode<E> tail;
		
		public Chain()
		{
			head = null;
			tail = null;
		}
		
		public void add(E item)
		{
			if (head == null)
			{
				head = new ChainNode<E>(item);
				tail = head;
			}
			else
			{
				tail.next = new ChainNode<E>(item);
				tail = tail.next;
			}
		}
	}

	private class ChainNode<E>
	{
		public ChainNode<E> next;
		public E value;
		
		public ChainNode(E value)
		{
			this.value = value;
		}
		 
	}

	///////////////////////////////////////////////////////////////
	// 
	// THE FOLLOWING METHODS ARE NOT IMPLEMENTED IN THIS RESEARCH.
	// 
	///////////////////////////////////////////////////////////////
	
	

	@Override
	public boolean addAll(Collection<? extends E> arg0)
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
	public Iterator<E> iterator()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean remove(Object arg0)
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
	public Object[] toArray()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public <T> T[] toArray(T[] arg0)
	{
		throw new UnsupportedOperationException();
	}

}
