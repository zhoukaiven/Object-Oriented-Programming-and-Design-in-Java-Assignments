/**
 *@author Kaiven Zhou kz2182
 *Calculates the number of days that has elasped since Bieber's birth.
 */
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DaysSinceBieber
{
	/**
	 *Today's date
	 */
	private GregorianCalendar today;
	/**
	 *Bieber's birthday
	 */
	private final GregorianCalendar bieberBirthday= new GregorianCalendar(1994,Calendar.MARCH,1);

	public DaysSinceBieber()
	{
		today = new GregorianCalendar();
	}
	/**
	 *Gets the number of days that have elapsed since Bieber's birthday
	 *@return the number of days that have elapsed since Bieber's birthday
	 */
	public int getDaysSince()
	{
		int daysSince = 0;
		GregorianCalendar iterator = bieberBirthday;
		while(iterator.get(Calendar.YEAR)!=today.get(Calendar.YEAR))
		{
			if(iterator.isLeapYear(iterator.get(Calendar.YEAR)))
			{
				daysSince++;
			}

			iterator.add(Calendar.YEAR,1);
			daysSince += 365;
		}

		daysSince+=today.get(Calendar.DAY_OF_YEAR);
		daysSince-=bieberBirthday.get(Calendar.DAY_OF_YEAR);
		return daysSince;
	}
}
