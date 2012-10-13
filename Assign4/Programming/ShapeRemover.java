import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

/**
 *@author Kaiven Zhou kz2182
 *
 *Stores all the ShapeAdder actions that are opposites. When this action is executed
 *the opposites are affected accordingly.
 *
 *Uses Action/command pattern.
 */
public class ShapeRemover extends AbstractAction
{
	private SceneComponent scene;
	private ArrayList<ShapeAdder> opposite;

	public ShapeRemover(SceneComponent aScene)
	{
		scene = aScene;
		opposite = new ArrayList<ShapeAdder>();
	}

	/**
	 *Adds an opposite action
	 *@param anOpposite
	 */
	public void setOppositeAction(ShapeAdder anOpposite)
	{
		opposite.add(anOpposite);
	}
	/**
	 *Checks if the opposite actions should be enabled/disabled
	 */
	public void checkOpposite()
	{
		for(int i=0; i<opposite.size(); i++)
		{
			if(scene.canAdd(opposite.get(i).getName()))
				opposite.get(i).setEnabled(true);
			else
				opposite.get(i).setEnabled(false);
		}
	}

	public void actionPerformed(ActionEvent event)
	{
		if(scene.canRemove()) //If shapes can be removed
		{
			scene.removeSelected();

			//if no more objects can be removed, disable this action
			if(!scene.canRemove())
			{
				setEnabled(false);
			}
			//check if the opposites can be enabled.
			checkOpposite();
		}
		else
		{	//disable this action, check if opposites can be enabled
			setEnabled(false);
			checkOpposite();
		}
	}
}