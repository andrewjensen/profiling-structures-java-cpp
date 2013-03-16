package english;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class MapImpl<K, V> implements Map<K, V>
{
	private int size;
	
	public MapImpl()
	{
		size = 0;
	}
	
	
	

	@Override
	public boolean containsKey(Object arg0)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsValue(Object arg0)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public V put(K arg0, V arg1)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() { return size == 0; }

	@Override
	public int size() { return size; }

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
