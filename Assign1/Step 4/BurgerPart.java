/**
 *@author Kaiven Zhou (kz2182)
 *
 *Contains the name and cost of the topping
 */
public class BurgerPart
{
	/**
	 *The name of the topping
	 */
	String name;
	/**
	 *The cost of the topping
	 */
	int cost;
	/**
	 *The maximum amount a user can purchase per burger
	 */
	int limit;
	/**
	 *The amount the store has left
	 */
	int stock;

	/**
	 *Class Constructor
	 *@param topping The name of the BurgerPart (string)
	 *@param myCost The cost of the BurgerCost (int)
	 *@param maxPurchaseAmount The maximum amount a customer can purchase per burger (int)
	 *@param inventory The amount of the topping left (int)
	 */
	public BurgerPart(String topping, int myCost, int maxPurchaseAmount, int inventory)
	{
		name = topping;
		cost = myCost;
		limit = maxPurchaseAmount;
		stock = inventory;
	}
	/**
	 *@return The name (string) of the BurgerPart
	 */
	public String getName()
	{
		return name;
	}
	/**
	 *@return The cost (int) of the BurgerPart
	 */
	public int getCost()
	{
		return cost;
	}
	/**
	 *@return The maximum amount of the topping that can be ordered per burger (int)
	 */
	public int getLimit()
	{
		return limit;
	}
	/**
	 *@return The amount of BurgerPart left (int)
	 */
	public int getStock()
	{
		return stock;
	}
	/**
	 *@param howMany How many of the BurgerPart are used
	 */
	public void useTopping(int howMany)
	{
		stock -= howMany;
	}
}