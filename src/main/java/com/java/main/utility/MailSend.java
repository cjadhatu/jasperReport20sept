/*package com.java.main.utility;
import java.io.IOException;

import com.sendgrid.*;
import com.sendgrid.SendGrid;

public class MailSend {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		
		//tushar.chandrakant.jadhav@jci.com
		//tushar.cjadhav05@gmail.com
		Email from = new Email("tushar.cjadhav05@gmail.com");
	    String subject = "Sending with SendGrid is Fun";
	    Email to = new Email("tushar.cjadhav05@gmail.com");
	    Content content = new Content("text/plain", "and easy to do anywhere, even with Java");
	    Mail mail = new Mail(from, subject, to, content);

	    SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
	    Request request = new Request();
	    try {
	      request.setMethod(Method.POST);
	      request.setEndpoint("mail/send");
	      request.setBody(mail.build());
	      Response response = sg.api(request);
	      System.out.println(response.getStatusCode());
	      System.out.println(response.getBody());
	      System.out.println(response.getHeaders());
	    } catch (IOException ex) {
	      throw ex;

	    }
	}

}
*/