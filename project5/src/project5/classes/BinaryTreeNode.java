package project5.classes;

import project5.interfaces.BinaryTree;
import project5.interfaces.Position;

public class BinaryTreeNode<E> implements Position<E> {

	private E e;
	private int index;
	private BinaryTree<E> treePointer;

	public BinaryTreeNode(E e, int index, BinaryTree<E> treePointer) {
		this.e = e;
		this.index = index;
		this.treePointer = treePointer;
	}

	@Override
	public E element() {
		return this.e;
	}

	public int getIndex() {
		return this.index;
	}

	public BinaryTree<E> getTreePointer() {
		return this.treePointer;
	}

	// (index, e)
	public String toString() {
		// TODO
//		return "Element->[" + this.element() + "] Index->[" + this.getIndex() + "]";
		return this.e.toString();
	}

}
