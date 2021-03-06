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

      msg2 = getParameter("msg2");
      String msg2Fontname = getParameter("msg2Fontname");
      int msg2Fontsize = Integer.parseInt(getParameter("msg2Fontsize"));
      msg2Delay = Integer.parseInt(getParameter("msg2Delay"));
	msg2Font = new Font(msg2Fontname, Font.PLAIN, msg2Fontsize);
	FontRenderContext msg2Context = g2.getFontRenderContext();
	msg2Bounds = msg2Font.getStringBounds(msg2, msg2Context);


     msg1Timer = new Timer(Math.abs(msg1Delay), new
         ActionListener()
         {
         	boolean collided=false;

            public void actionPerformed(ActionEvent event)
            {
            	double msg1Left = msg1Start;
    			double msg1Right = msg1Start + msg1Bounds.getWidth();
    			double msg2Left = msg2Start;
    			double msg2Right = msg2Start + msg2Bounds.getWidth();
            	if(msg1Delay>0)
            	{
            		msg1Start--;
            		if (msg1Start + msg1Bounds.getWidth() <0)
                 		 msg1Start = getWidth(); //width of applet
            	}
				else if(msg1Delay<0)
				{
					msg1Start++;
					if(msg1Start >= getWidth())
						msg1Start = -(int)msg1Bounds.getWidth();
				}

				if(msg2Left <= msg1Right && msg1Right <= msg2Left + ERROR_BOUND)
				{
					if(!collided)
					{
						msg1Delay*=-1;
						msg2Delay*=-1;
						collided=true;
					}
				}
				else if (msg2Left <= msg1Left && msg1Left <= msg2Left+ERROR_BOUND)
				{
					if(!collided)
					{
						msg1Delay*=-1;
						msg2Delay*=-1;
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
         {	boolean collided = false;
            public void actionPerformed(ActionEvent event)
            {
            	double msg1Left = msg1Start;
    double msg1Right = msg1Start + msg1Bounds.getWidth();
    double msg2Left = msg2Start;
    double msg2Right = msg2Start + msg2Bounds.getWidth();
            	if(msg2Delay>0)
            	{
            		msg2Start--;
            		if (msg2Start + msg2Bounds.getWidth() <0)
                 		 msg2Start = getWidth(); //width of applet
            	}
				else if(msg2Delay<0)
				{
					msg2Start++;
					if(msg2Start >= getWidth())
						msg2Start = -(int) msg2Bounds.getWidth();
				}
				if(msg1Left <= msg2Right && msg2Right <= msg1Left + ERROR_BOUND)
				{
					if(!collided)
					{
						msg1Delay*=-1;
						msg2Delay*=-1;
						collided=true;
					}
				}
				else if (msg1Right <= msg2Right && msg2Right <= msg1Right+ERROR_BOUND)
				{
					if(!collided)
					{
						msg1Delay*=-1;
						msg2Delay*=-1;
						collided=true;
					}
				}
				else
					collided = false;

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
      g.setFont(msg1Font);
      g.drawString(msg1, msg1Start, (int) -msg1Bounds.getY());
      g.setFont(msg2Font);
      g.drawString(msg2, msg2Start, (int) -msg2Bounds.getY());
   }

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
