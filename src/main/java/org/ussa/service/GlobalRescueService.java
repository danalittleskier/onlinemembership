package org.ussa.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.ussa.beans.AccountBean;
import org.ussa.beans.LineItemBean;
import org.ussa.exception.GlobalRescueException;
import org.ussa.model.Inventory;
import org.ussa.model.MemberSeason;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;

public class GlobalRescueService {

	//private static final String PARTNERGUID = "E872C58A-15C5-AD6F-99DC-3C81565EA71F"; // bad guid to cause errors
	private static final String PARTNERGUID = "E872C58A-15C5-AD6F-99DC-3C81565EA71E";
	private static final String HTTP_STAGING_GLOBALRESCUE_COM_API_INDEX_CFM = "http://staging.globalrescue.com/api/index.cfm";
	/**
	 * return US States
	 * @return null on error
	 */
	public GRCountry getCountry(){
		List<GRCountry> countries = getCountries();
		GRCountry localCountry = null;
		if(countries != null){
			localCountry = countries.get(1);
		}
		return localCountry;
	}
	
	private List<GRCountry> countries = null;
	private List<GRCountry> getCountries() {
		if(countries == null){
			PostMethod method = new PostMethod(HTTP_STAGING_GLOBALRESCUE_COM_API_INDEX_CFM);
			method.addParameter("partner_guid", PARTNERGUID);
			method.addParameter("action","getStates");
			
			Document doc = doPost(method);
			if(doc != null){
				countries = new ArrayList<GRCountry>();
				NodeList countryNodes = doc.getElementsByTagName("country");
				for(int i = 0; i < countryNodes.getLength(); i++){
					Node countryNode = countryNodes.item(i);
					NamedNodeMap cn = countryNode.getAttributes();
					Integer id = Integer.valueOf(cn.getNamedItem("id").getNodeValue());
					GRCountry country = new GRCountry(id.intValue());
					countries.add(country);
					NodeList stateNodes = countryNode.getChildNodes();
					for(int stateI = 0; stateI < stateNodes.getLength(); stateI++){
						Node stateNode = stateNodes.item(stateI);
						NamedNodeMap sn = stateNode.getAttributes();
						Integer stateId = Integer.valueOf(sn.getNamedItem("id").getNodeValue());
						NodeList stateProperties = stateNode.getChildNodes();
						String name = null;
						String abbreviation = null;
						for(int propI = 0; propI < stateProperties.getLength(); propI++){
							Node node = stateProperties.item(propI);
							String nodeName = node.getNodeName();
							if("name".equals(nodeName)){
								name = node.getFirstChild().getNodeValue();
							} else if("abbreviation".equals(nodeName)){
								abbreviation = node.getFirstChild().getNodeValue();
							}
						}
						GRState state = new GRState(stateId.intValue(),name,abbreviation);
						country.getStates().add(state);
					}
				}
			}
		}
		return countries;
	}
	
	/**
	 * Post method, read & parse response
	 * 
	 * @param method
	 * @return null if any kind of exception
	 */
	protected Document doPost(PostMethod method){
		HttpClient client = new HttpClient();
		BufferedReader br = null;
		Document doc = null;
		
		StringBuilder xmlb = null;
		try {
			int returnCode = client.executeMethod(method);

			if (returnCode == HttpStatus.SC_NOT_IMPLEMENTED) {
				System.err
						.println("The Post method is not implemented by this URI");
				// still consume the response body
				method.getResponseBodyAsString();
			} else {
				br = new BufferedReader(new InputStreamReader(
						method.getResponseBodyAsStream()));
				String readLine;
				xmlb = new StringBuilder();
				while (((readLine = br.readLine()) != null)) {
					String dataLine = readLine.trim();
					if(dataLine.length() == 0){
						continue;
					}
					System.err.println(readLine);
					xmlb.append(dataLine);
				}
				doc = loadXMLFromString(xmlb.toString() );
				
				System.out.println(doc.toString());
				System.out.println("--------------");
				System.out.print(domString(doc.getDocumentElement(), ""));
			}
		} catch (Exception e) {
			System.err.println(e);
			System.err.print(xmlb.toString());
		} finally {
			method.releaseConnection();
			if (br != null)
				try {
					br.close();
				} catch (Exception fe) {
				}
		}
		
		return doc;
	}
	
