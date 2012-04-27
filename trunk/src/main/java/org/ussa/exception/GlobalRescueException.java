package org.ussa.exception;

public class GlobalRescueException extends Exception
{
	public GlobalRescueException(String message)
	{
		super(message);
	}
	
	//TODO make space to hold all of the errors that may come back from a post
	// see GlobalRescueService
}
