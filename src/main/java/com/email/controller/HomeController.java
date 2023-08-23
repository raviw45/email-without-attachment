package com.email.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.email.helper.Message;
import com.email.model.EmailMessage;
import com.email.services.EmailServices;

@Controller
public class HomeController {
	@Autowired
	private EmailServices emailServices;
	@Autowired
	private Environment environment;
    
	@GetMapping("/")
	public String homeHandler() {
		return "index";
	}
	
	@PostMapping("/send")
	public String sendEmailHandler(@ModelAttribute EmailMessage emailMessage,HttpSession session) {
		String from=environment.getProperty("spring.mail.username");
		String content="First Name: "+emailMessage.getFirstName();
		content+=",  Last Name: "+emailMessage.getLastName();
		content+=",  Email: "+emailMessage.getEmail();
		content+=",  Address: "+emailMessage.getAddress();
		content+=",  Phone: "+emailMessage.getPhone();
		String to="ravikantwaghmare82@gmail.com";
		String subject="Job shortlisting from portfolio site";
		boolean result=this.emailServices.sendEmail(from, to, content, subject);
		if(result) {
			session.setAttribute("message", new Message("Email sent successfully!!","alert-success"));
		}else {
			session.setAttribute("message", new Message("Something went wrong!!!!", "alert-danger"));
		}
		return "index";
	}
}
