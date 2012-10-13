import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.*;
import java.awt.geom.*;
import javax.swing.*;

public class Message extends Applet
{
	private String msg;
	private String fontName;
	private int fontsize;
	private int delay;
	private Font font;
	private Rectangle2D bounds;

	private int width;
	private int left;
	private int right;
	private int mass;

	private int appWidth;
	private double errorMargin;

	private static final int MOVEMENT_DISTANCE = 1;


	public Message(String aMsg, String aFontName, int aFontsize, int aDelay, Font aFont, Rectangle2D aBound, int startingX, double anErrorMargin, int anAppWidth)
	{
		msg = aMsg;
		fontName = aFontName;
		fontsize = aFontsize;
		delay = aDelay;
		font = aFont;
		bounds = aBound;

		errorMargin = anErrorMargin;
		appWidth = anAppWidth;

		width = (int) bounds.getWidth();
		left = startingX;
		right = left + width;
		mass = fontsize * width;

	}
	public void move()
	{
		if(delay>0)
    	{
    		left -= MOVEMENT_DISTANCE;
    		if (right <0)
         		 left = appWidth; //width of applet
    	}
		else if(delay<0)
		{
			left+=MOVEMENT_DISTANCE;
			if(left >= appWidth)
				left = -(int)bounds.getWidth();
		}
	}
	public boolean collidesWith(Message other)
	{
		if((double)Math.abs(left-other.getRight())<= errorMargin)
			return true;
		else if((double)Math.abs(right-other.getLeft())<=errorMargin)
			return true;
		else
			return false;
	}

	public int getLeft()
	{
		return left;
	}
	public int getRight()
	{
		return right;
	}
	public void setDelay(int newDelay)
	{
		delay = newDelay;
	}
	public void paint(Graphics g)
	{
		g.setFont(font);
     	 g.drawString(msg, left, (int) -bounds.getY());
	}
}