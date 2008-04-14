package org.ussa.service;

import org.ussa.beans.AccountBean;

public interface CreditCardProcessingService
{
	public void processCard(AccountBean accountBean) throws Exception;
}
