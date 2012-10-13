/**
   @author Kaiven Zhou kz2182

   This class was modified from the original,
   taken from Horstmann's Object-oriented Design & Patterns

   A car that can be moved around, and wraps around.
*/
import java.awt.*;
import java.awt.geom.*;
import java.util.*;

public class CarShape implements MoveableShape
{
   /**
      Constructs a car item.
      @param x the left of the bounding rectangle
      @param y the top of the bounding rectangle
      @param xSpeed the initial x speed
      @param ySpeed the initial y speed
      @param width the width of the bounding rectangle
   */
   public CarShape(int x, int y, int xSpeed, int ySpeed,int width)
   {
      this.x = x;
      this.y = y;
      this.xSpeed = xSpeed;
      this.ySpeed = ySpeed;
      xMultiplier = 1;
      yMultiplier = 1;
      this.width = width;
      unit = width /6;
      boundary = -1;
   }
   /**
      Constructs a car item.
      @param x the left of the bounding rectangle
      @param y the top of the bounding rectangle
      @param xSpeed the initial x speed
      @param ySpeed the initial y speed
      @param width the width of the bounding rectangle
      @param iconWidth the farthest the car can travel before it wraps around
   */
   public CarShape(int x, int y, int xSpeed, int ySpeed,int width, int iconWidth)
   {
      this.x = x;
      this.y = y;
      this.xSpeed = xSpeed;
      this.ySpeed = ySpeed;
      xMultiplier = 1;
      yMultiplier = 1;
      this.width = width;
      unit = width /6;
      boundary = iconWidth;
   }
	/**
	 *Translate the cars dx and dy forward, then checks if the car is still
	 *within boundary
	 */
	public void translate()
	{
		x += xSpeed*xMultiplier;
		y += ySpeed*yMultiplier;

		checkBoundary();
	}
	/**
	 *Increases the speed of the car
	 *@param xMultiplier the x Multiplier
	 *@param yMultiplier the y Multiplier
	 */
	public void changeSpeed(int xMultiplier, int yMultiplier)
	{
		this.xMultiplier = xMultiplier;
		this.yMultiplier = yMultiplier;
	}
	/**
	 *Draw the car
	 *@param g2 the Graphics2D object to draw on
	 */
   public void draw(Graphics2D g2)
   {
   		//All "magic numbers" have been removed
   		//Computes all the relationships
   		 roofCompartmentTop = y;
   		 roofCompartmentDepth = 1 * unit;
   		 roofCompartmentBottom = roofCompartmentDepth + roofCompartmentTop;

   		 bodyTop = roofCompartmentDepth;
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

   		 diameter = 1 * unit;

   		 leftWheelOffset = 1 * unit;
   		 leftWheelLeft = leftWheelOffset + bodyLeft;
   		 interwheelOffset = 3 * unit;
   		 rightWheelLeft = leftWheelLeft + interwheelOffset;

      Rectangle2D.Double body
            = new Rectangle2D.Double(bodyLeft, bodyTop, bodyWidth, bodyDepth);
      Ellipse2D.Double frontTire
            = new Ellipse2D.Double(leftWheelLeft, wheelTop, diameter, diameter);
      Ellipse2D.Double rearTire
            = new Ellipse2D.Double(rightWheelLeft, wheelTop, diameter, diameter);

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

      Line2D.Double frontWindshield
            = new Line2D.Double(r1, r2);
      Line2D.Double roofTop
            = new Line2D.Double(r2, r3);
      Line2D.Double rearWindshield
            = new Line2D.Double(r3, r4);

      g2.draw(body);
      g2.draw(frontTire);
      g2.draw(rearTire);
      g2.draw(frontWindshield);
      g2.draw(roofTop);
      g2.draw(rearWindshield);
   }
   /**
    *If boundary = -1, then the car does not wrap around
    *otherwise the car is forced into the boundary using the modulo operation
    */
   private void checkBoundary()
   {
   		if(boundary != -1)
   		{
   			x=x%boundary;
   			y=y%boundary;
   		}
   }
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
    *initial x speed
    */
   private int xSpeed;
   /**
    *initial y speed
    */
   private int ySpeed;
   /**
    *x speed multiplier, to be controlled by the slider
    *(this way, when slider = 0, all cars stop, and after
    *when slider > 0 the cars can move again
    */
   private int xMultiplier;
   /**
    *y speed multiplier
    */
   private int yMultiplier;
   /**
    *width
    */
   private int width;
   /**
    *farthest the car can go before it wraps around
    */
   private int boundary;

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

	private int diameter;

	private int leftWheelOffset;
	private int leftWheelLeft;
	private int interwheelOffset;
	private int rightWheelLeft;
}
