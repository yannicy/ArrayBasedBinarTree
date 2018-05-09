package project5.classes;

import project5.interfaces.BinaryTree;
import project5.interfaces.Position;

public class BinaryTreeNode<E> implements Position<E> {

	/** The e. */
	private E e;
	
	/** The index. */
	private int index;
	
	/** The tree pointer. */
	private BinaryTree<E> treePointer;

	/**
	 * Instantiates a new binary tree node.
	 *
	 * @param e the e
	 * @param index the index
	 * @param treePointer the tree pointer
	 */
	public BinaryTreeNode(E e, int index, BinaryTree<E> treePointer) {
		this.e = e;
		this.index = index;
		this.treePointer = treePointer;
	}

	/**
	 * Element.
	 *
	 * @return the e
	 */
	@Override
	public E element() {
		return this.e;
	}

	/**
	 * Gets the index.
	 * @return the index
	 */
	public int getIndex() {
		return this.index;
	}

	/**
	 * Gets the tree pointer.
	 * @return the tree pointer
	 */
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
