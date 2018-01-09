/**
 * Programming AE2
 * Processes report on letter frequencies
 */
public class LetterFrequencies
{
	/** Size of the alphabet */
	private final int SIZE = 26;

	/** Count for each letter */
	private int [] alphaCounts = new int[SIZE];

	/** The alphabet */
	private char [] alphabet = new char[SIZE]; 

	/** Average frequency counts */
	private double [] avgCounts = {8.2, 1.5, 2.8, 4.3, 12.7, 2.2, 2.0, 6.1, 7.0,
			0.2, 0.8, 4.0, 2.4, 6.7, 7.5, 1.9, 0.1, 6.0,  
			6.3, 9.1, 2.8, 1.0, 2.4, 0.2, 2.0, 0.1};

	/** Character that occurs most frequently */
	private char maxCh;

	/** Total number of characters encrypted/decrypted */
	private int totChars;

	/**
	 * Instantiates a new letterFrequencies object.
	 */
	public LetterFrequencies()
	{	
		totChars=0;
		for(int i=0; i<SIZE;i++) {
			alphabet[i]=(char)( 'A' + i );
			alphaCounts[i]=0;
		}
	}

	/**
	 * Increases frequency details for given character
	 * @param ch the character just read
	 */
	public void addChar(char ch)
	{
		totChars++;
		for(int i=0; i< SIZE; i++) {
			if(ch == alphabet[i]) {
				alphaCounts[i]++;	
			}
		}
	}

	/**
	 * Gets the maximum frequency
	 * @return the maximum frequency
	 */
	private double getMaxPC()
	{
		double Max = 0;
		for(int i =0; i<SIZE; i++) {
			if(alphaCounts[i] > Max) {
				Max = alphaCounts[i];
				maxCh = alphabet[i];
			}
		}
		return Max;
	}

	/**
	 * Returns a String consisting of the full frequency report
	 * @return the report
	 */
	public String getReport()
	{
		String output = new String("LETTER ANALYSIS \r\n\r\n");
		output+=String.format("%-20s %-20s %-20s %-20s %-20s \r\n", "Letter", "Freq", "Freq%", "AvgFreq%", "Diff");
		double[] freqP = new double[SIZE];
		for(int i=0; i<SIZE;i++) {
			freqP[i] = 100*(alphaCounts[i])/(double)totChars;
			double diff = freqP[i] - avgCounts[i];
			output+=String.format("%-20c %-20d %-20.1f %-20.1f %-20.1f \r\n", alphabet[i], alphaCounts[i], freqP[i], avgCounts[i], diff);
		}
		double maxFq = this.getMaxPC();
		output+=String.format("\r\nThe most frequent letter is '%c' at %2.1f%%", maxCh,maxFq);
		return output;
	}
}
