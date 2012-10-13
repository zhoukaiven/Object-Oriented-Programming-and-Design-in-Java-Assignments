import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *@author Kaiven Zhou kz2182
   A program that allows users to edit a scene composed
   of items.

   This class was modified from the original, taken from
   Horstmann's Object-oriented Design & Patterns (ISBN 0-471-74487-5)
*/
public class SceneEditor
{
   public static void main(String[] args)
   {
   	//Create the frame
      JFrame frame = new JFrame();
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	//Create the scene
      final SceneComponent scene = new SceneComponent(MAX_NUM_SHAPES,MIN_NUM_SHAPES);

		//Create the PestrianShapeAdder action and its button
		PedestrianShapeAdder newPedestrianMaker
			= new PedestrianShapeAdder(scene,"Pedestrian",WIDTH_FOR_SCENE,HEIGHT_FOR_SCENE);
		newPedestrianMaker.putValue(Action.NAME,"Pedestrian");
		JButton pedestrianButton = new JButton(newPedestrianMaker);
		pedestrianButton.setPreferredSize(new Dimension(BUTTON_WIDTH,BUTTON_HEIGHT));

	//Create the HouseShapeAdder action and its button
	HouseShapeAdder newHouseMaker = new HouseShapeAdder(scene,"House");
	newHouseMaker.putValue(Action.NAME,"House");
	JButton houseButton = new JButton(newHouseMaker);
	houseButton.setPreferredSize(new Dimension(BUTTON_WIDTH,BUTTON_HEIGHT));

	//Create the CarShapeAdder action and its button
	CarShapeAdder newCarMaker = new CarShapeAdder(scene,"Car");
	newCarMaker.putValue(Action.NAME,"Car");
	JButton carButton = new JButton(newCarMaker);
	carButton.setPreferredSize(new Dimension(BUTTON_WIDTH,BUTTON_HEIGHT));

	//Create the ShapeRemover action and its button
	ShapeRemover remove = new ShapeRemover(scene);
	remove.putValue(Action.NAME, "Remove");
	JButton removeButton = new JButton(remove);
	removeButton.setPreferredSize(new Dimension(BUTTON_WIDTH,BUTTON_HEIGHT));
	removeButton.setEnabled(false); //button initially disabled because there are no shapes in the beginning

	//each of the ShapeAdders are associated to each other
	newPedestrianMaker.setAssociateAction(newHouseMaker);
	newPedestrianMaker.setAssociateAction(newCarMaker);

	newHouseMaker.setAssociateAction(newPedestrianMaker);
	newHouseMaker.setAssociateAction(newCarMaker);

	newCarMaker.setAssociateAction(newPedestrianMaker);
	newCarMaker.setAssociateAction(newHouseMaker);

	//remove is the opposite of all the ShapeAdders, and vice versa
	remove.setOppositeAction(newPedestrianMaker);
	remove.setOppositeAction(newHouseMaker);
	remove.setOppositeAction(newCarMaker);

	newPedestrianMaker.setOppositeAction(remove);
	newHouseMaker.setOppositeAction(remove);
	newCarMaker.setOppositeAction(remove);

	//add the buttons to the JPanel
      JPanel buttons = new JPanel();
      buttons.add(houseButton);
      buttons.add(carButton);
      buttons.add(pedestrianButton);
      buttons.add(removeButton);
	//add butons and scene to the frame
      frame.add(scene, BorderLayout.CENTER);
      frame.add(buttons, BorderLayout.NORTH);
      frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
      frame.setVisible(true);
   }
	private static final int FRAME_WIDTH = 500;
	private static final int FRAME_HEIGHT = 300;
	private static final int BUTTON_WIDTH = 100;
	private static final int BUTTON_HEIGHT = 30;
 	private static final int MAX_NUM_SHAPES = 20;
   	private static final int MIN_NUM_SHAPES = 2;

	/**
	 *For shapes that are randomly generated, their coordinates cannot be as large
	 *as the coordinates of the frame because the buttons take up space. In other words
	 *the buttons push the coordinate system of the shapes, meaning some shapes may
	 *be created, but outside the visible frame. Therefore, these variables are used to
	 *ensure that randomly generated shapes appear in the frame.
	 */
	private static final int HEIGHT_FOR_SCENE = FRAME_HEIGHT - BUTTON_HEIGHT;
	private static final int WIDTH_FOR_SCENE = FRAME_WIDTH;

}


