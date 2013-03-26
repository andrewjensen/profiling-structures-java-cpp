#ifndef sortedset_impl_h
#define sortedset_impl_h

#include <iostream>
#include <string>

//!  BSTNode implements a binary search tree node
class BSTNode {
	friend class BST; //!< BST can access private members of BSTNode

public:

	//!  Constructor
	BSTNode(const double v) :
			value(v), left(NULL), right(NULL) {
	}

	//! Copy Constructor
	BSTNode(const BSTNode & other) :
			value(other.value), left(other.left), right(other.right) {
	}

	//!  Read-only public methods for use by clients of the BST class
	const double getValue() const {
		return value;
	}

	BSTNode* getLeft() const {
		return left;
	}

	BSTNode* getRight() const {
		return right;
	}

	//! Assignment operator
	BSTNode& operator=(const BSTNode & other) {
		if (this != &other) {
			value = other.value;
			left = other.left;
			right = other.right;
		}

		return *this;
	}
	double value; //!< value stored in the node
	BSTNode* left; //!< pointer to the node's left child
	BSTNode* right; //!< pointer to the node's right child

private:



};

//!  BST implements a binary search tree
class SortedSet_Impl {

public:

	//!  No-arg constructor.  Initializes an empty BST
	SortedSet_Impl() {
		size = 0;
		root = NULL;
	}

	//!  @return a pointer to the root node of the tree, or NULL if the tree is empty.
	//!  @note This is useful for BST clients that need to traverse the tree.)
	BSTNode* getRoot() const {
		return root;
	}

	//!  @return the number of values in the BST
	int getSize() const {
		return size;
	}

	bool recursiveInsert(double v, BSTNode* treetop) {
		if (v == treetop->getValue()) // if already in the tree
			return false;

		else if (v < treetop->getValue()) {
			if (treetop->getLeft() == NULL) {
				treetop->left = new BSTNode(v);
				return true;
			} else
				return recursiveInsert(v, treetop->getLeft()); // do i need to return this value?????
		} else {
			if (treetop->getRight() == NULL) {
				treetop->right = new BSTNode(v);
				return true;
			} else
				return recursiveInsert(v, treetop->getRight()); //do i need to return this value??
		}

	}

	//!  Inserts value v into the BST
	//!
	//!  @param v The new value being inserted
	//!
	//!  @return a boolean stating whether or not it was inserted
	bool add(double v) {
		if (root == NULL) {
			root = new BSTNode(v);
			size++;
			return true;
		} else {
			bool added = recursiveInsert(v, root);
			if (added)
				size++;
			return added;
		}
	}

	bool recursiveFind(double v, BSTNode* treetop) {
		BSTNode* nodePointer = treetop;

		if (v == nodePointer->getValue()) {
			return true;
		} else if (v < nodePointer->getValue()) {
			if (nodePointer->getLeft() == NULL) {
				return false;
			} else
				return recursiveFind(v, nodePointer->getLeft());
		} else //this happens if v > nodePointer's value
		{
			if (nodePointer->getRight() == NULL) {
				return false;
			} else
				return recursiveFind(v, nodePointer->getRight());
		}
	}

	//!  @param v The new value being searched for
	//!
	//!  @return a boolean if it was found or not.
	bool contains(double v) {
		if (root == NULL)
			return NULL;
		else
			return recursiveFind(v, root);
	}

private:
	BSTNode* root;
	int size;
};

#endif
