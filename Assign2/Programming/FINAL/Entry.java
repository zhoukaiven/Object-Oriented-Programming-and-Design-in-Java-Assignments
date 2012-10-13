/**
 *@author Kaiven Zhou kz2182
 *A class which stores a value, and the frequency of the value.
 */
public class Entry
{
	/**
	 *how many of the value appear in consecutively
	 */
	private int freq;
	/**
	 *The element
	 */
	private int value;
	/**
	 *Constructs an Entry object from a given frequency and value
	 *@param aFreq the frequency
	 *@param aValue the value
	 */
	public Entry(int aFreq, int aValue)
	{
		freq = aFreq;
		value = aValue;
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
	 *Gets the value
	 *@return the value
	 */
	public int getValue()
	{
		return value;
	}
	/**
	 *Increases this frequency by a speicified amount
	 *@param toAdd the amount to increase by
	 */
	public void addFreq(int toAdd)
	{
		freq+=toAdd;
	}
	/**
	 *Increases this value by a specified amount.
	 *No bounds are placed on how much the value can be increased/decreased, because the sequence is storing ints.
	 *If the sequence were to store pixels, the amount would have to be checked to ensure that the value remains within the bounds
	 *of a pixel (from 0 to 255).
	 *@param toAdd the amount to be increase by
	 */
	public void addValue(int toAdd)
	{
		value+=toAdd;
	}
	/**
	 *Gets the frequency and the value as a String
	 *@return the frequency and the value as a String
	 */
	public String toString()
	{
		return "(" + freq + "," + value + ")";
	}
	/**
	 *Returns true if the frequencys equal and the values equal, else false
	 *@param other the other Entry to compare with
	 *@return true if the frequencys equal and the values equal, else false
	 */
	public boolean equals(Entry other)
	{
		if(freq == other.getFreq() && value == other.getValue())
			return true;
		else
			return false;
	}
}