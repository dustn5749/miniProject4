package com.kosa.project4.board.service;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MailService {

	@Autowired
	private JavaMailSender mailSender;

	
	@Async
	// �̸��� ������
	public void sendMail(String email, int boardNum) {
		MimeMessage message = mailSender.createMimeMessage();
		try {
			if(!email.isEmpty()) {
				String subject = "[miniproject3] �Խñۿ� �亯�� �޷Ƚ��ϴ�";
				String text = "�Խù� : " + String.valueOf(boardNum) + "�� ���� �亯�� �޷Ƚ��ϴ�. ^^";
				MimeMessageHelper messageHepler = new MimeMessageHelper(message, true, "UTF-8");
				messageHepler.setSubject(subject);
				messageHepler.setTo(email);
				messageHepler.setText(text);
				mailSender.send(message);
						
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
