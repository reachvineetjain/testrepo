package com.ccighgo.utils;

import java.util.Random;

public class PasscodeGenerator {

   public static char[] generateRandomPasscode(Integer minLength, Integer maxLength, Integer noOfUpCases, Integer noOfDigits, Integer noOfSplChars) {
      if (minLength > maxLength)
         throw new IllegalArgumentException("Minimum password length cannot be greater than maximum password length");
      if ((noOfUpCases + noOfDigits + noOfSplChars) > minLength)
         throw new IllegalArgumentException("Minimum password should be atleast sum of (Upper case, Digits, Special character) Length");

      Random random = new Random();
      int length = random.nextInt(maxLength - minLength + 1) + minLength;
      char[] passcode = new char[length];

      Integer index = 0;
      for (int i = 0; i < noOfUpCases; i++) {
         index = getNextIndex(random, length, passcode);
         passcode[index] = CCIConstants.ALPHABETS_UPPER_CASE.charAt(random.nextInt(CCIConstants.ALPHABETS_UPPER_CASE.length()));
      }
      for (int i = 0; i < noOfDigits; i++) {
         index = getNextIndex(random, length, passcode);
         passcode[index] = CCIConstants.NUMBERS.charAt(random.nextInt(CCIConstants.NUMBERS.length()));
      }
      for (int i = 0; i < noOfSplChars; i++) {
         index = getNextIndex(random, length, passcode);
         passcode[index] = CCIConstants.SPECIAL_CHARACTERS.charAt(random.nextInt(CCIConstants.SPECIAL_CHARACTERS.length()));
      }
      for (int i = 0; i < length; i++) {
         if (passcode[i] == 0) {
            passcode[i] = CCIConstants.ALPHABETS_LOWER_CASE.charAt(random.nextInt(CCIConstants.ALPHABETS_LOWER_CASE.length()));
         }
      }
      return passcode;
   }

   private static Integer getNextIndex(Random random, Integer length, char[] passcode) {
      Integer index = random.nextInt(length);
      while (passcode[index = random.nextInt(length)] != 0);
      return index;
   }
}
