public class TimeOfDayTester
{
	public static void main(String[] args)
	{
		TimeOfDay test = new TimeOfDay(1,10,30,"a");
		TimeOfDay test2 = new TimeOfDay(1,10,0,"a");
		TimeOfDay test3 = new TimeOfDay(1,11,30,"a");
		TimeOfDay test4 = new TimeOfDay(1,10,20,"p");

		System.out.println(test.secondsFrom(test2));
		System.out.println(test.secondsFrom(test3));
		System.out.println(test.secondsFrom(test4));
	}
}