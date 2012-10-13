/**
   @author Kaiven Zhou kz2182

   A car shape.

   I decided there was no need to make a class that used the composite pattern,
   which CarShape, HouseShape and PedestrianShape would extend, because of the overhead,
   as well as the fact that drawSelection could be different for different shapes.

   To be noted is that intersections are based on bounding rectagles:
   the GeneralPath of this CarShape is checked if it intersects with the bounding
   rectangle of the other. This is an approximation that is more efficient than
   calculating the exact contact points of both shapes, but is less accurate because
   of the blank spaces that a bounding rectangle might have.

   However, the contains method uses the GeneralPath's contain method to check if a point
   is within the CarShape (i.e. a bounding rectangle is not used in contains)

   This class was modified from the original, taken from
   Horstmann's Object-oriented Design & Patterns (ISBN 0-471-74487-5)
*/
import java.awt.*;
import java.awt.geom.*;
import java.util.*;

public class CarShape extends SelectableShape
{
   /**
      Constructs a car shape.
      @param x the left of the bounding rectangle
      @param y the top of the bounding rectangle
      @param width the width of the bounding rectangle
   */
   public CarShape(int x, int y, int width)
   {
      this.x = x;
      this.y = y;
      this.width = width;
      unit = width / 6;
      updateRepresentation();
   }

	/**
	 *Draws the car
	 *@param g2 the Graphics2D object to draw on
	 */
   public void draw(Graphics2D g2)
   {
   		updateRepresentation();
      g2.draw(body);
      g2.draw(frontTire);
      g2.draw(rearTire);
      g2.draw(frontWindshield);
      g2.draw(roofTop);
      g2.draw(rearWindshield);
   }
   /**
    *Fills in the body of the car when selected
    *@param g2 the Graphis2D object to draw on
    */
   public void drawSelection(Graphics2D g2)
   {
      Rectangle2D.Double body
            = new Rectangle2D.Double(bodyLeft, bodyTop, bodyWidth, bodyDepth);
      g2.fill(body);
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
	 *@return the border of the CarShape
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
	 *Updates the representation of the CarShape
	 */
   private void updateRepresentation()
   {
   		//All "magic numbers" have been removed
   		//Computes all the relationships
   		 roofCompartmentTop = y;
   		 roofCompartmentDepth = 1 * unit;
   		 roofCompartmentBottom = roofCompartmentDepth + roofCompartmentTop;

   		 bodyTop = roofCompartmentBottom;
   		 bodyDepth = 1 * unit;
   		 bodyBottom = bodyDepth + bodyTop;

   		 wheelTop = bodyBottom;

   		 bodyLeft = x;
   		 leftWindowOffset = 1 * unit;

   		 leftWindowLeft = leftWindowOffset + bodyLeft;
   		 leftWindowWidth = 1 * unit;
   		 leftWindowRight = leftWindowWidth + leftWindowLeft;

   		 roofPanelLeft = leftWindowRight;
   		 roofPanelWidth = 2 * unit;
   		 roofPanelRight = roofPanelWidth + roofPanelLeft;

   		 rightWindowLeft = roofPanelRight;
   		 rightWindowWidth = 1*unit;
   		 rightWindowRight = rightWindowWidth + rightWindowLeft;

   		 bodyWidth = 6 * unit;
   		 bodyRight = bodyWidth + bodyLeft;

   		 wheelDiameter = 1 * unit;

   		 leftWheelOffset = 1 * unit;
   		 leftWheelLeft = leftWheelOffset + bodyLeft;
   		 interwheelOffset = 3 * unit;
   		 rightWheelLeft = leftWheelLeft + interwheelOffset;
   		  wheelBottom = bodyBottom + wheelDiameter;

      body = new Rectangle2D.Double(bodyLeft, bodyTop, bodyWidth, bodyDepth);
      frontTire = new Ellipse2D.Double(leftWheelLeft, wheelTop, wheelDiameter, wheelDiameter);
      rearTire = new Ellipse2D.Double(rightWheelLeft, wheelTop, wheelDiameter, wheelDiameter);

      // The bottom of the front windshield
      Point2D.Double r1
            = new Point2D.Double(leftWindowLeft,roofCompartmentBottom);
      // The front of the roof
      Point2D.Double r2
            = new Point2D.Double(roofPanelLeft, roofCompartmentTop);
      // The rear of the roof
      Point2D.Double r3
            = new Point2D.Double(roofPanelRight, roofCompartmentTop);
      // The bottom of the rear windshield
      Point2D.Double r4
            = new Point2D.Double(rightWindowRight, roofCompartmentBottom);

      frontWindshield = new Line2D.Double(r1, r2);
      roofTop = new Line2D.Double(r2, r3);
      rearWindshield = new Line2D.Double(r3, r4);

		border = new GeneralPath();
		border.append(body,true);
		border.append(frontTire,true);
		border.append(rearTire,true);
		border.append(frontWindshield,true);
		border.append(roofTop,true);
		border.append(rearWindshield,true);
		border.closePath();
   }

	private GeneralPath border; //the border of the shape
	private Rectangle2D.Double body;
	private Ellipse2D.Double frontTire;
	private Ellipse2D.Double rearTire;
	private Line2D.Double frontWindshield;
	private Line2D.Double roofTop;
	private Line2D.Double rearWindshield;
   /**
    *unit length used to draw the car
    */
   private int unit;
   /**
    *x coordinate of the car
    */
   private int x;
   /**
    *y coordinate of the car
    */
   private int y;
   /**
    *width
    */
   private int width;

   //The rest are based of the original class
   private int roofCompartmentTop;
   private int roofCompartmentDepth;
	private int roofCompartmentBottom;

	private int bodyTop;
	private int bodyDepth;
	private int bodyBottom;

	private int wheelTop;

	private int bodyLeft;
	private int leftWindowOffset;

	private int leftWindowLeft;
	private int leftWindowWidth;
	private int leftWindowRight;

	private int roofPanelLeft;
	private int roofPanelWidth;
	private int roofPanelRight;

	private int rightWindowLeft;
	private int rightWindowWidth;
	private int rightWindowRight;

	private int bodyWidth;
	private int bodyRight;

	private int wheelDiameter;
	private int wheelBottom;
	private int leftWheelOffset;
	private int leftWheelLeft;
	private int interwheelOffset;
	private int rightWheelLeft;
}
