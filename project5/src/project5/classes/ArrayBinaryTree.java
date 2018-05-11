package project5.classes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import project5.exceptions.EmptyTreeException;
import project5.exceptions.InvalidPositionException;
import project5.exceptions.UnemptyTreeException;
import project5.interfaces.BinaryTree;
import project5.interfaces.Position;

public class ArrayBinaryTree<E> implements BinaryTree<E> {

	// tree: binary tree
	private BinaryTreeNode<E>[] tree;

	// n: number of nodes
	private int n;

	// capacity(constant var): inizial size of the tree
	private final int capacity = 2;

	// Constructors
	/**
	 * creates an empty binary tree
	 */
	@SuppressWarnings("unchecked")
	public ArrayBinaryTree() {
		this.n = 0;
		this.tree = new BinaryTreeNode[capacity];
	}

	/**
	 * ArrayBinaryTree creates a binary tree with a root node
	 * 
	 * @param root
	 */
	public ArrayBinaryTree(E root) {
		this();
		this.addRoot(root);
	}

	// Methods
	/**
	 * extends the capacity of an array by doubling its length
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
	 * checkCapacity used in methods insertLeft() & insertRight() checks if the
	 * array is big enough to store new nodes
	 * 
	 * @param index
	 */
	public void checkCapacity(int index) {
		if (index * 2 > tree.length - 1)
			expandCapacity();
	}

	/**
	 * Height. returns the maximum number of ancestors of the tree throws an
	 * exception if the tree contains no nodes
	 *
	 * @return the height of the tree
	 * @throws EmptyTreeException
	 *             the empty tree exception
	 */
	@Override
	public int height() throws EmptyTreeException {
		if (this.isEmpty()) {
			throw (new EmptyTreeException());
		} else {
			int h = 0;
			for (Position<E> p : positions()) {
				if (isExternal(p)) {
					h = Math.max(h, depth(p));
				}
			}
			return h;
		}
	}

	/**
	 * Elements.
	 *
	 * @return the list
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
	 * Positions.
	 *
	 * @return the list
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
	 * Root.
	 *
	 * @return the position
	 * @throws EmptyTreeException
	 *             the empty tree exception
	 */
	@Override
	public Position<E> root() throws EmptyTreeException {
		if (this.isEmpty()) {
			throw (new EmptyTreeException());
		}
		return tree[1];
	}

	/**
	 * Parent. returns the position of the parent element of the node p throws an
	 * InvalidPositionException depending on the method isRoot() and if node p is
	 * the root node
	 *
	 * @param p
	 *            the p
	 * @return the position
	 * @throws InvalidPositionException
	 *             the invalid position exception
	 */
	@Override
	public Position<E> parent(Position<E> p) throws InvalidPositionException {
		BinaryTreeNode<E> node = (BinaryTreeNode<E>) p;
		if (node == null || node.getTreePointer() != this) {
			// if (this.isRoot(p) == true) {
			throw (new InvalidPositionException());
		} else {
			return tree[node.getIndex() / 2];
		}
	}

	/**
	 * Children. returns an ArrayList with all children of p throws an
	 * InvalidPositionException depending on the methods hasLeft() / hasRight()
	 * 
	 * @param p
	 *            the p
	 * @return the list
	 * @throws InvalidPositionException
	 *             the invalid position exception
	 */
	@Override
	public List<Position<E>> children(Position<E> p) throws InvalidPositionException {
		BinaryTreeNode<E> node = (BinaryTreeNode<E>) p;
		List<Position<E>> children = new ArrayList<>(2);
		if (node == null || node.getTreePointer() != this) {
			throw (new InvalidPositionException());
		} else if (hasLeft(p)) {
			children.add(leftChild(p));
			if (hasRight(p)) {
				children.add(rightChild(p));
			}
		}
		return children;
	}

	/**
	 * Descendants.
	 *
	 * @param p
	 *            the p
	 * @return the list
	 * @throws InvalidPositionException
	 *             the invalid position exception
	 */
	@Override
	public List<Position<E>> descendants(Position<E> p) throws InvalidPositionException {
		List<Position<E>> descendants = new ArrayList<>();
		BinaryTreeNode<E> node = (BinaryTreeNode<E>) p;

		if (node == null || node.getTreePointer() != this) {
			throw (new InvalidPositionException());
		} else if (this.isInternal(p)) {
			descendants.addAll(this.children(p));

			if (this.hasLeft(p)) {
				Position<E> pLeft = this.leftChild(p);
				descendants.addAll(descendants(pLeft));
			}
			if (this.hasRight(p)) {
				Position<E> pRight = this.rightChild(p);
				descendants.addAll(descendants(pRight));
			}
		}

		return descendants;
	}

