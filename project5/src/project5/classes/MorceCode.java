package project5.classes;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import project5.interfaces.BinaryTree;
import project5.interfaces.Position;



public class MorseCode {

	private BinaryTree<String> morseTree = new ArrayBinaryTree<>("Start");
	
	

	public static MorseTree<String> loadTree(File morse) throws FileNotFoundException{
		BinaryTree<String> morseTree = new ArrayBinaryTree<>();
		Scanner scanner = null;
		try {
			scanner = new Scanner(morse);
			String line;
			while (scanner.hasNextLine()) {
				line = scanner.nextLine();
				String symbol = line.split("\t| ")[0];
				String code = line.split("\t| ")[1];
				morseTree.add(symbol, code);
			}
			
		} finally {
			if (scanner != null) {
				scanner.close();
				
			}
		}
		return morseTree;
		
	}
	
	public String decode(String code) {
		Node current = root;
		boolean exists = true;
		for (int i=0; (i<code.length() && exists); i++){
			if (code.substring(i, i+1).equals(".")){
				if (current.leftChild == null){
					exists = false;
				}
				current = current.left;
			}
			else if (code.substring(i, i+1).equals("-")){
				if (current.rightChild == null){
					exists = false;
				}
				current = current.rightChild;
			}
		}
		if (exists){
			return (String) current.value;
		}
		return null;
	}

	
	public void add(String symbol, String code) {
		Node current = root;
		for (int i = 0; i < code.length(); i++) {
			
			if (code.substring(i, i+1).equals(".")){
				if (current.left == null){
					current.left = new Node(null, null, null);
				}
				current = current.left;
			}
			else if (code.substring(i, i+1).equals("-")){
				if (current.right == null){
					current.right = new Node(null, null, null);
				}
				current = current.right;
			}
		}
		current.value = symbol;
	}
	
	
		

	public static void main(String[] args) {
		BinaryTree<String> treeTest = new ArrayBinaryTree<>();
		
	}
	
}




