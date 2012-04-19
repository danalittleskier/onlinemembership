package org.ussa.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;

public class GlobalRescueService {

	/**
	 * return US States
	 * @return
	 */
	public GRCountry getCountry(){
		List<GRCountry> countries = getCountries();
		return countries.get(1);
	}
	private List<GRCountry> countries = null;
	private List<GRCountry> getCountries() {
		if(countries == null){
			PostMethod method = new PostMethod("http://staging.globalrescue.com/api/index.cfm");
			method.addParameter("partner_guid", "E872C58A-15C5-AD6F-99DC-3C81565EA71E");
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
	
	protected Document doPost(PostMethod method){
		HttpClient client = new HttpClient();
		BufferedReader br = null;
		Document doc = null;
		
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
				StringBuilder xmlb = new StringBuilder();
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
	
	public static void main(String args[]) {

		HttpClient client = new HttpClient();
		//client.getParams().setParameter("http.useragent", "Test Client");

		//BufferedReader br = null;

		PostMethod method = new PostMethod("http://staging.globalrescue.com/api/index.cfm");
		method.addParameter("partner_guid", "E872C58A-15C5-AD6F-99DC-3C81565EA71E");
		method.addParameter("action","getStates");

		GlobalRescueService grs = new GlobalRescueService();
		
		List<GRCountry> countries = grs.getCountries();
		
		System.out.println(countries.size());
		

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

}