	/**
	 * Ancestors.
	 *
	 * @param p
	 *            the p
	 * @return the list
	 * @throws InvalidPositionException
	 *             the invalid position exception
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
	 * Checks if is root. checks if the node p is the root node or not throws an
	 * InvalidPositionException if p equal null or not from this tree
	 * 
	 * @param p
	 *            the p
	 * @return true, if is root
	 * @throws InvalidPositionException
	 *             the invalid position exception
	 */
	@Override
	public boolean isRoot(Position<E> p) throws InvalidPositionException {
		BinaryTreeNode<E> node = (BinaryTreeNode<E>) p;
		if (node == null || node.getTreePointer() != this) {
			throw (new InvalidPositionException());
		} else if (node.getIndex() != 1) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Checks if is internal. checks if the node p has at least one child throws an
	 * InvalidPositionException if p is equal null or not from this tree throws also
	 * an InvalidPositionException depending on the methods hasLeft() / hasRight()
	 * 
	 * @param p
	 *            the p
	 * @return true, if is internal
	 * @throws InvalidPositionException
	 *             the invalid position exception
	 */
	@Override
	public boolean isInternal(Position<E> p) throws InvalidPositionException {
		BinaryTreeNode<E> node = (BinaryTreeNode<E>) p;
		if (node == null || node.getTreePointer() != this) {
			throw (new InvalidPositionException());
		} else if (this.hasLeft(p) || this.hasRight(p)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks if is external. checks if the node p is not internal. returns true if
	 * p hasn't got any children -> therefore is a leaf throws also an
	 * InvalidPositionException depending on the methods isInternal / hasLeft() /
	 * hasRight()
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
	 * depth
	 * 
	 * @param p
	 * @return returns depth of the tree
	 * @throws InvalidPositionException
	 */
	@Override
	public int depth(Position<E> p) throws InvalidPositionException {
		BinaryTreeNode<E> node = (BinaryTreeNode<E>) p;
		if (this.isRoot(node)) {
			return 0;
		} else
			return 1 + depth(parent(node));
	}

	/**
	 * addRoot adds a root node (at index 1) with the value e to the tree if the
	 * tree is empty throws an UnemptyTreeException if the tree is not empty
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
	 * insertChild insert a node p with the value e as a left child checks if p is
	 * an external node if there is already a left child insert the node as a right
	 * child throws an InvalidPositionException depending on the methods hasLeft() /
	 * hasRight()
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
		} else if (this.hasLeft(p) == false) {
			return this.insertLeft(p, e);
		} else if (this.hasRight(p) == false) {
			return this.insertRight(p, e);
		} else {
			throw (new InvalidPositionException());
		}
	}

	/**
	 * replaceElement replaces the value of the node p with e throws an
	 * InvalidPositionException if the node is equal null or not from this tree
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
	 * swapElements switches the nodes p and q throws an InvalidPositionException if
	 * both nodes are equal null throws also an InvalidPositionException depending
	 * on the methods replaceElement()
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
	 * returns the number of nodes from the tree
	 */
	@Override
	public int size() {
		return n;
	}

	/**
	 * checks if there are any nodes in the tree or not returns false if there are
	 * nodes in the tree
	 */
	@Override
	public boolean isEmpty() {
		if (n == 0) {
			return true;
		}
		return false;
	}

	/**
	 * Left child.
	 *
	 * @param p
	 *            the p
	 * @return the position
	 * @throws InvalidPositionException
	 *             the invalid position exception
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
	 * Right child.
	 *
	 * @param p
	 *            the p
	 * @return the position
	 * @throws InvalidPositionException
	 *             the invalid position exception
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
	 * Sibling.
	 *
	 * @param p
	 *            the p
	 * @return the position
	 * @throws InvalidPositionException
	 *             the invalid position exception
	 */
	@Override
	public Position<E> sibling(Position<E> p) throws InvalidPositionException {
		if (this.hasLeft(this.parent(p))) {
			return leftChild(this.parent(p));
		} else {
			return rightChild(this.parent(p));
		}
	}

	/**
	 * Checks for left.
	 *
	 * @param p
	 *            the p
	 * @return true, if successful
	 * @throws InvalidPositionException
	 *             the invalid position exception
	 */
	@Override
	public boolean hasLeft(Position<E> p) throws InvalidPositionException {
		BinaryTreeNode<E> node = (BinaryTreeNode<E>) p;
		if (node == null || node.getTreePointer() != this) {
			throw (new InvalidPositionException());
		} else if (node.getIndex() * 2 < tree.length && tree[node.getIndex() * 2] != null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Checks for right.
	 *
	 * @param p
	 *            the p
	 * @return true, if successful
	 * @throws InvalidPositionException
	 *             the invalid position exception
	 */
	@Override
	public boolean hasRight(Position<E> p) throws InvalidPositionException {
		BinaryTreeNode<E> node = (BinaryTreeNode<E>) p;
		if (node == null || node.getTreePointer() != this) {
			throw (new InvalidPositionException());
		} else if (node.getIndex() * 2 + 1 < tree.length && tree[node.getIndex() * 2 + 1] != null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Insert left.
	 *
	 * @param p
	 *            the p
	 * @param e
	 *            the e
	 * @return the position
	 * @throws InvalidPositionException
	 *             the invalid position exception
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
	 * Insert right.
	 *
	 * @param p
	 *            the p
	 * @param e
	 *            the e
	 * @return the position
	 * @throws InvalidPositionException
	 *             the invalid position exception
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
	 * Insert children.
	 *
	 * @param p
	 *            the p
	 * @param e1
	 *            the e 1
	 * @param e2
	 *            the e 2
	 * @throws InvalidPositionException
	 *             the invalid position exception
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
	 * Removes the subtree.
	 *
	 * @param p
	 *            the p
	 * @throws InvalidPositionException
	 *             the invalid position exception
	 */
	@Override
	public void RemoveSubtree(Position<E> p) throws InvalidPositionException {
		// TODO Auto-generated method stub
	}

	@Override
	public String toString() {
		return Arrays.toString(tree);
	}

}