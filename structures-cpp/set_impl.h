#ifndef set_impl_h
#define set_impl_h

#include <iostream>
#include <sstream>
#include <string>

using namespace std;


class ChainNode
{
	friend class Chain;
	friend class Set_Impl;

public:
	ChainNode* next;
	double value;

	ChainNode(double value_in)
	{
		value = value_in;
		next = NULL;
	}
	~ChainNode()
	{

	}

	string toString()
	{
		stringstream builder;
		builder << value;
		return builder.str();
	}

};

class Chain
{
	friend class Set_Impl;

private:
	ChainNode* head;
	ChainNode* tail;

public:

	Chain()
	{
		head = NULL;
		tail = NULL;
	}

	void add(double item)
	{
		if (head == NULL)
		{
			head = new ChainNode(item);
			tail = head;
		}
		else
		{
			tail->next = new ChainNode(item);
			tail = tail->next;
		}
	}

	string toString()
	{
		stringstream builder;

		builder << "[";

		if (head != NULL)
		{
			builder << head;

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




class Set_Impl
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

	Set_Impl()
	{
		init(INITIAL_CAPACITY, LOAD_FACTOR);
	}

	Set_Impl(int initialCapacity, double loadFactor_in)
	{
		init(initialCapacity, loadFactor_in);
	}



	~Set_Impl() { }

	bool add(double item)
	{
		if (contains(item))
			return false;

		double scaled = (double) (size+1);
		double cap = (double)hashTableLength * loadFactor;
		if (scaled > cap )
			resizeHashTable();

		Chain chain = getChain(item);

		chain.add(item);

		size++;
		return true;
	}

	bool contains(double item)
	{
		Chain chain = getChain(item);

		for (ChainNode* node = chain.head; node != NULL; node = node->next)
			if (node->value == item)
				return true;

		return false;
	}

	int getSize() { return size; }

	bool isEmpty() { return size == 0; }


	string toString()
	{
		stringstream builder;

		for (int i = 0; i < hashTableLength; i++)
		{
			builder << i << ": " << hashTable[i].toString() << "\n";
		}

		return builder.str();
	}


private:

	void init(int initialCapacity, double loadFactor_in)
	{
		size = 0;

		loadFactor = loadFactor_in;

		hashTableLength = initialCapacity;

		//This is hard!  See here for a solution:
		// http://stackoverflow.com/a/530289

		hashTable[hashTableLength];
		for (int i = 0; i < hashTableLength; i++)
			hashTable[i] = Chain();
	}

	void resizeHashTable()
	{

		//This is old now.
		Chain oldHashTable[] = hashTable;
		int oldHashTableLength = hashTableLength;

		//Create the new table and fill it with chains.
		hashTableLength = hashTableLength * GROW_FACTOR;
		hashTable = Chain[hashTableLength];

		for (int i = 0; i < hashTableLength; i++)
			hashTable[i] = new Chain();

		//Move the elements from the old table into the new table

		for (int i = 0; i < oldHashTableLength; i++)
		{
			Chain chain = oldHashTable[i];
			ChainNode* node = chain.head;
			while (node != NULL)
			{
				double item = node->value;
				node = node->next;

				Chain insertChain = getChain(item);
				insertChain.add(item);
			}
		}
	}

	Chain getChain(double item)
	{
//		System.out.println("getChain()");

		long itemCast = (long) item;
		int location = (item & 0x7FFFFFFF) % hashTableLength;
//		System.out.println(location);

		return hashTable[location];
	}
};

#endif
