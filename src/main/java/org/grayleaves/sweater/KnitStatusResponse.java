package org.grayleaves.sweater;

public class KnitStatusResponse extends StatusResponse {


	protected static final int STITCHES_PER_YARD = 200;
	protected static KnitStatusResponseEnum COLOR = KnitStatusResponseEnum.GRAY;

	public static void color(KnitStatusResponseEnum color) {
		COLOR  = color; 
	}

	private String knitResponse = "";
	private YarnService service;

	public KnitStatusResponse() {
		super(); 
	}
	
	public void knit(int stitches) {
		int yards = (stitches / STITCHES_PER_YARD) + 1 ;
		int available = getYarn(COLOR, yards);
		int possible = getPossibleStitches(stitches, available, yards);
		if (available < yards) {
			
			knitResponse = "insufficient "+getColor()+" yarn available to knit "+stitches+" stitches; "+possible+" stitches knitted";
		}
		else {
			knitResponse = stitches+" stitches knitted using "+available+" yards of GRAY yarn";
		}
	}

	private int getPossibleStitches(int stitches, int available, int yards) {
		int possible = stitches;
		if (available < yards) {
			possible = available * STITCHES_PER_YARD;
		}
		return possible;
	}
	
	protected int getYarn(KnitStatusResponseEnum color, int yards) {
		int available = 0; 
		if (service != null) {
			available = service.getYarn(color, yards); 
		}
		return available;
	}

	public String getColor() {
		return COLOR.toString();
	}
	public void setColor(String color) {
		
	}
	public String getKnitResponse() {
		return knitResponse;
	}


	public void setKnitResponse(String knitResponse) {
		this.knitResponse = knitResponse;
	}

	public void setYarnService(YarnService yarnService) {
		this.service = yarnService; 
	}

	public static void forceColor(String color) {
		for (KnitStatusResponseEnum yarnEnum : KnitStatusResponseEnum.values()) {
			if (color.equalsIgnoreCase(yarnEnum.toString())) {
				color(yarnEnum); 
			}
		}
	}

}
