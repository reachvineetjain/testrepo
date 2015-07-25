package com.ccighgo.security;

import org.apache.shiro.SecurityUtils;

/**
 * 
 * Utility class to perform authorization checks
 *
 */
public class AuthorizationUtility {
	
	/**
	 * Checks if the logged in user is a admin
	 * @param loggedInUser
	 * @return
	 */
	public static boolean isUserAdmin(String loggedInUser) {
		//TODO - Get the role from Enum
		return SecurityUtils.getSubject().hasRole("admin");
	}
	
	/**
	 * Checks if the logged in user can access the account
	 * @param cciaccount - The cciaccount to be accessed
	 * @return - true/false based on the access priviliges
	 */
	public static boolean canAccessAccount(String cciaccount) {
		String loggedInUser = SecurityUtils.getSubject().getPrincipal().toString();
		if(!cciaccount.equals(loggedInUser)) {
			return false;
		}
		return true;
	}
	
	public static void validateRole(String cciaccount) {/*
		if(!AuthorizationUtility.canAccessAccount(cciaccount)) {
			throw new CcighgoServiceException(ErrorCode.SUPPORT_INVALID_ROLE, "Not authorized to access account!!");
		}
	*/}

}
