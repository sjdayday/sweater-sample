
package org.grayleaves.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class Clock
{
    private static Calendar CALENDAR; 
    private static boolean CLOCK_SET = false;  

    public static void setDateForTesting(String dateString)
    {
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss aa");
        Date date = null; 
        try {
            date = format.parse(dateString);
        } catch (ParseException e) {
            date = new Date(); // force it to today
            System.out.println("Couldn't parse the date, so set it to today's date.");
        }
        CALENDAR = new GregorianCalendar(); 
        CALENDAR.setTime(date); 
        CLOCK_SET = true;
    }
    public static void reset()
    {
        CLOCK_SET = false;
    }
    public static String getFormattedDateString()
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyy_MM_dd__hh_mm_ss.SSSaa");
        return format.format(getCalendar().getTime());
    }
    public static Calendar getCalendar() 
    {
        if (!CLOCK_SET)  
        {
            CALENDAR = new GregorianCalendar();
            CALENDAR.setTime(new Date()); 
        }
        return CALENDAR;
    }
	public static void sleep(int milliseconds) {
        if (CLOCK_SET)  
        {
            CALENDAR.add(Calendar.MILLISECOND, milliseconds); 
        }
        else 
        {
        	try {
				Thread.sleep(milliseconds);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
        }
		
	}
	private long startTime = 0;
	private long endTime = 0;
	private long elapsedTime = 0;
	private boolean started;
	public Clock() {
		started = false; 
	}
	public void start() {
		startTime = getCalendar().getTimeInMillis();
		started = true; 
	}
	public long stop() {
		if (started) {
			endTime = getCalendar().getTimeInMillis(); 
			elapsedTime = endTime - startTime;
		} 
		else {
			elapsedTime = 0; 
		}
		return elapsedTime;
	}
	public long getElapsedTime() {
		return elapsedTime;
	}
}
