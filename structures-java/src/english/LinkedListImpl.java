package english;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class LinkedListImpl<E extends Comparable<E>> implements List<E> {

	private Node<E> head;
	private Node<E> tail;
	private int size;

	LinkedListImpl() {
		head = null;
		tail=null;
		size = 0;
	}

	@Override
	public boolean add(E val) {

		if(head == null) {
			head = new Node<E>(val);
			tail = head;
		}
		else {
			tail.next = new Node<E>(val);
			tail = tail.next;
		}
		
//		
//		Node<E> curNode = head;
//		if (head == null) {
//			head = new Node<E>(val);
//		} 
//		else {
//			while (curNode.next != null) {
//				curNode = curNode.next;
//			}
//			curNode.next = new Node<E>(val);
//		}

		size++;
		return true;
	}

	public boolean find(E val) {
		Node<E> curNode = head;

		while (curNode != null) {
			if ((curNode.val).compareTo(val) == 0) {
				return true;
			} 
			else
				curNode = curNode.next;
		}

		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean contains(Object arg0) {

		E val;
		try {
			val = (E) arg0;

		} catch (ClassCastException e) {
			System.out.println("Type cast exception");
			return false;
		}
		return find(val);
	}

	@SuppressWarnings("hiding")
	private class Node<E extends Comparable<E>> {
		public E val;
		public Node<E> next;

		public Node(E val) {
			this.val = val;
			this.next = null;
		}
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void add(int arg0, E arg1) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(Collection<? extends E> arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(int arg0, Collection<? extends E> arg1) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void clear() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public E get(int arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int indexOf(Object arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isEmpty() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Iterator<E> iterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public int lastIndexOf(Object arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<E> listIterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<E> listIterator(int arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean remove(Object arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public E remove(int arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public E set(int arg0, E arg1) {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<E> subList(int arg0, int arg1) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object[] toArray() {
		throw new UnsupportedOperationException();
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		throw new UnsupportedOperationException();
	}
}
