package it.solvingteam.padelmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
@Autowired
private JavaMailSender emailSender;

public void sendMail(String to, String subject,String text ) {
	SimpleMailMessage message = new SimpleMailMessage();
	message.setFrom("daniesercizio@gmail.com");
	message.setTo(to);
	message.setSubject(subject);
	message.setText(text);
	emailSender.send(message);
	
}
}
