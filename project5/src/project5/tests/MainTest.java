package project5.tests;

import org.junit.Test;

import project5.classes.ArrayBinaryTree;
import project5.classes.BinaryTreeNode;
import project5.exceptions.InvalidPositionException;
import project5.interfaces.BinaryTree;
import project5.interfaces.Position;

public class MainTest {

	public static void main(String[] args) {
//		BinaryTree<Integer> tree1 = new ArrayBinaryTree<>();
//		BinaryTree<Integer> tree2 = new ArrayBinaryTree<>();
//		Position<Integer> po = tree2.addRoot(1);
//		tree2.insertChildren(po, 10, 5);
//		Position<Integer> le = tree2.leftChild(po);
//		System.out.println(tree2.isRoot(le));
		
//		BinaryTree<Integer> tree = new ArrayBinaryTree<>();
//		tree.insertChild(null, 1);
//		
//		BinaryTree<Integer> tree = new ArrayBinaryTree<>();
//		tree.leftChild(null);

//		@Test(expected = InvalidPositionException.class)
//		public void test38() {
//			BinaryTree<Integer> tree1 = new ArrayBinaryTree<>();
//			BinaryTree<Integer> tree2 = new ArrayBinaryTree<>();
//			Position<Integer> p = tree2.addRoot(1);
//			tree1.hasLeft(p);
//		}
		
		BinaryTree<Integer> tree1 = new ArrayBinaryTree<>();
		BinaryTree<Integer> tree2 = new ArrayBinaryTree<>();
		Position<Integer> p = tree2.addRoot(1);
		tree2.insertChildren(p, 5, 6);
		System.out.println(tree2.children(p));
		System.out.println(tree2.toString());
//		BinaryTree<Integer> tree1 = new ArrayBinaryTree<>();
//		BinaryTree<Integer> tree2 = new ArrayBinaryTree<>();
//		Position<Integer> p = tree2.addRoot(1);
//		System.out.println(p.toString());
		

	}

}
