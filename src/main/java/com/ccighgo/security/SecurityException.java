package com.ccighgo.security;

public class SecurityException extends Exception {
	public SecurityException(){
		
	}
	
	public SecurityException(String message){
		super(message);
	}
	
	public SecurityException(Throwable inner){
		super(inner);
	}
	
	public SecurityException(String message, Throwable inner){
		super(message, inner);
	}
	
}
