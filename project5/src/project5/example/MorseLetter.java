package project5.example;

/**
 * The Class MorseLetter.
 * used as a node for a binary tree
 * MorseLetter contains a char MORSECHAR and the associated MORSECODE of the char
 */
public class MorseLetter {
	
	/** The morsechar. */
	private final char MORSECHAR;
	
	/** The morsecode. */
	private final String MORSECODE;

	/**
	 * Instantiates a new morse letter.
	 *
	 * @param morseChar the morse char
	 * @param morseCode the morse code
	 */
	public MorseLetter(char morseChar, String morseCode) {
		this.MORSECHAR = morseChar;
		this.MORSECODE = morseCode;
	}

	/**
	 * Gets the morsechar.
	 *
	 * @return the morsechar
	 */
	public char getMORSECHAR() {
		return this.MORSECHAR;
	}

	/**
	 * Gets the morsecode.
	 *
	 * @return the morsecode
	 */
	public String getMORSECODE() {
		return this.MORSECODE;
	}


	@Override
	public String toString() {
		return MORSECHAR + "  " + MORSECODE;
	}

}
