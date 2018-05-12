package project5.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import project5.classes.ArrayBinaryTree;
import project5.exceptions.EmptyTreeException;
import project5.exceptions.InvalidPositionException;
import project5.exceptions.UnemptyTreeException;
import project5.interfaces.BinaryTree;
import project5.interfaces.Position;

public class ArrayBinaryTreeTest {

	@Test
	public void test1() {
		BinaryTree<Integer> tree = new ArrayBinaryTree<>();

		// empty tree
		assertTrue(tree.isEmpty());
		assertEquals(0, tree.size());
		assertEquals(0, tree.elements().size());
		assertEquals(0, tree.positions().size());
		assertEquals("", tree.toString());

		// 1
		Position<Integer> root = tree.addRoot(1);
		assertFalse(tree.isInternal(root));
		assertTrue(tree.isExternal(root));
		assertFalse(tree.hasLeft(root));
		assertFalse(tree.hasRight(root));
		assertFalse(tree.isEmpty());
		assertEquals(1, tree.size());
		assertEquals(0, tree.height());
		assertEquals(1, tree.elements().size());
		assertEquals(1, tree.positions().size());
		assertEquals(root, tree.root());
		assertEquals((Integer) 1, root.element());
		assertEquals(0, tree.depth(root));
		assertEquals(0, tree.ancestors(root).size());
		assertEquals(0, tree.descendants(root).size());
		assertEquals("1", tree.toString());

		// 1 (2)
		Position<Integer> child1 = tree.insertLeft(root, 2);
		assertTrue(tree.isInternal(root));
		assertFalse(tree.isExternal(root));
		assertFalse(tree.isInternal(child1));
		assertTrue(tree.isExternal(child1));
		assertTrue(tree.hasLeft(root));
		assertFalse(tree.hasRight(root));
		assertFalse(tree.isEmpty());
		assertEquals(2, tree.size());
		assertEquals(1, tree.height());
		assertEquals(child1, tree.leftChild(tree.root()));
		assertEquals((Integer) 2, child1.element());
		assertEquals(2, tree.elements().size());
		assertEquals(2, tree.positions().size());
		assertEquals(1, tree.depth(child1));
		assertEquals(1, tree.ancestors(child1).size());
		assertEquals(0, tree.descendants(child1).size());
		assertEquals(1, tree.descendants(root).size());
		assertEquals("1 (2 .)", tree.toString());

		// 1 (2 3)
		Position<Integer> child2 = tree.insertRight(root, 3);
		assertTrue(tree.isInternal(root));
		assertFalse(tree.isExternal(root));
		assertFalse(tree.isInternal(child2));
		assertTrue(tree.isExternal(child2));
		assertTrue(tree.hasLeft(root));
		assertTrue(tree.hasRight(root));
		assertFalse(tree.isEmpty());
		assertEquals(3, tree.size());
		assertEquals(1, tree.height());
		assertEquals(child2, tree.rightChild(tree.root()));
		assertEquals((Integer) 3, child2.element());
		assertEquals(3, tree.elements().size());
		assertEquals(3, tree.positions().size());
		assertEquals(1, tree.depth(child2));
		assertEquals(1, tree.ancestors(child2).size());
		assertEquals(0, tree.descendants(child2).size());
		assertEquals(2, tree.descendants(root).size());
		assertEquals(tree.sibling(child2), child1);
		assertEquals("1 (2 3)", tree.toString());

		// 1 (2 3 (5))
		Position<Integer> child21 = tree.insertLeft(child2, 5);
		assertTrue(tree.isInternal(root));
		assertFalse(tree.isExternal(root));
		assertFalse(tree.isInternal(child21));
		assertTrue(tree.isExternal(child21));
		assertFalse(tree.isEmpty());
		assertEquals(4, tree.size());
		assertEquals(2, tree.height());
		assertEquals(child21, tree.leftChild(child2));
		assertEquals((Integer) 5, child21.element());
		assertEquals(4, tree.elements().size());
		assertEquals(4, tree.positions().size());
		assertEquals(2, tree.depth(child21));
		assertEquals(2, tree.ancestors(child21).size());
		assertEquals(0, tree.descendants(child21).size());
		assertEquals(3, tree.descendants(root).size());
		assertEquals("1 (2 3 (5 .))", tree.toString());

		// 1 (2 3 (5 6))
		Position<Integer> child22 = tree.insertRight(child2, 6);
		assertTrue(tree.isInternal(root));
		assertFalse(tree.isExternal(root));
		assertFalse(tree.isInternal(child22));
		assertTrue(tree.isExternal(child22));
		assertFalse(tree.isEmpty());
		assertEquals(5, tree.size());
		assertEquals(2, tree.height());
		assertEquals(child22, tree.children(child2).get(1));
		assertEquals((Integer) 6, child22.element());
		assertEquals(5, tree.elements().size());
		assertEquals(5, tree.positions().size());
		assertEquals(2, tree.depth(child22));
		assertEquals(2, tree.ancestors(child22).size());
		assertEquals(0, tree.descendants(child22).size());
		assertEquals(4, tree.descendants(root).size());
		assertEquals(child22, tree.sibling(child21));
		assertEquals("1 (2 3 (5 6))", tree.toString());

		// 1 (2 3 (5 6 (8)))
		Position<Integer> child221 = tree.insertLeft(child22, 8);
		assertTrue(tree.isInternal(root));
		assertFalse(tree.isExternal(root));
		assertFalse(tree.isInternal(child221));
		assertTrue(tree.isExternal(child221));
		assertFalse(tree.isEmpty());
		assertEquals(6, tree.size());
		assertEquals(3, tree.height());
		assertEquals(child221, tree.leftChild(child22));
		assertEquals((Integer) 8, child221.element());
		assertEquals(6, tree.elements().size());
		assertEquals(6, tree.positions().size());
		assertEquals(3, tree.depth(child221));
		assertEquals(3, tree.ancestors(child221).size());
		assertEquals(0, tree.descendants(child221).size());
		assertEquals(5, tree.descendants(root).size());
		assertEquals("1 (2 3 (5 6 (8 .)))", tree.toString());

		tree.replaceElement(child2, 9);
		assertEquals("1 (2 9 (5 6 (8 .)))", tree.toString());

		tree.swapElements(child21, child22);
		assertEquals("1 (2 9 (6 5 (8 .)))", tree.toString());
	}

