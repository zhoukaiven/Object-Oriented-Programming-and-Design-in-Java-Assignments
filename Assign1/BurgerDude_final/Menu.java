/**
 *@author Kaiven Zhou (kz2182)
 *
 *Contains an ArrayList of BurgerPart, and methods to access the BurgerParts
 *An ArrayList is used because in the future a method could be added which allows the user to add items to the menu,
 *meaning that the list of toppings (in AL option) must be able to grow. A Linked list is NOT used because the list
 *must be interated through multiple times.
 *
 */

import java.util.ArrayList;
public class Menu
{
	/**
	 *Contains a list of BurgerPart (the toppings)
	 *An ArrayList is used because in the future a method could be added which allows the user to add items to the menu,
	 *meaning that the list of toppings (in AL option) must be able to grow. A Linked list is NOT used because the list
	 *must be interated through multiple times.
	 */
	ArrayList<BurgerPart> option;

	/**
	 *Cost of the burger without any toppings
	 */
	int baseBurgerCost;
	/**
	 *Class Constructor, containing the list of toppings, their prices, their limits (per burger), and how many are in stock.
	 */
	public Menu()
	{
		option = new ArrayList<BurgerPart>();

		option.add(new BurgerPart("extra patties",100,2,10));
		option.add(new BurgerPart("cheese",75,3,10));
		option.add(new BurgerPart("bacon",100,5,10));
		option.add(new BurgerPart("lettuce",25,10,75));
		option.add(new BurgerPart("tomatoes",25,10,100));

		baseBurgerCost = 200;
	}
	/**
	 *@return the cost of a burger with no toppings
	 */
	public int getBaseCost()
	{
		return baseBurgerCost;
	}
	/**
	 *@param indexOfTopping The index of the topping within the menu
	 *@return the topping
	 */
	public BurgerPart getTopping(int indexOfTopping)
	{
		return option.get(indexOfTopping);
	}
	/**
	 *@param indexOfTopping The index of the topping within the menu
	 *@return the maximum amount of that topping the customer is allowed to order
	 */
	public int getLimit(int indexOfTopping)
	{
		return option.get(indexOfTopping).getLimit();
	}
	/**
	 *@param indexOfTopping The index of the topping within the menu
	 *@return the amount of the topping left
	 *
	 */
	public int getAmountLeft(int indexOfTopping)
	{
		return option.get(indexOfTopping).getStock();
	}
	/**
	 *@param userWantedAmount How many of the topping the user wants
	 *@param indexOfTopping The index of the topping in the menu
	 */
	public int amountAvailable(int userWantedAmount, int indexOfTopping)
	{
		if(userWantedAmount<=0)
		{
			System.out.println("Negative amount? I'll assume you want none.");
			return 0;
		}
		else if(getAmountLeft(indexOfTopping)==0)
		{
			System.out.println("I'm sorry. We've run out of that topping.");
			return 0;
		}
		else if(getLimit(indexOfTopping) > getAmountLeft(indexOfTopping) && userWantedAmount > getAmountLeft(indexOfTopping) )
		{
			/**
	 		*If the user wants more than whats left in the inventory, and the maximum amount the user is allowed is greater than
	 		*this number, then the user will get everything left in inventory.
			*/
			System.out.println("I'm afraid we have "+getAmountLeft(indexOfTopping)+" left. I gave you all that I could.");
			return getAmountLeft(indexOfTopping);
		}
		else if(getAmountLeft(indexOfTopping)> getLimit(indexOfTopping) && userWantedAmount > getLimit(indexOfTopping))
		{
			/**
	 		*If the user wants more than the maximum amount the user is allowed, and this maximum amount is less than whats left in
	 		*the inventory, then the user will get the maximum allowed amount.
			*/
			System.out.println("I'm afraid you're not allowed to order more than "+getLimit(indexOfTopping)+". I gave you all that I could.");
			return getLimit(indexOfTopping);
		}
		else
		{
			System.out.println(userWantedAmount + " have been added.");
			return userWantedAmount;
		}
	}
	/**
	 *@return the number of toppings in the menu
	 */
	public int getSize()
	{
		return option.size();
	}
	/**
	 *@param indexOfTopping the index of the topping in the menu
	 *@param howManyUsed how much to decrease the amount of the topping available by
	 */
	public void useTopping (int indexOfTopping, int howManyUsed)
	{
		option.get(indexOfTopping).useTopping(howManyUsed);
	}

}