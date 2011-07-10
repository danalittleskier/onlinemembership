package org.ussa.service;

import org.ussa.beans.AccountBean;

public interface MemberRegistrationService
{
	public void processRegistration(AccountBean accountBean) throws Exception;
}
