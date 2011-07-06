package org.ussa.bl.impl;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.ussa.beans.AccountBean;
import org.ussa.bl.EmailUtility;

public class EmailUtilityImpl implements EmailUtility  {

    private String host;
    private String port;
    private String username;
    private String password;
    private String defaultFromAddress;
    private String defaultReplyToAddress;
    private String mailProperties;
    private boolean enabled;
    
    /* (non-Javadoc)
	 * @see org.ussa.util.EmailUtility#refundEmail(org.ussa.beans.AccountBean)
	 */
    public boolean refundEmail(AccountBean accountBean) {

	try {
	    
	    StringBuffer message = new StringBuffer();
	    message.append("US Ski and Snowboard Customer Ð Notification of Voided Transaction");
	    message.append("<br/><br/>");
	    message.append("An error has occurred while processing your membership. The following transaction has been voided:");
	    message.append("<br/><br/>");
	    message.append("Transaction Id: " + accountBean.getPaymentBean().getCompletedTransactionId());
	    message.append("<br/>");
	    message.append("Amount: $" + accountBean.getCartBean().getTotal().toString());
	    message.append("<br/><br/>");
	    message.append("We apologize for any inconvenience. Please retry your transaction or contact us.");
	    message.append("email us <a href=\"mailto:membership@ussa.org\">here</a>");
	    message.append("<br/><br/>");
	    message.append("USSA Membership Staff");
	    message.append("<br/>");
	    message.append("<a href=\"mailto:membership@ussa.org\">membership@ussa.org</a>");
	    message.append("<br/>");
	    message.append("435.647.2666");
	    return sendEmail(accountBean.getConfirmationEmail(), "US Ski and Snowboard Customer Refund Notification", message.toString());
	} catch (Exception ex) {
	    ex.printStackTrace();
	    return false;
	}
    }

   
    private boolean sendEmail(String to, String subject, String message) {

	try {
	    // Set the host smtp address
	    Properties props = System.getProperties();
	    props.put("mail.smtp.host", this.host);
	    props.put("mail.smtp.port", this.port);

	    // Iterate through all the mail properties passed it
	    // Add each key,value to the props object
	    if (this.mailProperties != null) {
		String key, value;
		for (String propertyKeyVal : this.mailProperties.split(";")) {
		    key = propertyKeyVal.split("=")[0];
		    value = propertyKeyVal.split("=")[1];
		    props.put(key, value);
		}
	    }

	    Authenticator auth = new SMTPAuthenticator();

	    Session session = Session.getDefaultInstance(props, auth);

	    // Create a message
	    Message msg = new MimeMessage(session);

	    // Set the from and to address
	    InternetAddress addressFrom = new InternetAddress(this.defaultFromAddress);
	    msg.setFrom(addressFrom);

	    InternetAddress addressTo = new InternetAddress(to);
	    msg.setRecipient(Message.RecipientType.TO, addressTo);

	    // Setting the Subject and Content Type
	    msg.setSubject(subject);
	    msg.setContent(message, "text/html");
	    Transport.send(msg);
	    return true;
	} catch (Exception ex) {
	    ex.printStackTrace();
	    return false;
	}
    }

    private class SMTPAuthenticator extends javax.mail.Authenticator {

	public PasswordAuthentication getPasswordAuthentication() {
	    String username = getUsername();
	    String password = getPassword();
	    return new PasswordAuthentication(username, password);
	}
    }

    public String getHost() {
	return host;
    }

    public void setHost(String host) {
	this.host = host;
    }

    public String getPort() {
	return port;
    }

    public void setPort(String port) {
	this.port = port;
    }

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getDefaultFromAddress() {
	return defaultFromAddress;
    }

    public void setDefaultFromAddress(String defaultFromAddress) {
	this.defaultFromAddress = defaultFromAddress;
    }

    public String getDefaultReplyToAddress() {
	return defaultReplyToAddress;
    }

    public void setDefaultReplyToAddress(String defaultReplyToAddress) {
	this.defaultReplyToAddress = defaultReplyToAddress;
    }

    public String getMailProperties() {
	return mailProperties;
    }

    public void setMailProperties(String mailProperties) {
	this.mailProperties = mailProperties;
    }

    public boolean isEnabled() {
	return enabled;
    }

    public void setEnabled(boolean enabled) {
	this.enabled = enabled;
    }
}
