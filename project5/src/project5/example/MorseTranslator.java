package project5.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import project5.classes.ArrayBinaryTree;
import project5.exceptions.InvalidPositionException;
import project5.interfaces.Position;

/**
 * The Class MorseTranslator. translate a morse code input into a readable
 * String or a String into morse code
 */
public class MorseTranslator {

	/** The Scanner in. */
	private Scanner in = new Scanner(System.in);

	/** The tree which contains Morseletters as nodes */
	private ArrayBinaryTree<MorseLetter> tree;

	/** The searched code. */
	private String searchedCode = "";

	/**
	 * Instantiates a new morse translator and fills the tree with MorseLetter nodes
	 */
	public MorseTranslator() {
		this.tree = new ArrayBinaryTree<MorseLetter>(new MorseLetter(' ', null));
		this.fillTree();
	}

	/**
	 * Run.
	 */
	public void run() {
		while (true) {
			try {
				String line = in.nextLine().toUpperCase();
				if (line.equals("EXIT")) {
					break;
				}
				if (line.matches("[a-zA-Z]{" + line.length() + "}")) {
					char[] chars = line.toCharArray();
					String result = "";
					for (int i = 0; i < chars.length; i++) {
						result = result + " " + this.translateCharToCode(Character.toString(chars[i]));
					}
					System.out.println(result);
					this.searchedCode = "";
				}
				if (line.matches("[. -]{" + line.length() + "}")) {
					String[] morseString = line.split(" ");
					String result = "";
					for (int i = 0; i < morseString.length; i++) {
						result = result + this.translateCodeToChar((morseString[i]));
					}
					System.out.println(result);
				}

			} catch (InvalidPositionException e) {
				System.out.println("Ungültiger Morsecode");
			}
		}

	}

	/**
	 * Translate char to the associated morse code.
	 *
	 * @param line
	 * @return the string
	 */
	private String translateCharToCode(String line) {
		Position<MorseLetter> pos = tree.root();
		return this.searchTree(pos, line);

	}

	/**
	 * Translate morse code into the associated char.
	 *
	 * @param line
	 * @return the string
	 */
	private String translateCodeToChar(String line) {
		Position<MorseLetter> pos = tree.root();
		for (int i = 0; i < line.length(); i++) {
			if (line.charAt(i) == '.') {
				pos = tree.leftChild(pos);
			}
			if (line.charAt(i) == '-') {
				pos = tree.rightChild(pos);
			}
		}
		return Character.toString(pos.element().getMORSECHAR());
	}

	/**
	 * Search tree.
	 *
	 * @param pos
	 * @param line
	 * @return the string searchedCode
	 */
	private String searchTree(Position<MorseLetter> pos, String line) {
		if (pos.element().getMORSECHAR() == line.charAt(0)) {
			this.searchedCode = pos.element().getMORSECODE();
		}
		for (Position<MorseLetter> posOfChildren : tree.children(pos)) {
			this.searchedCode = "" + this.searchTree(posOfChildren, line);
		}
		return this.searchedCode;
	}

	/**
	 * Fill tree.
	 * Fills the tree with MorseLetter which contain information from the morse.txt file
	 */
	private void fillTree() {
		File file = new File("morse.txt");
		try (BufferedReader in = new BufferedReader(new FileReader(file))) {
			String line = null;
			while ((line = in.readLine()) != null) {
				this.insertInTree(new MorseLetter(Character.toUpperCase(line.charAt(0)), line.substring(2)));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Insert in tree.
	 * Inserts the MorseLetters into the binary tree
	 *
	 * @param letter
	 */
	private void insertInTree(MorseLetter letter) {
		Position<MorseLetter> pos = tree.root();
		int indexOfMorsecode = 0;
		while (true) {
			if (letter.getMORSECODE().charAt(indexOfMorsecode) == '.') {
				if (tree.hasLeft(pos)) {
					pos = tree.leftChild(pos);
				} else {
					tree.insertLeft(pos, letter);
					break;
				}
			}
			if (letter.getMORSECODE().charAt(indexOfMorsecode) == '-') {
				if (tree.hasRight(pos)) {
					pos = tree.rightChild(pos);
				} else {
					tree.insertRight(pos, letter);
					break;
				}
			}
			indexOfMorsecode++;
		}

	}

}
