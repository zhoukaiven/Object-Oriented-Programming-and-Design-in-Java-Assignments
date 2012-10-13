/**
 *@author Kaiven Zhou (kz2182)
 *
 *Contains an ArrayList of String representing each burger that has been order, and the total cost of all the burgers.
 *An ArrayList is used because the number of burgers is unknown, and the list must be iterated through at least once.
 */
import java.util.ArrayList;

public class Order
{
	/**
	 *Contains a list of burgers
	 *An ArrayList is used because the number of burgers is unknown,
	 *and the list must be iterated through at least once.
	 */
	ArrayList<String> burger;
	/**
	 *The cost of all the burgers
	 */
	int totalCost;

	/**
	 *Creates a new ArrayList<String>, where each element is a burger (String), and sets the totalCost to 0
	 */
	public Order()
	{
		burger = new ArrayList<String>();
		totalCost = 0;
	}
	/**
	 *Outputs the contents of burger by iterating through the ArrayList
	 */
	public void displayCurrentOrder()
	{
		System.out.println("Your current order:");
		for(int i=0; i<burger.size(); i++)
		{
			System.out.println("A burger with "+burger.get(i)+".");
		}
		System.out.format("For a total of $%.2f.\n",totalCost/100.0);
	}
	/*
	 *@return The total cost (int)
	 */
	public int getTotalCost()
	{
		return totalCost;
	}
	/*
	 *@params newBurger the burger to be added (string), the cost of that burger (int)
	 *@params costOfNewBurger the cost of the burger, to be added to the totalCost
	 *Adds the new burger to the end of ArrayList Burger, and increases the total cost by the burger's price
	 */
	public void addToOrder(String newBurger, int costOfNewBurger)
	{
		burger.add(newBurger);
		totalCost += costOfNewBurger;
	}
}