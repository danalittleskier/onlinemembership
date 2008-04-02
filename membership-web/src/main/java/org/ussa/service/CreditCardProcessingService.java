package org.ussa.service;

import org.ussa.beans.PaymentBean;

public interface CreditCardProcessingService
{
	public void processCard(PaymentBean paymentBean) throws Exception;
}
