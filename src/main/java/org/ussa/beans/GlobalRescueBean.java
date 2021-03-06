package org.ussa.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.ussa.bl.RulesBL;
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

	private static final long serialVersionUID = 1L;

	public static final int MAXAGE = 85;

	private AccountBean accountBean;
	private RulesBL rulesBL;
	private boolean iagree = false;
	private List<String> messages;
	private HashMap<String,Person> people = new LinkedHashMap<String,Person>();
	static private HashMap<String,String> labels= new HashMap<String,String>();
	private String selectedProduct = "none";
	
	static {
		labels.put("parent1", "Parent 1");
		labels.put("parent2", "Parent 2");
		labels.put("dependent1", "Dependent 1");
		labels.put("dependent2", "Dependent 2");
		labels.put("dependent3", "Dependent 3");
		labels.put("dependent4", "Dependent 4");
	}
	
	public GlobalRescueBean(){ initialize();}
	public GlobalRescueBean(AccountBean accountBean,RulesBL rulesBL){
		this.accountBean = accountBean;
		this.rulesBL = rulesBL;
		initialize();
	}
	
	protected void initialize() {
		setPerson("parent1",new Person("","","","","",""));
		setPerson("parent2",new Person("","","","","",""));
		setPerson("dependent1",new Person("","","","","",""));
		setPerson("dependent2",new Person("","","","","",""));
		setPerson("dependent3",new Person("","","","","",""));
		setPerson("dependent4",new Person("","","","","",""));
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
	 * get purchased product from cart.  Assumes that cart will
	 * only have one SPONSOR type product.
	 * 
	 * @return null if nothing in cart
	 */
	public LineItemBean getPurchasedProduct(){
		CartBean cart = getAccountBean().getCartBean();
		List<LineItemBean> lineItems = cart.getLineItems(Inventory.INVENTORY_TYPE_SPONSORS);
		if(lineItems.size() == 0){
			return null;
		}
		return lineItems.get(0);
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
	
	public Person getParent1() { 
		return people.get("parent1"); 
	} 
	
	public void setParent1(Person parent1) { 
		people.put("parent1", parent1);
	}


	public Person getParent2() {
		return people.get("parent2");
	}

	public void setParent2(Person parent2) {
		people.put("parent2", parent2);
	}

	public Person getPerson(String descKey) {
		return people.get(descKey);
	}

	public void setPerson(Person dependent) {
		people.put(dependent.descKey, dependent);
	}
	
	public void setPerson(String descKey, Person person){
		person.setDescKey(descKey);
		setPerson(person);
	}
	
	public Person getDependent1() {
		return getPerson("dependent1");
	}

	public void setDependent1(Person dependent1) {
		setPerson("dependent1",dependent1);
	}

	public Person getDependent2() {
		return getPerson("dependent2");
	}

	public void setDependent2(Person dependent2) {
		setPerson("dependent2",dependent2);
	}

	public Person getDependent3() {
		return getPerson("dependent3");
	}

	public void setDependent3(Person dependent3) {
		setPerson("dependent3",dependent3);
	}

	public Person getDependent4() {
		return getPerson("dependent4");
	}

	public void setDependent4(Person dependent4) {
		setPerson("dependent4", dependent4);
	}

	public RulesBL getRulesBL() {
		return rulesBL;
	}

	public void setRulesBL(RulesBL rulesBL) {
		this.rulesBL = rulesBL;
	}
	
	public boolean getAre2People(){
		boolean parent = false;
		int slots = 0;
		for(Person person: people.values()){
			try {
				if((person.getLastName().length() > 0) && (person.getFirstName().length() > 0)){
					if(person.getDescKey().startsWith("parent")){
						parent = true;
					}
					if(++slots >= 2 && parent){
						return true;
					}
				}
			} catch (NullPointerException npe){
				continue;
			}
		}
		return false;
	}

	public List<String> getMessages() {
		return messages;
	}
	public void setMessages(List<String> messages) {
		this.messages = messages;
	}
	
	public String getSelectedProduct() {
		return selectedProduct;
	}
	public void setSelectedProduct(String selectedProduct) {
		this.selectedProduct = selectedProduct;
	}
	public boolean getIsFamilyProduct(){
		if(!getIsInCart()){
			return false;
		}
		LineItemBean purchase = getPurchasedProduct();
		if(!purchase.getInventory().getId().equals(Inventory.INV_ID_SPONSORS_FAMILY)){
			return false;
		}
		return true;
	}
	
	public List<String> getBadDateKeys(){
		List<String> retval = new ArrayList<String>(); 
		for(Person person : people.values()){
			if(!person.getIsValid()){
				continue;
			}
			if(!validDate(person.getBirthdate())){
				retval.add(labels.get(person.descKey));
			}
		}
		return retval;
	}
	
	private boolean validDate(String date){
		if(date == null){
			return false;
		}
		if(date.length() != 10){
			return false;
		}
		String [] tokens = date.split("/");
		if(tokens.length != 3){
			return false;
		}
		if(tokens[0].length() != 2){
			return false;
		}
		if(tokens[1].length() != 2){
			return false;
		}
		if(tokens[2].length() != 4){
			return false;
		}
		int month = Integer.parseInt(tokens[0]);
		if(month == 0 || month > 12){
			return false;
		}
		int day = Integer.parseInt(tokens[1]);
		if(day == 0 || day > 31){
			return false;
		}
		int year = Integer.parseInt(tokens[2]);
		if(year == 0 || year < 1900){
			return false;
		}
		return true;
	}

	public class Person implements Serializable{
		private static final long serialVersionUID = 1L;
		
		private String firstName;
		private String lastName;
		private String birthMonth;
		private String birthDay;
		private String birthYear;
		private String birthdate;
		private String descKey;
		
		@Deprecated
		public Person(String firstName, String lastName,
				String birthMonth,
				String birthDay,
				String birthYear,
				String descKey){
			this.firstName = firstName;
			this.lastName = lastName;
			this.birthMonth = birthMonth;
			this.birthDay = birthDay;
			this.birthYear = birthYear;
			this.descKey = descKey;
		}
		
		public Person(String firstName, String lastName, String birthdate, String descKey){
			this.firstName = firstName;
			this.lastName = lastName;
			this.descKey = descKey;
			this.birthdate = birthdate;
		}
		
		/**
		 * Person is considered valid if a first and last name have been entered
		 * 
		 * @return
		 */
		public boolean getIsValid(){
			if((StringUtils.isBlank(firstName)) && StringUtils.isBlank(lastName)){
				return false;
			}
			return true;
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
		
		public String getBirthdate(){
			//StringBuilder sb = new StringBuilder();
			//sb.append(birthMonth).append("/").append(birthDay).append("/").append(birthYear);
			//birthdate = sb.toString();
			return birthdate;
		}
		
		public void setBirthdate(String birthdate){
			this.birthdate = birthdate;
		}
		
		public void setDescKey(String descKey){
			this.descKey = descKey;
		}
		
		public String getDescKey(){
			return descKey;
		}
	}
}
