/**
 * Programming AE2
 * Class contains Vigenere cipher and methods to encode and decode a character
 */
public class VCipher
{
	private char [] alphabet;   //the letters of the alphabet
	private final int SIZE = 26;
	// more instance variables
	private char [][] cipher;
	private int keyLength;
	private int encodeCounter;
	private int decodeCounter;

	/** 
	 * The constructor generates the cipher
	 * @param keyword the cipher keyword
	 */
	public VCipher(String keyword)
	{
		encodeCounter =0;
		decodeCounter =0;
		String checkString = ""; 
		alphabet = new char [SIZE];
		for (int i = 0; i < SIZE; i++) {
			alphabet[i] = (char)('A' + i);
		}
		keyLength=keyword.length();
		cipher = new char [SIZE][keyLength]; //keyLength redundant here but necessary for encoding.
		for(int i=0;i<keyword.length();i++) {
			for (int j=0;j<SIZE;j++) {
				if(j==0) {
					cipher[j][i] = keyword.charAt(i);
				}
				else {
					if (cipher[0][i]+j <= 'Z') {
					cipher[j][i] = (char) (cipher[0][i]+j);
					}
					else {
						int arrayPositionZ=0;
						for(int k=0;k<j;k++) {
							if(cipher[k][i] == 'Z')
								arrayPositionZ = k;
						}
						cipher[j][i] = (char) ('A'+j-(arrayPositionZ+1));
					}
				}
				checkString+=cipher[j][i]+" ";
			}
			checkString+="\n";
		}
		System.out.println(checkString);
	}
	/**
	 * Encode a character
	 * @param ch the character to be encoded
	 * @return the encoded character
	 */	
	public char encode(char ch)
	{
		if( encodeCounter >= keyLength ) {
			encodeCounter =0;
		}
		for (int i = 0; i < SIZE; i++) {
			if(ch == alphabet[i]) {
				return cipher[i][encodeCounter++];
			}
		}
		//System.err.println("unexpected character");
		return ch;
		
	}

	/**
	 * Decode a character
	 * @param ch the character to be decoded
	 * @return the decoded character
	 */  
	public char decode(char ch)
	{
		if( decodeCounter >= keyLength ) {
			decodeCounter =0;
		}
		for (int i = 0; i < SIZE; i++) {
			if(ch == cipher[i][decodeCounter]) {
				decodeCounter++;
				return alphabet[i];
			}
		}
		//System.err.println("unexpected character");
		return ch;
	}
}
