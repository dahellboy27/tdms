package com.bezkoder.spring.files.csv.helper;

public enum MastPortalType {

	Mast("01"), Portal("02"), TTC("03"), Umbrella_Mast("04"), Head_Span("05");
	
	public final String mastPortalTypeId;

	private MastPortalType(String i) {
		this.mastPortalTypeId =  i;
	}

	public String getMastPortalTypeId() {
		return mastPortalTypeId;
	}

}
