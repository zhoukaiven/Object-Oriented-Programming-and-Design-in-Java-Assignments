import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

/**
 *@author Kaiven Zhou kz2182
 *
 *Stores all the ShapeAdder actions that are opposites or associates. When this action is executed
 *the opposites and associates are affected accordingly.
 *
 *Uses Action pattern.
 */
public abstract class ShapeAdder extends AbstractAction
{
	private ArrayList<ShapeRemover> opposite;
	private ArrayList<ShapeAdder> associate;
	private SceneComponent scene;
	private String name;

	public ShapeAdder(SceneComponent aScene, String aName)
	{
		scene = aScene;
		name = aName;
		opposite = new ArrayList<ShapeRemover>();
		associate = new ArrayList<ShapeAdder>();
	}
	/**
	 *Adds an opposite action
	 *@param anOpposite
	 */
	public void setOppositeAction(ShapeRemover anOpposite)
	{
		opposite.add(anOpposite);
	}
	/**
	 *Adds an associate action
	 *@param anAssociate
	 */
	public void setAssociateAction(ShapeAdder anAssociate)
	{
		associate.add(anAssociate);
	}

	/**
	 *Checks if the associate actions should be enabled/diabled
	 */
	public void checkAssociate()
	{
		for(int i=0; i<associate.size(); i++)
		{
			if(scene.canAdd(associate.get(i).getName()))
				associate.get(i).setEnabled(true);
			else
				associate.get(i).setEnabled(false);
		}
	}
	/**
	 *Checks if the opposite actions should be enabled/disabled
	 */
	public void checkOpposite()
	{
		boolean state;
		if(scene.canRemove())
			state = true;
		else
			state = false;
		for(int i=0; i<opposite.size(); i++)
		{
			opposite.get(i).setEnabled(state);
		}
	}
	/**
	 *@return the name
	 */
	public String getName()
	{
		return name;
	}
}