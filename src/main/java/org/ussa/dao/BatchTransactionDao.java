package org.ussa.dao;

import org.ussa.beans.AccountBean;

public interface BatchTransactionDao
{
	public void insertToBatchTables(AccountBean bean);
}
