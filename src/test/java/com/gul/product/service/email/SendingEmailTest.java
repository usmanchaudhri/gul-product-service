package com.gul.product.service.email;

import org.junit.Test;
import com.sendgrid.*;

public class SendingEmailTest {

	@Test
	public void test_sendingEmail() {
		SendGrid sendgrid = new SendGrid("app41139901@heroku.com", "1tps67gx4017");

		SendGrid.Email email = new SendGrid.Email();
		email.addTo("azhar.rao@gmail.com");
		email.setFrom("azhar.rao@gmail.com");
		email.setSubject("Hello World");
		email.setText("My first email through SendGrid");

		try {
		  SendGrid.Response response = sendgrid.send(email);
		  System.out.println(response.getStatus());
		  System.out.println(response.getMessage());
		} catch (SendGridException e) {
		  System.out.println(e);
		}
	}
	
}