	/**
	 * This is hardcoded for US.  Only US residents will be allowed to get to the GlobalRescue page
	 * 
	 * @param accountBean
	 * @throws GlobalRescueException 
	 */
	public void createPrepaidAccount(AccountBean accountBean) throws GlobalRescueException{
		PostMethod method = new PostMethod(HTTP_STAGING_GLOBALRESCUE_COM_API_INDEX_CFM);
		method.addParameter("partner_guid", PARTNERGUID);
		method.addParameter("action","createPrePaidAccount");
		method.addParameter("first_name",accountBean.getMember().getFirstName());
		method.addParameter("last_name",accountBean.getMember().getLastName());
		String programId = mapProductToProgramId(accountBean.getGlobalRescueBean().getPurchasedProduct());
		method.addParameter("program_id", programId);
		method.addParameter("coverage_period_id",mapProductToCoverage(accountBean.getGlobalRescueBean().getPurchasedProduct()));
		method.addParameter("telephone",accountBean.getAddress().getPhoneHome());
		method.addParameter("email_address",accountBean.getMember().getEmail());
		method.addParameter("start_date",getStartDate());
		method.addParameter("dob",accountBean.getBirthDate());
		method.addParameter("address_1", accountBean.getAddress().getDeliveryAddress());
		method.addParameter("city",accountBean.getAddress().getCity());
		method.addParameter("state_id",nullToEmpty(mapStateToGlobalRescueState(accountBean.getAddress().getStateCode())));
		method.addParameter("zip",accountBean.getAddress().getPostalCode());
		method.addParameter("country_id","1");
		method.addParameter("purchase_price",accountBean.getGlobalRescueBean().getPurchasedProduct().getInventory().getAmount().toPlainString());
		method.addParameter("reference_employee",accountBean.getMember().getId().toString());
		method.addParameter("corp_discount_code","ussaapi");
		
		Document doc = doPost(method);
		
		if(doc == null) {
			throw new GlobalRescueException("Error during post");
		} else {
			GRResult result = new GRResult(doc);
			if(result.isError()){
				List<String> details = result.getErrorDetailsList();
				accountBean.getGlobalRescueBean().setMessages(details);
				throw new GlobalRescueException(details.get(0));
			}
			else if(result.isSuccess()){
				String guid = result.getSuccessGuid();
				System.out.println("GlobalRescue added guid:" + guid + ":" + accountBean.getMember().getId());
				MemberSeason mseas = accountBean.getMemberSeason();
				//TODO test that column is persisted to DB
				mseas.setGlobalRescueGUID(guid);
			}
		}
	}
	
	/**
	 * 
	 * @param thing
	 * @return  return empty string ("") if thing is null
	 */
	private static String nullToEmpty(String thing){
		return (thing == null) ? "" : thing;
	}
	
	
	protected String mapProductToProgramId(LineItemBean lineItem){
		Inventory inventory = lineItem.getInventory();
		String invId = inventory.getId();
		if(Inventory.INV_ID_SPONSORS_INDIVIDUAL.equals(invId)){
			return "1";
		} else if (Inventory.INV_ID_SPONSORS_STUDENT.equals(invId)){
			return "9";
		} else if (Inventory.INV_ID_SPONSORS_FAMILY.equals(invId)){
			return "5";
		}
		return null;
	}
	
	protected String mapProductToCoverage(LineItemBean lineItem){
		Inventory inventory = lineItem.getInventory();
		String invId = inventory.getId();
		if(Inventory.INV_ID_SPONSORS_INDIVIDUAL.equals(invId)){
			return "1";
		} else if (Inventory.INV_ID_SPONSORS_STUDENT.equals(invId)){
			return "41";
		} else if (Inventory.INV_ID_SPONSORS_FAMILY.equals(invId)){
			return "21";
		}
		return null;
	}
	
	/**
	 * 
	 * @param stateCode
	 * @return null if any kind of error
	 */
	protected String mapStateToGlobalRescueState(String stateCode){
		if(stateCode == null){
			return null;
		}
		GRCountry us = getCountry();
		if(us == null){
			return null;
		}
		String retval = null;
		for(GRState state : us.getStates()){
			if(state.getAbbreviation().equals(stateCode)){
				retval = new Integer(state.getId()).toString();
				break;
			}
		}
		return retval;
	}
	
	/**
	 * 
	 * @return next day from signup
	 */
	protected String getStartDate(){
		long today = new Date().getTime();
		long tomorrow = today + (1000 * 60 * 60 * 24);
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		return dateFormat.format(tomorrow).toString();
	}
	
