package project5.classes;

import java.util.Arrays;
import java.util.List;

import project5.exceptions.EmptyTreeException;
import project5.exceptions.InvalidPositionException;
import project5.exceptions.UnemptyTreeException;
import project5.interfaces.BinaryTree;
import project5.interfaces.Position;

public class ArrayBinaryTree<E> implements BinaryTree<E> {

	private BinaryTreeNode<E>[] tree;
	private int n;
	private final int capacity = 2;

	// creates an empty binary tree
	@SuppressWarnings("unchecked")
	public ArrayBinaryTree() {
		this.n = 0;
		this.tree = new BinaryTreeNode[capacity];
	}
	
	// creates a binray tree with a certain values as root
	public ArrayBinaryTree(E root) {
		// ruft den ersten Konstruktor auf
		this();
		this.addRoot(root);
	}
	
	@SuppressWarnings("unchecked")
	public void expandCapacity() {
		BinaryTreeNode<E>[] temp = new BinaryTreeNode[tree.length * 2];
		
		for (int i = 0; i < tree.length; i++) {
			temp[i] = tree[i];
		}
		
		this.tree = temp;
	}
	
	public void checkCapacity() {
		if(tree[tree.length-1] != null) {
			expandCapacity();
		}
		else {
			System.out.println("Das Array ist noch genügend gross");
		}
	}

	@Override
	public int height() throws EmptyTreeException {
		// TODO Auto-generated method stub

		return 0;
	}

	@Override
	public List<E> elements() {
		// TODO Auto-generated method stub
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
	public Position<E> parent(Position<E> p) throws InvalidPositionException {
		BinaryTreeNode<E> node = (BinaryTreeNode<E>) p;
		if (node.getIndex() == 1) {
			throw (new InvalidPositionException());
		}
		return tree[node.getIndex() / 2];
	}

	@Override
	public List<Position<E>> children(Position<E> p) throws InvalidPositionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Position<E>> descendants(Position<E> p) throws InvalidPositionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Position<E>> ancestors(Position<E> p) throws InvalidPositionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isRoot(Position<E> p) throws InvalidPositionException {
		BinaryTreeNode<E> node = (BinaryTreeNode<E>) p;
		if (node.getIndex() != 1) {
			throw (new InvalidPositionException());
		}else {
			return true;
		}
	}

	@Override
	public boolean isInternal(Position<E> p) throws InvalidPositionException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isExternal(Position<E> p) throws InvalidPositionException {
		// TODO Auto-generated method stub
		
		return false;
	}

	@Override
	public int depth(Position<E> p) throws InvalidPositionException {
		p.element();
		BinaryTreeNode<E> node = (BinaryTreeNode<E>) p;
		node.getIndex();

		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Position<E> addRoot(E e) throws UnemptyTreeException {
		// if not empty..... 
		if(this.isEmpty() == false) {
			throw (new UnemptyTreeException());
		}else {
			n = n + 1;
			tree[1] = new BinaryTreeNode<E>(e, 1);
			return tree[1];
		}
		
	}

	@Override
	public Position<E> insertChild(Position<E> p, E e) throws InvalidPositionException {
		// TODO Auto-generated method stub
		BinaryTreeNode<E> node = (BinaryTreeNode<E>) p;
		if(tree[node.getIndex() * 2] == null){
			n = n+1;
			tree[node.getIndex() * 2] = new BinaryTreeNode<E>(e, node.getIndex() * 2);
			return tree[node.getIndex() * 2];
		}else if (tree[node.getIndex()*2+1] == null){
			n = n+1;
			tree[node.getIndex() * 2+1] = new BinaryTreeNode<E>(e, node.getIndex()*2+1);
			return tree[node.getIndex() * 2+1];
		}else {
			throw (new InvalidPositionException());
		}
		
	}
	@Override
	public E replaceElement(Position<E> p, E e) throws InvalidPositionException {
		
		return null;
	}

	@Override
	public void swapElements(Position<E> p, Position<E> q) throws InvalidPositionException {
		// TODO Auto-generated method stub$
		BinaryTreeNode<E> nodeP = (BinaryTreeNode<E>) p;
		BinaryTreeNode<E> nodeQ = (BinaryTreeNode<E>) q;
		BinaryTreeNode<E> nodeTemp;
		
		// IF statement um Probleme abzufangen -> p & q = null, p & q in verschiedenen trees
		
		
		nodeTemp = tree[nodeP.getIndex()];
	
		tree[nodeP.getIndex()] = tree[nodeQ.getIndex()];
		tree[nodeQ.getIndex()] = nodeTemp;
		
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return n;
	}

	@Override
	public boolean isEmpty() {
		// tree.length <= capacity && zusätzlich?
		// Noch prüfen ob es andere Elemente hat?
		if(n == 0) {
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Position<E> leftChild(Position<E> p) throws InvalidPositionException {
		// TODO Auto-generated method stub
		p = (Position<E>) tree[2*n];
		return p;
	}

	@Override
	public Position<E> rightChild(Position<E> p) throws InvalidPositionException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Position<E> sibling(Position<E> p) throws InvalidPositionException {
		/*BinaryTreeNode<E> node = (BinaryTreeNode<E>) p;
		if(this.parent(node)) {
			
		}
		if (node.getIndex() == 1) {
			throw (new InvalidPositionException());
		}
		this.parent(node);
		ArrayBinaryTree<E> test;
		test.parent(node);
		parent
		if(tree[node.getIndex() == tree.])*/
		return null;
	}

	@Override
	public boolean hasLeft(Position<E> p) throws InvalidPositionException {
		BinaryTreeNode<E> node = (BinaryTreeNode<E>) p;
		if(tree[node.getIndex()*2] != null) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean hasRight(Position<E> p) throws InvalidPositionException {
		BinaryTreeNode<E> node = (BinaryTreeNode<E>) p;
		if(tree[node.getIndex()*2+1] != null) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public Position<E> insertLeft(Position<E> p, E e) throws InvalidPositionException {
		BinaryTreeNode<E> node = (BinaryTreeNode<E>) p;
		if(tree[node.getIndex() * 2] == null){
			n = n+1;
			tree[node.getIndex() * 2] = new BinaryTreeNode<E>(e, node.getIndex() * 2);
			return tree[node.getIndex() * 2];
		}else {
			throw (new InvalidPositionException());
		}
	}

	@Override
	public Position<E> insertRight(Position<E> p, E e) throws InvalidPositionException {
		BinaryTreeNode<E> node = (BinaryTreeNode<E>) p;
		if(tree[node.getIndex() * 2+1] == null){
			n = n+1;
			tree[node.getIndex() * 2+1] = new BinaryTreeNode<E>(e, node.getIndex() * 2);
			return tree[node.getIndex() * 2+1];
		}else {
			throw (new InvalidPositionException());
		}
	}

	@Override
	public void insertChildren(Position<E> p, E e1, E e2) throws InvalidPositionException {
		// TODO Auto-generated method stub

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