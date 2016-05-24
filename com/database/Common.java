package com.database;

import java.util.Date;
import java.util.regex.Pattern;
import java.text.*;

/**
 * Class for common static methods
 * @version 1.0 10 May 2016
 */
public class Common {
	public static enum errorCode {
		NOERROR,
		COMMANDNOTFOUND,
		SUCCESS,
		EXIT,
		WRONGARGNUM,
		INVALIDSEARCHTYPE,
		WRONGARG,
		OBJNOTFOUND
	}
	
	/** Converts string to date 
	 * @param string A string to convert
	 * @return A Date object with converted date
	 */
	public static Date convertStringToDate(String string) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date(0);
		try {
			date = sdf.parse(string);
		} catch (ParseException exception) {
			
		}
		return date;
	}
	
	/** Converts date to string 
	 * @param date A date to convert to a string
	 * @return A string containing converted date
	 */
	public static String convertDateToString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String string = sdf.format(date);
		return string;
	}
	
	/** Checking if a string is a date 
	 * @param string A string to check
	 * @param noWildCard If true * symbol is allowed in date
	 * @return true if string is a date
	 */
	public static boolean checkDate(String string, boolean noWildCard) {
		boolean result = false;
		if (noWildCard) result = Pattern.matches("\\d{2}/\\d{2}/\\d{4}", string);
		else result = Pattern.matches("(\\d{2}|\\*)/(\\d{2}|\\*)/(\\d{4}|\\*)", string);
		return result;
	}
}