	@Test
	public void test2() {
		BinaryTree<Integer> tree = new ArrayBinaryTree<>();

		// empty tree
		assertTrue(tree.isEmpty());
		assertEquals(0, tree.size());
		assertEquals(0, tree.elements().size());
		assertEquals(0, tree.positions().size());
		assertEquals("", tree.toString());

		// 1
		Position<Integer> root = tree.addRoot(1);
		assertFalse(tree.isInternal(root));
		assertTrue(tree.isExternal(root));
		assertFalse(tree.isEmpty());
		assertEquals(1, tree.size());
		assertEquals(0, tree.height());
		assertEquals(1, tree.elements().size());
		assertEquals(1, tree.positions().size());
		assertEquals(root, tree.root());
		assertEquals((Integer) 1, root.element());
		assertEquals(0, tree.depth(root));
		assertEquals(0, tree.ancestors(root).size());
		assertEquals(0, tree.descendants(root).size());
		assertEquals("1", tree.toString());

		// 1 (2)
		Position<Integer> child1 = tree.insertLeft(root, 2);
		assertTrue(tree.isInternal(root));
		assertFalse(tree.isExternal(root));
		assertFalse(tree.isInternal(child1));
		assertTrue(tree.isExternal(child1));
		assertFalse(tree.isEmpty());
		assertEquals(2, tree.size());
		assertEquals(1, tree.height());
		assertEquals(child1, tree.leftChild(tree.root()));
		assertEquals((Integer) 2, child1.element());
		assertEquals(2, tree.elements().size());
		assertEquals(2, tree.positions().size());
		assertEquals(1, tree.depth(child1));
		assertEquals(1, tree.ancestors(child1).size());
		assertEquals(0, tree.descendants(child1).size());
		assertEquals(1, tree.descendants(root).size());
		assertEquals("1 (2 .)", tree.toString());

		// 1 (2 3)
		Position<Integer> child2 = tree.insertRight(root, 3);
		assertTrue(tree.isInternal(root));
		assertFalse(tree.isExternal(root));
		assertFalse(tree.isInternal(child2));
		assertTrue(tree.isExternal(child2));
		assertFalse(tree.isEmpty());
		assertEquals(3, tree.size());
		assertEquals(1, tree.height());
		assertEquals(child2, tree.rightChild(tree.root()));
		assertEquals((Integer) 3, child2.element());
		assertEquals(3, tree.elements().size());
		assertEquals(3, tree.positions().size());
		assertEquals(1, tree.depth(child2));
		assertEquals(1, tree.ancestors(child2).size());
		assertEquals(0, tree.descendants(child2).size());
		assertEquals(2, tree.descendants(root).size());
		assertEquals("1 (2 3)", tree.toString());

		// 1 (2 3 (5))
		Position<Integer> child21 = tree.insertLeft(child2, 5);
		assertTrue(tree.isInternal(root));
		assertFalse(tree.isExternal(root));
		assertFalse(tree.isInternal(child21));
		assertTrue(tree.isExternal(child21));
		assertFalse(tree.isEmpty());
		assertEquals(4, tree.size());
		assertEquals(2, tree.height());
		assertEquals(child21, tree.leftChild(child2));
		assertEquals((Integer) 5, child21.element());
		assertEquals(4, tree.elements().size());
		assertEquals(4, tree.positions().size());
		assertEquals(2, tree.depth(child21));
		assertEquals(2, tree.ancestors(child21).size());
		assertEquals(0, tree.descendants(child21).size());
		assertEquals(3, tree.descendants(root).size());
		assertEquals("1 (2 3 (5 .))", tree.toString());

		// 1 (2 3 (5 6))
		Position<Integer> child22 = tree.insertRight(child2, 6);
		assertTrue(tree.isInternal(root));
		assertFalse(tree.isExternal(root));
		assertFalse(tree.isInternal(child22));
		assertTrue(tree.isExternal(child22));
		assertFalse(tree.isEmpty());
		assertEquals(5, tree.size());
		assertEquals(2, tree.height());
		assertEquals(child22, tree.rightChild(child2));
		assertEquals((Integer) 6, child22.element());
		assertEquals(5, tree.elements().size());
		assertEquals(5, tree.positions().size());
		assertEquals(2, tree.depth(child22));
		assertEquals(2, tree.ancestors(child22).size());
		assertEquals(0, tree.descendants(child22).size());
		assertEquals(4, tree.descendants(root).size());
		assertEquals("1 (2 3 (5 6))", tree.toString());

		// 1 (2 3 (5 6 (8)))
		Position<Integer> child221 = tree.insertRight(child22, 8);
		assertTrue(tree.isInternal(root));
		assertFalse(tree.isExternal(root));
		assertFalse(tree.isInternal(child221));
		assertTrue(tree.isExternal(child221));
		assertFalse(tree.isEmpty());
		assertEquals(6, tree.size());
		assertEquals(3, tree.height());
		assertEquals(child221, tree.rightChild(child22));
		assertEquals((Integer) 8, child221.element());
		assertEquals(6, tree.elements().size());
		assertEquals(6, tree.positions().size());
		assertEquals(3, tree.depth(child221));
		assertEquals(3, tree.ancestors(child221).size());
		assertEquals(0, tree.descendants(child221).size());
		assertEquals(5, tree.descendants(root).size());
		assertEquals("1 (2 3 (5 6 (. 8)))", tree.toString());

		tree.replaceElement(child2, 9);
		assertEquals("1 (2 9 (5 6 (. 8)))", tree.toString());

		tree.swapElements(child21, child22);
		assertEquals("1 (2 9 (6 5 (. 8)))", tree.toString());
	}

