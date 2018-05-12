package project5.interfaces;

import project5.exceptions.InvalidPositionException;

public interface BinaryTree<E> extends Tree<E> {
	
	/**
	 * Left child.
	 *
	 * @param p the p
	 * @return the position
	 * @throws InvalidPositionException the invalid position exception
	 */
	public Position<E> leftChild(Position<E> p) throws InvalidPositionException;

	/**
	 * Right child.
	 *
	 * @param p the p
	 * @return the position
	 * @throws InvalidPositionException the invalid position exception
	 */
	public Position<E> rightChild(Position<E> p) throws InvalidPositionException;

	/**
	 * Sibling.
	 *
	 * @param p the p
	 * @return the position
	 * @throws InvalidPositionException the invalid position exception
	 */
	public Position<E> sibling(Position<E> p) throws InvalidPositionException;

	/**
	 * Checks for left.
	 *
	 * @param p the p
	 * @return true, if successful
	 * @throws InvalidPositionException the invalid position exception
	 */
	public boolean hasLeft(Position<E> p) throws InvalidPositionException;

	/**
	 * Checks for right.
	 *
	 * @param p the p
	 * @return true, if successful
	 * @throws InvalidPositionException the invalid position exception
	 */
	public boolean hasRight(Position<E> p) throws InvalidPositionException;

	/**
	 * Insert left.
	 *
	 * @param p the p
	 * @param e the e
	 * @return the position
	 * @throws InvalidPositionException the invalid position exception
	 */
	public Position<E> insertLeft(Position<E> p, E e) throws InvalidPositionException;

	/**
	 * Insert right.
	 *
	 * @param p the p
	 * @param e the e
	 * @return the position
	 * @throws InvalidPositionException the invalid position exception
	 */
	public Position<E> insertRight(Position<E> p, E e) throws InvalidPositionException;

	/**
	 * Insert children.
	 *
	 * @param p the p
	 * @param e1 the e 1
	 * @param e2 the e 2
	 * @throws InvalidPositionException the invalid position exception
	 */
	public void insertChildren(Position<E> p, E e1, E e2) throws InvalidPositionException;

	/**
	 * Removes the subtree.
	 *
	 * @param p the p
	 * @throws InvalidPositionException the invalid position exception
	 */
	public void RemoveSubtree(Position<E> p) throws InvalidPositionException;

}
