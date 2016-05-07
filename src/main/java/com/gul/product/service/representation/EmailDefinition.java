package com.gul.product.service.representation;

public class EmailDefinition {

	private String from;
	private String to;
	private String subject;
	private String body;
	private String image;
	private String miscellaneous;
	
	public EmailDefinition() {
	}
	
	public EmailDefinition (String from, String to, String subject, String body, String image, String miscellanous) {
		this.from = from;
		this.to = to;
		this.subject = subject;
		this.body = body;
		this.image = image;
		this.miscellaneous = miscellanous;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getMiscellaneous() {
		return miscellaneous;
	}

	public void setMiscellaneous(String miscellaneous) {
		this.miscellaneous = miscellaneous;
	}
}
