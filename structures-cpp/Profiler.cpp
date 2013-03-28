#include <fstream>
#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include <string>
#include <set>
#include <tr1/unordered_set>
//std::tr1::unordered_set
#include <vector>
#include <list>
#include <cstdlib>
#include <sys/time.h>
#include <ctime>

#include "ArrayList_Impl.h"
#include "LinkedList_Impl.h"
#include "Set_Impl.h"
#include "SortedSet_Impl.h"

const int TEST_RUNS = 10;

using namespace std;

long fillCollection(LinkedList_Impl* col) {

	clock_t start = clock();

	for (int i = 0; i < 600000; i++) {
		col->add((double) rand());
	}

	clock_t fill = clock();

	return (((fill - start) * 1000000000) / CLOCKS_PER_SEC);
}

long findCollection(LinkedList_Impl* col) {

	clock_t start = clock();

	for (int i = 0; i < 100; i++) {
		col->contains((double) i);
	}
	clock_t find = clock();

	return (((find - start) * 1000000000) / CLOCKS_PER_SEC);
}

int main(int argc, const char* argv[]) {

	LinkedList_Impl* col = new LinkedList_Impl(); // HOW TO MAKE THIS A LINKEDLIST?

	long fillTimes[TEST_RUNS], findTimes[TEST_RUNS];
	cout << "Starting" << endl;
	for (int i = 0; i < TEST_RUNS; i++) {

		cout << i + 1 << " " << endl;
		col = new LinkedList_Impl();

		fillTimes[i] = fillCollection(col);
		findTimes[i] = findCollection(col);
	}

	long averageFillTime, averageFindTime;

	long totalTime = 0;
	for (int i = 0; i < TEST_RUNS; i++)
		totalTime += fillTimes[i];

	averageFillTime = totalTime / TEST_RUNS;

	totalTime = 0;
	for (int i = 0; i < TEST_RUNS; i++)
		totalTime += findTimes[i];

	averageFindTime = totalTime / TEST_RUNS;

	cout << "\nAverage Fill Time: " << averageFillTime << endl;
	cout << "Average Find Time: " << averageFindTime << endl;
	cout << "-----------------------------------------" << endl;

	return 0;
}
