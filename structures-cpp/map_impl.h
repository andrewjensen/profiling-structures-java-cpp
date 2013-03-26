#ifndef map_impl_h
#define map_impl_h

#include <iostream>
#include <stdio.h>
#include <string>

using namespace std;


	//  CHAIN CLASS



class EntryImpl
{
public:
	string key;
	int value;

	EntryImpl(string key_in, int value_in)
	{
		key = key_in;
		value = value_in;
	}

    int hashCode()
    {
		int h = hash;
		if (h == 0)
		{
			int off = offset;
			char val[] = value;
			int len = count;

			for (int i = 0; i < len; i++)
			{
				h = 31*h + val[off++];
			}
			hash = h;
		}
		return h;
	}

	string toString()
	{
		stringstream builder;
		builder << key + "=" + value;
		return builder.str();
	}
};


class ChainNode
{
public:
	ChainNode* next;
	EntryImpl entry;

	ChainNode(EntryImpl entry_in)
	{
		entry = entry_in;
		next = NULL;
	}

	string toString()
	{
		if (entry != NULL)
			return entry.toString();
		else
			return "NULL";
	}

};

class Chain
{
	friend class Map_Impl;

private:
	ChainNode* head;
	ChainNode* tail;

public:

	Chain()
	{
		head = NULL;
		tail = NULL;
	}

	bool containsKey(string key)
	{
		ChainNode* node = head;

		while (node != NULL)
		{
			if (node->entry.key == key)
				return true;

			node = node->next;
		}

		return false;
	}

	/**
	 * @param entry
	 * @return true if the entry was added, or false if the key already existed.
	 */
	bool put(EntryImpl entry)
	{
		if (head == NULL)
		{
			//There aren't any elements in this chain, so make this the first.
			head = new ChainNode(entry);
			tail = head;
			return true;
		}

		ChainNode* node = head;


		while (node != NULL)
		{
			if (node->entry.key == entry.key)
			{
				//This node contains the key, so replace the value.
				node->entry.value = entry.value;
				return false;
			}

			if (node->next == NULL)
			{
				//We've reached the end of the chain, so we should add the entry.
				node->next = new ChainNode(entry);
				tail = node->next;
				return true;
			}

			//Try the next node.
			node = node->next;
		}

		cout << "We shouldn't be here!!!" << endl;
		return false;
	}

	string toString()
	{
		stringstream builder;

		builder << "[";

		if (head != NULL)
		{
			builder << head->toString();

			ChainNode* node = head;

			while (node->next != NULL)
			{
				node = node->next;
				builder << ", " << node->toString();
			}
		}

		builder << "]";

		return builder.str();
	}
};



class Map_Impl
{
public:
	static const int INITIAL_CAPACITY = 16;
	static const double LOAD_FACTOR = 0.75;
	static const int GROW_FACTOR = 2;


private:
	Chain hashTable[];
	int hashTableLength;
	int size;
	double loadFactor;



public:

	Map_Impl()
	{
		init(INITIAL_CAPACITY, LOAD_FACTOR);
	}

	Map_Impl(int initialCapacity, double loadFactor)
	{
		init(initialCapacity, loadFactor);
	}

	~Map_Impl()
	{

	}



	bool containsKey(string key)
	{
		EntryImpl entry = new EntryImpl(key, 0);
		Chain chain = getChain(entry);

		return chain.containsKey(key);
	}

	void put(string key, int value)
	{
		EntryImpl entry = new EntryImpl(key, value);

		cout << "put()" << endl;

		double scaled = (double) (size+1);
		double cap = (double)hashTableLength * loadFactor;

		if (scaled > cap )
			resizeHashTable();

		Chain chain = getChain(entry);

		bool result = chain.put(entry);

		if (result)
			size++;	//We added a new entry, so we should add to the size.

		return;
	}

	bool isEmpty() { return getSize() == 0; }

	int getSize() { return size; }


	string toString()
	{
		stringstream builder;

		for (int i = 0; i < hashTableLength; i++)
		{
			builder << i << ": " << hashTable[i].toString() << endl;
		}

		return builder.str();
	}


private:


	void init(int initialCapacity, double loadFactor_in)
	{
		size = 0;
		loadFactor = loadFactor_in;

		hashTableLength = initialCapacity;

		hashTable = new Chain[initialCapacity];

		for (int i = 0; i < hashTableLength; i++)
			hashTable[i] = Chain();
	}

	bool containsEntry(EntryImpl entry)
	{
		Chain chain = getChain(entry);

		for (ChainNode* node = chain.head; node != NULL; node = node->next)
			if (node->entry == entry)
				return true;

		return false;
	}

	void resizeHashTable()
	{
		cout << "resizeHashTable()" << endl;


		//This is old now.
		Chain* oldHashTable = hashTable;
		int oldHashTableLength = hashTableLength;

		//Create the new table and fill it with chains.
		hashTableLength = hashTableLength * GROW_FACTOR;
		hashTable = new Chain[hashTableLength];

		for (int i = 0; i < hashTableLength; i++)
			hashTable[i] = Chain();

		//Move the elements from the old table into the new table

		for (int i = 0; i < oldHashTableLength; i++)
		{
			Chain chain = oldHashTable[i];
			ChainNode* node = chain.head;
			while (node != NULL)
			{
				EntryImpl entry = node->entry;
				node = node->next;

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
	Chain getChain(EntryImpl entry)
	{
//		System.out.println("getChain()");

		int location = (entry.hashCode() & 0x7FFFFFFF) % hashTableLength;
//		System.out.println(location);

		Chain chain = (Chain) hashTable[location];
		return chain;
	}





};

#endif
