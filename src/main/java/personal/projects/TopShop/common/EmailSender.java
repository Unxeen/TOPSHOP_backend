package personal.projects.TopShop.common;

import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
//@NoArgsConstructor
//@Data
public class EmailSender {

    private JavaMailSender mailSender;

    public void sendEmail(String emailTo, String subject, String body){

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("ayoubelghazi6@gmail.com");
        message.setTo(emailTo);
        message.setText(body);
        message.setSubject(subject);

        mailSender.send(message);
        System.out.println("Mail sent successfully");
    }



//    private MailSender mailSender;
//    private SimpleMailMessage templateMessage;

//    public void sendEmail(String to, String subject, String body){
//
//        SimpleMailMessage message = new SimpleMailMessage(templateMessage);
//        message.setTo(to);
//        message.setText(body);
//        message.setSubject(subject);
//
//        try {
//            mailSender.send(message);
//        } catch (MailException exception){
//            exception.printStackTrace();
//        }


//        Properties props = new Properties();
//        props.setProperty("mail.smtp.host", "smtp.gmail.com");
//        props.setProperty("mail.smtp.port", "587");
//        props.setProperty("mail.smtp.auth", "true");
//        props.setProperty("mail.smtp.starttls.enable", "true");
//
//        Session session = Session.getDefaultInstance(props, new Authenticator() {
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(FROM_EMAIL, FROM_PASSWORD);
//            }
//        });
//
//        try {
//            MimeMessage message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(FROM_EMAIL));
//            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
//            message.setSubject(subject);
//            message.setText(body);
//
//            Transport.send(message);
//            System.out.println("Email sent successfully");
//
//        } catch (MessagingException exception){
//            exception.printStackTrace();
//        }

//    }


}
