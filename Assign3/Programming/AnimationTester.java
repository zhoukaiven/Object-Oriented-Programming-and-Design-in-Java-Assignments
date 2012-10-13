/**
   @author Kaiven Zhou kz2182
	This class was modified from the original,
   taken from Horstmann's Object-oriented Design & Patterns

   This program implements an animation that moves
   multiple car shapes.

*/
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.util.Random;

public class AnimationTester
{
   public static void main(String[] args)
   {
      JFrame frame = new JFrame();

	Random generator = new Random();

	//Create NUM_CARS CarShapes, each with a random starting x position and x speed
	//All the cars wrap around
      final CarShape shape1 =
      	new CarShape(generator.nextInt(ICON_WIDTH),0,
      				generator.nextInt(MAX_XSPEED_BOOST),0, CAR_WIDTH, ICON_WIDTH);
      final CarShape shape2 =
      	new CarShape(generator.nextInt(ICON_WIDTH),0,
      				generator.nextInt(MAX_XSPEED_BOOST),0, CAR_WIDTH, ICON_WIDTH);
      final CarShape shape3 =
      	new CarShape(generator.nextInt(ICON_WIDTH),0,
      				generator.nextInt(MAX_XSPEED_BOOST),0, CAR_WIDTH, ICON_WIDTH);

      final GroupOfCars group = new GroupOfCars(NUM_CARS);
      group.add(0,shape1);
      group.add(1,shape2);
      group.add(2,shape3);

      ShapeIcon icon = new ShapeIcon(group,ICON_WIDTH, ICON_HEIGHT);

      final JLabel label = new JLabel(icon);

      frame.setLayout(new FlowLayout());
      frame.add(label);

		//Create a slider which adjusts the speed increments of all the cars
		JSlider speedAdjuster = new JSlider(MIN_XSPEED_BOOST,MAX_XSPEED_BOOST);
		speedAdjuster.createStandardLabels(1);
		speedAdjuster.setMajorTickSpacing(1);
		speedAdjuster.setPaintLabels(true);
		speedAdjuster.setSnapToTicks(true);
		speedAdjuster.addChangeListener(new
			ChangeListener()
			{
				public void stateChanged(ChangeEvent e)
				{
					JSlider source = (JSlider)e.getSource();
					if(!source.getValueIsAdjusting())
					{
						//Increment the speed of all the cars based on the slider value
						int xMultiplier = source.getValue();
						group.changeSpeed(xMultiplier,1);
					}
				}
			});
		frame.add(speedAdjuster);

      final int DELAY = 100;
      // Milliseconds between timer ticks
      Timer t = new Timer(DELAY, new
         ActionListener()
         {
            public void actionPerformed(ActionEvent event)
            {
            	//translate the cars
               group.translate();
               label.repaint();
            }
         });
      t.start();

      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.pack();
      frame.setVisible(true);
   }
	/**
	 *the width of the icon
	 */
   private static final int ICON_WIDTH = 400;
   /**
    *the height of the icon
    */
   private static final int ICON_HEIGHT = 100;
   /**
    *the width of the car
    */
   private static final int CAR_WIDTH = 100;
   /**
    *the number of cars
    */
	private static final int NUM_CARS = 3;
	/**
	 *the slowest the cars can go
	 */
	private static final int MIN_XSPEED_BOOST = 0;
	/**
	 *the fastest the cars can go
	 */
	private static final int MAX_XSPEED_BOOST = 10;
}
