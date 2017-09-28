package org.ussa.service;

import org.ussa.beans.AccountBean;
import org.ussa.beans.GlobalRescueBean;
import org.ussa.beans.GlobalRescueBean.Person;
import org.ussa.beans.LineItemBean;
import org.ussa.exception.GlobalRescueException;
import org.ussa.model.Inventory;
import org.ussa.model.MemberSeason;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.lang.StringUtils;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.net.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.text.DateFormat;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class GlobalRescueServiceNewVersion {
    private static Log log = LogFactory.getLog(GlobalRescueBean.class);
    private static final DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	private static final String PARTNERGUID = "0fde1d01-1806-e211-9be9-000c2972e8b4";
    private static final String USERNAME = "ussa@globalrescue.com";
    private static final String PASSWORD = "ussaapi2017";
	private static final String HTTP_STAGING_GLOBALRESCUE_COM_API_INDEX_VALIDATE= "https://apitest.globalrescue.com/grapi/api/v2/packages/";
	private static final String HTTP_STAGING_GLOBALRESCUE_COM_API_INDEX_RENEW="https://apitest.globalrescue.com/grapi/api/v2/membership/postpaid/renew";
	private static final String HTTP_STAGING_GLOBALRESCUE_COM_API_INDEX_NEW="https://apitest.globalrescue.com/grapi/api/v2/membership/postpaid/new";

	public static String getParamsString(Map<String, String> params) throws UnsupportedEncodingException {
	        StringBuilder result = new StringBuilder();
	
	        for (Map.Entry<String, String> entry : params.entrySet()) {
	            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
	            result.append("=");
	            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
	            result.append("&");
	        }
	
	        String resultString = result.toString();
	        return resultString.length() > 0 ? resultString.substring(0, resultString.length() - 1) : resultString;
	    }
	

	public static void sendingPostRequestMembership(AccountBean accountBean) throws Exception{
		URL url = new URL("");
		if(accountBean.getMemberSeason().getGlobalRescueGUID().isEmpty()){
			url = new URL(HTTP_STAGING_GLOBALRESCUE_COM_API_INDEX_NEW);
		}
		else{
			url = new URL(HTTP_STAGING_GLOBALRESCUE_COM_API_INDEX_RENEW);
		}
		HttpURLConnection con = (HttpURLConnection) url.openConnection();	

		Date date = new Date();
		String gender = accountBean.getMember().getGender();
		if (gender.equalsIgnoreCase("M")){
			gender = "Male";
		}
		else{
			gender = "Female";
		}
		
		JSONObject obj = new JSONObject();
        obj.put("firstName", accountBean.getMember().getFirstName());
        obj.put("lastName", accountBean.getMember().getLastName());
        obj.put("email", accountBean.getMember().getEmail());
        obj.put("membershipStartDate", sdf.format(date));
        obj.put("dob", sdf.format(accountBean.getMember().getBirthDate()));
        obj.put("primaryPhone", accountBean.getAddress().getPhoneHome());
        obj.put("satellitePhone", "");
        obj.put("gender", gender);
        obj.put("packageCode", "1110299");
        obj.put("referralCode", "USSAAPI20");
        if(!accountBean.getMemberSeason().getGlobalRescueGUID().isEmpty()){
        	obj.put("memberId", accountBean.getMemberSeason().getGlobalRescueGUID());
        }
        //Add address as another object
        JSONObject obj2 = new JSONObject();
        obj2.put("lineOne", accountBean.getAddress().getDeliveryAddress());
        obj2.put("lineTwo", "");
        obj2.put("city", accountBean.getAddress().getCity());
        obj2.put("state", accountBean.getAddress().getStateCode());
        obj2.put("country", accountBean.getAddress().getCountry());
        obj2.put("zipCode", accountBean.getAddress().getPostalCode());
        obj.put("mailingAddress", obj2);
        
        JSONObject obj3 = new JSONObject();
        obj3.put("lineOne", accountBean.getAddress().getDeliveryAddress());
        obj3.put("lineTwo", "");
        obj3.put("city", accountBean.getAddress().getCity());
        obj3.put("state", accountBean.getAddress().getStateCode());
        obj3.put("country", accountBean.getAddress().getCountry());
        obj3.put("zipCode", accountBean.getAddress().getPostalCode());
        obj.put("residenceAddress", obj3);
                	
        JSONArray familyList = new JSONArray();
        JSONObject objFamily = new JSONObject();
        objFamily.put("firstName", "");
        objFamily.put("lastName", "");
        objFamily.put("gender", "");
        objFamily.put("relationship","");
        objFamily.put("dob", "");
        objFamily.put("email", "");
        familyList.add(objFamily);
        obj.put("familyMembers", familyList);
        
        try{
        
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
         
		con.setRequestProperty("X_USERNAME", USERNAME);
		con.setRequestProperty("X_PASSWORD", PASSWORD);
		con.setRequestProperty("X_PARTNER_GUID", PARTNERGUID);
		
        log.warn("before sending json string" +obj.toJSONString());
		// Send post request
		  con.setDoOutput(true);
		  DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		  wr.writeBytes(obj.toJSONString());
		  wr.flush();
		  wr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
		  
		  int responseCode = con.getResponseCode();
		  log.warn("Sending post request : "+ url);
		  log.warn("Response code : "+ responseCode);
		  
		// Reading response from input Stream
		  	InputStreamReader inputStr;
		  	if(responseCode >= 400){
		  		inputStr = new InputStreamReader(con.getErrorStream());
		  	}
		  	else{
		  		inputStr = new InputStreamReader(con.getInputStream());
		  	}
			BufferedReader in = new BufferedReader(inputStr);
		
			String output;
			StringBuffer response = new StringBuffer();
			while ((output = in.readLine()) != null) {
				response.append(output);
			}
			in.close();

			
			JSONParser parser = new JSONParser();
			Object obj5 = (JSONObject) parser.parse(response.toString());

			//printing result from response
			log.warn("response to string" +response.toString());
			
	        JSONObject jsonDetail = (JSONObject) obj5;
	        //log.warn(jsonObject);
	        if(responseCode == 200){
	        	JSONObject detail = (JSONObject) jsonDetail.get("detail");
	        	String memberID = (String) detail.get("memberId");

	        	log.warn("Member Id" + memberID);
	        }
	        else{
	        	 JSONArray errors = (JSONArray) jsonDetail.get("errors");
	             
	             for (int i = 0; i < errors.size(); i++) {
	             	log.warn(((JSONObject) errors.get(i)).get("code").toString() +" "+ ((JSONObject) errors.get(i)).get("message").toString());


	                 }
	        }
	        
	        con.disconnect();
		  
	}
	
	// HTTP GET request
	public static void sendingGetRequest() throws Exception {
		URL url = new URL(HTTP_STAGING_GLOBALRESCUE_COM_API_INDEX_VALIDATE);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		
		con.setRequestProperty("X_USERNAME", USERNAME);
		con.setRequestProperty("X_PASSWORD", PASSWORD);
		con.setRequestProperty("X_PARTNER_GUID", PARTNERGUID);

		int responseCode = con.getResponseCode();
		log.warn("Sending get request : "+ url);
		log.warn("Response code : "+ responseCode);

		// Reading response from input Stream
		
		BufferedReader in = new BufferedReader(
				new InputStreamReader(con.getInputStream()));
		String output;
		StringBuffer response = new StringBuffer();

		while ((output = in.readLine()) != null) {
			response.append(output);
		}
		in.close();

		//printing result from response
		//log.warn(response.toString());
		
		
		JSONParser parser = new JSONParser();
		Object obj = (JSONObject) parser.parse(response.toString());

        JSONObject jsonObject = (JSONObject) obj;
        //log.warn(jsonObject);
        JSONObject detail = (JSONObject) jsonObject.get("detail");
        		
        String statusMsg = (String) jsonObject.get("statusMessage");
        log.warn(statusMsg);

     // loop array of insurance packages
        JSONArray packages = (JSONArray) detail.get("packages");
        
        for (int i = 0; i < packages.size(); i++) {
        	log.warn(((JSONObject) packages.get(i)).get("name").toString() +" "+ ((JSONObject) packages.get(i)).get("packageCode").toString());


            }
            
        /**Iterator<String> iterator = packages.iterator();
        while (iterator.hasNext()) {
            log.warn(iterator.next());
        }
        **/
		con.disconnect();
	 }
	
	public void createPrepaidAccount(AccountBean accountBean) throws GlobalRescueException{
		
	}
		   
		 public static void main(String args[]) throws Exception {
		 }
	
	
	}