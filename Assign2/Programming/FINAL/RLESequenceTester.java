/**
 *@author Kaiven Zhou kz2182
 *Testing will make sure that every method of RLESequence works properly.
 *Additional tests for set(), get(), and tailReplace() will ensure that indecies out of bounds throw exceptions.
 *Additional tests for set() will ensure that any set() done will either split an Entry into multiple entries, cause Entries to fuse, or leave the sequence unchanged.
 *Additional tests for get() will ensure that the edge cases work.
 *Additional tests for tailReplace() will ensure that after a tailReplace() any Entries that need to be merged are.
 */
public class RLESequenceTester
{
	public static void main (String[] args)
	{
		int[] array = {0,0,0,0,0,0,0,0,0,0};
		RLESequence A = new RLESequence(array);
		System.out.println("Sequence A, made with an array: "+A.toString());
		System.out.println("Internal representation: "+A.internalToString());

		int length = 10;
		RLESequence B = new RLESequence(length);
		System.out.println("Sequence B, made with a length: "+B.toString());
		System.out.println("Internal representation: "+B.internalToString());

		if(A.equals(B))
			System.out.println("The two sequences equal.");

		System.out.println("Setting element 3 in B to value=1");
		B.set(3,1);

		if(!B.equals(A))
		{
			System.out.println("The sequences no longer equal");
			System.out.println("Sequence B: "+B.toString());
			System.out.println("Internal representation: "+B.internalToString());
			System.out.println("The value of element 3 in B is "+B.get(3));
		}

		System.out.println("Incrementing B by 1.");
		B.increment(1);
		System.out.println("Sequence B: "+B.toString());

		int[] arrayOfB = B.toArray();
		System.out.print("Array of B created. It contains:");
		for(int i=0; i<arrayOfB.length; i++)
		{
			System.out.print(arrayOfB[i]+" ");
		}

		System.out.println("\nSequence A's length: "+A.length());
		System.out.println("tailReplacing A at 5 with B.");
		A.tailReplace(5,B);
		System.out.println("Sequence A: "+A.toString());
		System.out.println("Internal representation: "+A.internalToString());
		System.out.println("Sequence A's length: "+A.length());

		System.out.println("In A, Setting element 0 to equal 1.");
		A.set(0,1);
		System.out.println("Sequence A: "+A.toString());
		System.out.println("Internal representation of A: "+A.internalToString());

		System.out.println("In A, Setting element 4 to equal 2.");
		A.set(4,2);
		System.out.println("Sequence A: "+A.toString());
		System.out.println("Internal representation of A: "+A.internalToString());

		System.out.println("In A, Setting element 5 to equal 2.");
		A.set(5,2);
		System.out.println("Sequence A: "+A.toString());
		System.out.println("Internal representation of A: "+A.internalToString());

		System.out.println("In A, Setting element 11 to equal 3.");
		A.set(11,3);
		System.out.println("Sequence A: "+A.toString());
		System.out.println("Internal representation of A: "+A.internalToString());

		System.out.println("In A, Setting element 14 to equal 3.");
		A.set(14,3);
		System.out.println("Sequence A: "+A.toString());
		System.out.println("Internal representation of A: "+A.internalToString());
	}
}