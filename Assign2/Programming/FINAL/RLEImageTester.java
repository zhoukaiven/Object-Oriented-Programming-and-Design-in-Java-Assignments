/**
 *@author Kaiven Zhou kz2182
 *Choice 3) of Step 4.
 *Testing will make sure that every method of RLESequence works properly.
 */
public class RLEImageTester
{
	public static void main(String[] args)
	{
		int[][] array = {{0,0,0,0,0},{1,1,1,1,1},{1,1,1,1,1},{2,2,2,2,2},{3,3,3,3,3}};
		RLEImage test = new RLEImage(array);

		System.out.println("New image: "+test.toString());
		System.out.println("Internally image is: "+test.internalToString());
		System.out.println("Image contains this many RLESequence objects: "+test.length());

		System.out.println("Using to2DArray, the image is:");
		int[][] original = test.to2DArray();
		for(int i=0; i<original.length; i++)
		{
			for(int j=0; j<original[i].length; j++)
				System.out.print(original[i][j] + " ");
		}

		System.out.println();

		RLEImage other = new RLEImage(original);
		if(other.equals(test))
		{
			System.out.println("A RLEImage made using to2Darray equals the original RLEImage");
		}

		original[0][0]=10;
		other = new RLEImage(original);
		System.out.println("New Image: "+other.toString());
		if(!test.equals(other))
		{
			System.out.println("Original image does not equal to this new image.");
		}
	}
}