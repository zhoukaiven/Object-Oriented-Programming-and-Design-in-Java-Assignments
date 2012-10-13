/**
 *@author Kaiven Zhou kz2182
 *A class that compresses a 2D array of numbers (Choice #3 of Step 4).
 *Numbers are stored in an ArrayList<TwoDEntry>, which stores repetitions of the same RLESequence in TwoDEntry.
 *An ArrayList<Entry> is used because the number of TwoDEntry's is unknown (changes based on the data).
 *As well, the ArrayList<TwoDEntry> must be iterated through to print to String, etc., as well as leaving open the possibility
 *of creating inserting methods.
 */
import java.util.ArrayList;
public class RLEImage
{
	/**
	 *the number of sequences
	 */
	int length;
	/**
	 *The compressed 2D sequence
	 */
	ArrayList<TwoDEntry> internal;
	/**
	 *Constructs the RELImage object from a 2D array
	 *@param original the uncompressed 2D array
	 */
	public RLEImage(int[][] original)
	{
		internal = new ArrayList<TwoDEntry>();
		compress(original);
		length = internal.size();
	}
	/**
	 *Gets the length of the sequence
	 *@return the length of the sequence
	 */
	public int length() //works
	{
		return length;
	}
	/**
	 *Gets the uncompressed 2D sequence as a string
	 *@return the uncompressed 2D sequence as a string
	 */
	public String toString()
	{
		String answer = "[";
		for(int i=0; i<internal.size(); i++)
		{
			answer+="["+internal.get(i).getValue().toString()+"]";
		}
		answer+="]";
		return answer;
	}
	/**
	 *Gets the compressed 2D sequence as a string
	 *@return the compressed 2D sequence as a string
	 */
	public String internalToString()
	{
		String answer="[";
		for(int i=0; i<internal.size(); i++)
		{
			answer+=internal.get(i).toString();
			if(i!=internal.size()-1)
				answer += ",";
		}
		answer+="]";
		return answer;
	}
	/**
	 *Gets the uncompressed 2D sequence as a 2D array
	 *@return the uncompressed 2D sequence as a 2D array
	 */
	public int[][] to2DArray()
	{
		int[][] answer = new int[internal.size()][];
		for(int i=0; i<internal.size(); i++)
		{
			answer[i] = internal.get(i).getValue().toArray();
		}
		return answer;
	}
	/**
	 *Returns true if the images are equal, else false.
	 *Note that this method uses the internalToString() method
	 *@param other the image to compare to
	 *@return true if the images are equal, else false
	 */
	public boolean equals(RLEImage other)
	{
		if(length() != other.length())
		{
			return false;
		}
		else if(internalToString().equals(other.internalToString()))
			return true;
		else
			return false;
	}
	/**
	 *Takes an array, compresses it, and stores it in the field "ArrayList<TwoDEntry> internal"
	 *@param input the array to be compressed
	 */
	private void compress(int [][] input)
	{
		int freq = 1;
		int[] value = input[0];
		for(int i=1;i<input.length; i++)
		{
			if(value.equals(input[i]))
			{
				freq++;
			}
			else
			{
				internal.add(new TwoDEntry(freq,value));

				value = input[i];
				freq = 1;
			}
		}
		internal.add(new TwoDEntry(freq,value));
	}
}