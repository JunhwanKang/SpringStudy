package com.cdj.zmarket.util;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.cdj.zmarket.dto.Mail;

@Component
public class MailUtil {
	@Autowired
	private JavaMailSender javaMailSender;
	
	public void joinCheckMail(String from, String to, String checkCode) {
		Mail mail = Mail.builder().to(to).from(from).subject("[득템마켓] 회원가입 인증 이메일").build();
		StringBuffer text = new StringBuffer("<p>회원가입 인증 이메일</p>");
		text.append("<p>인증을 위해 아래 버튼을 클릭하세요.</p>");
		text.append("<a href='http://localhost:8081/zmarket/user/join_check?checkCode=" );
		text.append(checkCode);
		text.append("'>클릭하세요</a>");
		mail.setText(text.toString());
		sendMail(mail);
	}

	public void sendMail(Mail mail) {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(message, false, "utf-8");
			helper.setFrom(mail.getFrom());
			helper.setTo(mail.getTo());
			helper.setSubject(mail.getSubject());
			helper.setText(mail.getText(), true);
			javaMailSender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
