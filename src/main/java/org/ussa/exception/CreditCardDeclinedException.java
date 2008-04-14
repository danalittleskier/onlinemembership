package org.ussa.exception;

public class CreditCardDeclinedException extends Exception
{
	public CreditCardDeclinedException(String message)
	{
		super(message);
	}
}
