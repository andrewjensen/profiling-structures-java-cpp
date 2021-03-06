#ifndef arraylist_impl_h
#define arraylist_impl_h

#include <iostream>
#include <string>

using namespace std;

class ArrayList_Impl
{
	public:
		static const int INITIAL_ARRAY_LENGTH = 10;
		static const int GROW_FACTOR = 2;

		ArrayList_Impl()
		{
			size = 0;
			arrayLength = INITIAL_ARRAY_LENGTH;
			elements = new double[INITIAL_ARRAY_LENGTH];
		}

		~ArrayList_Impl()
		{

		}

		bool add(double item)
		{
			if (size == arrayLength)
				resizeArray();

			elements[size] = item;
			size++;

			return true;
		}

		bool contains(double item)
		{
			for (int i = 0; i < size; i++)
				if (elements[i] == item)
					return true;

			return false;
		}

		double get(int index)
		{
			return elements[index];
		}

		int getSize() { return size; }

		bool isEmpty() { return size == 0; }

		void resizeArray()
		{
			int newArrayLength = arrayLength * GROW_FACTOR;

			double* newElements = new double[newArrayLength];

			for (int i = 0; i < arrayLength; i++)
				newElements[i] = elements[i];

			arrayLength = newArrayLength;
			elements = newElements;	//FIXME: is this valid?
		}

	private:
		double* elements;
		int size;
		int arrayLength;


};

#endif
