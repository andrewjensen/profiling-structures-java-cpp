#ifndef linkedlist_impl_h
#define linkedlist_impl_h

#include <string>
using namespace std;

//! LLNode implements a linked list node
class LLNode {

	friend class LinkedList_Impl; // LinkedList can access private members of LLNode
public:

	//!  Constructor
	LLNode(const double v, LLNode * n) :
			value(v), next(n) {
	}

	//! Copy Constructor
	LLNode(const LLNode& other) :
			value(other.value), next(other.next) {
	}

	//! Assignment operator
	LLNode& operator=(const LLNode& other) {
		if (this != &other) {
			value = other.value;
			next = other.next;
		}
		return *this;
	}

	double getValue() {
		return value;
	}

	LLNode* getNext() {
		return next;
	}

private:
	double value; //!< value stored in the node
	LLNode* next; //!< pointer to next node in the list
};

//! LinkedList implements a doubly-linked list
class LinkedList_Impl {
public:

	//!  No-arg constructor.  Initializes an empty linked list
	LinkedList_Impl() {
		size = 0;
		root = NULL;
	}

	//!  Destructor
	~LinkedList_Impl();

	//!  @return the number of values in the list
	int getSize() const {
		return size;
	}

	//!  Inserts value v into the list after node n
	//!
	//!  @param v The new value being inserted
	//!  @param n A node that is already in the list after which the new node should
	//!      be inserted.
	//!      If n is NULL, the new node should be inserted at the beginning of the list.
	//!
	//!  @return a pointer to the newly inserted node
	bool add(const double v) {

			if(root==NULL){
				tail = root = new LLNode(v, NULL);
				size++;
				return true;
			}
			else {
				LLNode* newNode = new LLNode(v, NULL);
				tail->next = newNode;
				tail = tail->next;
				size++;
				return true;
			}

//		if (root == NULL) {
//			root = new LLNode(v, NULL);
//			size++;
//			return true;
//		} else {
//			LLNode* newNode = new LLNode(v, NULL);
//			LLNode* curNode = root;
//			while (curNode->next != NULL) {
//				curNode = curNode->next;
//			}
//			curNode->next = newNode;
//			size++;
//			return true;
//		}


	}

	//! Searches for the first occurrence of value v that appears in the list
	//!   after node n
	//!
	//!  @param v The value being searched for
	//!  @return true if found, false otherwise
	bool contains(const double v) const {
		LLNode* curNode = root;

		while (curNode != NULL) {
			if (curNode->value == v) {
				return true;
			} else
				curNode = curNode->next;
		}
		return false;
	}

	LLNode* getRoot() {
		return root;
	}

private:
	int size;
	LLNode* tail;
	LLNode* root;
};

#endif
