package com.dsa360.api.notification;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.dsa360.api.constants.ReviewType;
import com.dsa360.api.dto.DSAApplicationDTO;
import com.dsa360.api.entity.RegionsEntity;
import com.dsa360.api.entity.RoleEntity;
import com.dsa360.api.exceptions.SomethingWentWrongException;

/**
 * @author RAM
 *
 */
@Service
public class NotificationServiceImpl implements NotificationService {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private TemplateEngine templateEngine;

	@Value("${spring.mail.username}")
	private String sender;

	@Value("${server.port}")
	private String port;

	@Value("${server.hostname}")
	private String host;
	
//	@Value("${ng.server}")
//	private String ngServer;

	@Override
	public void dsaRegistrationConfirmationMail(String to, String dsaName, String dsaId, String registeredName,
			String contactInfo) {
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			Context context = new Context();
			context.setVariable("dsaName", dsaName);
			context.setVariable("dsaId", dsaId);
			context.setVariable("registeredName", registeredName);
			context.setVariable("contactInfo", contactInfo);

			String text = templateEngine.process("dsa-registration", context);

			helper.setFrom(sender, "DSA 360 Solution");
			helper.setTo(to);
			helper.setSubject("Welcome to DSA 360 Solution: DSA Registration Confirmed!");
			helper.setText(text,true);

			mailSender.send(message);
		} catch (MailSendException | MailAuthenticationException | MessagingException
				| UnsupportedEncodingException e) {

			throw new SomethingWentWrongException("Failed to send the email", e);

		}

	}

	@Override
	public void dsaKycConfirmationMail(String to, String kycId, String dsaId, String dsaName, String contact,
			String address, List<String> docs) {
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			Context context = new Context();
			context.setVariable("dsaName", dsaName);
			context.setVariable("dsaId", dsaId);
			context.setVariable("kycId", kycId);
			context.setVariable("registeredName", dsaName);
			context.setVariable("email", to);
			context.setVariable("contactNumber", contact);
			context.setVariable("address", address);
			context.setVariable("docs", docs);

			String html = templateEngine.process("kyc_submission", context);

			helper.setFrom(sender, "DSA 360 Solution");
			helper.setTo(to);
			helper.setSubject("KYC Submission Confirmed - " + dsaId);
			helper.setText(html, true);

			mailSender.send(message);
		} catch (MailSendException | MailAuthenticationException | MessagingException
				| UnsupportedEncodingException e) {

			throw new SomethingWentWrongException("Failed to send the email", e);

		}

	}

	@Override
	public void dsaReviewMail(String to, String dsaName, String reviewStatus, String type,String dsaId) {
		String html = null;
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			Context context = new Context();
			context.setVariable("dsaName", dsaName);
			context.setVariable("reviewStatus", reviewStatus);
			context.setVariable("dsaId", dsaId);
			if (ReviewType.APPLICATION.getValue().equals(type)) {
				
				html = templateEngine.process("applicatiion_review", context);
				helper.setSubject("DSA Application : " + reviewStatus);

			} else if (ReviewType.KYC.getValue().equals(type)) {
				
				html = templateEngine.process("kyc_review", context);
				helper.setSubject("DSA KYC : " + reviewStatus);

			}

			helper.setFrom(sender, "DSA 360 Solution");
			helper.setTo(to);
			helper.setText(html, true);

			mailSender.send(message);
		} catch (MailSendException | MailAuthenticationException | MessagingException
				| UnsupportedEncodingException e) {
			throw new SomethingWentWrongException("Failed to send the email", e);

		}
	}

	@Override
	public void userProfileCreatedConfirmationMail(DSAApplicationDTO dsaById, String username, String password,
			List<RoleEntity> allRoleByIds, List<RegionsEntity> allRegionsByIds) {

		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			Context context = new Context();

			String fullName = dsaById.getFirstName() + " " + dsaById.getLastName();
			List<String> roles = allRoleByIds.stream().map(role -> role.getName().replace("ROLE_", ""))
					.collect(Collectors.toList());

			List<String> regions = allRegionsByIds.stream().map(RegionsEntity::getRegionName)
					.collect(Collectors.toList());

			context.setVariable("dsaName", fullName);
			context.setVariable("dsaId", dsaById.getDsaApplicationId());
			context.setVariable("username", username);
			context.setVariable("password", password);
			context.setVariable("roles", roles);
			context.setVariable("regions", regions);

			String html = templateEngine.process("user-profile", context);

			helper.setFrom(sender, "DSA 360 Solution");
			helper.setTo(dsaById.getEmailAddress());
			helper.setSubject("Welcome to DSA 360 Solution: DSA Profile Created !");
			helper.setText(html, true);

			mailSender.send(message);
		} catch (MailSendException | MailAuthenticationException | MessagingException
				| UnsupportedEncodingException e) {

			throw new SomethingWentWrongException("Failed to send the email", e);

		}

	}

	@Override
	public void emailVerificationRequestMail(String dsaId, String dsaName, String emailTo, String token) {

		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);

			Context context = new Context();
			context.setVariable("dsaName", dsaName);
			context.setVariable("dsaId", dsaId);
			context.setVariable("email", emailTo);
            String url = "http://" + host + ":" + port + "/public/verify-email/" + dsaId + "?token=" + token;
			context.setVariable("url", url);

			String html = templateEngine.process("email-verification", context);

			helper.setFrom(sender, "DSA 360 Solution");
			helper.setTo(emailTo);
			helper.setSubject("DSA 360: Email Verification Request - " + dsaId);
			helper.setText(html, true);

			mailSender.send(message);
		} catch (MailSendException | MailAuthenticationException | MessagingException
				| UnsupportedEncodingException e) {

			throw new SomethingWentWrongException("Failed to send the email", e);

		}
	}

}
