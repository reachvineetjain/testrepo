/**
 * 
 */
package com.ccighgo.service.components.usermanagment;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.ccighgo.exception.ErrorCode;
import com.ccighgo.exception.ValidationException;

/**
 * UserManager class servers as business component for user login and logout 
 * of the system, fetching user details, roles validation etc
 * 
 * @author ravimishra
 *
 */
@Component
public class UserManager {
	
	
	public void validateRequired(String name, String value) throws ValidationException {
		if(value == null || value.trim().equals("")){
			throw new ValidationException(ErrorCode.MISSING_REQUIRED_VALUE, "The parameter '" + name + "' is required");
		}
	}

	private void validateRequired(String name, Date value) throws ValidationException {
		if(value == null){
			throw new ValidationException(ErrorCode.MISSING_REQUIRED_VALUE, "The parameter '" + name + "' is required");
		}
	}

}
