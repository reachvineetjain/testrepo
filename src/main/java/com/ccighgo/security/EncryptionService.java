package com.ccighgo.security;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.shiro.codec.Base64;
import org.springframework.stereotype.Component;
import java.lang.SecurityException;

@Component
public class EncryptionService {

	private static String HMAC_SHA256 = "HmacSHA256";
	private static String AES = "AES";
	
	/*
	 * A key with 16 bytes. This means we are using AES128. 
	 * To make it AES 192 increase the key bytes to 24
	 * For AES 256 the bytes of the key should be 32
	 */
	private static final byte[] keyValue = new byte[] { 'C', 'R', '3', 'O', '5', 'P', 'A', 'N', 'C', 'C', '1', 'G', 'H', 'G', '0', '1'};
	
	/**
	 * Performs HMAC-SHA256 encryption of the given string with the key provided.
	 * @param baseString - The string to be encrypted
	 * @param key - The key to be used for HMAC hashing
	 * @return - The encrypted bytes
	 * @throws UnsupportedEncodingException
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 */
	public byte[] computeSignature(String baseString, String key) throws UnsupportedEncodingException, 
			InvalidKeyException, NoSuchAlgorithmException {
		SecretKey secretKey = null;
		synchronized (this) {
			byte[] keyBytes = key.getBytes("UTF-8");
			secretKey = new SecretKeySpec(keyBytes, HMAC_SHA256);
		}
		Mac mac = Mac.getInstance(HMAC_SHA256);
		mac.init(secretKey);
		byte[] hash = mac.doFinal(baseString.getBytes("UTF-8"));
		return hash;
	}

	/**
	 * Encrypts the given password using HMAC-SHA256 encryption and subsequently base64 encodes the encrypted bytes and returns
	 * the base64 encoded password. 
	 * @param password - The password to be encrypted
	 * @return - The encrypted password
	 * @throws InvalidKeyException
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchAlgorithmException
	 */
	public String encryptAndBase64Encode(String password) throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException {
		byte[] hashed = computeSignature(password, password);
		String base64EncodedPassword = base64Encode(hashed);
		return base64EncodedPassword;
	}
	
	public String encryptAndBase64Encode(String baseString, String key) throws InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException {
		byte[] hashed = computeSignature(baseString, key);
		String base64EncodedPassword = base64Encode(hashed);
		return base64EncodedPassword;
	}
	
	
	private String base64Encode(byte[] hash) {
		String hashString = Base64.encodeToString(hash);
		return hashString;
	}
	
	private byte[] base64Decode(String encryptedData) {
		byte[] encryptedBytes = Base64.decode(encryptedData);
		return encryptedBytes;
	}
	
	/**
	 * Performs AES encryption of a given identifier. This string can be later decrypted using a common key
	 * @param identifier - The identifier to encrypt
	 * @return - The encrypted string
	 */
	public String aesEncryptAndBase64Encode(String identifier) {
        try {
        	Key key = generateKey();
			Cipher cipher = Cipher.getInstance(AES);
			cipher.init(Cipher.ENCRYPT_MODE, key);
			 byte[] encryptedBytes = cipher.doFinal(identifier.getBytes("UTF-8"));
			 String base64EncodedString = base64Encode(encryptedBytes);
			 return base64EncodedString;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			throw new SecurityException(e);
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
			throw new SecurityException(e);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
			throw new SecurityException(e);
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
			throw new SecurityException(e);
		} catch (BadPaddingException e) {
			e.printStackTrace();
			throw new SecurityException(e);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new SecurityException(e);
		}
	}
	
	/**
	 * Decrypts an AES encrypted base 64 encoded string and returns the decrypted string
	 * @param encryptedString - The encrypted string to decrypt
	 * @return - The base64 decoded and decrypted string.
	 * 
	 */
	public String aesDecryptAndBase64Decode(String encryptedString) {
		try {
			Key key = generateKey();
	        Cipher c = Cipher.getInstance(AES);
	        c.init(Cipher.DECRYPT_MODE, key);
	        byte[] encryptedBytes = base64Decode(encryptedString);
	        byte[] decryptedBytes = c.doFinal(encryptedBytes);
	        String decryptedValue = new String(decryptedBytes);
	        return decryptedValue;
		} catch(Exception e) {
			e.printStackTrace();
			throw new SecurityException(e);
		}
	}
	
	private static final int INVISIBLE_CHARACTERS = 5;
	private static final String MASK_CHARACTER = "*";
	
	/**
	 * Returns masked credit card numbers with * except the last 5 digits
	 * @param creditCardNumber
	 */
	public String maskEncryptedCreditCardNumber(String encryptedCreditCardNumber) {
		String creditCardNumber = aesDecryptAndBase64Decode(encryptedCreditCardNumber);
		return maskCreditCardNumber(creditCardNumber);
	}
	
	public String maskCreditCardNumber(String creditCardNumber) {
		// last 5 characters visible
		int invisibleCharacters  = INVISIBLE_CHARACTERS;
		
		String maskCharacter = MASK_CHARACTER;

        // mask the string leaving any visible characters
        String partToBeMasked = creditCardNumber.substring(0, creditCardNumber.length() - invisibleCharacters);                                                            
        String mask = "";                                         
        for(int i = 0; i < partToBeMasked.length(); i++) {
           mask += maskCharacter;
        }   
        // concatenate mask string with the last visible characters              
        String maskedNumber = mask + creditCardNumber.substring(creditCardNumber.length() - invisibleCharacters);      

		return maskedNumber;
	}
	
	private static Key generateKey() throws UnsupportedEncodingException {
        Key key = new SecretKeySpec(keyValue, AES);
        return key;
	}
}