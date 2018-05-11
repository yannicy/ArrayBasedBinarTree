package project5.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import project5.classes.ArrayBinaryTree;
import project5.interfaces.BinaryTree;
import project5.interfaces.Position;

public class MainTest {
	
	public static void test5() {
		BinaryTree<Integer> tree = new ArrayBinaryTree<>();
		Position<Integer> p = tree.addRoot(0);
		for (int i = 1; i < 20; i++) {
			p = tree.insertRight(p, i);
			assertFalse(tree.isEmpty());
			assertEquals(i + 1, tree.size());
			assertEquals(i, tree.height());
			assertEquals(i + 1, tree.elements().size());
			assertEquals(i + 1, tree.positions().size());
			assertEquals(i, tree.depth(p));
			assertEquals(i, tree.ancestors(p).size());
			assertEquals(0, tree.descendants(p).size());
//			assertEquals(i, tree.descendants(tree.root()).size());
		}
	}
	
	

	public static void main(String[] args) {
		BinaryTree<Integer> tree1 = new ArrayBinaryTree<>();
		Position<Integer> posRoot = tree1.addRoot(1);
		Position<Integer> posL1 = tree1.insertChild(posRoot, 10);
		Position<Integer> posR1 = tree1.insertChild(posRoot, 20);
		
		Position<Integer> posL2 = tree1.insertChild(posL1, 11);
		Position<Integer> posL3 = tree1.insertChild(posL1, 12);
		Position<Integer> posL4 = tree1.insertChild(posL2, 13);
		Position<Integer> posL5 = tree1.insertChild(posL2, 14);
		Position<Integer> posL6 = tree1.insertChild(posL3, 15);
		Position<Integer> posL7 = tree1.insertChild(posL3, 16);
		
		Position<Integer> posR2 = tree1.insertChild(posR1, 21);
		Position<Integer> posR3 = tree1.insertChild(posR1, 22);
		Position<Integer> posR4 = tree1.insertChild(posR2, 23);
		Position<Integer> posR5 = tree1.insertChild(posR2, 24);
		Position<Integer> posR6 = tree1.insertChild(posR3, 25);
		Position<Integer> posR7 = tree1.insertChild(posR3, 26);
		
		System.out.println(tree1.toString());
		System.out.println(tree1.descendants(posRoot));
		System.out.println(tree1.ancestors(posL7));
		System.out.println(tree1.parent(posRoot));

		BinaryTree<Integer> tree2 = new ArrayBinaryTree<>();
		Position<Integer> posRoot2 = tree2.addRoot(1);
		
//		for (int i = 1; i < 20; i++) {
//		}
		posRoot2 = tree2.insertRight(posRoot2, 5);
		System.out.println(tree2.descendants(posRoot2));
		System.out.println(posRoot2.toString());
//		System.out.println(tree2.toString());
//		System.out.println(tree2.descendants(tree2.root()).size());
//		System.out.println(tree2.root());
		
		test5();
	}
	


}
