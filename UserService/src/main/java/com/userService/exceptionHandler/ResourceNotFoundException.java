package com.userService.exceptionHandler;

public class ResourceNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException()
	{
		super("Resource Not Found on the Server");
	}
	
	public ResourceNotFoundException(String message)
	{
		super(message);
	}
}
