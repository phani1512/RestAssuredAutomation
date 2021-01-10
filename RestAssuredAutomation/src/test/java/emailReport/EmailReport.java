package emailReport;

import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.testng.annotations.AfterSuite;

import com.base.TestBase;

public class EmailReport extends TestBase {

	@AfterSuite
	private void EmailReport() {

		final String username = "phaneendraphani20@gmail.com";
		final String password = "Phanindraa@15";

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("phaneendraphani22@yahoo.in"));
			message.setSubject("Testing Subject.html");
			BodyPart messageBodyPart1 = new MimeBodyPart();
			messageBodyPart1.setText("Hi," + "\n" + "Please, find the Automation test Report for Transportal API" + "\n"
					+ "\n" + "Note : This is an automatic generated mail by Automation Script" + "\n" + "\n" + "\n"
					+ "\n" + "Thank you," + "\n" + "Phanindraa");
			MimeBodyPart messageBodyPart2 = new MimeBodyPart();
			File file = new File("D:\\Git\\TransportalRestAssuredAPI\\RestAssuredAPIAutomation\\Reports\\Report.html");
			String fileName = "Test Report.html";
			DataSource source = new FileDataSource(file);
			messageBodyPart2.setDataHandler(new DataHandler(source));
			messageBodyPart2.setFileName(fileName);

			Multipart multipart = new MimeMultipart();

			multipart.addBodyPart(messageBodyPart1);
			multipart.addBodyPart(messageBodyPart2);

			message.setContent(multipart);

			message.setContent(multipart);
			System.out.println("Sending Email Report.......");
			Transport.send(message);
			System.out.println("Report Email Sent");

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
