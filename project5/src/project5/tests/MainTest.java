package project5.tests;

import project5.classes.ArrayBinaryTree;

public class MainTest {

	public static void main(String[] args) {
		ArrayBinaryTree<Integer> emptyTree = new ArrayBinaryTree<>();
		ArrayBinaryTree<Integer> rootTree = new ArrayBinaryTree<Integer>(3);
		
		System.out.println("Empty root: ");
		System.out.println(emptyTree.toString());
		System.out.println(emptyTree.isEmpty());
		System.out.println(emptyTree.size());
		//emptyTree.addRoot(4); 
		emptyTree.expandCapacity();
		emptyTree.checkCapacity();
		emptyTree.addRoot(5);
		System.out.println(emptyTree.isRoot(1));
		System.out.println(emptyTree.toString());
		
		System.out.println("Tree with root: ");
		System.out.println(rootTree.toString());
		System.out.println(rootTree.isEmpty());


		

	}

}
