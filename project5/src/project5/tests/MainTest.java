package project5.tests;

import project5.classes.ArrayBinaryTree;
import project5.interfaces.Position;

public class MainTest {

	public static void main(String[] args) {
		ArrayBinaryTree<Integer> emptyTree = new ArrayBinaryTree<>();
		// ArrayBinaryTree<Integer> rootTree = new ArrayBinaryTree<Integer>(3);
		
		System.out.println("Empty tree: ");
		System.out.println(emptyTree.toString());
		emptyTree.addRoot(5);
		System.out.println(emptyTree.toString());
		
		
		Position<Integer> pos = emptyTree.root();
		Position<Integer> pos2 = emptyTree.insertChild(pos, 2);
		System.out.println(emptyTree.toString());
		/*Position<Integer> pos3 = emptyTree.insertChild(pos, 8);
		Position<Integer> posParent = emptyTree.parent(pos2);

		System.out.println(emptyTree.size());
		System.out.println(emptyTree.toString());
		
		emptyTree.swapElements(pos, pos2);
		System.out.println(emptyTree.size());
		System.out.println(emptyTree.toString());
		System.out.println("TEST: " + pos);
		emptyTree.insertChildren(pos3, 9, 0);
		System.out.println(emptyTree.toString());
		System.out.println(emptyTree.size()); /*
		// System.out.println(emptyTree.isEmpty());
		/*System.out.println(emptyTree.size());
		//emptyTree.addRoot(4); 
		emptyTree.expandCapacity();
		emptyTree.checkCapacity();
		//emptyTree.addRoot(5);
		Position<Integer> pos = emptyTree.addRoot(5);
		System.out.println(emptyTree.isRoot(pos));
		System.out.println(emptyTree.);
		
		System.out.println(emptyTree.toString());
		
		System.out.println("Tree with root: ");
		System.out.println(rootTree.toString());
		System.out.println(rootTree.isEmpty());
		 */

		

	}

}
