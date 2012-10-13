/**
 *@author Kaiven Zhou kz2182
   This class was modified from the original,
   taken from Horstmann's Object-oriented Design & Patterns

   An icon that contains a GroupOfCars.
   Pattern: Composite
   Instead of containing a MoveableShape (as the original did),
   ShapeIcon now contains a GroupOfCars
*/
import java.awt.*;
import java.util.*;
import javax.swing.*;


public class ShapeIcon implements Icon
{
	/**
	 *Constructs the ShapeIcon from a GroupOfCars, width, and height
	 *@param group a GroupOfCars object
	 *@param width the width of the ShapeIcon
	 *@param height the height of the ShapeIcon
	 */
   public ShapeIcon(GroupOfCars group,int width, int height)
   {
      this.group = group;
      this.width = width;
      this.height = height;
   }
	/**
	 *@return the width of the ShapeIcon
	 */
   public int getIconWidth()
   {
      return width;
   }
	/**
	 *@return the height of the ShapeIcon
	 */
   public int getIconHeight()
   {
      return height;
   }
	/**
	 *@param c the Component
	 *@param g the Graphics
	 *@param x the x coordinate
	 *@param y the y coordinate
	 */
   public void paintIcon(Component c, Graphics g, int x, int y)
   {
      Graphics2D g2 = (Graphics2D) g;
      group.draw(g2);
   }
	/**
	 *the width of the ShapeIcon
	 */
   private int width;
   	/**
	 *the height of the ShapeIcon
	 */
   private int height;
   	/**
	 *the GroupOfCars
	 */
   private GroupOfCars group;
}


