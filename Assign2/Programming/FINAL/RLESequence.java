/**
 *@author Kaiven Zhou kz2182
 *A class that compresses and stores a list of numbers.
 *Numbers are stored in an ArrayList<Entry>, which stores repetitions of the same value in an Entry.
 *An ArrayList<Entry> is used because the number of Entry's is unknown (changes based on the data).
 *As well, the ArrayList<Entry> must be iterated through in order to find an element, to print to String, etc.
 *as well as leaving the possibility of creating inserting methods.
 */
import java.util.ArrayList;
import java.lang.IndexOutOfBoundsException;

public class RLESequence
{
	/**
	 *the number of elements in the sequence
	 */
	private int length;
	/**
	 *the compressed sequence
	 */
	private ArrayList<Entry> internal;
	/**
	 *Construct a RLESequence object from a given length. By default, all elements are 0.
	 *@param aLength the number of elements in the sequence
	 */
	public RLESequence(int aLength)
	{
		length = aLength;
		internal = new ArrayList<Entry>();
		internal.add(new Entry(length,0));
	}
	/**
	 *Construct a RLESequence object from a given array
	 *@param original the uncompressed array
	 */
	public RLESequence(int[] original)
	{
		internal = new ArrayList<Entry>();
		compress(original);
		length = original.length;
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
	 *Gets the uncompressed sequence as a string
	 *@return the uncompressed sequence as a string
	 */
	public String toString() //works
	{
		String answer = "";
		for(int i=0; i<internal.size(); i++)
		{
			for(int j=0; j<internal.get(i).getFreq(); j++)
			{
				answer += internal.get(i).getValue() + " ";
			}
		}
		return answer;

	}
	/**
	 *Gets the compressed sequence as a string
	 *@return the compressed sequence as a string
	 */
	public String internalToString()
	{
		String answer="[";
		for(int i=0; i<internal.size(); i++)
		{
			answer += internal.get(i).toString();
			if(i!=internal.size()-1)
				answer += ",";
		}
		answer += "]";

		return answer;
	}
	/**
	 *Gets the uncompressed sequence as an array
	 *@return the uncompressed sequence as an array
	 */
	public int[] toArray()
	{
		int[] answer = new int[length];
		int indexIterator = 0;

		for(int i=0; i<internal.size(); i++)
		{
			for(int j=0; j<internal.get(i).getFreq(); j++)
			{
				answer[indexIterator+j] = internal.get(i).getValue();
			}
			indexIterator+=internal.get(i).getFreq();
		}

		return answer;
	}
	/**
	 *Returns true if the sequences are equal, else false.
	 *Note that this method uses the internalToString() method
	 *@param otherSequence the sequence to compare to
	 *@return true if the sequences are equal, else false
	 */
	public boolean equals(RLESequence otherSequence)
	{
		if(length() != otherSequence.length())
		{
			return false;
		}
		else if(internalToString().equals(otherSequence.internalToString()))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	/**
	 *Gets the element of the uncompressed sequence at the speicifed index
	 *@param index the index of the desired element
	 *@return the element
	 */
	public int get(int index)
	{
		if(index>=length || index<0)
			throw new IllegalArgumentException("Out of Bounds. Valid indices are from 0 to "+(length-1)+".");

		int indexIterator = 0;

		for(int i=0; i<internal.size(); i++)
		{
			indexIterator+=internal.get(i).getFreq();

			if(indexIterator> index)
				return internal.get(i).getValue();
		}

		throw new IllegalArgumentException("Out of Bounds. Valid indices are from 0 to "+(length-1)+".");
	}
	/**
	 *Changes the element at the specified index in the uncompressed sequence to a new value
	 *Instead of uncompressing the entire sequence, I uncompress the entry that is going to be changed.
	 *@param index the index of the element to be changed
	 *@param value the value which the element should be changed to
	 */
	public void set(int index, int value)
	{
		if(index>=length || index<0)
			throw new IllegalArgumentException("Out of Bounds. Valid indices are from 0 to "+(length-1)+".");

		int indexIterator =0;
		for(int i=0; i<internal.size(); i++)
		{
			indexIterator += internal.get(i).getFreq();
			if(indexIterator>index)
			{
				if(value == internal.get(i).getValue())
					return;
				else
				{
					int oldValue = internal.get(i).getValue();
					int oldFreq = internal.get(i).getFreq();

					internal.remove(i);
					if(indexIterator-index-1 != 0)
						internal.add(i,new Entry(indexIterator-index-1,oldValue));

					internal.add(i,new Entry(1,value));

					if(oldFreq-indexIterator+index != 0)
						internal.add(i,new Entry(oldFreq-indexIterator+index,oldValue));

					for(int k=0; k<internal.size(); k++)
					{
						while(k!=internal.size()-1 && internal.get(k).getValue()==internal.get(k+1).getValue())
						{
							int newFreq = internal.get(k).getFreq() + internal.get(k+1).getFreq();
							int newValue = internal.get(k).getValue();
							internal.remove(k);
							internal.set(k,new Entry(newFreq,newValue));
						}
					}
					return;
				}
			}
		}
	}
	/**
	 *Removes all the elements after and including a specified index, the appends another sequence to this sequence
	 *Instead of uncrompressing the entire sequence, I uncompress the entry that is going to be changed.
	 *@param index the specified index
	 *@param other the RLESequence to be appended
	 */
	public void tailReplace(int index, RLESequence other)
	{
		if(index>=length || index<0)
			throw new IllegalArgumentException("Out of Bounds. Valid indices are from 0 to "+(length-1)+".");

		int indexIterator =0;
		for(int i=0; i<internal.size(); i++)
		{
			indexIterator += internal.get(i).getFreq();
			if(indexIterator>index)
			{
				int oldValue = internal.get(i).getValue();
				int oldFreq = internal.get(i).getFreq();

				internal.subList(i,internal.size()).clear();

				internal.addAll(other.internal);

				if(oldFreq-indexIterator+index != 0)
						internal.add(i,new Entry(oldFreq-indexIterator+index,oldValue));

				int newLength=0;
				for(int j=0; j<internal.size(); j++)
				{
					newLength+=internal.get(j).getFreq();
				}
				length = newLength;
				return;
			}
		}

	}
	/**
	 *Increases every element of the uncompressed sequence by a specified value.
	 *
	 *@param byWhat the value to increase every element by
	 */
	public void increment(int byWhat)
	{
		for(int i=0; i<internal.size(); i++)
		{
			internal.get(i).addValue(byWhat);
		}
	}
	/**
	 *Takes an array, compresses it, and stores it in the field "ArrayList<Entry> internal"
	 *@param input the array to be compressed
	 */
	private void compress(int [] input)
	{
		int value=input[0];
		int freq=1;
		for(int i=1; i<input.length; i++)
		{
			if(value == input[i])
			{
				freq++;
			}
			else
			{
				internal.add(new Entry(freq,value));

				value = input[i];
				freq = 1;
			}
		}
		internal.add(new Entry(freq,value));
	}
}