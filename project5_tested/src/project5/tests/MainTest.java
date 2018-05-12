package project5.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import project5.classes.ArrayBinaryTree;
import project5.classes.BinaryTreeNode;
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
			assertEquals(i, tree.descendants(tree.root()).size());
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
		
		BinaryTree<Integer> tree2 = new ArrayBinaryTree<>();
		Position<Integer> posRoot2 = tree2.addRoot(1);
		Position<Integer> posL12 = tree2.insertChild(posRoot2, 2);
		Position<Integer> posR12 = tree2.insertRight(posRoot2, 3);
		
		Position<Integer> posL22 = tree2.insertChild(posL12, 4);
		Position<Integer> posL32 = tree2.insertChild(posL12, 5);
		Position<Integer> posL42 = tree2.insertChild(posL22, 13);
//		Position<Integer> posL52 = tree2.insertChild(posL22, 14);
//		Position<Integer> posL62 = tree2.insertChild(posL32, 15);
		Position<Integer> posL72 = tree2.insertChild(posL32, 16);
		
		BinaryTree<Integer> tree3 = new ArrayBinaryTree<>();
//		Position<Integer> posRoot2 = tree2.addRoot(1);
		
		System.out.println(tree2.toString());
		
		System.out.println(tree1.toString());
		System.out.println(tree1.descendants(posRoot));
//		System.out.println(tree1.ancestors(posL7));
		
		
		BinaryTree<Integer> tree4 = new ArrayBinaryTree<>();
		Position<Integer> posRoot4 = tree4.addRoot(1);
		Position<Integer> pos2 = tree4.insertLeft(posRoot4, 2);
		Position<Integer> pos3 = tree4.insertRight(posRoot4, 3);
		
		Position<Integer> pos5 = tree4.insertChild(pos3, 4);
		Position<Integer> pos6 = tree4.insertChild(pos3, 5);
		Position<Integer> pos8 = tree4.insertRight(pos6, 7);

		System.out.println(tree4.toString());
		System.out.println(tree4.descendants(posRoot4).toString());
		System.out.println(tree4.descendants(posRoot4).size());
		
	}

}
