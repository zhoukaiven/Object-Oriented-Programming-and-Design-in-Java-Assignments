import java.awt.*;
import java.awt.geom.*;

/**
   @author Kaiven Zhou kz2182

   A pedestrian shape.

   I decided there was no need to make a class that used the composite pattern,
   which CarShape, HouseShape and PedestrianShape would extend, because of the overhead,
   as well as the fact that drawSelection could be different for different shapes.

   Also to be noted is that intersections are based on bounding rectagles,
   the GeneralPath of this PedestrianShape is checked if it intersects with the bounding
   rectangle of the other. This is an approximation that is more efficient than
   calculating the exact contact points of both shapes, but is less accurate because
   of the blank spaces that a bounding rectangle might have.

   However, the contains method uses the GeneralPath's contain method to check if a point
   is within the PedestrianShape (i.e. a bounding rectangle is not used in contains)

   Also, the special condition of the PedestrianShape is that it can only be added to
   the scene if the number of shapes in the scene is an even number. Refer to the
   method canAddPedestrian in SceneComponent.
*/

public class PedestrianShape extends SelectableShape
{
	/**
	 *Constructs a Pedestrian Shape
	 *@param x the left of the bounding rectangle
	 *@param y the top of the bounding rectangle
      @param width the width of the bounding rectangle
	 */
	public PedestrianShape(int x, int y, int width)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		unit = width/6;
		updateRepresentation();
	}

	/**
	 *Draws the pedestrian
	 *@param g2 the Graphics2D object to draw on
	 */
	public void draw(Graphics2D g2)
	{
		updateRepresentation();
		g2.draw(head);
		g2.draw(body);
	}
	/**
    *Fills in the body of the pedestrian when selected
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
	 *@return the border of the PedestrianShape
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
	 *Updates the representation of the PedestrianShape
	 */
	private void updateRepresentation()
	{
		headLeft = x;
		headTop = y;
		headDiameter = 1 * unit;

		bodyLeft = headLeft;
		bodyTop = headTop + headDiameter;
		bodyWidth = 1 * unit;
		bodyDepth = 2 * unit;

		head = new Ellipse2D.Double(headLeft,headTop, headDiameter, headDiameter);
		body = new Rectangle2D.Double(bodyLeft,bodyTop,bodyWidth,bodyDepth);

		border = new GeneralPath();
		border.append(head,true);
		border.append(body,true);
		border.closePath();
	}

	private int x;
	private int y;
	private int width;
	private int unit;

	private GeneralPath border; //the border of the shape
	private Ellipse2D.Double head;
	private Rectangle2D.Double body;

	private int headLeft;
	private int headTop;
	private int headDiameter;
	private int bodyLeft;
	private int bodyTop;
	private int bodyWidth;
	private int bodyDepth;
}