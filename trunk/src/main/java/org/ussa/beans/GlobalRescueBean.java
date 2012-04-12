package org.ussa.beans;

import java.io.Serializable;
import java.math.BigDecimal;

public class GlobalRescueBean implements Serializable {

	public String getGlobalRescueMessage() {
		return "Global Rescue Memeber";
	}
	
	public BigDecimal getAmount(){
		BigDecimal retval = new BigDecimal(325.12);
		return retval;
	}
	
	public String getPolicyDescription(){
		return "Policy 325.12";
	}
}
