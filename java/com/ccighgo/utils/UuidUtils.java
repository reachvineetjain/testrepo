package com.ccighgo.utils;

import java.util.UUID;

import javax.xml.bind.DatatypeConverter;

public class UuidUtils {

	public static String nextHexUUID () {
		byte[] uuidBytes = getGuidAsByteArray();
		
		return DatatypeConverter.printHexBinary(uuidBytes);
	}
	
	public static byte[] getGuidAsByteArray() {

		UUID uuid = UUID.randomUUID();
		long longOne = uuid.getMostSignificantBits();
		long longTwo = uuid.getLeastSignificantBits();

		return new byte[] { (byte) (longOne >>> 56), (byte) (longOne >>> 48),
				(byte) (longOne >>> 40), (byte) (longOne >>> 32),
				(byte) (longOne >>> 24), (byte) (longOne >>> 16),
				(byte) (longOne >>> 8), (byte) longOne,
				(byte) (longTwo >>> 56), (byte) (longTwo >>> 48),
				(byte) (longTwo >>> 40), (byte) (longTwo >>> 32),
				(byte) (longTwo >>> 24), (byte) (longTwo >>> 16),
				(byte) (longTwo >>> 8), (byte) longTwo };
	}
	
}
