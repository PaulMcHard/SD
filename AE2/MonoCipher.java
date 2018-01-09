/**
 * Programming AE2
 * Contains monoalphabetic cipher and methods to encode and decode a character.
 */
public class MonoCipher
{
	/** The size of the alphabet. */
	private final int SIZE = 26;

	/** The alphabet. */
	private char [] alphabet;

	/** The cipher array. */
	private char [] cipher;

	/**
	 * Instantiates a new mono cipher.
	 * @param keyword the cipher keyword
	 */
	public MonoCipher(String keyword)
	{
		//create alphabet
		alphabet = new char [SIZE];
		for (int i = 0; i < SIZE; i++)
			alphabet[i] = (char)('A' + i);

		// create first part of cipher from keyword
		// create remainder of cipher from the remaining characters of the alphabet
		// print cipher array for testing and tutors
		cipher = new char [SIZE];
		int skip = 0;
		for(int i=0;i<SIZE;i++) {
			if(i<keyword.length()) {
				cipher[i] = keyword.charAt(i);
			}
			else {
				cipher[i] = (char)('Z'- (i-(keyword.length()-skip)));
				for( int k = 0; k < keyword.length(); k++) {
					for( int j = 0; j < keyword.length(); j++ ) {
						if (cipher[i] == keyword.charAt(j)) {
							++skip;
							cipher[i] = (char)('Z'- (i-(keyword.length()-skip)));
							//System.out.println(skip);
						}
					}
				}
			}
			System.err.println(cipher[i]);
		}

	}

	/**
	 * Encode a character
	 * @param ch the character to be encoded
	 * @return the encoded character
	 */
	public char encode(char ch)
	{
		for (int i = 0; i < SIZE; i++) {
			if(ch == alphabet[i]) {
				return cipher[i];
			}
		}
		//System.err.println("unexpected character");
		return ch;
	}

	/**
	 * Decode a character
	 * @param ch the character to be encoded
	 * @return the decoded character
	 */
	public char decode(char ch)
	{
		for (int i = 0; i < SIZE; i++) {
			if(ch == cipher[i]) {
				return alphabet[i];
			}
		}
		//System.err.println("unexpected character");
		return ch;
	}
}
