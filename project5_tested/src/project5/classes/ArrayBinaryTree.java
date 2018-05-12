package project5.classes;

import java.util.ArrayList;
import java.util.List;

import project5.exceptions.EmptyTreeException;
import project5.exceptions.InvalidPositionException;
import project5.exceptions.UnemptyTreeException;
import project5.interfaces.BinaryTree;
import project5.interfaces.Position;

public class ArrayBinaryTree<E> implements BinaryTree<E> {

	/** the tree */
	private BinaryTreeNode<E>[] tree;

	/** The n: number of nodes in the tree. */
	private int n;

	/** The capacity: inizial size of the tree */
	private final int capacity = 2;

	/**
	 * Constructor
	 * ArrayBinaryTree: creates an empty binary tree
	 */
	@SuppressWarnings("unchecked")
	public ArrayBinaryTree() {
		this.n = 0;
		this.tree = new BinaryTreeNode[capacity];
	}

	/**
	 * Constructor
	 * ArrayBinaryTree: creates a binary tree with a root node
	 * 
	 * @param root
	 */
	public ArrayBinaryTree(E root) {
		this();
		this.addRoot(root);
	}

	/**
	 * expandCapacity: extends the capacity of an array by doubling its length
	 */
	@SuppressWarnings("unchecked")
	public void expandCapacity() {
		BinaryTreeNode<E>[] temp = new BinaryTreeNode[tree.length * 2];
		for (int i = 0; i < tree.length; i++) {
			temp[i] = tree[i];
		}
		this.tree = temp;
	}

	/**
	 * checkCapacity: checks if the array is big enough to store new nodes
	 * used in methods insertLeft() & insertRight()
	 * 
	 * @param index
	 */
	public void checkCapacity(int index) {
		if (index * 2 > tree.length - 1)
			expandCapacity();
	}

	/**
	 * Height: returns the maximum number of ancestors of the tree 
	 * throws an exception if the tree contains no nodes
	 *
	 * @return the height of the tree
	 * @throws EmptyTreeException
	 */
	@Override
	public int height() throws EmptyTreeException {
		if (this.isEmpty()) {
			throw (new EmptyTreeException());
		} else {
			int height = 0;
			for (Position<E> p : positions()) {
				if (isExternal(p)) {
					height = Math.max(height, depth(p));
				}
			}
			return height;
		}
	}

	/**
	 * Elements: List with the elements of the nodes from the tree
	 *
	 * @return the list with elements of nodes
	 */
	@Override
	public List<E> elements() {
		List<E> elements = new ArrayList<>();
		for (int i = 0; i < tree.length; i++) {
			if (tree[i] != null) {
				elements.add(tree[i].element());
			}
		}
		return elements;
	}

	/**
	 * Positions: List with the position of the nodes from the tree
	 *
	 * @return the list with positions of nodes
	 */
	@Override
	public List<Position<E>> positions() {
		List<Position<E>> positions = new ArrayList<>();
		for (int i = 0; i < tree.length; i++) {
			if (tree[i] != null) {
				positions.add(tree[i]);
			}
		}
		return positions;
	}

	/**
	 * Root: returns the root node of the tree
	 * throws an exception if the tree contains no nodes
	 *
	 * @return the position of the root node
	 * @throws EmptyTreeException
	 */
	@Override
	public Position<E> root() throws EmptyTreeException {
		if (this.isEmpty()) {
			throw (new EmptyTreeException());
		}
		return tree[1];
	}

	/**
	 * Parent: returns the position of the parent element of the node p 
	 * throws an InvalidPositionException if p is equal null or not from this tree
	 *
	 * @param p
	 * @return the position form parent of p
	 * @throws InvalidPositionException
	 */
	@Override
	public Position<E> parent(Position<E> p) throws InvalidPositionException {
		BinaryTreeNode<E> node = (BinaryTreeNode<E>) p;
		if (node == null || node.getTreePointer() != this) {
			throw (new InvalidPositionException());
		} else {
			return tree[node.getIndex() / 2];
		}
	}

	/**
	 * Children: returns an List with all children of p 
	 * throws an InvalidPositionException depending on the methods hasLeft() / hasRight()
	 * 
	 * @param p
	 * @return the list with all children of p
	 * @throws InvalidPositionException
	 */
	@Override
	public List<Position<E>> children(Position<E> p) throws InvalidPositionException {
		BinaryTreeNode<E> node = (BinaryTreeNode<E>) p;
		List<Position<E>> children = new ArrayList<>(2);
		if (hasLeft(node)) {
			children.add(leftChild(node));
		}
		if (hasRight(node)) {
			children.add(rightChild(node));
		}
		return children;
	}

