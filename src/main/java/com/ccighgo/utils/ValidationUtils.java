package com.ccighgo.utils;

import org.apache.commons.validator.EmailValidator;

import com.ccighgo.exception.ErrorCode;
import com.ccighgo.exception.ValidationException;

public class ValidationUtils {

   public static void validateRequired(String value) throws ValidationException {
      if (value == null || value.trim().equals(CCIConstants.EMPTY_DATA)) {
         throw new ValidationException(ErrorCode.MISSING_REQUIRED_VALUE, "The parameter  is required");
      }
   }

   public static String validateEmail(String emailId) {
      String validEmail = null;
      if (emailId == null || emailId.trim().equals(CCIConstants.EMPTY_DATA)) {
         throw new ValidationException(ErrorCode.MISSING_REQUIRED_VALUE, "Email id is required");
      } else if (!isValidEmailId(emailId)) {
         throw new ValidationException(ErrorCode.INVALID_EMAIL_ID, "Entered email id is an invalid email id");
      } else {
         validEmail = emailId;
      }
      return validEmail;
   }

   public static boolean isValidEmailId(String emailId) {
      EmailValidator emailValidator = EmailValidator.getInstance();
      return emailValidator.isValid(emailId);
   }

   public static boolean isValidSeasonId(String seasonId) {
      if (seasonId == null || (!seasonId.isEmpty() && Integer.valueOf(seasonId) == 0)) {
         throw new ValidationException(ErrorCode.INVALID_SEASON_ID, "season id missing or zero");
      }
      if (seasonId != null && Integer.valueOf(seasonId) == -1) {
         throw new ValidationException(ErrorCode.DUPLICATE_SEASON_NAME, "season with same name already exists, please select different name");
      }
      return true;
   }

}
