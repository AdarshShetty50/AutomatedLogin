package tests;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.testng.annotations.Test;

import libraries.CommonLib;
import libraries.Constants;
import libraries.ProjectConstants;
import libraries.RTData;
import libraries.ReadExcel;

public class send_Mail {
	@Test
	public void sendMail() {	
		try {
			ReadExcel excel = new ReadExcel();
			String strCurrDate = CommonLib.GetCurrentDate().replace("/", "-");
			String filePath = ProjectConstants.OUTPUTSHEETPATH + "/" + strCurrDate;
			File theNewestFile = null;
			File dir = new File(filePath);
			FileFilter fileFilter = new WildcardFileFilter("*.html");
			File[] files = dir.listFiles(fileFilter);

			if (files.length > 0) {
				/** The newest file comes first **/
				Arrays.sort(files, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
				theNewestFile = files[0];
			}
			
			System.out.println("Sending Email...");
			XSSFSheet configSheet = excel.readXLSXfile(ProjectConstants.DRIVEREXCEL,ProjectConstants.CONFIGSHEET );
			Constants.strAutomationReportReceiver = excel.CellValue(configSheet, 4, 1);
			final String user= "Adarsh.Shetty@Integrogroup.com";
			final String password=CommonLib.DecryptPassword("Q2hhbmdlLlBhc3N3b3JkLlBsZWFzZQ==");
			
			String to=Constants.strAutomationReportReceiver;
			
			List<String> reciepientsList = Arrays.asList(to.split(";"));
			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtp.office365.com");			
			props.put("mail.smtp.port", "587"); 

			Session session = Session.getDefaultInstance(props,  
					new javax.mail.Authenticator() {  
				protected PasswordAuthentication getPasswordAuthentication() {  
					return new PasswordAuthentication(user,password);  
				}  
			});  
			MimeMessage message = new MimeMessage(session);  
			message.setFrom(new InternetAddress(user));
			
			for (int i =0; i<reciepientsList.size(); i++) {
			message.addRecipient(Message.RecipientType.TO,new InternetAddress(reciepientsList.get(i)));	
			}
			
			
			String Subject="Risk Tracker Automation Report for " + CommonLib.GetCurrentDate();
			message.setSubject(Subject);  
			String body="Hi,\n\nPlease find attached automation report.\n\nRegards,\nAutomation Team";											
			BodyPart messageBodyPart1 = new MimeBodyPart();  
			messageBodyPart1.setText(body);
			Multipart multipart = new MimeMultipart();			  
			MimeBodyPart messageBodyPart2 = new MimeBodyPart();  			

			String filename1 = theNewestFile.getAbsolutePath();//Constants.ReportPathwithName;  
			File filePath2 = new File(filename1);
			if (filePath2.exists()) {
				DataSource source1 = new FileDataSource(filename1);  
				messageBodyPart2.setDataHandler(new DataHandler(source1));  
				messageBodyPart2.setFileName(new File(filename1).getName()); 
				multipart.addBodyPart(messageBodyPart2); 
			} 			
			multipart.addBodyPart(messageBodyPart1); 	
			message.setContent(multipart);
			Transport.send(message);  		  
			System.out.println("Email sent successfully");  
			

		} catch (Exception e) {
			System.out.println("" + e.toString());
		}  
	}		
}