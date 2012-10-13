import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

/**
 *@author Kaiven Zhou kz2182
 *
 *This action causes a PedestrianShape to be drawn, only if there is room
 *(i.e can be drawn without overlapping another shape). Then, all associate
 *actions are updated, checking whether other shapes can be added,
 *and the remove action is updated, checking whether shapes can now be
 *removed.
 *
 *Also, the special condition of the PedestrianShape is that it can only be added to the scene
   if the number of shapes in the scene is an even number. Refer to the method canAddPedestrian
   in SceneComponent.
 */
public class PedestrianShapeAdder extends ShapeAdder
{
   /**
    *Placing the PedestrianShape at a random point, could lead to an infinite loop
    *(the random points could just happen to always to be within shapes, or there actually
    *is no more room left). Therefore RANDOM_PLACEMENT_TRIES is the most number of random
    *points generated before giving up.
    *
    *This method is less accurate then others (such as knowing where every shape is and making
    *a decision), but is much more efficient.
    */
   private static final int RANDOM_PLACEMENT_TRIES = 5;
	private static final int PEDESTRIAN_WIDTH = 50;
	private int maxX;
	private int maxY;
	private SceneComponent scene;
	private JFrame frame;

	/**
	 *Constructs a PedestrianShapeAdder action.
	 *@param aScene the scene to add the shape to
	 *@param aName identifies which shape this action creates
	 *@param maxXCoordinate maximum X coordinate
	 *@param maxYCoordinate maximum Y coordinate
	 */
	public PedestrianShapeAdder(SceneComponent aScene,String aName,int maxXCoordinate,int maxYCoordinate )
	{
		super(aScene,aName);
		scene = aScene;
		maxX=maxXCoordinate;
		maxY=maxYCoordinate;
	}

	public void actionPerformed(ActionEvent event)
	{
		//Check if action is allowed to add a PedestrianShape
		if(scene.canAdd(getName()))
		{
			//If able, tries to generate a PedestrianShape in a random location
			 SelectableShape possibleShape = generateShape();
			 //If a PedestrianShape in a random location can be generated, add it to the scene
			 if(possibleShape!=null)
			 	scene.add(possibleShape);

			//Check if this action, and all associates are allowed to act
			if(!scene.canAdd(getName()))
				setEnabled(false);
			super.checkAssociate();

			//Check if the opposite action is allowed to act
			super.checkOpposite();
		}
		else
		{
			/**
			 *If not allowed to add a PedestrianShape
			 *then disable this action and its associates,
			 *and enable the opposite action.
			 */
			setEnabled(false);
			super.checkAssociate();
			//super.setOppositeState(true);
			super.checkOpposite();
		}
	}
	/**
	 *Generates a shape if possible
	 *@return SelectableShape or null, if a space the shape can/cannot be found
	 */
	private SelectableShape generateShape()
	{
		Random randomX; //random number generator for X coordinate
		Random randomY; //random number generator for Y coordinate
		PedestrianShape tryShape;

		for(int i=0; i<RANDOM_PLACEMENT_TRIES; i++)
		{
			randomX = new Random();
			randomY = new Random();

			tryShape = new PedestrianShape(randomX.nextInt(maxX),randomX.nextInt(maxY),PEDESTRIAN_WIDTH);

			if(scene.canPlace(tryShape))
				return tryShape;
		}
		return null; //if a spot to put the shape cannot be found
	}
}