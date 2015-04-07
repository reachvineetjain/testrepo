/**
 * 
 */
package com.ccighgo.authentication;

import java.util.List;

/**
 * @author ravimishra
 *
 */
public interface AuthenticationServices {
    
    boolean isValidUser(String username);
    
    <T> List<T> getPermissions();
    
    boolean hasPermission(String username, String resource, String type);

}
