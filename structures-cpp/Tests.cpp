#include <fstream>
#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include <string>

#include "ArrayList_Impl.h"
#include "LinkedList_Impl.h"
#include "Set_Impl.h"
#include "SortedSet_Impl.h"
#include "Map_Impl.h"

using namespace std;

int main(int argc, const char* argv[]) {
	cout << "I am the test Program!" << endl;

	LinkedList_Impl* myList = new LinkedList_Impl();

	cout << "size pre-add: " << myList->getSize() << endl;

	myList->add(1);
	myList->add(2);
	myList->add(3);

	cout << "size post-add: " << myList->getSize() << endl;

	cout << endl << "1 is true, 0 is false" << endl;
	cout << "Contains 1: " << myList->contains(1) << endl;
	cout << "Contains 2: " << myList->contains(2) << endl;
	cout << "Contains 3: " << myList->contains(3) << endl;
	cout << "Contains 4: " << myList->contains(4) << endl << endl;

	return 0;
}
