import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.*;
import java.awt.geom.*;
import javax.swing.*;

/**
 *@author Kaiven Zhou kz2182
 *A String that has a mass, a velocity, collides with other Message objects, and wraps around the displayed screen.
 */
public class Message
{
	/**
	 *The Message object's text
	 */
	private String msg;
	/**
	 *font of the Message
	 */
	private Font font;

	/**
	 *x coordinate of the left hand side of the Message
	 */
	private double left;
	/**
	 *width of the Message
	 */
	private double width;
	/**
	 *x coordinate of the right hand side of the Message
	 */
	private double right;
	/**
	 *height of the Message
	 */
	private double height;

	/**
	 *Mass of object, defined to be width*height
	 */
	private double mass;
	/**
	 *How much the object moves per change in time. The change in time is determined by the timer.
	 */
	private double velocity;
	private double appWidth;
	/**
	 *In order to check if two doubles equal, I use an error margin, and check if the absolute value difference between
	 *the two doubles is less than this error margin.
	 */
	private double errorMargin;

	public Message(String aMsg, Font aFont,double startingX, Rectangle2D aBound, double aVelocity, double anAppWidth, double anErrorMargin)
	{
		msg = aMsg;
		font = aFont;

		left = startingX;
		width = aBound.getWidth();
		right = left+width;
		height = aBound.getHeight();

		mass = Math.abs(width*height);
		velocity = aVelocity;
		appWidth = anAppWidth;
		errorMargin = anErrorMargin;
	}
	/**
	 *Moves the Message.
	 */
	public void move()
	{
		left+=velocity;
		right = left + width;
		/**
		 If the object is moving right
		 */
		if(velocity>0)
    	{
			if(left >= appWidth)
			{
				left = -width;
				right = left + width;
			}
    	}
		else if(velocity<0) //if the object is moving left
		{
    		if (right <0)
    		{
    			left = appWidth; //width of applet
    			right = left + width;
    		}
		}
	}
	/**
	 *Checks if two Message objects have collided.
	 *@param other the other Message this Message could collide with
	 */
	public boolean collidesWith(Message other)
	{
		if((double)Math.abs(right-other.getLeft())<= errorMargin)
			return true;
		else if((double)Math.abs(right-other.getRight())<=errorMargin)
			return true;
		else if((double)Math.abs(left-other.getRight())<=errorMargin)
			return true;
		else if ((double)Math.abs(left-other.getLeft())<=errorMargin)
			return true;
		else
			return false;
	}
	/**
	 *Changes the momentums of two Message objects colliding.
	 *@param other the other Message this Message collides with
	 */
	public void afterCollision(Message other)
	{
		double u1 = velocity;
		double m1 = mass;
		double u2 = other.getVelocity();
		double m2 = other.getMass();

		velocity = (u1*(m1-m2)+2*u2*m2)/(m1+m2);

		double otherVelocity = (u2*(m2-m1)+2*u1*m1)/(m1+m2);
		other.setVelocity(otherVelocity);
	}
	/**
	 *@return the x coordiate of the left hand side of the Message
	 */
	public double getLeft()
	{
		return left;
	}
	/**
	 *@return the x coordinate of the right hand side of the Message
	 */
	public double getRight()
	{
		return right;
	}
	/**
	 *@return the mass of the Message
	 */
	public double getMass()
	{
		return mass;
	}
	/**
	 *@return the velocity of the Message
	 */
	public double getVelocity()
	{
		return velocity;
	}
	/**
	 *Changes the velocity
	 *@param newVelocity the new Velocity of the Message
	 */
	public void setVelocity(double newVelocity)
	{
		velocity = newVelocity;
	}
	/**
	 *@param g the Graphics to draw to
	 */
	public void draw(Graphics g)
	{
		g.setFont(font);
     	 g.drawString(msg, (int) left, (int) height);
	}
}