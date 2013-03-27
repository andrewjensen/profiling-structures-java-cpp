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

#include "ArrayList_Impl.h"
#include "LinkedList_Impl.h"
#include "Set_Impl.h"
#include "SortedSet_Impl.h"

const int TEST_RUNS = 10;

using namespace std;

long fillCollection(vector<double> col) {

	timeval startTime;
	gettimeofday(&startTime, NULL);
	long start = (startTime.tv_sec * 1000) + (startTime.tv_usec / 1000);

	for (int i = 0; i < 600000; i++) {

		col.push_back((double) rand());

	}

	timeval fillTime;
	gettimeofday(&fillTime, NULL);
	long fill = (fillTime.tv_sec * 1000) + (fillTime.tv_usec / 1000);

	return fill-start;
}

long findCollection(vector<double> col) {

	timeval startTime;
	gettimeofday(&startTime, NULL);
	long start = (startTime.tv_sec * 1000) + (startTime.tv_usec / 1000);

	for (int i = 0; i < 100; i++) {

		//USE ITERATOR FOR VECTOR
		//col.contains(i + 0.0);

	}

	timeval findTime;
	gettimeofday(&findTime, NULL);
	long find = (findTime.tv_sec * 1000) + (findTime.tv_usec / 1000);

	return find - start;
}

int main(int argc, const char* argv[]) {

	//set<double> col();
	//list<double> col(); // HOW TO MAKE THIS A LINKEDLIST?
	//std::tr1::unordered_set<double> col();
	vector<double> col;

	long fillTimes[TEST_RUNS], findTimes[TEST_RUNS];

	for (int i = 0; i < TEST_RUNS; i++) {
		cout << (i + 1) << " ";
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
