import java.awt.event.*;
import javax.swing.*;

/**
 *@author Kaiven Zhou kz2182
 *
 *This action causes a HouseShape to be drawn, at a location defined by constants.
 *Then, all associate actions are updated, checking whether other shapes can be added,
 *and the remove action is updated, checking whether shapes can now be
 *removed.
 */
public class HouseShapeAdder extends ShapeAdder
{
   private static final int HOUSE_INITIAL_X = 20;
   private static final int HOUSE_INITIAL_Y = 20;
   private static final int HOUSE_WIDTH = 50;

	private SceneComponent scene;

	/**
	 *Constructs a CarShapeAdder action.
	 *@param aScene the scene to add the shape to
	 *@param aName identifies which shape this action creates
	 */
	public HouseShapeAdder(SceneComponent aScene, String aName)
	{
		super(aScene,aName);
		scene = aScene;
	}

	public void actionPerformed(ActionEvent event)
	{
		//Check if action is allowed to add a HouseShape
		if(scene.canAdd(getName()))
		{
			scene.add(generateShape());

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
			 *If not allowed to add a HouseShape
			 *then disable this action and its associates,
			 *and enable the opposite action.
			 */
			 setEnabled(false);
			super.checkAssociate();
			super.checkOpposite();
		}
	}
	/**
	 *@return a new HouseShape
	 */
	private SelectableShape generateShape()
	{
		return new HouseShape(HOUSE_INITIAL_X,HOUSE_INITIAL_Y,HOUSE_WIDTH);
	}
}
