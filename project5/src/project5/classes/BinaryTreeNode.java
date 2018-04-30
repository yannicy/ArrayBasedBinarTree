package project5.classes;

import project5.interfaces.Position;

public class BinaryTreeNode<E> implements Position<E>{
	
	private E e;
	private int index;
	
	public BinaryTreeNode(E e, int index) {
		this.e = e;
		this.index = index;
	}

	
	@Override
	public E element() {
		return this.e;
	}
	
	public int getIndex() {
		return this.index;
	}

	// (index, e)
	public String toString() {
		// TODO
		return this.e.toString();
	}
	
}
