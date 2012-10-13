/**
 *@author Kaiven Zhou (kz2182)
 *
 *Initializes a CounterPerson
 */
import java.util.ArrayList;

public class BurgerDude
{
	public static void main (String[] args)
	{
		/**
	 	*Initialize a Menu, Cook, and CounterPerson
		*/
		Menu myMenu = new Menu();
		Cook myCook = new Cook(myMenu);
		CounterPerson myCounterPerson = new CounterPerson();

		myCounterPerson.placeOrder(myMenu,myCook);
	}
}