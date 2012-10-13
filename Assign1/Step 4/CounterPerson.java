/**
 *@author Kaiven Zhou (kz2182)
 *Takes in the users input
 */
import java.util.ArrayList;
import java.util.Scanner;

public class CounterPerson
{
	/**
	 *Class Constructor
	 */
	public CounterPerson()
	{
	}
	/**
	 *Gets the user's input, and creates burgers based on the input.
	 */
	public void placeOrder(Menu myMenu, Cook myCook)
	{
		Scanner myScanner = new Scanner(System.in);

		Order userOrder = new Order();

		boolean wantToOrder = true;
		while(wantToOrder)
		{
			myCook.newBurger();
			System.out.println("Order your burger by indicating how many of the topping you want.");
			System.out.println("Enter 'next' to stop adding toppings to your current burger.");
			System.out.format("A plain burger with no toppings is $%.2f.\n",myMenu.getBaseCost()/100.0);

			/**
			 *Iterate through every topping in myMenu. Ask the user how many of the topping they want. If this number is wihin
			 *restrictions, the value is sent to the myCook and the topping is added to the burger.
			 */
			int reply=-1;
			BurgerPart possibleTopping;
			for(int i=0; i < myMenu.getSize(); i++)
			{
				possibleTopping = myMenu.getTopping(i);

				System.out.format("How much/many " + possibleTopping.getName() + " would you like? (Each cost $ %.2f. You cannot order more than " + myMenu.getLimit(i) + ").\n", possibleTopping.getCost()/100.0 );

				try
				{
					reply = myScanner.nextInt();

					int howManyViable = myMenu.amountAvailable(reply,i);
					if(howManyViable != 0)
						myCook.addToBurger(i,howManyViable);
				}
				catch(Exception e)
				{
					/**
	 				*If the user enters "next" the the for loop ends because the iterator is increased.
	 				*Else, the scanner is cleared (because it currently contains an int)
					*/
					if(myScanner.nextLine().equals("next"))
						i += myMenu.getSize();
					else
						System.out.println("Not a valid response. I'll assume you want none.");
				}
			}

			userOrder.addToOrder(myCook.getBurger(), myCook.getCost() );
			userOrder.displayCurrentOrder();

			System.out.println("Would you like to order another burger? '1' for yes. '0' for no.");
			try
			{
				reply = myScanner.nextInt();

				if(reply==1)
					wantToOrder = true;
				else if(reply==0)
				{
					wantToOrder = false;
					System.out.format("Your total comes out to: $%.2f.\n" ,userOrder.getTotalCost()/100.0);
					System.out.println("Thank you, and have a great day!");
				}

			}
			catch(Exception e)
			{
				wantToOrder = false;
				System.out.format("I'll take that as a no. Your total comes out to: $%.2f.\n" ,userOrder.getTotalCost()/100.0);
				System.out.println("Thank you, and have a great day!");
			}
		}

	}
}