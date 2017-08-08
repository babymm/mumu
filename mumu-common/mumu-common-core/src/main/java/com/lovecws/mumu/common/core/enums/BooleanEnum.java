package com.lovecws.mumu.common.core.enums;

public enum BooleanEnum {

	/**
	 * 0
	 */
	FALSE, 
	
	/**
	 * 1
	 */
	TRUE;

	@Override
	public String toString() {
		return String.valueOf(super.ordinal());
	}
}