	/**
	 * Descendants: returns an List with all descendants of p
	 * throws an InvalidPositionException depending on the methods isInternal / hasLeft() / hasRight()
	 *
	 * @param p
	 * @return the list with all descendants of p
	 * @throws InvalidPositionException
	 */
	@Override
	public List<Position<E>> descendants(Position<E> p) throws InvalidPositionException {
		List<Position<E>> descendants = new ArrayList<>();
		BinaryTreeNode<E> node = (BinaryTreeNode<E>) p;
		if (this.isInternal(node)) {
			descendants.addAll(this.children(node));
			if (this.hasLeft(node)) {
				Position<E> nodeLeft = this.leftChild(node);
				descendants.addAll(descendants(nodeLeft));
			}
			if (this.hasRight(node)) {
				Position<E> nodeRight = this.rightChild(node);
				descendants.addAll(descendants(nodeRight));
			}
		}
		return descendants;
	}

	/**
	 * Ancestors: returns an List with all ancestors of p
	 * throws an InvalidPositionException depending on the method parent()
	 *
	 * @param p
	 * @return the list with all ancestors of p
	 * @throws InvalidPositionException
	 */
	@Override
	public List<Position<E>> ancestors(Position<E> p) throws InvalidPositionException {
		List<Position<E>> ancestors = new ArrayList<>();
		if (this.parent(p) != null) {
			ancestors.add(this.parent(p));
			Position<E> pParent = this.parent(p);
			ancestors.addAll(ancestors(pParent));
		}
		return ancestors;
	}

