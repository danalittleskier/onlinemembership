package org.ussa.util;

import java.util.Properties;

import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.ussa.common.model.User;

public class UssaJavaMailer extends JavaMailSenderImpl {
	protected final Log log = LogFactory.getLog(getClass());
	private String defaultFromAddress;
	private String defaultReplyToAddress;
	private boolean enabled;
	
	public UssaJavaMailer(String mailProperties) {
		Properties props = System.getProperties();
		
		if (StringUtils.isNotBlank(mailProperties)) {
			String key, value;
			for (String propertyKeyVal : mailProperties.split(";")) {
				key = propertyKeyVal.split("=")[0];
				value = propertyKeyVal.split("=")[1];
				props.put(key, value);
			}
		}
		
		this.setJavaMailProperties(props);
	}
	
	public String getBasicAccountChangeEmailText(String accountProperty) {
		StringBuffer emailBody = new StringBuffer("PLEASE DO NOT REPLY TO THIS EMAIL. This is an automated message. If you need assistance, please call 435-647-2666\n\n");
		emailBody.append("Recent changes have been made to your ").append(accountProperty).append(" thru USSA's Online Account Service.\n\n");
		emailBody.append("If these changes were not made by you, then please contact USSA Member Services at 435-647-2666.");
		
		return emailBody.toString();
	}
	
	public String getBasicAccountCreatedEmailText() {
		StringBuffer emailBody = new StringBuffer("PLEASE DO NOT REPLY TO THIS EMAIL. This is an automated message. If you need assistance, please call 435-647-2666\n\n");
		emailBody.append("An account has been created for you thru USSA's Online Account Service.\n\n");
		emailBody.append("If you did not create this account, then please contact USSA Member Services at 435-647-2666.");
		
		return emailBody.toString();
	}
	
	public String getBasicPasswordResetEmailText(String password) {
		StringBuffer emailBody = new StringBuffer("PLEASE DO NOT REPLY TO THIS EMAIL. This is an automated message. If you need assistance, please call 435-647-2666\n\n");
		emailBody.append("Your password has been reset to " + password + " thru USSA's Online Account Service.\n\n");
		emailBody.append("If you did not request your password to be changed, then please contact USSA Member Services at 435-647-2666.");
		
		return emailBody.toString();
	}
	
	public SimpleMailMessage getBasicMailMessage(User user) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(user.getEmail());
		mailMessage.setFrom(this.getDefaultFromAddress());
		mailMessage.setReplyTo(this.getDefaultReplyToAddress());
		
		return mailMessage;
	}
	
	/**
	 * Send the provided simpleMessage email and log any <code>MailException</code> errors that occur
	 * @see org.springframework.mail.javamail.JavaMailSender.send(SimpleMailMessage)
	 * 
	 * @param simpleMessage		The <code>SimpleMailMessage</code> to be sent
	 */
	public void safeSend(SimpleMailMessage simpleMessage) {
		if (enabled) {
			try {
				super.send(simpleMessage);
			} catch (MailException e) {
				log.error("An error occured while sending an email", e);
			}
		}
	}
	
	@Override
	public void send(MimeMessage mimeMessage) throws MailException {
		if (enabled) {
			super.send(mimeMessage);
		}
	}

	@Override
	public void send(MimeMessage[] mimeMessages) throws MailException {
		if (enabled) {
			super.send(mimeMessages);
		}
	}

	@Override
	public void send(MimeMessagePreparator mimeMessagePreparator) throws MailException {
		if (enabled) {
			super.send(mimeMessagePreparator);
		}
	}

	@Override
	public void send(MimeMessagePreparator[] mimeMessagePreparators) throws MailException {
		if (enabled) {
			super.send(mimeMessagePreparators);
		}
	}

	@Override
	public void send(SimpleMailMessage simpleMessage) throws MailException {
		if (enabled) {
			super.send(simpleMessage);
		}
	}

	@Override
	public void send(SimpleMailMessage[] simpleMessages) throws MailException {
		if (enabled) {
			super.send(simpleMessages);
		}
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

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
