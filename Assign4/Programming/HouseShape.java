import java.awt.*;
import java.awt.geom.*;

/**
   @author Kaiven Zhou kz2182

   A house shape.

   I decided there was no need to make a class that used the composite pattern,
   which CarShape, HouseShape and PedestrianShape would extend, because of the overhead,
   as well as the fact that drawSelection could be different for different shapes.

   Also to be noted is that intersections are based on bounding rectagles,
   the GeneralPath of this HouseShape is checked if it intersects with the bounding
   rectangle of the other. This is an approximation that is more efficient than
   calculating the exact contact points of both shapes, but is less accurate because
   of the blank spaces that a bounding rectangle might have.

   However, the contains method uses the GeneralPath's contain method to check if a point
   is within the HouseShape (i.e. a bounding rectangle is not used in contains)

   This class was modified from the original, taken from
   Horstmann's Object-oriented Design & Patterns (ISBN 0-471-74487-5)
*/
public class HouseShape extends SelectableShape
{
   /**
      Constructs a house shape.
      @param x the left of the bounding rectangle
      @param y the top of the bounding rectangle
      @param width the width of the bounding rectangle
   */
   public HouseShape(int x, int y, int width)
   {
      this.x = x;
      this.y = y;
      this.width = width;
      unit = width / 6;
      updateRepresentation();
   }

	/**
	 *Draws the house
	 *@param g2 the Graphics2D object to draw on
	 */
   public void draw(Graphics2D g2)
   {
   		updateRepresentation();
	  g2.draw(base);
      g2.draw(roofLeftSide);
      g2.draw(roofRightSide);
   }
	/**
    *Fills in the base of the house when selected
    *@param g2 the Graphis2D object to draw on
    */
   public void drawSelection(Graphics2D g2)
   {
   		 Rectangle2D.Double base
         = new Rectangle2D.Double(baseLeft, baseTop, baseWidth, baseDepth);
   		g2.fill(base);
   }

	/**
	 *Returns true if the given point is within the GeneralPath that is the border of the shape
	 *@param p the point
	 */
	 public boolean contains(Point2D p)
	 {
	 	return border.contains(p.getX(),p.getY());
	 }
	/**
	 *@return the border of the HouseShape
	 */
	 public GeneralPath getBorder()
	{
		return border;
	}
	/**
	 *@return true if the border of this shape intersects the bounding rectangle of the other SceneShape
	 */
   public boolean intersects(SceneShape other)
   {
   		return border.intersects(other.getBorder().getBounds());
   }

	/**
	 *Moves the shape
	 *@param dx change in x coordinate
	 *@param dy change in y coordinate
	 */
   public void translate(int dx, int dy)
   {
      x += dx;
      y += dy;
   }
	/**
	 *Updates the representation of the HouseShape
	 */
   private void updateRepresentation()
   {
   		roofLeft = x;
		roofWidth = 1 * width;
		roofRight = roofLeft + roofWidth;

		roofTop = y;
		roofHeight = 1 * width;
		roofBottom = roofTop + roofHeight;

		roofMiddle = (roofLeft + roofRight)/2;

		baseLeft = roofLeft;
   		baseTop = roofBottom;
   		baseWidth = 1 * width;
   		baseDepth = 1 * width;

        base = new Rectangle2D.Double(baseLeft, baseTop, baseWidth, baseDepth);

      // The left bottom of the roof
      Point2D.Double r1
         = new Point2D.Double(roofLeft, roofBottom);
      // The top of the roof
      Point2D.Double r2
         = new Point2D.Double(roofMiddle, roofTop);
      // The right bottom of the roof
      Point2D.Double r3
         = new Point2D.Double(roofRight, roofBottom);

      roofLeftSide = new Line2D.Double(r1, r2);
      roofRightSide = new Line2D.Double(r2, r3);

         border = new GeneralPath();
         border.append(base,true);
         border.append(roofLeftSide,true);
         border.append(roofRightSide,true);
         border.closePath();
   }

	private GeneralPath border; //the border of the shape
	private Rectangle2D.Double base;
	private Line2D.Double roofLeftSide;
	private Line2D.Double roofRightSide;
   private int x;
   private int y;
   private int width;
   private int unit;
   private int roofLeft;
   private int roofWidth;
   private int roofRight;
   private int roofTop;
   private int roofHeight;
   private int roofBottom;
   private int roofMiddle;
   private int baseLeft;
   private int baseTop;
   private int baseWidth;
   private int baseDepth;
}
