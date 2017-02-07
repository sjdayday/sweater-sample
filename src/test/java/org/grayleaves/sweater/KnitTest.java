package org.grayleaves.sweater;

import static org.junit.Assert.*;

import org.grayleaves.utility.Clock;
import org.junit.Before;
import org.junit.Test;

public class KnitTest {


	private KnitStatusResponse status;
	@Before
	public void setUp()
    {
        Clock.reset();
        StatusResponse.forceDelay(0); 
        StatusResponse.throwExceptions(false); 
    }
	@Test
	public void colorDefaultsToGray() {
		status = new KnitStatusResponse(); 
		assertEquals("GRAY", status.getColor());
	}
	@Test
	public void severalColorsSupported() {
		KnitStatusResponse.color(KnitStatusResponseEnum.WHITE);
		status = new KnitStatusResponse(); 
		assertEquals("WHITE", status.getColor());
	}
	@Test
	public void knittingDefaultsToNoYarn() {
		status = new KnitStatusResponse(); 
		status.knit(1);
		assertEquals("insufficient GRAY yarn available to knit 1 stitches; 0 stitches knitted", status.getKnitResponse());
	}
	@Test
	public void yarnServiceCanBeProvided() {
		status = new KnitStatusResponse(new TestingYarnService(KnitStatusResponseEnum.GRAY, 10)); 
		status.knit(1);
		assertEquals("1 stitches knitted using 1 yards of GRAY yarn", status.getKnitResponse());
	}
	
	@Test
	public void knittingWithYarnReturnsStitchesKnitted() {
		status = new KnitStatusResponse(new TestingYarnService(KnitStatusResponseEnum.GRAY, 10)); 
		status.knit(150);
		assertEquals("150 stitches knitted using 1 yards of GRAY yarn", status.getKnitResponse());
	}
	@Test
	public void knittingMoreThanStitchesPerYardUsesAnotherYard() {
		status = new KnitStatusResponse(new TestingYarnService(KnitStatusResponseEnum.GRAY, 10)); 
		status.knit(250); 
		assertEquals("stitches per yard = "+KnitStatusResponse.STITCHES_PER_YARD,
				"250 stitches knitted using 2 yards of GRAY yarn", status.getKnitResponse());
	}
	
}
