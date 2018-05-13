package project5.tests;

import project5.classes.ArrayBinaryTree;
import project5.interfaces.BinaryTree;
import project5.interfaces.Position;

public class MainTest {

	public static void main(String[] args) {
		BinaryTree<Integer> tree = new ArrayBinaryTree<>();
		
		// tree height: 0
		Position<Integer> rootPos = tree.addRoot(1);
		
		// tree height: 1
		Position<Integer> leftNodePos1 = tree.insertLeft(rootPos, 2);
		Position<Integer> rightNodePos1 = tree.insertRight(rootPos, 3);
		
		// tree height: 2
		tree.insertLeft(leftNodePos1, 4);
		tree.insertRight(leftNodePos1, 5);
		
		tree.insertLeft(rightNodePos1, 6);
		tree.insertRight(rightNodePos1, 7);

		// toString
		System.out.println(tree.toString());
	}

}
