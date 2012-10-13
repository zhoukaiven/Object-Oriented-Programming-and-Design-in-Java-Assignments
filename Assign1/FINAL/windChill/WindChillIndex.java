import java.lang.Math;
/**
This class calculates the wind chill based upon temperature and field
@author Kaiven Zhou (kz2182)
*/

public class WindChillIndex
{
	private double maxTemperature;
	private double minWindSpeed;
	/**
		@param myMaxTemp maximum allowed temperature in Fahrenheit
		@param myMinWindSpeed lowest wind speed temperature in miles per hour

		Constructor. Sets values.
	*/
	public WindChillIndex(double myMaxTemp, double myMinWindSpeed)
	{
		maxTemperature = myMaxTemp;
		minWindSpeed = myMinWindSpeed;
	}
	/**
	 *Wind chills can be generated at the max temp, but cannot be generated at the lowest wind speed (must be higher).
	 *This decision reflects my interpretation of the text found on the NWS Wind chill chart.
	 *
	 *@param temperature the temperature in Fahrenheit
	 *@param windSpeed the wind speed in miles per hour
	 *@return boolean: if the inputs are valid
	 */
	public boolean validInput(double temperature, double windSpeed)
	{
		if(temperature > maxTemperature|| windSpeed <=	 minWindSpeed)
		{
			System.out.println("Windchill Temperature is only defined for temperatures at or below "+maxTemperature+"F and wind speeds above "+minWindSpeed+" mph.");
			return false;
		}
		else
			return true;
	}
	/**
		Returns the wind chill based on the NWS Wind chill chart
		@param temperature the temperature in Fahrenheit
		@param windSpeed the wind speed in miles per hour
		@return the wind chill
	*/
	public double calculate(double temperature, double windSpeed)
	{
		return (double)( 35.74 + 0.6125*temperature - 35.75*Math.pow(windSpeed, 0.16) + 0.4275*temperature*Math.pow(windSpeed, 0.16) );
	}

}
