/**
 *@author Kaiven Zhou (kz2182)
 *
 *Builds the burger (string) by storing toppings (BurgerPart) then adding their names together.
 *Also stores the total cost of the burger (int).
 *An ArrayList is used because the number of toppings is unknown,
 *and the list must be iterated through at least once.
 */
import java.util.ArrayList;

public class Cook
{
	/**
	 *Cost of the burger
	 */
	private int burgerCost;
	/**
	 *ArrayList that contains the names of each of the toppings.
	 *An ArrayList is used because the number of toppings is unknown,
	 *and the list must be iterated through at least once.
	 */
	private ArrayList<String> burgerTopping;
	/**
	 *The menu of toppings
	 */
	private Menu myMenu;

	/*
	 *Class Constuctor
	 *@param options The restaurant's menu (Menu)
	 *Sets the burger's cost to 0. Creates a new ArrayList to holds the names of the toppings.
	 */
	public Cook(Menu options)
	{
		burgerCost = options.getBaseCost();
		myMenu = options;
		burgerTopping = new ArrayList<String>();
	}
	/*
	 *Sets the burger's cost to 0. Clears the ArrayList that holds the names of the toppings.
	 */
	public void newBurger()
	{
		burgerCost = myMenu.getBaseCost();;
		burgerTopping.clear();
	}
	/**
	 *@param indexOfTopping The index of the topping in the menu (int)
	 *@param howMany How many of the topping is used (int)
	 */
	public void addToBurger(int indexOfTopping, int howMany)
	{
		BurgerPart topping = myMenu.getTopping(indexOfTopping);
		burgerTopping.add(myMenu.getTopping(indexOfTopping).getName());
		burgerCost += topping.getCost() * howMany;
		myMenu.useTopping(indexOfTopping,howMany);
	}
	/**
	 *@return the burger (string)
	 */
	public String getBurger()
	{
		String burger="";
		if(burgerTopping.size()==0)
			return "nothing extra";

		for(int i=0; i<burgerTopping.size(); i++)
		{
			burger += burgerTopping.get(i);
			if(i!=burgerTopping.size()-1)
				burger += ", ";
		}

		return burger;
	}
	/**
	 *@return the cost of the burger
	 */
	public int getCost()
	{
		return burgerCost;
	}

}