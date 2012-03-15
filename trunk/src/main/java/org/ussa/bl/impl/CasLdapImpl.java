package org.ussa.bl.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.ussa.beans.AccountBean;
import org.ussa.beans.UserBean;
import org.ussa.bl.CasLdap;
import org.ussa.ldap.UssaLdap;
import org.ussa.util.StringUtils;

public class CasLdapImpl implements CasLdap {

    private UssaLdap ussaLdap;
    private String currentMemberGroupName;
    private String currentSafeSportGroupName;
    
    private static String DATE_FORMAT = "MM/dd/yyyy";
    private static SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);

    /*
     * (non-Javadoc)
     * 
     * @see org.ussa.bl.impl.CasLdap#getUserInfo(javax.servlet.http.HttpSession)
     */
    public UserBean getUserInfo(String username) {

	UserBean userBean = new UserBean();

	// Get user info from ldap
	Map<String, String> user = ussaLdap.getUser(username);

	// Put values returned from ldap into accountsProfileBean
	userBean.setUsername(user.get("uid"));

	// Profile Bean
	userBean.setFirstName(user.get("givenName"));
	userBean.setLastName(user.get("sn"));
	userBean.setEmail(user.get("mail"));
	userBean.setZipCode(user.get("zipCode"));
	userBean.setBirthDate(user.get("birthDate"));

	// Main USSA Id Field
	if (user.containsKey("ussaId")) {
	    userBean.setUssaId(user.get("ussaId"));
	}

	// Any Alternate USSA Ids associated with account
	if (user.containsKey("ussaIdAlternates")) {
	    userBean.setUssaIdAlternates(user.get("ussaIdAlternates"));
	}

	return userBean;
    }

    public boolean doesIdBelongToAccount(String id, UserBean userBean) {

	// Does the account even have ussa id's associated with it
	if (StringUtils.isBlank(userBean.getUssaId()) && StringUtils.isBlank(userBean.getUssaIdAlternates()))
	    return false; // no it does not; return

	// Is the id passed in the primary ussa id field
	if (StringUtils.isNotBlank(userBean.getUssaId())) {
	    if (userBean.getUssaId().contains(id)) { // ussa id is in primary field
		return true; // id is found in primary ussa id field
	    }
	}

	// Is the id passed in the ussa id alternates field
	if (StringUtils.isNotBlank(userBean.getUssaIdAlternates())) {
	    if (userBean.getUssaIdAlternates().contains(id)) {
		return true; // Id found in alternates field, return true
	    }
	}

	// Id was not found with this account, return false
	return false;
    }
    
   // public void addUssaIdToSafeSportGroup(UserBean userBean, AccountBean accountBean, String ussaId)

    public void addUssaIdToAccount(UserBean userBean, AccountBean accountBean, String ussaId) {

	// Is the ussa id already in the ussa id field
	if (StringUtils.isNotBlank(userBean.getUssaId())) {
	    if (userBean.getUssaId().contains(ussaId)) { // ussa id is in primary field
		// We know for sure that the renewed member is the primary member
		// on the account in ldap - lets give them the currentMemberGroup
		// that comes from the context.xml so it can be easily changed
		List<String> memberGroups = new ArrayList<String>();
		memberGroups.add(currentMemberGroupName);
		addGroupsToUser(userBean.getUsername(), memberGroups);
		if(accountBean.isNeedsSafeSportCourse()){
			memberGroups.add(currentSafeSportGroupName);
			addGroupsToUser(userBean.getUsername(),memberGroups);
		}
		return; // id is found in primary ussa id field; no need to add it
	    }
	}

	// Is the ussa id already in the ussa id alternates field
	if (StringUtils.isNotBlank(userBean.getUssaIdAlternates())) {
	    if (userBean.getUssaIdAlternates().contains(ussaId)) {
		return; // Id found in alternates field; no need to add it
	    }
	}
	
	// See if primary ussa id field is blank
	// If it already contains an id, move on to DEFAULT:
	if (StringUtils.isBlank(userBean.getUssaId())) {
	    // Compare to the ldap account
	    if (userBean.getLastName().equalsIgnoreCase(accountBean.getMember().getLastName()) && 
		    userBean.getBirthDate().equals(accountBean.getBirthDate()) &&		    
		    userBean.getZipCode().equals(accountBean.getAddress().getPostalCode())) {
		
		Map<String, String> updateUser = ussaLdap.update(userBean.getUsername(), "ussaId", ussaId);

		// If there was an error during update process, print message to the logs
		if (updateUser.containsKey("error"))
		    System.out.println(updateUser.get("error"));

		// We know for sure that the renewed member is the primary member
		// on the account in ldap - lets give them the currentMemberGroup
		// that comes from the context.xml so it can be easily changed
		List<String> memberGroups = new ArrayList<String>();
		memberGroups.add(currentMemberGroupName);
		addGroupsToUser(userBean.getUsername(), memberGroups);
		if(accountBean.isNeedsSafeSportCourse()){
			memberGroups.add(currentSafeSportGroupName);
			addGroupsToUser(userBean.getUsername(),memberGroups);
		}

		// We have to do this so no more code is executed
		return;

	    }
	}

	// DEFAULT fallback - only get to this is the ussa id is not in any field or their is already something in the primary ussa id field
	String ussaIdAlts = (StringUtils.isNotBlank(userBean.getUssaIdAlternates()) ? userBean.getUssaIdAlternates() : "") + ussaId + ",";

	Map<String, String> updateUser = ussaLdap.update(userBean.getUsername(), "ussaIdAlternates", ussaIdAlts);

	// If there was an error during update process, print message to the logs
	if (updateUser.containsKey("error"))
	    System.out.println(updateUser.get("error"));

	return;
    }

    public void addGroupsToUser(String username, List<String> groups) {
	Map<String, String> addGroups = ussaLdap.addGroupsToUser(username, groups);

	// If there was an error during update process, print message to the logs
	if (addGroups.containsKey("error"))
	    System.out.println(addGroups.get("error"));
    }

    public void setUssaLdap(UssaLdap ussaLdap) {
	this.ussaLdap = ussaLdap;
    }

    public void setCurrentSafeSportGroupName(String currentSafeSportGroupName) {
		this.currentSafeSportGroupName = currentSafeSportGroupName;
	}

	public void setCurrentMemberGroupName(String currentMemberGroupName) {
	this.currentMemberGroupName = currentMemberGroupName;
    }
}
