import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.*;
import java.awt.geom.*;
import javax.swing.*;

/**
 *This class taken from Horstmann's Object Oriented Design and Patterns
 *by Cay S. Horstmann
 *ISBN 0-471-74487-5
 */
public class BannerApplet extends Applet
{
	private static final double ERROR_BOUND = 1.1;

   public void init()
   {
   	Graphics2D g2 = (Graphics2D) getGraphics();

      msg1 = getParameter("msg1");
      String msg1Fontname = getParameter("msg1Fontname");
      int msg1Fontsize = Integer.parseInt(getParameter("msg1Fontsize"));
      msg1Delay = Integer.parseInt(getParameter("msg1Delay"));
      msg1Font = new Font(msg1Fontname, Font.PLAIN, msg1Fontsize);
      FontRenderContext msg1Context = g2.getFontRenderContext();
      msg1Bounds = msg1Font.getStringBounds(msg1, msg1Context);

      firstMessage = new Message(msg1,msg1Fontname,msg1Fontsize,msg1Delay,msg1Font,msg1Bounds,msg1Start,ERROR_BOUND,getWidth());

      msg2 = getParameter("msg2");
      String msg2Fontname = getParameter("msg2Fontname");
      int msg2Fontsize = Integer.parseInt(getParameter("msg2Fontsize"));
      msg2Delay = Integer.parseInt(getParameter("msg2Delay"));
	msg2Font = new Font(msg2Fontname, Font.PLAIN, msg2Fontsize);
	FontRenderContext msg2Context = g2.getFontRenderContext();
	msg2Bounds = msg2Font.getStringBounds(msg2, msg2Context);

	secondMessage = new Message(msg2,msg2Fontname,msg2Fontsize,msg2Delay,msg2Font,msg2Bounds,msg2Start,ERROR_BOUND,getWidth());


     msg1Timer = new Timer(Math.abs(msg1Delay), new
         ActionListener()
         {
         	boolean collided=false;

            public void actionPerformed(ActionEvent event)
            {
				firstMessage.move();
				if(firstMessage.collidesWith(secondMessage))
				{
					if(!collided)
					{
						msg1Delay*=-1;
						firstMessage.setDelay(msg1Delay);
						msg2Delay*=-1;
						secondMessage.setDelay(msg2Delay);
						collided=true;
					}
				}
				else
					collided = false;

               repaint();
            }
         });

      msg2Timer = new Timer(Math.abs(msg2Delay), new
         ActionListener()
         {
         	boolean collided=false;

            public void actionPerformed(ActionEvent event)
            {
				secondMessage.move();
				/*if(secondMessage.collidesWith(firstMessage))
				{
					if(!collided)
					{
						msg1Delay*=-1;
						firstMessage.setDelay(msg1Delay);
						msg2Delay*=-1;
						secondMessage.setDelay(msg2Delay);
						collided=true;
					}
				}
				else
					collided = false;*/

               repaint();
            }
         });
   }

   public void start()
   {
      msg1Timer.start();
      msg2Timer.start();
   }

   public void stop()
   {
      msg1Timer.stop();
      msg2Timer.stop();
   }
   public void paint(Graphics g)
   {
   	firstMessage.paint(g);
    secondMessage.paint(g);
   }
	private Message firstMessage;
	private Message secondMessage;
   private Timer msg1Timer;
   private Timer msg2Timer;
   private int msg1Start=0;
   private int msg2Start=500;
   private int msg1Delay;
   private int msg2Delay;
   private String msg1;
   private String msg2;
   private Font msg1Font;
   private Font msg2Font;
   private Rectangle2D msg1Bounds;
   private Rectangle2D msg2Bounds;
}
