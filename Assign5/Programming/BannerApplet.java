import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.*;
import java.awt.geom.*;
import javax.swing.*;

/**
 *@author Kaiven Zhou kz2182
 *This class taken from Horstmann's Object Oriented Design and Patterns
 *by Cay S. Horstmann
 *ISBN 0-471-74487-5
 *
 *A banner applet that shows scrolling text that collides
 */
public class BannerApplet extends Applet
{
	private static final double ERROR_BOUND = 5;
	private static final int SYSTEM_DELAY = 10;

   public void init()
   {
   	Graphics2D g2 = (Graphics2D) getGraphics();

	//Get the data needed  from the html to make a Message object
      String msg1 = getParameter("msg1"); //the text
      double msg1Start = Double.parseDouble(getParameter("msg1Start")); //the starting x coordinate
      String msg1Fontname = getParameter("msg1Fontname"); //the font name
      int msg1Fontsize = Integer.parseInt(getParameter("msg1Fontsize")); //the font size
      double velocity1 = Double.parseDouble(getParameter("v1")); //the starting velocity
      Font msg1Font = new Font(msg1Fontname, Font.PLAIN, msg1Fontsize); //the font
      FontRenderContext msg1Context = g2.getFontRenderContext();
      Rectangle2D msg1Bounds = msg1Font.getStringBounds(msg1, msg1Context); //the bounding rectangle

      firstMessage = new Message(msg1,msg1Font,msg1Start,msg1Bounds,velocity1,getWidth(),ERROR_BOUND);

	//Get the data needed  from the html to make a Message object
      String msg2 = getParameter("msg2"); //the text
      double msg2Start = Double.parseDouble(getParameter("msg2Start"));//the starting x coordinate
      String msg2Fontname = getParameter("msg2Fontname"); //the font name
      int msg2Fontsize = Integer.parseInt(getParameter("msg2Fontsize")); //the font size
      double velocity2 = Double.parseDouble(getParameter("v2"));  //the starting velocity
	  Font msg2Font = new Font(msg2Fontname, Font.PLAIN, msg2Fontsize); //the font
	FontRenderContext msg2Context = g2.getFontRenderContext();
	Rectangle2D msg2Bounds = msg2Font.getStringBounds(msg2, msg2Context); //the bounding rectangle

      secondMessage = new Message(msg2,msg2Font,msg2Start,msg2Bounds,velocity2,getWidth(),ERROR_BOUND);

     timer = new Timer(SYSTEM_DELAY, new
         ActionListener()
         {
            public void actionPerformed(ActionEvent event)
            {
            	//move the Message objects, then check if they collide
            	//if they do collide, change their momentums
				firstMessage.move();
				secondMessage.move();
				if(firstMessage.collidesWith(secondMessage))
				{
					firstMessage.afterCollision(secondMessage);
				}

				//used to prvent flickering
				imageBuffer = createImage(getWidth(),getHeight());
				graphicsBuffer = imageBuffer.getGraphics();
               repaint();
            }
         });
   }

   public void start()
   {
      timer.start();
   }

   public void stop()
   {
   		timer.stop();
   }
   //update is overridden to prevent flickering
   public void update(Graphics g)
   {
   	paint(g);
   }
   public void paint(Graphics g)
   {
   	firstMessage.draw(graphicsBuffer);
    secondMessage.draw(graphicsBuffer);
    g.drawImage(imageBuffer,0,0,this);
   }

   private Timer timer; //the timer
   	private Message firstMessage, secondMessage; //the Message objects

   	/**
   	 *In order to prevent the "flickering", the imageBuffer and graphicsBuffer are used
   	 */
   	private Image imageBuffer;
   	private Graphics graphicsBuffer;
}