	public static void main(String args[]) throws Exception {

		HttpClient client = new HttpClient();
		//client.getParams().setParameter("http.useragent", "Test Client");

		//BufferedReader br = null;

		PostMethod method = new PostMethod(HTTP_STAGING_GLOBALRESCUE_COM_API_INDEX_CFM);
		method.addParameter("partner_guid", PARTNERGUID);
		method.addParameter("action","createPrepaidAccount");
		

		GlobalRescueService grs = new GlobalRescueService();
		
		Document doc = grs.doPost(method);
		
		GRResult result = null;
		if(doc != null) {
		
			NodeList elements = doc.getElementsByTagName("result");
			
			System.out.println(elements);
			elements.item(0).getAttributes().getNamedItem("status").getNodeValue();
			
			result = grs.new GRResult(doc);
			System.out.println("GRResult error:" + result.isError());
			List<String> errorDetails = result.getErrorDetailsList();
			for(String edetail : errorDetails){
				System.out.println(edetail);
			}
		}
		else {
			System.out.println("DOC is null");
		}
		
		String successString = "<result status=\"success\" > <account id=\"new guid\"/> <errors/> </result>";
		doc = GlobalRescueService.loadXMLFromString(successString);
		result = grs.new GRResult(doc);
		System.out.println("GRResult success:" + result.isSuccess());
		System.out.println("GRResult guid:" + result.getSuccessGuid());
		
		//List<GRCountry> countries = grs.getCountries();
		
		//System.out.println(countries.size());
		

	}
	
	public static Document loadXMLFromString(String xml) throws Exception {
		InputSource is = new InputSource(new StringReader(xml));
		DOMParser parser = new DOMParser();
		
		// set to false for small files, if set with small files increases memory and decreases performance
		parser.setFeature("http://apache.org/xml/features/dom/defer-node-expansion", false); 
		
		parser.parse(is);
		return parser.getDocument();
	}
	
	public static String domString(Node e, String tab){
		final String NL = "\n";
		StringBuilder outstring = new StringBuilder();
		if(e.getNodeType() == Node.TEXT_NODE){
			outstring.append(tab).append(" ").append(e.getNodeValue()).append(NL);
			return outstring.toString();
		}
		
		outstring.append(tab).append(e.getNodeName()).append(NL);
		
		NamedNodeMap as = e.getAttributes();
		if(as != null && as.getLength() > 0){
			outstring.append("attributes=[");
			for(int i = 0 ; i < as.getLength(); i++){
				outstring.append((i == 0)? "" : ", ").append(as.item(i));
			}
			outstring.append("]").append(NL);
		}
		outstring.append(NL);
		
		//if(e.getNodeValue() != null)
		if(e.getChildNodes().getLength() > 0){
			outstring.append(tab).append(" ").append(e.getNodeValue()).append(NL);
			
			NodeList childs = e.getChildNodes();
			for(int i = 0; i < childs.getLength(); i++){
				outstring.append(domString(childs.item(i), tab + " "));
			}
		}
		return outstring.toString();
	}
	
	public enum StateCountry {
		NON,//TODO ZTS make sure enumeration starts 0
		US,
		CANADA
	}
	
	public class GRCountry {
		private Integer id;
		private List<GRState> states = new ArrayList();
		
		public GRCountry(Integer id){
			this.id = id;
		}
		
		public int getId() {
			return id;
		}
		protected void setId(int id) {
			this.id = id;
		}
		public void setStates(List<GRState> states){
			this.states = states;
		}
		public List<GRState> getStates(){
			return states;
		}
		
	}
	
	public class GRState {
		private int id;
		private String name;
		private String abbreviation;
		
		public GRState(int id, String name, String abbreviation){
			this.id = id;
			this.name = name;
			this.abbreviation = abbreviation;
		}
		
		public int getId() {
			return id;
		}
		protected void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		protected void setName(String name) {
			this.name = name;
		}
		public String getAbbreviation() {
			return abbreviation;
		}
		protected void setAbbreviation(String abbreviation) {
			this.abbreviation = abbreviation;
		}
	}
	
	public class GRResult {
		private Document doc;
		
		public GRResult(Document doc){
			this.doc = doc;
		}
		
		public Document getDocument(){
			return doc;
		}
		
		public boolean isError(){
			NodeList elements = doc.getElementsByTagName("result");
			String status = elements.item(0).getAttributes().getNamedItem("status").getNodeValue();
			return "error".equals(status) ? true : false;
		}
		
		public boolean isSuccess(){
			NodeList elements = doc.getElementsByTagName("result");
			String status = elements.item(0).getAttributes().getNamedItem("status").getNodeValue();
			return "success".equals(status) ? true : false;
		}
		
		public String getSuccessGuid(){
			if(!isSuccess()){
				return "";
			}
			NodeList elements = doc.getElementsByTagName("account");
			String id = elements.item(0).getAttributes().getNamedItem("id").getNodeValue();
			return id;
		}
		
		public NodeList getErrorDetails() {
			NodeList elements = doc.getElementsByTagName("details");
			return elements;
		}
		
		public List<String> getErrorDetailsList() {
			List<String> retlist = new ArrayList<String>();
			NodeList elements = doc.getElementsByTagName("details");
			for(int elementsI = 0; elementsI < elements.getLength(); elementsI++){
				Node element = elements.item(elementsI);
				String detail = element.getFirstChild().getNodeValue();
				retlist.add(detail);
			}
			return retlist;
		}
	}

}
