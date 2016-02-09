package com.ccighgo.service.components.sevis;

public enum EVReprintReason {
	/*
	 * Reason Code for reprint (Value: 05 = Other 12 = Lost 13 = Damaged 14 =
	 * Stolen)
	 */
	OTHER("05"), LOST("12"), DAMAGED("13"), STOLEN("14");

	private String code;

	private EVReprintReason(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

}