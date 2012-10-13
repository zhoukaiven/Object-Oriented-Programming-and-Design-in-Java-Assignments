/**
 *@author Kaiven Zhou kz2182
 *Keeps track of the time of day in hours, minutes, seconds, and am or pm
 */
public class TimeOfDay
{
	/**
	 *The number of hours
	 */
	private int hours;
	/**
	 *The number of minutes
	 */
	private int minutes;
	/**
	 *The number of seconds
	 */
	private int seconds;
	/**
	 *Which half of the day, where "a" represents AM and "p" represents PM
	 */
	private String amOrPm;

	/**
	 *Constructs a TimeOfDay object from a specified hour, minute, second, and half of day.
	 *Hours must be from 1 to 12, minutes and seconds from 0 to 59, amOrPm as "a" or "p"
	 *@param nextHours the hours
	 *@param nextMin the minutes
	 *@param nextSeconds the seconds
	 *@param whichHalf which half of the day
	 */
	public TimeOfDay(int nextHours, int nextMin, int nextSeconds, String whichHalf)
	{
		if(1<= nextHours && nextHours<=12 && 0<=nextMin && nextMin <= 59 && 0<=nextSeconds && nextSeconds<=59)
		{
			if(!whichHalf.equals("a")&&!whichHalf.equals("p"))
			{
				throw new IllegalArgumentException("Hours must be from 1 to 12, minutes and seconds from 0 to 59, amOrPm as \"a\" or \"p\".");
			}
			else
			{
				hours = nextHours;
				minutes = nextMin;
				seconds = nextSeconds;
				amOrPm = whichHalf;
			}
		}
		else
		{
			throw new IllegalArgumentException("Hours must be from 1 to 12, minutes and seconds from 0 to 59, amOrPm as \"a\" or \"p\".");
		}

	}
	/**
	 *Adds seconds to the current time, and changes the hours,minutes,seconds, and half of day accordingly
	 *@param addSeconds the number of seconds
	 *@return a new TimeOfDay object which has the new time
	 */
	public TimeOfDay addSeconds(int addSeconds)
	{
		TimeOfDay result;

		int resultHours=hours;
		int resultMinutes=minutes;
		int resultSeconds=seconds+addSeconds;
		String whichHalf = amOrPm;

		if(resultSeconds>60)
		{
			resultMinutes++;
			resultSeconds-= 60;
		}
		else if(resultSeconds<0)
		{
			resultMinutes--;
			resultSeconds+=60;
		}

		if(resultMinutes>60)
		{
			resultHours++;
			resultMinutes-=60;
		}
		else if(resultMinutes<0)
		{
			resultHours--;
			resultMinutes+=60;
		}

		if(resultHours>12)
		{
			resultHours-=12;
			if(whichHalf.equals("a"))
				whichHalf="p";
			else
				whichHalf="a";
		}
		else if(resultHours<1)
		{
			resultHours+=12;
			if(whichHalf.equals("a"))
				whichHalf="p";
			else
				whichHalf="a";
		}

		result = new TimeOfDay(resultHours,resultMinutes,resultSeconds,whichHalf);
		return result;
	}
	/**
	 *Returns how many seconds it takes for this time to reach the other
	 *@return how many seconds it takes for this time to reach the other
	 */
	public int secondsFrom(TimeOfDay other)
	{
		int diffHours = other.hours - hours;
		int diffMinutes = other.minutes - minutes;
		int diffSeconds = other.seconds - seconds;

		if(amOrPm.equals(other.amOrPm))
			return diffHours*3600+diffMinutes*60+diffSeconds;
		else
		{
			if(other.amOrPm.equals("p"))
				diffHours+=12;
			else
				diffHours-=12;

			return diffHours*3600+diffMinutes*60+diffSeconds;
		}
	}
}