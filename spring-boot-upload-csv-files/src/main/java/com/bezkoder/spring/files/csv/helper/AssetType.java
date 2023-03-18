package com.bezkoder.spring.files.csv.helper;

public enum AssetType {

	MAST("03");
	
	public final String assetType;

	private AssetType(String assetCode) {
		this.assetType =  assetCode;
	}

	public String getAssetType() {
		return assetType;
	}
	
}