	/**
	 * isRoot: checks if the node p is the root node or not 
	 * throws an InvalidPositionException if p equal null or not from this tree
	 * 
	 * @param p
	 * @return true, if is root
	 * @throws InvalidPositionException
	 */
	@Override
	public boolean isRoot(Position<E> p) throws InvalidPositionException {
		BinaryTreeNode<E> node = (BinaryTreeNode<E>) p;
		if (node == null || node.getTreePointer() != this) {
			throw (new InvalidPositionException());
		}
		if (node.getIndex() != 1) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * isInternal: checks if the node p has at least one child 
	 * throws an InvalidPositionException if p is equal null or not from this tree throws also
	 * an InvalidPositionException depending on the methods hasLeft() / hasRight()
	 * 
	 * @param p
	 * @return true, if is internal
	 * @throws InvalidPositionException
	 */
	@Override
	public boolean isInternal(Position<E> p) throws InvalidPositionException {
		BinaryTreeNode<E> node = (BinaryTreeNode<E>) p;
		if (node == null || node.getTreePointer() != this) {
			throw (new InvalidPositionException());
		}
		if (this.hasLeft(p) || this.hasRight(p)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * isExternal: checks if the node p is not internal.
	 * returns true if p hasn't got any children -> therefore is a leaf
	 * throws an InvalidPositionException depending on the methods isInternal / hasLeft() / hasRight()
	 * 
	 * @param p
	 * @return true, if is external
	 * @throws InvalidPositionException
	 */
	@Override
	public boolean isExternal(Position<E> p) throws InvalidPositionException {
		return !this.isInternal(p);
	}

	/**
	 * depth: the depth from node p
	 * throws an InvalidPositionException depending on the methods isRoot() / parent()
	 * 
	 * @param p
	 * @return returns depth of of the node p form the tree
	 * @throws InvalidPositionException
	 */
	@Override
	public int depth(Position<E> p) throws InvalidPositionException {
		BinaryTreeNode<E> node = (BinaryTreeNode<E>) p;
		if (this.isRoot(node)) {
			return 0;
		} else {
			return 1 + depth(parent(node));
		}
	}

	/**
	 * addRoot: adds a root node (at index 1) with the value e to the tree 
	 * throws an UnemptyTreeException if the tree is not empty
	 * 
	 * @param e
	 * @return the root node
	 * @throws UnemptyTreeException
	 */
	@Override
	public Position<E> addRoot(E e) throws UnemptyTreeException {
		if (this.isEmpty() == false) {
			throw (new UnemptyTreeException());
		} else {
			n = 1;
			tree[1] = new BinaryTreeNode<E>(e, 1, this);
			return tree[1];
		}
	}

	/**
	 * insertChild: insert a node p with the value e as a left child 
	 * checks if p is an external node if there is already a left child insert the node as a right child 
	 * throws an InvalidPositionException depending on the methods hasLeft() / hasRight() / isInternal()
	 * 
	 * @param p
	 * @param e
	 * @return the position of the inserted child
	 * @throws InvalidPositionException
	 */
	@Override
	public Position<E> insertChild(Position<E> p, E e) throws InvalidPositionException {
		if (this.isExternal(p)) {
			return this.insertLeft(p, e);
		}
		if (this.hasLeft(p) == false) {
			return this.insertLeft(p, e);
		}
		if (this.hasRight(p) == false) {
			return this.insertRight(p, e);
		} else {
			throw (new InvalidPositionException());
		}
	}

	/**
	 * replaceElement: replaces the value of the node p with e 
	 * throws an InvalidPositionException if the node is equal null or not from this tree
	 * 
	 * @param p
	 * @param e
	 * @return the new inserted element
	 * @throws InvalidPositionException
	 */
	@Override
	public E replaceElement(Position<E> p, E e) throws InvalidPositionException {
		BinaryTreeNode<E> node = (BinaryTreeNode<E>) p;
		if (node == null || node.getTreePointer() != this) {
			throw (new InvalidPositionException());
		} else {
			tree[node.getIndex()] = new BinaryTreeNode<E>(e, node.getIndex(), this);
			return node.element();
		}
	}

	/**
	 * swapElements: switches the nodes p and q 
	 * throws an InvalidPositionException if both nodes are equal null 
	 * throws also an InvalidPositionException depending on the methods replaceElement()
	 * 
	 * @param p
	 * @param q
	 * @throws InvalidPositionException
	 */
	@Override
	public void swapElements(Position<E> p, Position<E> q) throws InvalidPositionException {
		if (p == null && q == null) {
			throw (new InvalidPositionException());
		} else {
			this.replaceElement(p, q.element());
			this.replaceElement(q, p.element());
		}
	}

	/**
	 * size: returns the number of nodes from the tree
	 * 
	 * @return number of nodes from a tree
	 */
	@Override
	public int size() {
		return n;
	}

	/**
	 * isEmpty: checks if there are any nodes in the tree 
	 * 
	 * @return true if there are no nodes in the tree
	 */
	@Override
	public boolean isEmpty() {
		if (n == 0) {
			return true;
		}
		return false;
	}

	/**
	 * Left child: checks if p has a left child and returns its position
	 * throws an InvalidPositionException depending on the methods hasLeft()
	 *
	 * @param p
	 * @return the position of the left child of p
	 * @throws InvalidPositionException
	 */
	@Override
	public Position<E> leftChild(Position<E> p) throws InvalidPositionException {
		BinaryTreeNode<E> node = (BinaryTreeNode<E>) p;
		if (this.hasLeft(p) == false) {
			throw (new InvalidPositionException());
		} else {
			return tree[node.getIndex() * 2];
		}
	}

	/**
	 * Right child: checks if p has a right child and returns its position
	 * throws an InvalidPositionException depending on the methods hasRight()
	 *
	 * @param p
	 * @return the position
	 * @throws InvalidPositionException
	 */
	@Override
	public Position<E> rightChild(Position<E> p) throws InvalidPositionException {
		BinaryTreeNode<E> node = (BinaryTreeNode<E>) p;
		if (this.hasRight(p) == false) {
			throw (new InvalidPositionException());
		} else {
			return tree[node.getIndex() * 2 + 1];
		}
	}

	/**
	 * Sibling: checks if p has a sibling and returns its position
	 * throws an InvalidPositionException depending on the methods hasLeft() / hasRight()
	 *
	 * @param p
	 * @return the position of the sibling of p
	 * @throws InvalidPositionException
	 */
	@Override
	public Position<E> sibling(Position<E> p) throws InvalidPositionException {
		BinaryTreeNode<E> node = (BinaryTreeNode<E>) p;
		BinaryTreeNode<E> nodeParent = (BinaryTreeNode<E>) this.parent(node);
		if (this.hasLeft(nodeParent) && this.hasRight(nodeParent)) {
			int indexLeftNode = nodeParent.getIndex() * 2;
			int indexRightNode = nodeParent.getIndex() * 2 + 1;
			if (node == tree[indexLeftNode]) {
				return tree[node.getIndex() + 1];
			}
			if (node == tree[indexRightNode]) {
				return tree[node.getIndex() - 1];
			}
		}
		throw (new InvalidPositionException());
	}

	/**
	 * hasLeft: checks if p has a left child
	 * throws an InvalidPositionException if p is equal null or not from this tree
	 *
	 * @param p
	 * @return true, if p has a left Child
	 * @throws InvalidPositionException
	 */
	@Override
	public boolean hasLeft(Position<E> p) throws InvalidPositionException {
		BinaryTreeNode<E> node = (BinaryTreeNode<E>) p;
		if (node == null || node.getTreePointer() != this) {
			throw (new InvalidPositionException());
		}
		if (node.getIndex() * 2 < tree.length && tree[node.getIndex() * 2] != null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * hasRight: checks if p has a right child
	 * throws an InvalidPositionException if p is equal null or not from this tree
	 *
	 * @param p
	 * @return true, if p has a right child
	 * @throws InvalidPositionException
	 */
	@Override
	public boolean hasRight(Position<E> p) throws InvalidPositionException {
		BinaryTreeNode<E> node = (BinaryTreeNode<E>) p;
		if (node == null || node.getTreePointer() != this) {
			throw (new InvalidPositionException());
		}
		if (node.getIndex() * 2 + 1 < tree.length && tree[node.getIndex() * 2 + 1] != null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Insert left: insert a left child and raise the size of the tree
	 * throws an InvalidPositionException if there is already a left child 
	 * and depending on the methods hasLeft() 
	 *
	 * @param p
	 * @param e
	 * @return the position of the inserted left child
	 * @throws InvalidPositionException
	 */
	@Override
	public Position<E> insertLeft(Position<E> p, E e) throws InvalidPositionException {
		if (this.hasLeft(p) == true) {
			throw (new InvalidPositionException());
		} else {
			BinaryTreeNode<E> node = (BinaryTreeNode<E>) p;
			int index = node.getIndex() * 2;
			this.checkCapacity(node.getIndex());
			tree[index] = new BinaryTreeNode<E>(e, index, this);
			n = n + 1;
			return tree[index];
		}
	}

	/**
	 * Insert right: insert a right child and raise the size of the tree
	 * throws an InvalidPositionException if there is already a right child 
	 * and depending on the methods hasRight() 
	 *
	 * @param p
	 * @param e
	 * @return the position of the inserted right child
	 * @throws InvalidPositionException
	 */
	@Override
	public Position<E> insertRight(Position<E> p, E e) throws InvalidPositionException {
		if (this.hasRight(p) == true) {
			throw (new InvalidPositionException());
		} else {
			BinaryTreeNode<E> node = (BinaryTreeNode<E>) p;
			int index = node.getIndex() * 2 + 1;
			this.checkCapacity(node.getIndex());
			tree[index] = new BinaryTreeNode<E>(e, index, this);
			n = n + 1;
			return tree[index];
		}
	}

	/**
	 * Insert children: insert a left child and a right child if p is external
	 * throws an InvalidPositionException if p is internal
	 *
	 * @param p
	 * @param e1
	 * @param e2
	 * @throws InvalidPositionException
	 */
	@Override
	public void insertChildren(Position<E> p, E e1, E e2) throws InvalidPositionException {
		if (this.isExternal(p)) {
			this.insertLeft(p, e1);
			this.insertRight(p, e2);
		} else {
			throw (new InvalidPositionException());
		}

	}

	/**
	 * RemoveSubtree: Removes p and his subtrees
	 * throws an InvalidPositionException depending on the methods children() / hasLeft() / hasRight()
	 *
	 * @param p
	 * @throws InvalidPositionException
	 */
	@Override
	public void RemoveSubtree(Position<E> p) throws InvalidPositionException {
		BinaryTreeNode<E> node = (BinaryTreeNode<E>) p;
		if (this.children(node) != null) {
			this.n = n - 1;
			if (this.hasLeft(node)) {
				BinaryTreeNode<E> nodeLeft = (BinaryTreeNode<E>) this.leftChild(node);
				tree[nodeLeft.getIndex()] = null;
				RemoveSubtree(nodeLeft);
			}
			if (this.hasRight(node)) {
				BinaryTreeNode<E> nodeRight = (BinaryTreeNode<E>) this.rightChild(node);
				tree[nodeRight.getIndex()] = null;
				RemoveSubtree(nodeRight);
			}
			if (!this.hasLeft(node) && !this.hasRight(node)) {
				tree[node.getIndex()] = null;
			}
		}
	}

	/**
	 * toString: displays the tree as String
	 */
	@Override
	public String toString() {
		if (this.isEmpty()) {
			return "";
		}
		return preOrderToString(root(), "", "");
	}

	/**
	 * preOrderToString: Formats the tree in a readable String
	 * throws an InvalidPositionException depending on the methods hasLeft() / hasRight()
	 * 
	 * @param p
	 * @param preString
	 * @param postString
	 * @throws InvalidPositionException
	 * @return the formatted String of the tree
	 */
	private String preOrderToString(Position<E> p, String preString, String postString) throws InvalidPositionException {
		BinaryTreeNode<E> node = (BinaryTreeNode<E>) p;
		String val = "";
		if (!this.isEmpty()) {
			val += preString + node.element() + postString;
			preString = "";
			postString = "";
			if (this.hasLeft(node)) {
				if (!this.hasRight(node)) {
					postString += " .)";
				}
				val += " (" + preOrderToString(this.leftChild(node), preString, postString) + "";
			}
			if (this.hasRight(node)) {
				if (!this.hasLeft(node)) {
					preString += "(. ";
				}
				val += " " + preOrderToString(this.rightChild(node), preString, postString) + ")";
			}
		}
		return val;
	}

}