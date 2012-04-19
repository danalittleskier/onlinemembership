package org.ussa.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;

public class GlobalRescueService {

	public static void main(String args[]) {

		HttpClient client = new HttpClient();
		client.getParams().setParameter("http.useragent", "Test Client");

		BufferedReader br = null;

		PostMethod method = new PostMethod("http://staging.globalrescue.com/api/index.cfm");
		method.addParameter("partner_guid", "E872C58A-15C5-AD6F-99DC-3C81565EA71E");
		method.addParameter("action","getStates");

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
				Document doc = loadXMLFromString(xmlb.toString() );
				
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

	}
	
	public static Document loadXMLFromString(String xml) throws Exception {
		//DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		//DocumentBuilder builder = factory.newDocumentBuilder();
		InputSource is = new InputSource(new StringReader(xml));
		DOMParser parser = new DOMParser();
		
		// set to false for small files, if set with small files increases memory and decreases performance
		parser.setFeature("http://apache.org/xml/features/dom/defer-node-expansion", false); 
		
		parser.parse(is);
		//return builder.parse(is);
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

}
