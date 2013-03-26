package english;

import java.util.Collection;
import java.util.Map;
import java.util.Set;


public class MapImpl<K, V> implements Map<K, V>
{
	public static final int INITIAL_CAPACITY = 16;
	public static final double LOAD_FACTOR = 0.75;
	public static final int GROW_FACTOR = 2;
	
	private Object[] hashTable;
	private int size;
	private double loadFactor;
	
	public MapImpl()
	{
		init(INITIAL_CAPACITY, LOAD_FACTOR);
	}
	
	public MapImpl(int initialCapacity, double loadFactor)
	{
		init(initialCapacity, loadFactor);
	}
	
	
	
	@Override
	public boolean containsKey(Object key)
	{
		EntryImpl entry = new EntryImpl(key, null);
		Chain chain = getChain(entry);
		
		return chain.containsKey(key);
	}

	@Override
	public V put(K key, V value)
	{
		EntryImpl entry = new EntryImpl(key, value);

//		System.out.println("put()");
		
		double scaled = (double) (size+1);
		double cap = (double)hashTable.length * loadFactor;
//		System.out.format("comparing %f and %f\n", scaled, cap);
		if (scaled > cap )
			resizeHashTable();

		Chain chain = getChain(entry);
		
		boolean result = chain.put(entry);
		
//		System.out.println("result of put: "+result);
		
		if (result)
			size++;	//We added a new entry, so we should add to the size.
			
		return null;
	}

	@Override
	public boolean isEmpty() { return size() == 0; }

	@Override
	public int size() { return size; }
	
	
	class EntryImpl
	{
		public Object key;
		public Object value;
		
		public EntryImpl(Object key, Object value)
		{
			this.key = key;
			this.value = value;
		}
		
		public int hashCode()
		{
			return key.hashCode();
		}
		
		public String toString() { return key.toString() + "=" + value.toString(); }
	}
	

	///////////////////////////////////////////////////////////////
	// 
	// THESE METHODS WERE COPIED FROM THE SET CLASS.
	// 
	///////////////////////////////////////////////////////////////
	
	
	private boolean containsEntry(EntryImpl entry)
	{
		Chain chain = getChain(entry);
		
		for (ChainNode node = chain.head; node != null; node = node.next)
			if (node.entry == entry)
				return true;
		
		return false;
	}
	

	private void resizeHashTable()
	{	
		System.out.println("resizeHashTable()");
		
		
		//This is old now.
		Object[] oldHashTable = hashTable;

		//Create the new table and fill it with chains.
		hashTable = new Object[hashTable.length * GROW_FACTOR];
		for (int i = 0; i < hashTable.length; i++)
			hashTable[i] = new Chain();
		
		//Move the elements from the old table into the new table
		
		for (int i = 0; i < oldHashTable.length; i++)
		{
			Chain chain = (Chain) oldHashTable[i];
			ChainNode node = chain.head;
			while (node != null)
			{
				EntryImpl entry = node.entry;
				node = node.next;
				
				Chain insertChain = getChain(entry);
				insertChain.put(entry);
			}
		}
	}
	
	/**
	 * Returns the chain that stores the entry.  Entries are hashed according to their keys.
	 * @param entry
	 * @return
	 */
	private Chain getChain(EntryImpl entry)
	{
//		System.out.println("getChain()");
		
		int location = (entry.key.hashCode() & 0x7FFFFFFF) % hashTable.length;
//		System.out.println(location);
		
		Chain chain = (Chain) hashTable[location];
		return chain;
	}
	
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		
		for (int i = 0; i < hashTable.length; i++)
		{
			builder.append(String.format("%d: %s\n", i, hashTable[i]));
		}
		
		return builder.toString();
	}
	

	private void init(int initialCapacity, double loadFactor)
	{
		this.size = 0;
		this.loadFactor = loadFactor;
		
		//This is hard!  See here for a solution:
		// http://stackoverflow.com/a/530289
		
		hashTable = new Object[initialCapacity];
		for (int i = 0; i < hashTable.length; i++)
			hashTable[i] = new Chain();
	}
	

	//  CHAIN CLASS
	
	private class Chain
	{
		private ChainNode head;
		private ChainNode tail;
		
		public Chain()
		{
			head = null;
			tail = null;
		}
		
		public boolean containsKey(Object key)
		{
			ChainNode node = head;
			
			while (node != null)
			{
				if (node.entry.key == key)
					return true;
				
				node = node.next;
			}
			
			return false;
		}
		
		/**
		 * @param entry
		 * @return true if the entry was added, or false if the key already existed.
		 */
		public boolean put(EntryImpl entry)
		{
			if (head == null)
			{
				//There aren't any elements in this chain, so make this the first.
				head = new ChainNode(entry);
				tail = head;
				return true;
			}
			
			ChainNode node = head;
			
			
			while (node != null)
			{
				if (node.entry.key == entry.key)
				{
					//This node contains the key, so replace the value.
					node.entry.value = entry.value;
					return false;
				}
				
				if (node.next == null)
				{
					//We've reached the end of the chain, so we should add the entry.
					node.next = new ChainNode(entry);
					tail = node.next;
					return true;
				}
				
				//Try the next node.
				node = node.next;
			}
			
			System.out.println("We shouldn't be here!!!");
			return false;
		}
	
		public String toString()
		{
			StringBuilder builder = new StringBuilder();
			
			builder.append("[");
			
			if (head != null)
			{
				builder.append(head);
				
				ChainNode node = head;
				
				while (node.next != null)
				{
					node = node.next;
					builder.append(", ");
					builder.append(node);
				}
			}
			
			builder.append("]");
			
			return builder.toString();
		}
	}

	private class ChainNode
	{
		public ChainNode next;
		public EntryImpl entry;
		
		public ChainNode(EntryImpl value)
		{
			this.entry = value;
		}
		
		public String toString()
		{
			if (entry != null)
				return entry.toString();
			else
				return "null";
		}
		 
	}

	///////////////////////////////////////////////////////////////
	// 
	// THE FOLLOWING METHODS ARE NOT IMPLEMENTED IN THIS RESEARCH.
	// 
	///////////////////////////////////////////////////////////////
	
	@Override
	public void clear()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean containsValue(Object arg0)
	{
		throw new UnsupportedOperationException();
	}
	
	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public V get(Object arg0)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public Set<K> keySet()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> arg0)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public V remove(Object arg0)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public Collection<V> values()
	{
		throw new UnsupportedOperationException();
	}

}
