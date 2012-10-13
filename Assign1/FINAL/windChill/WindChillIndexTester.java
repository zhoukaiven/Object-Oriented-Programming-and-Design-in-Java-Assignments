import java.lang.Double;
import java.util.Scanner;
/**
Takes user input and returns the wind chill.
@author Kaiven Zhou (kz2182)
*/
public class WindChillIndexTester
{
	public static void main (String[] args)
	{
		Scanner myScanner = new Scanner(System.in);
		WindChillIndex NWSchart = new WindChillIndex(50,3);

		double windChill;
		double temperature;
		double windSpeed;

		boolean keepAsking = true;

		while(keepAsking)
		{
			System.out.println("Enter temperature (F) then wind speed (mph). Enter 'done' to exit.");
			try
			{
				temperature = myScanner.nextDouble();
				windSpeed = myScanner.nextDouble();

				if(NWSchart.validInput(temperature,windSpeed))
				{
					windChill = NWSchart.calculate(temperature,windSpeed);
					System.out.println(windChill);
				}
			}
			catch(Exception e)
			{
				if(myScanner.nextLine().equals("done"))
				{
					System.out.println("Goodbye.");
					keepAsking = false;
				}
				else
					System.out.println("Invalid command line arguments. Should be two doubles.");
			}
		}

	}
}
