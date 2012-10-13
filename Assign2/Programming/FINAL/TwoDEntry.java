/**
 *@author Kaiven Zhou kz2182
 *A class which stores a RLESequence and the frequency of the sequence.
 */
public class TwoDEntry
{
	/**
	 *how many of the sequences appear in consecutively
	 */
	int freq;
	/**
	 *The sequence
	 */
	RLESequence value;
	/**
	 *Constructs a TwoDEntry object from a given frequency and 1D array
	 *@param aFreq the frequency
	 *@param array the 1D array
	 */
	public TwoDEntry(int aFreq, int [] array)
	{
		freq = aFreq;
		value = new RLESequence(array);
	}
	/**
	 *Gets the frequency
	 *@return the frequency
	 */
	public int getFreq()
	{
		return freq;
	}
	/**
	 *Gets the RLESequence
	 *@return the RLESequence
	 */
	public RLESequence getValue()
	{
		return value;
	}
	/**
	 *Gets the frequency and the RLESequence as a String
	 *@return the frequency and the RLESequence as a String
	 */
	public String toString()
	{
		return "("+freq+","+value.internalToString()+")";
	}
	/**
	 *Returns true if the frequencys equal and the RLESequences equal, else false
	 *@param other the other Entry to compare with
	 *@return true if the frequencys equal and the RLESequences equal, else false
	 */
	public boolean equals(Entry other)
	{
		if(freq == other.getFreq() && value.equals(other.getValue()))
			return true;
		else
			return false;
	}
}