	@Test
	public void test3() {
		BinaryTree<Integer> tree = new ArrayBinaryTree<>();
		Position<Integer> p = tree.addRoot(0);
		for (int i = 1; i < 20; i++) {
			p = tree.insertLeft(p, i);
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

	@Test
	public void test4() {
		BinaryTree<Integer> tree = new ArrayBinaryTree<>();
		Position<Integer> p = tree.addRoot(0);
		for (int i = 1; i < 20; i++) {
			p = tree.insertLeft(p, i);
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

	@Test
	public void test5() {
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

	@Test
	public void test5b() {
		BinaryTree<Integer> tree = new ArrayBinaryTree<>();
		Position<Integer> root = tree.addRoot(0);
		Position<Integer> child1 = tree.insertLeft(root, 1);
		Position<Integer> child2 = tree.insertRight(root, 2);
		tree.insertLeft(child1, 11);
		tree.insertRight(child1, 12);
		tree.insertLeft(child2, 21);
		tree.insertRight(child2, 22);
		assertFalse(tree.isEmpty());
		assertEquals(tree.size(), 7);
		assertEquals("0 (1 (11 12) 2 (21 22))", tree.toString());
		tree.RemoveSubtree(child1);
		assertEquals("0 (. 2 (21 22))", tree.toString());
		assertFalse(tree.hasLeft(tree.root()));
		assertTrue(tree.hasRight(tree.root()));
		assertEquals(4, tree.size());
		tree.RemoveSubtree(child2);
		assertEquals("0", tree.toString());
		assertFalse(tree.hasLeft(tree.root()));
		assertFalse(tree.hasRight(tree.root()));
		assertEquals(1, tree.size());
	}

	@Test(expected = EmptyTreeException.class)
	public void test6() {
		BinaryTree<Integer> tree = new ArrayBinaryTree<>();
		tree.height();
	}

	@Test(expected = EmptyTreeException.class)
	public void test7() {
		BinaryTree<Integer> tree = new ArrayBinaryTree<>();
		tree.root();
	}

	@Test(expected = InvalidPositionException.class)
	public void test8() {
		BinaryTree<Integer> tree = new ArrayBinaryTree<>();
		tree.parent(null);
	}

	@Test(expected = InvalidPositionException.class)
	public void test9() {
		BinaryTree<Integer> tree1 = new ArrayBinaryTree<>();
		BinaryTree<Integer> tree2 = new ArrayBinaryTree<>();
		Position<Integer> p = tree2.addRoot(1);
		tree1.parent(p);
	}

	@Test(expected = InvalidPositionException.class)
	public void test10() {
		BinaryTree<Integer> tree = new ArrayBinaryTree<>();
		tree.children(null);
	}

	@Test(expected = InvalidPositionException.class)
	public void test11() {
		BinaryTree<Integer> tree1 = new ArrayBinaryTree<>();
		BinaryTree<Integer> tree2 = new ArrayBinaryTree<>();
		Position<Integer> p = tree2.addRoot(1);
		tree1.children(p);
	}

	@Test(expected = InvalidPositionException.class)
	public void test12() {
		BinaryTree<Integer> tree = new ArrayBinaryTree<>();
		tree.descendants(null);
	}

	@Test(expected = InvalidPositionException.class)
	public void test13() {
		BinaryTree<Integer> tree1 = new ArrayBinaryTree<>();
		BinaryTree<Integer> tree2 = new ArrayBinaryTree<>();
		Position<Integer> p = tree2.addRoot(1);
		tree1.descendants(p);
	}

	@Test(expected = InvalidPositionException.class)
	public void test14() {
		BinaryTree<Integer> tree = new ArrayBinaryTree<>();
		tree.ancestors(null);
	}

	@Test(expected = InvalidPositionException.class)
	public void test15() {
		BinaryTree<Integer> tree1 = new ArrayBinaryTree<>();
		BinaryTree<Integer> tree2 = new ArrayBinaryTree<>();
		Position<Integer> p = tree2.addRoot(1);
		tree1.ancestors(p);
	}

	@Test(expected = InvalidPositionException.class)
	public void test16() {
		BinaryTree<Integer> tree = new ArrayBinaryTree<>();
		tree.isRoot(null);
	}

	@Test(expected = InvalidPositionException.class)
	public void test17() {
		BinaryTree<Integer> tree1 = new ArrayBinaryTree<>();
		BinaryTree<Integer> tree2 = new ArrayBinaryTree<>();
		Position<Integer> p = tree2.addRoot(1);
		tree1.isRoot(p);
	}

	@Test(expected = InvalidPositionException.class)
	public void test18() {
		BinaryTree<Integer> tree = new ArrayBinaryTree<>();
		tree.isInternal(null);
	}

	@Test(expected = InvalidPositionException.class)
	public void test19() {
		BinaryTree<Integer> tree1 = new ArrayBinaryTree<>();
		BinaryTree<Integer> tree2 = new ArrayBinaryTree<>();
		Position<Integer> p = tree2.addRoot(1);
		tree1.isInternal(p);
	}

	@Test(expected = InvalidPositionException.class)
	public void test20() {
		BinaryTree<Integer> tree = new ArrayBinaryTree<>();
		tree.isExternal(null);
	}

	@Test(expected = InvalidPositionException.class)
	public void test21() {
		BinaryTree<Integer> tree1 = new ArrayBinaryTree<>();
		BinaryTree<Integer> tree2 = new ArrayBinaryTree<>();
		Position<Integer> p = tree2.addRoot(1);
		tree1.isExternal(p);
	}

	@Test(expected = InvalidPositionException.class)
	public void test22() {
		BinaryTree<Integer> tree = new ArrayBinaryTree<>();
		tree.depth(null);
	}

	@Test(expected = InvalidPositionException.class)
	public void test23() {
		BinaryTree<Integer> tree1 = new ArrayBinaryTree<>();
		BinaryTree<Integer> tree2 = new ArrayBinaryTree<>();
		Position<Integer> p = tree2.addRoot(1);
		tree1.depth(p);
	}

	@Test(expected = UnemptyTreeException.class)
	public void test24() {
		BinaryTree<Integer> tree = new ArrayBinaryTree<>();
		tree.addRoot(1);
		tree.addRoot(2);
	}

	@Test(expected = InvalidPositionException.class)
	public void test25() {
		BinaryTree<Integer> tree = new ArrayBinaryTree<>();
		tree.insertChild(null, 1);
	}

	@Test(expected = InvalidPositionException.class)
	public void test26() {
		BinaryTree<Integer> tree1 = new ArrayBinaryTree<>();
		BinaryTree<Integer> tree2 = new ArrayBinaryTree<>();
		Position<Integer> p = tree2.addRoot(1);
		tree1.insertChild(p, 2);
	}

	@Test(expected = InvalidPositionException.class)
	public void test27() {
		BinaryTree<Integer> tree = new ArrayBinaryTree<>();
		tree.replaceElement(null, 1);
	}

	@Test(expected = InvalidPositionException.class)
	public void test28() {
		BinaryTree<Integer> tree1 = new ArrayBinaryTree<>();
		BinaryTree<Integer> tree2 = new ArrayBinaryTree<>();
		Position<Integer> p = tree2.addRoot(1);
		tree1.replaceElement(p, 2);
	}

	@Test(expected = InvalidPositionException.class)
	public void test29() {
		BinaryTree<Integer> tree = new ArrayBinaryTree<>();
		tree.swapElements(null, null);
	}

	@Test(expected = InvalidPositionException.class)
	public void test30() {
		BinaryTree<Integer> tree1 = new ArrayBinaryTree<>();
		BinaryTree<Integer> tree2 = new ArrayBinaryTree<>();
		Position<Integer> p = tree2.addRoot(1);
		Position<Integer> q = tree1.addRoot(2);
		tree1.swapElements(p, q);
	}

	@Test(expected = InvalidPositionException.class)
	public void test31() {
		BinaryTree<Integer> tree = new ArrayBinaryTree<>();
		tree.leftChild(null);
	}

	@Test(expected = InvalidPositionException.class)
	public void test32() {
		BinaryTree<Integer> tree1 = new ArrayBinaryTree<>();
		BinaryTree<Integer> tree2 = new ArrayBinaryTree<>();
		Position<Integer> p = tree2.addRoot(1);
		tree1.leftChild(p);
	}

	@Test(expected = InvalidPositionException.class)
	public void test33() {
		BinaryTree<Integer> tree = new ArrayBinaryTree<>();
		tree.rightChild(null);
	}

	@Test(expected = InvalidPositionException.class)
	public void test34() {
		BinaryTree<Integer> tree1 = new ArrayBinaryTree<>();
		BinaryTree<Integer> tree2 = new ArrayBinaryTree<>();
		Position<Integer> p = tree2.addRoot(1);
		tree1.rightChild(p);
	}

	@Test(expected = InvalidPositionException.class)
	public void test35() {
		BinaryTree<Integer> tree = new ArrayBinaryTree<>();
		tree.sibling(null);
	}

	@Test(expected = InvalidPositionException.class)
	public void test36() {
		BinaryTree<Integer> tree1 = new ArrayBinaryTree<>();
		BinaryTree<Integer> tree2 = new ArrayBinaryTree<>();
		Position<Integer> p = tree2.addRoot(1);
		tree1.sibling(p);
	}

	@Test(expected = InvalidPositionException.class)
	public void test37() {
		BinaryTree<Integer> tree = new ArrayBinaryTree<>();
		tree.hasLeft(null);
	}

	@Test(expected = InvalidPositionException.class)
	public void test38() {
		BinaryTree<Integer> tree1 = new ArrayBinaryTree<>();
		BinaryTree<Integer> tree2 = new ArrayBinaryTree<>();
		Position<Integer> p = tree2.addRoot(1);
		tree1.hasLeft(p);
	}

	@Test(expected = InvalidPositionException.class)
	public void test39() {
		BinaryTree<Integer> tree = new ArrayBinaryTree<>();
		tree.hasRight(null);
	}

	@Test(expected = InvalidPositionException.class)
	public void test40() {
		BinaryTree<Integer> tree1 = new ArrayBinaryTree<>();
		BinaryTree<Integer> tree2 = new ArrayBinaryTree<>();
		Position<Integer> p = tree2.addRoot(1);
		tree1.hasRight(p);
	}

	@Test(expected = InvalidPositionException.class)
	public void test41() {
		BinaryTree<Integer> tree = new ArrayBinaryTree<>();
		tree.insertLeft(null, 1);
	}

	@Test(expected = InvalidPositionException.class)
	public void test42() {
		BinaryTree<Integer> tree1 = new ArrayBinaryTree<>();
		BinaryTree<Integer> tree2 = new ArrayBinaryTree<>();
		Position<Integer> p = tree2.addRoot(1);
		tree1.insertLeft(p, 1);
	}

	@Test(expected = InvalidPositionException.class)
	public void test43() {
		BinaryTree<Integer> tree = new ArrayBinaryTree<>();
		tree.insertRight(null, 1);
	}

	@Test(expected = InvalidPositionException.class)
	public void test44() {
		BinaryTree<Integer> tree1 = new ArrayBinaryTree<>();
		BinaryTree<Integer> tree2 = new ArrayBinaryTree<>();
		Position<Integer> p = tree2.addRoot(1);
		tree1.insertRight(p, 1);
	}

	@Test(expected = InvalidPositionException.class)
	public void test45() {
		BinaryTree<Integer> tree = new ArrayBinaryTree<>();
		tree.insertChildren(null, 1, 2);
	}

	@Test(expected = InvalidPositionException.class)
	public void test46() {
		BinaryTree<Integer> tree1 = new ArrayBinaryTree<>();
		BinaryTree<Integer> tree2 = new ArrayBinaryTree<>();
		Position<Integer> p = tree2.addRoot(1);
		tree1.insertChildren(p, 1, 2);
	}

	@Test
	public void test48() {
		// BinaryTree<Integer> tree = new ArrayBinaryTree<>();
		// List<Integer> inorder = tree.inorder();
		// assertTrue(inorder.equals(Arrays.asList()));
	}

	@Test
	public void test49() {
		BinaryTree<Integer> tree = new ArrayBinaryTree<>();
		Position<Integer> root = tree.addRoot(1);
		tree.insertLeft(root, 0);
		tree.insertRight(root, 2);
		assertEquals("1 (0 2)", tree.toString());
		// List<Integer> inorder = tree.inorder();
		// assertTrue(inorder.equals(Arrays.asList(0, 1, 2)));
	}

	@Test
	public void test50() {
		BinaryTree<Integer> tree = new ArrayBinaryTree<>();
		Position<Integer> root = tree.addRoot(5);
		Position<Integer> left = tree.insertLeft(root, 3);
		Position<Integer> right = tree.insertRight(root, 8);
		Position<Integer> leftleft = tree.insertLeft(left, 2);
		tree.insertLeft(leftleft, 1);
		tree.insertRight(left, 4);
		Position<Integer> rightleft = tree.insertLeft(right, 6);
		tree.insertRight(rightleft, 7);
		Position<Integer> rightright = tree.insertRight(right, 9);
		tree.insertRight(rightright, 10);
		assertEquals("5 (3 (2 (1 .) 4) 8 (6 (. 7) 9 (. 10)))", tree.toString());
		// List<Integer> inorder = tree.inorder();
		// assertTrue(inorder.equals(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9,
		// 10)));
	}

}
