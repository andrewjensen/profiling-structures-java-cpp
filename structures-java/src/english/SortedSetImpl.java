package english;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.SortedSet;

public class SortedSetImpl<E extends Comparable<E>> implements SortedSet<E> {

	private Node<E> root;

	SortedSetImpl(){
		root = null;
	}
	
	@Override
	public boolean add(E val) {

		Node<E> curNode = root;
		
		if(curNode == null){
			root = new Node<E>(val);
		}
		else{	
			while(curNode != null){
			
				if((curNode.val).compareTo(val) < 0 ){
					curNode = curNode.right;
				}
				else if((curNode.val).compareTo(val) > 0){
					curNode = curNode.left;
				}
				else{
					return false;
				}
			}
			curNode = new Node<E>(val);
			
		}
		
		return true;
	}
	
	public boolean find(E val){
		
		Node<E> curNode = root;
		
		if(curNode == null){
			return false;
		}
		else{	
			while(curNode != null){
			
				if((curNode.val).compareTo(val) < 0 ){
					curNode = curNode.right;
				}
				else if((curNode.val).compareTo(val) > 0){
					curNode = curNode.left;
				}
				else{
					return true;
				}
			}
			return false;
		}		
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean contains(Object arg0){ 
		E val;
		try{
		    val=(E) arg0;
		    
		  }catch(ClassCastException e){
		    return false;
		  }
		
		return find(val);
		
		
	}
	
	
	@SuppressWarnings("hiding")
	private class Node<E extends Comparable<E>> 
	{
		public E val;
		public Node<E> left;
		public Node<E> right;
		
		public Node(E val)
		{
			this.val = val;
			this.left = null;
			this.right = null;
		}
	}
	
	
	
	
	
	

	@Override
	public boolean addAll(Collection<? extends E> arg0) {
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
	public boolean isEmpty() {
		throw new UnsupportedOperationException();
	}
	@Override
	public Iterator<E> iterator() {
		throw new UnsupportedOperationException();
	}
	@Override
	public boolean remove(Object arg0) {
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
	public int size() {
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
	@Override
	public Comparator<? super E> comparator() {
		throw new UnsupportedOperationException();
	}
	@Override
	public E first() {
		throw new UnsupportedOperationException();
	}
	@Override
	public SortedSet<E> headSet(E arg0) {
		throw new UnsupportedOperationException();
	}
	@Override
	public E last() {
		throw new UnsupportedOperationException();
	}
	@Override
	public SortedSet<E> subSet(E arg0, E arg1) {
		throw new UnsupportedOperationException();
	}
	@Override
	public SortedSet<E> tailSet(E arg0) {
		throw new UnsupportedOperationException();
	}
}
