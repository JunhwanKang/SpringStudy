package com.cdj.sboard.util;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.cdj.sboard.dto.Mail;

@Component
public class MailUtil {
	@Autowired
	private JavaMailSender javaMailSender;
	
	public void sendJoinCheckMail(String from, String to, String checkCode) {
		Mail mail = Mail.builder().from(from).to(to).subject("���� Ȯ�� ����").build();
		StringBuffer buf = new StringBuffer("<p>ȸ�������� ���� �ȳ� �����Դϴ�</p>");
		buf.append("<p>������ Ȯ���� ���� �Ʒ� ��ũ�� Ŭ���ϼ���</p>");
		buf.append("<p>���� Ȯ�� ��ũ: ");
		buf.append("<a href='http://localhost:8081/sboard/user/join_check?checkCode=");
		buf.append(checkCode);
		buf.append("'>Ŭ���ϼ���</a></p>");
		mail.setText(buf.toString());
		sendMail(mail);
	}
	private void sendMail(Mail mail) {
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
	public void sendResetPasswordMail(String from, String to, String password) {
		Mail mail = Mail.builder().from(from).to(to).subject("�ӽ� ��й�ȣ").build();
		StringBuffer buf = new StringBuffer("<p>�ӽ� ��й�ȣ�Դϴ�.</p>");
		buf.append("<p>�ӽ� ��й�ȣ: "+password+"</p>");
		mail.setText(buf.toString());
		sendMail(mail);
	}

	
}
