package com.email.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailServices {
   
	@Autowired
	private JavaMailSender sender;
	@Override
	public boolean sendEmail(String from, String to, String content, String subject) {
		boolean flag=false;
		try {
		 SimpleMailMessage mailMessage=new SimpleMailMessage();
		 mailMessage.setFrom(from);
		 mailMessage.setTo(to);
		 mailMessage.setSubject(subject);
		 mailMessage.setText(subject);
		 mailMessage.setText(content);
		 sender.send(mailMessage);
		 flag=true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

}
