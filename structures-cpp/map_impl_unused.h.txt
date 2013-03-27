#ifndef map_impl_h
#define map_impl_h

#include <iostream>
#include <stdio.h>
#include <string>
#include <sstream>

using namespace std;


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
    	//FIXME: implement
    	return 5;
	}

	string toString()
	{
		stringstream builder;
		builder << key << "=" << value;
		return builder.str();
	}
};


class Map_ChainNode
{
public:
	Map_ChainNode* next;
	EntryImpl* entry;

	Map_ChainNode(EntryImpl* entry_in)
	{
		cout << "Map_ChainNode(" << entry_in->toString() << ")" << endl;
		entry = entry_in;
		next = NULL;
	}

	string toString()
	{
		if (entry != NULL)
			return entry->toString();
		else
			return "NULL";
	}

};

class Map_Chain
{
	friend class Map_Impl;

private:
	Map_ChainNode* head;
	Map_ChainNode* tail;

public:

	Map_Chain()
	{
		head = NULL;
		tail = NULL;
	}

	bool containsKey(string key)
	{
		Map_ChainNode* node = head;

		while (node != NULL)
		{
			if (node->entry->key == key)
				return true;

			node = node->next;
		}

		return false;
	}

	/**
	 * @param entry
	 * @return true if the entry was added, or false if the key already existed.
	 */
	bool put(EntryImpl* entry)
	{
		cout << "chain.put("<<entry->toString()<<")" << endl;

		if (head == NULL)
		{
			cout << "adding new element" << endl;
			//There aren't any elements in this chain, so make this the first.
			head = new Map_ChainNode(entry);

			cout << "result of add: " << head->toString() << endl;

			tail = head;
			return true;
		}

		Map_ChainNode* node = head;

		while (node != NULL)
		{
			if (node->entry->key == entry->key)
			{
				//This node contains the key, so replace the value.
				node->entry->value = entry->value;
				return false;
			}

			if (node->next == NULL)
			{
				//We've reached the end of the chain, so we should add the entry.
				node->next = new Map_ChainNode(entry);
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
			builder << "...";

			builder << head->toString();

			Map_ChainNode* node = head;

			while (node->next != NULL)
			{
				node = node->next;
				builder << ", " << node->toString();
			}
		}
		else
		{
			builder << head->toString();
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
	Map_Chain* hashTable;
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
		EntryImpl* entry = new EntryImpl(key, 0);
		Map_Chain chain = getChain(entry);

		return chain.containsKey(key);
	}

	void put(string key, int value)
	{
		EntryImpl* entry = new EntryImpl(key, value);

		cout << "put()" << endl;

		double scaled = (double) (size+1);
		double cap = (double)hashTableLength * loadFactor;

		if (scaled > cap )
			resizeHashTable();

		Map_Chain chain = getChain(entry);

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

		hashTable = new Map_Chain[hashTableLength];

		for (int i = 0; i < hashTableLength; i++)
			hashTable[i] = Map_Chain();
	}

	bool containsEntry(EntryImpl* entry)
	{
		Map_Chain chain = getChain(entry);

		for (Map_ChainNode* node = chain.head; node != NULL; node = node->next)
			if (node->entry == entry)
				return true;

		return false;
	}

	void resizeHashTable()
	{
		cout << "resizeHashTable()" << endl;


		//This is old now.
		Map_Chain* oldHashTable = hashTable;
		int oldHashTableLength = hashTableLength;

		//Create the new table and fill it with chains.
		hashTableLength = hashTableLength * GROW_FACTOR;
		hashTable = new Map_Chain[hashTableLength];

		for (int i = 0; i < hashTableLength; i++)
			hashTable[i] = Map_Chain();

		//Move the elements from the old table into the new table

		for (int i = 0; i < oldHashTableLength; i++)
		{
			Map_Chain chain = oldHashTable[i];
			Map_ChainNode* node = chain.head;
			while (node != NULL)
			{
				EntryImpl* entry = node->entry;
				node = node->next;

				Map_Chain insertChain = getChain(entry);
				insertChain.put(entry);
			}
		}
	}

	/**
	 * Returns the chain that stores the entry.  Entries are hashed according to their keys.
	 * @param entry
	 * @return
	 */
	Map_Chain getChain(EntryImpl* entry)
	{
//		System.out.println("getChain()");

		int location = (entry->hashCode() & 0x7FFFFFFF) % hashTableLength;
//		System.out.println(location);

		Map_Chain chain = hashTable[location];
		return chain;
	}





};

#endif
