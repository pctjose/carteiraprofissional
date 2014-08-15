package org.apm.carteiraprofissional.utils;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @author Helio.Machabane
 * <p>It helps us to calculate age based on the birthdate
 *    We wont to make sure no one below 18 gets registered</p>
 */
public class AgeHelper {

	/**
	 * Month representations
	 */
	public static final int JAN = 0;
	public static final int FEB = 1;
	public static final int MAR = 2;
	public static final int APR = 3;
	public static final int MAY = 4;
	public static final int JUN = 5;
	public static final int JUL = 6;
	public static final int AUG = 7;
	public static final int SEP = 8;
	public static final int OCT = 9;
	public static final int NOV = 10;
	public static final int DEC = 11;
	
	/**
	*
	* @param year birth year
	* @param month birth month
	* @param day birth day
	* @return calculated age
	*/
	public static int calculateMyAge(int year, int month, int day) {
	Calendar birthCal = new GregorianCalendar(year, month, day);
	 
	Calendar nowCal = new GregorianCalendar();
	 
	int age = nowCal.get(Calendar.YEAR) - birthCal.get(Calendar.YEAR);
	 
	boolean isMonthGreater = birthCal.get(Calendar.MONTH) >= nowCal.get(Calendar.MONTH);
	 
	boolean isMonthSameButDayGreater = birthCal.get(Calendar.MONTH) == nowCal.get(Calendar.MONTH)
	&& birthCal.get(Calendar.DAY_OF_MONTH) > nowCal.get(Calendar.DAY_OF_MONTH);
	 
	if (isMonthGreater || isMonthSameButDayGreater) {
		age=age-1;
	}
	
		return age;
	}
	
	
	public static void main(String[] args) {
		
	System.out.println("Born In 1983-NOV-11. Current Age: "+ calculateMyAge(1983, NOV, 23));
	}
}
