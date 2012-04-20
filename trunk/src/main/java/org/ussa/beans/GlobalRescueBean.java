package org.ussa.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import org.ussa.bl.RulesBL;
import org.ussa.bl.impl.RulesBLImpl;
import org.ussa.model.Inventory;

/**
 * This bean contains session information necessary to create a 
 * GlobalRescue account.  As it grows it may be best to refactor into 
 * subtypes
 * 
 * @author admin
 *
 */
public class GlobalRescueBean implements Serializable {

	public static final int MAXAGE = 85;

	private AccountBean accountBean;
	private RulesBL rulesBL;
	private boolean iagree = false;
	
	private Person parent2;
	private Person dependent1;
	private Person dependent2;
	private Person dependent3;
	private Person dependent4;
	
	public GlobalRescueBean(){ }
	public GlobalRescueBean(AccountBean accountBean,RulesBL rulesBL){
		this.accountBean = accountBean;
		this.rulesBL = rulesBL;
	}
	
	public AccountBean getAccountBean() {
		return accountBean;
	}

	public void setAccountBean(AccountBean accountBean) {
		this.accountBean = accountBean;
	}
	
	
	/**
	 * true if a Global Rescue product is in the cart
	 * @return
	 */
	public boolean getIsInCart(){
		CartBean cart = getAccountBean().getCartBean();
		List<LineItemBean> lineItems = cart.getLineItems(Inventory.INVENTORY_TYPE_SPONSORS);
		return lineItems.size() > 0 ? true : false;
	}
	
	/**
	 * true if age less than 85
	 * 
	 * @return
	 */
	public boolean getEligible(){
		return getAge() < MAXAGE ? true : false;
	}
	
	public Integer getAge(){
		//RulesBL rules = new RulesBLImpl();
		int age= rulesBL.getAgeForCurrentRenewSeason(accountBean.getMember().getBirthDate());
		return age;
	}

	//TODO debug
	public String getGlobalRescueMessage() {
		return "Global Rescue Memeber";
	}
	
	//TODO debug
	public BigDecimal getAmount(){
		BigDecimal retval = new BigDecimal(325.12);
		return retval;
	}
	
	public String getPolicyDescription(){
		return "Policy 325.12";
	}
	
	public boolean getiagree(){
		return this.iagree;
	}
	
	public void setiagree(boolean iagree){
		this.iagree = iagree;
	}
	
	public Person getParent2() {
		return parent2;
	}

	public void setParent2(Person parent2) {
		this.parent2 = parent2;
	}

	public Person getDependent1() {
		return dependent1;
	}

	public void setDependent1(Person dependent1) {
		this.dependent1 = dependent1;
	}

	public Person getDependent2() {
		return dependent2;
	}

	public void setDependent2(Person dependent2) {
		this.dependent2 = dependent2;
	}

	public Person getDependent3() {
		return dependent3;
	}

	public void setDependent3(Person dependent3) {
		this.dependent3 = dependent3;
	}

	public Person getDependent4() {
		return dependent4;
	}

	public void setDependent4(Person dependent4) {
		this.dependent4 = dependent4;
	}

	public RulesBL getRulesBL() {
		return rulesBL;
	}

	public void setRulesBL(RulesBL rulesBL) {
		this.rulesBL = rulesBL;
	}

	public class Person {
		private String firstName;
		private String lastName;
		private String birthMonth;
		private String birthDay;
		private String birthYear;
		
		public Person(String firstName, String lastName,
				String birthMonth,
				String birthDay,
				String birthYear){
			this.firstName = firstName;
			this.lastName = lastName;
			this.birthMonth = birthMonth;
			this.birthDay = birthDay;
			this.birthYear = birthYear;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getBirthMonth() {
			return birthMonth;
		}

		public void setBirthMonth(String birthMonth) {
			this.birthMonth = birthMonth;
		}

		public String getBirthDay() {
			return birthDay;
		}

		public void setBirthDay(String birthDay) {
			this.birthDay = birthDay;
		}

		public String getBirthYear() {
			return birthYear;
		}

		public void setBirthYear(String birthYear) {
			this.birthYear = birthYear;
		}
	}
}
