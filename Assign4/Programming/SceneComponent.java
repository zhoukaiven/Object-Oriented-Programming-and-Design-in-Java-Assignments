import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;
import java.util.*;

/**
 *@author Kaiven Zhou kz2182

   A component that shows a scene composed of shapes.

   This class was modified from the original,
   taken from Horstmann's Object-oriented Design & Patterns.
*/
public class SceneComponent extends JComponent
{
   public SceneComponent(int maxNumShapes, int minNumShapes)
   {
		this.maxNumShapes = maxNumShapes;
		this.minNumShapes = minNumShapes;

      shapes = new ArrayList<SceneShape>();

      addMouseListener(new
         MouseAdapter()
         {
            public void mousePressed(MouseEvent event)
            {
               mousePoint = event.getPoint();
               for (SceneShape s : shapes)
               {
                  if (s.contains(mousePoint))
                     s.setSelected(!s.isSelected());
               }
               repaint();
            }
         });

      addMouseMotionListener(new
         MouseMotionAdapter()
         {
            public void mouseDragged(MouseEvent event)
            {
               Point lastMousePoint = mousePoint;
               mousePoint = event.getPoint();
               for (SceneShape s : shapes)
               {
                  if (s.isSelected())
                  {
                     double dx = mousePoint.getX() - lastMousePoint.getX();
                     double dy = mousePoint.getY() - lastMousePoint.getY();
                     s.translate((int) dx, (int) dy);
                  }
               }
               repaint();
            }
         });
   }

   /**
      Adds a shape to the scene.
      @param s the shape to add
   */
   public void add(SceneShape s)
   {
      shapes.add(s);
      repaint();
   }

   /**
      Removes all selected shapes from the scene.
   */
   public void removeSelected()
   {
      for (int i = shapes.size() - 1; i >= 0; i--)
      {
         SceneShape s = shapes.get(i);
         if (s.isSelected()) shapes.remove(i);
      }
      repaint();
   }
	/**
	 *Draws all the shapes, selected and unselected
	 *@param g the Graphics object to draw on
	 */
   public void paintComponent(Graphics g)
   {
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D) g;
      for (SceneShape s : shapes)
      {
         s.draw(g2);
         if (s.isSelected())
            s.drawSelection(g2);
      }
   }
   /**
	 *@return true if the number of shapes currently drawn is more than minNumShapes
	 */
	public boolean canRemove()
	{
		if(shapes.size()>minNumShapes)
			return true;
		else
			return false;
	}
	/**
	 *@return true if the gloabl conditions for adding shapes are satisfied AND
	 *the shape's specific conditions are satisfied.
	 */
	public boolean canAdd(String aName)
	{
		/**Current global condition:
		 *number of shapes currently drawn is less than maxNumShapes
		 */
		if(!(shapes.size()<maxNumShapes))
			return false;
		else if(aName.equals("Car"))
			return canAddCar();
		else if(aName.equals("House"))
			return canAddHouse();
		else if(aName.equals("Pedestrian"))
			return canAddPedestrian();

		return false;
	}
	/**
	 *@return true if aShape can be placed without intersecting any other shapes
	 */
	public boolean canPlace(SelectableShape aShape)
	{
		for(int i=0; i<shapes.size(); i++)
		{
			if(aShape.intersects(shapes.get(i)))
				return false;
		}
		return true;
	}
	/**
	 *@return true if the special conditions of adding a PedestrianShape are true
	 */
	private boolean canAddPedestrian()
	{
		/**
		 *Additional condition is that the number of shapes already drawn must be even
		 */
		if(shapes.size()%2==0)
		{
			return true;
		}
		else
			return false;
	}
	//No additional condition
	private boolean canAddCar()
	{
		return true;
	}
	//No additional condition
	private boolean canAddHouse()
	{
		return true;
	}

   private ArrayList<SceneShape> shapes;
   private Point mousePoint;
   //the maximum number of shapes that can be in the scene
   private int maxNumShapes;
   /**
    *the minimum number of shapes that can be in the scene, excluding the beginning
    *when there are none and shapes are being added
    */
   private int minNumShapes;
}
