/**
 *@autho Kaiven Zhou 2182
   This class was modified from the original,
   taken from Horstmann's Object-oriented Design & Patterns

   A group of cars that move around.
   Pattern: Composite
   I use the composite pattern because all the cars can be controlled like a single car:
   all the cars translations are controlled by the same slider, and all the cars need to
   be drawn.

   An array is used because I get to decide the number of CarShapes (the size of the group).
*/
import java.awt.*;
import java.awt.geom.*;
import java.util.*;

public class GroupOfCars implements MoveableShape
{
	private CarShape[] group;

	/**
	 *@param numberOfCars the number of cars in the group
	 */
	public GroupOfCars(int numberOfCars)
	{
		group = new CarShape[numberOfCars];
	}
	/**
	 *Translates each of the cars
	 */
	public void translate()
	{
		for(int i=0; i<group.length; i++)
		{
			group[i].translate();
		}
	}
	/**
	 *Changes the speed of each of the cars
	 */
	public void changeSpeed(int dx, int dy)
	{
		for(int i=0; i<group.length; i++)
		{
			group[i].changeSpeed(dx, dy);
		}
	}
	/**
	 *Draws each of the cars
	 */
	public void draw(Graphics2D g2)
	{
		for(int i=0; i<group.length; i++)
		{
			group[i].draw(g2);
		}
	}
	/**
	 *Adds a car to the group
	 */
	public void add(int index, CarShape car)
	{
		group[index] = car;
	}
}
