/**
 *@author Kaiven Zhou kz2182
   This class was modified from the original,
   taken from Horstmann's Object-oriented Design & Patterns

   A shape that can be moved around.
*/
import java.awt.*;

public interface MoveableShape
{
   /**
      Draws the shape.
      @param g2 the graphics context
   */
   void draw(Graphics2D g2);
   /**
      Moves the shape by an ammount stored within the shape
   */
   void translate();
   /**
    *Changes the speed of the shape
    *@param xMultiplier the x multiplier
    *@param yMultiplier the y multiplier
    */
   void changeSpeed(int xMultiplier, int yMultiplier);
}
