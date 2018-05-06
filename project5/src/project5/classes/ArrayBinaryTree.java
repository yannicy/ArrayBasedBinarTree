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

	//tree: binary tree 
	private BinaryTreeNode<E>[] tree;
	
	// n: number of nodes
	private int n;
	
	// capacity(constant var): inizial size of the tree
	private final int capacity = 2;

	// Constructors
	// creates an empty binary tree
	@SuppressWarnings("unchecked")
	public ArrayBinaryTree() {
		this.n = 0;
		this.tree = new BinaryTreeNode[capacity];
	}

	// creates a binary tree with a root node
	public ArrayBinaryTree(E root) {
		// ruft den ersten Konstruktor auf
		this();
		this.addRoot(root);
	}

	// Methods
	// extends the capacity of an array by doubling its length
	@SuppressWarnings("unchecked")
	public void expandCapacity() {
		BinaryTreeNode<E>[] temp = new BinaryTreeNode[tree.length * 2];
		for (int i = 0; i < tree.length; i++) {
			temp[i] = tree[i];
		}
		this.tree = temp;
	}
	
	// used in methods insertLeft() & insertRight()
	// checks if the array is big enough to store new nodes
	public void checkCapacity(int index) {
		if (index * 2 > tree.length - 1)
			expandCapacity();
	}

	@Override
	// returns the maximum number of ancestors of the tree
	// throws an exception if the tree contains no nodes
	public int height() throws EmptyTreeException {
		if (this.isEmpty()) {
			throw (new EmptyTreeException());
		}else {
			int h = 0;
			for (Position<E> p : positions()) {
				if (isExternal(p)) {
					h = Math.max(h, depth(p));
				}
			}
			return h;
		}
	}

	@Override
	public List<E> elements() {
		return null;
	}

	@Override
	public List<Position<E>> positions() {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public Position<E> root() throws EmptyTreeException {
		if (this.isEmpty()) {
			throw (new EmptyTreeException());
		}
		return tree[1];
	}

	@Override
	// returns the position of the parent element of the node p
	// throws an InvalidPositionException depending on the method isRoot() and if node p is the root node
	public Position<E> parent(Position<E> p) throws InvalidPositionException {
		BinaryTreeNode<E> node = (BinaryTreeNode<E>) p;
		if (this.isRoot(p) == true) {
			throw (new InvalidPositionException());
		} else {
			return tree[node.getIndex() / 2];
		}
	}

	@Override
	// returns an ArrayList with all children of p
	// throws an InvalidPositionException depending on the methods hasLeft() / hasRight()
	public List<Position<E>> children(Position<E> p) throws InvalidPositionException {
		List<Position<E>> children = new ArrayList<>(2);
		if (leftChild(p) != null) {
			children.add(leftChild(p));
			if (rightChild(p) != null) {
				children.add(rightChild(p));
			}
		}
		return children;
	}

	@Override
	public List<Position<E>> descendants(Position<E> p) throws InvalidPositionException {
		
		return null;
	}

	@Override
	public List<Position<E>> ancestors(Position<E> p) throws InvalidPositionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	// checks if the node p is the root node or not
	// throws an InvalidPositionException if p equal null or not from this tree 
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

	@Override
	// checks if the node p has at least one child
	// throws an InvalidPositionException if p is equal null or not from this tree
	// throws also an InvalidPositionException depending on the methods hasLeft() / hasRight()
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

	@Override
	// checks if the node p is not internal.
	// returns true if p hasn't got any children -> therefore is a leaf
	// throws also an InvalidPositionException depending on the methods isInternal / hasLeft() / hasRight()
	public boolean isExternal(Position<E> p) throws InvalidPositionException {
		return !this.isInternal(p);
	}

	@Override
	public int depth(Position<E> p) throws InvalidPositionException {
		// p.element();
		// BinaryTreeNode<E> node = (BinaryTreeNode<E>) p;
		// node.getIndex();

		return 0;
	}

	@Override	
	// adds a root node (at index 1) with the value e to the tree if the tree is empty
	// throws an UnemptyTreeException if the tree is not empty
	public Position<E> addRoot(E e) throws UnemptyTreeException {
		if (this.isEmpty() == false) {
			throw (new UnemptyTreeException());
		} else {
			n = n + 1;
			tree[1] = new BinaryTreeNode<E>(e, 1, this);
			return tree[1];
		}

	}

	@Override
	// insert a node p with the value e as a left child
	// if there is already a left child insert the node as a right child
	// throws an InvalidPositionException depending on the methods hasLeft() / hasRight()
	public Position<E> insertChild(Position<E> p, E e) throws InvalidPositionException {
		if (this.hasLeft(p) == false) {
			return this.insertLeft(p, e);
		} else {
			return this.insertRight(p, e);
		}
	}

	@Override
	// replaces the value of the node p with e
	// throws an InvalidPositionException if the node is equal null or not from this tree
	public E replaceElement(Position<E> p, E e) throws InvalidPositionException {
		BinaryTreeNode<E> node = (BinaryTreeNode<E>) p;
		if (node == null || node.getTreePointer() != this) {
			throw (new InvalidPositionException());
		} else {
			tree[node.getIndex()] = new BinaryTreeNode<E>(e, node.getIndex(), this);
			return node.element();
		}
	}

	@Override
	// switches the nodes p and q
	// throws an InvalidPositionException if both nodes are equal null
	// throws also an InvalidPositionException depending on the methods replaceElement()
	public void swapElements(Position<E> p, Position<E> q) throws InvalidPositionException {
		if (p == null && q == null) {
			throw (new InvalidPositionException());
		} else {
			this.replaceElement(p, q.element());
			this.replaceElement(q, p.element());
		}
	}

	@Override
	// returns the number of nodes from the tree
	public int size() {
		return n;
	}

	@Override
	// checks if there are any nodes in the tree or not
	// returns false if there are nodes in the tree
	public boolean isEmpty() {
		if (n == 0) {
			return true;
		}
		return false;
	}

	@Override
	public Position<E> leftChild(Position<E> p) throws InvalidPositionException {
		BinaryTreeNode<E> node = (BinaryTreeNode<E>) p;
		if (this.hasLeft(p) == false) {
			throw (new InvalidPositionException());
		} else {
			return tree[node.getIndex() * 2];
		}
	}

	@Override
	public Position<E> rightChild(Position<E> p) throws InvalidPositionException {
		BinaryTreeNode<E> node = (BinaryTreeNode<E>) p;
		if (this.hasRight(p) == false) {
			throw (new InvalidPositionException());
		} else {
			return tree[node.getIndex() * 2 + 1];
		}
	}

	@Override
	public Position<E> sibling(Position<E> p) throws InvalidPositionException {
		if (this.hasLeft(this.parent(p))) {
			return leftChild(this.parent(p));
		} else {
			return rightChild(this.parent(p));
		}
	}

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

	@Override
	public void insertChildren(Position<E> p, E e1, E e2) throws InvalidPositionException {
		// Check if external -> ToDo
		this.insertLeft(p, e1);
		this.insertRight(p, e2);
	}

	@Override
	public void RemoveSubtree(Position<E> p) throws InvalidPositionException {
		// TODO Auto-generated method stub

	}

	@Override
	public String toString() {
		return Arrays.toString(tree);
	}

}