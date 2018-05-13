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
	 * Constructor
	 * BinaryTreeNode: Instantiates a new binary tree node.
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
	 * Element: returns the element of a node
	 *
	 * @return the e
	 */
	@Override
	public E element() {
		return this.e;
	}

	/**
	 * getIndex: returns the index
	 * 
	 * @return the index
	 */
	public int getIndex() {
		return this.index;
	}

	/**
	 * getTreePointer: returns the treePointer
	 * 
	 * @return the tree pointer
	 */
	public BinaryTree<E> getTreePointer() {
		return this.treePointer;
	}

	/**
	 * toString: Formats the nodes of a tree in a readable String
	 * 
	 * @return the formatted String of the nodes
	 */
	public String toString() {
		return "Element->[" + this.element() + "] with Index->[" + this.getIndex() + "]";
	}

}
