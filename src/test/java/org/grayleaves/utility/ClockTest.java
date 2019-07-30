package org.grayleaves.utility;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.grayleaves.utility.Clock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class ClockTest 
{
	private Clock clock;
	@Before
	public void setUp()
    {
        Clock.reset();
    }
	@Test
	public void defaultsToCurrentDate() throws Exception {
		Calendar calendar = Clock.getCalendar();
		Thread.sleep(1); 
        GregorianCalendar testCalendar = new GregorianCalendar();
        assertTrue(testCalendar.after(calendar));
	}
	@Test
	public void staysSameWhenSet() throws Exception {
        Clock.setDateForTesting("10/15/2005 12:00:14 PM");
        assertEquals("2005_10_15__12_00_14.000PM",Clock.getFormattedDateString());
        Calendar calendar = Clock.getCalendar();
        Thread.sleep(1); 
        Calendar testCalendar = Clock.getCalendar();
        assertTrue("real time has advanced, but forced clock time unchanged",
        		calendar.equals(testCalendar));
        assertEquals(2005, testCalendar.get(Calendar.YEAR)); 
	}
	@Test
	public void whenSetSleepAdvancesClockWithoutRealDelay() throws Exception {
		Clock.setDateForTesting("10/15/2005 12:00:14 PM");
		Calendar calendar = Clock.getCalendar();
		assertEquals(0, calendar.get(Calendar.MILLISECOND)); 
		Clock.sleep(47); 
		assertEquals(47, calendar.get(Calendar.MILLISECOND)); 
	}
	@Test
	public void whenNotSetSleepCausesRealDelay() throws Exception {
		GregorianCalendar nowCalendar = new GregorianCalendar();
		nowCalendar.add(Calendar.MILLISECOND, 4); 

		Clock.sleep(5);
		Calendar calendar = Clock.getCalendar();
		assertTrue(calendar.after(nowCalendar));
	}
	@Test
	public void clockInstanceTracksElapsedTime() throws Exception {
		Clock.setDateForTesting("10/15/2005 12:00:14 PM");
		clock = new Clock(); 
		clock.start(); 
		Clock.sleep(5);
		long elapsed = clock.stop(); 
		assertEquals(5, elapsed);
		assertEquals(5, clock.getElapsedTime());
	}
	@Test
	public void stopWithoutStartReturnsZero() throws Exception {
		clock = new Clock(); 
		assertEquals(0, clock.getElapsedTime());
		long elapsed = clock.stop(); 
		assertEquals(0, clock.getElapsedTime());
		
	}
	@After
	public void tearDown()
	{
		Clock.reset();
	}
}
