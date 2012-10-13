import java.awt.*;
import java.awt.geom.*;

/**
 *@author Kaiven Zhou kz2182
 *
   A shape that is a part of a scene.

   This class was modified from the original, taken from
   Horstmann's Object-oriented Design & Patterns (ISBN 0-471-74487-5)
*/
public interface SceneShape
{
   /**
      Draws this item.
      @param g2 the graphics context
   */
   void draw(Graphics2D g2);
   /**
      Draws the selection adornment of this item.
      @param g2 the graphics context
   */
   void drawSelection(Graphics2D g2);
   /**
      Sets the selection state of this item.
      @param b true if this item is selected
   */
   void setSelected(boolean b);
   /**
      Gets the selection state of this item.
      @return true if this item is selected
   */
   boolean isSelected();
   /**
      Translates this item by a given amount.
      @param dx the amount to translate in x-direction
      @param dy the amount to translate in y-direction
   */
   void translate(int dx, int dy);
   /**
      Tests whether this item contains a given point.
      @param p a point
      @return true if this item contains p
   */
   boolean contains(Point2D p);

	/**
	 *Tests whether this shape and the other intersect
	 *@param other the other SceneShape
	 */
   boolean intersects(SceneShape other);

	/**
	 *@return GeneralPath the border of the shape
	 */
   GeneralPath getBorder();
}

