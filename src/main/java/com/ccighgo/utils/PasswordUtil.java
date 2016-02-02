/**
 * 
 */
package com.ccighgo.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.ccighgo.exception.ErrorCode;
import com.ccighgo.exception.ValidationException;

/**
 * @author ravi
 *
 */
public class PasswordUtil {

   public static String hashKey(String input) {
      if (input == null || input.isEmpty()) {
         throw new ValidationException(ErrorCode.INVALID_VERIFICATION_CODE, "password cannot be null or empty");
      }
      // check if length of password is less than 8 chars
      if (input.length() < 8) {
         throw new ValidationException(ErrorCode.INVALID_VERIFICATION_CODE, "password cannot be less than 8 characters");
      }
      String hashedkey = null;
      try {
         MessageDigest md = MessageDigest.getInstance("SHA-1");
         md.update(input.getBytes());
         byte[] bytes = md.digest();
         StringBuilder sb = new StringBuilder();
         for (int i = 0; i < bytes.length; i++) {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
         }
         hashedkey = sb.toString();
      } catch (NoSuchAlgorithmException e) {
         e.printStackTrace();
      }
      return hashedkey;
   }

}
