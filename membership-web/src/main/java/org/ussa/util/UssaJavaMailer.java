package org.ussa.util;

import java.util.Properties;

import org.springframework.mail.javamail.JavaMailSenderImpl;

public class UssaJavaMailer extends JavaMailSenderImpl {
	private String fromAddress;
	private String replyToAddress;
	
	public UssaJavaMailer() {
		Properties props = System.getProperties();
		props.setProperty("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		
		this.setJavaMailProperties(props);
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String getReplyToAddress() {
		return replyToAddress;
	}

	public void setReplyToAddress(String replyToAddress) {
		this.replyToAddress = replyToAddress;
	}
}
