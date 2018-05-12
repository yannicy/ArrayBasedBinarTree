package project5.interfaces;

import java.util.List;

import project5.exceptions.EmptyTreeException;
import project5.exceptions.InvalidPositionException;
import project5.exceptions.UnemptyTreeException;

public interface Tree<E> extends BasicCollection<E> {
	
	/**
	 * Height.
	 *
	 * @return the int
	 * @throws EmptyTreeException the empty tree exception
	 */
	public int height() throws EmptyTreeException;
	
	/**
	 * Elements.
	 *
	 * @return the list
	 */
	public List<E> elements();
	
	/**
	 * Positions.
	 *
	 * @return the list
	 */
	public List<Position<E>> positions();
	
	/**
	 * Root.
	 *
	 * @return the position
	 * @throws EmptyTreeException the empty tree exception
	 */
	public Position<E> root() throws EmptyTreeException;
	
	/**
	 * Parent.
	 *
	 * @param p the p
	 * @return the position
	 * @throws InvalidPositionException the invalid position exception
	 */
	public Position<E> parent(Position<E> p) throws InvalidPositionException;
	
	/**
	 * Children.
	 *
	 * @param p the p
	 * @return the list
	 * @throws InvalidPositionException the invalid position exception
	 */
	public List<Position<E>> children(Position<E> p) throws InvalidPositionException;

	/**
	 * Descendants.
	 *
	 * @param p the p
	 * @return the list
	 * @throws InvalidPositionException the invalid position exception
	 */
	public List<Position<E>> descendants(Position<E> p) throws InvalidPositionException;

	/**
	 * Ancestors.
	 *
	 * @param p the p
	 * @return the list
	 * @throws InvalidPositionException the invalid position exception
	 */
	public List<Position<E>> ancestors(Position<E> p) throws InvalidPositionException;
	
	/**
	 * Checks if is root.
	 *
	 * @param p the p
	 * @return true, if is root
	 * @throws InvalidPositionException the invalid position exception
	 */
	public boolean isRoot(Position<E> p) throws InvalidPositionException;

	/**
	 * Checks if is internal.
	 *
	 * @param p the p
	 * @return true, if is internal
	 * @throws InvalidPositionException the invalid position exception
	 */
	public boolean isInternal(Position<E> p) throws InvalidPositionException;

	/**
	 * @param p
	 * @return
	 * @throws InvalidPositionException
	 */
	public boolean isExternal(Position<E> p) throws InvalidPositionException;

	/**
	 * @param p
	 * @return
	 * @throws InvalidPositionException
	 */
	public int depth(Position<E> p) throws InvalidPositionException;
	
	/**
	 * @param e
	 * @return
	 * @throws UnemptyTreeException
	 */
	public Position<E> addRoot(E e) throws UnemptyTreeException;
	
	/**
	 * @param p
	 * @param e
	 * @return
	 * @throws InvalidPositionException
	 */
	public Position<E> insertChild(Position<E> p, E e) throws InvalidPositionException;
	
	/**
	 * @param p
	 * @param e
	 * @return
	 * @throws InvalidPositionException
	 */
	public E replaceElement(Position<E> p, E e) throws InvalidPositionException;
	
	/**
	 * @param p
	 * @param q
	 * @throws InvalidPositionException
	 */
	public void swapElements(Position<E> p, Position<E> q) throws InvalidPositionException;
		
}
