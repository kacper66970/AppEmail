package applicationEmail.Email.Service;

import applicationEmail.Email.Model.Email;
import applicationEmail.Email.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
@Service
public class SenderMail {
    @Autowired
    private JavaMailSender javaMailSender;


//    @Autowired
//    public SenderMail(JavaMailSender javaMailSender, User user) {
//        this.javaMailSender = javaMailSender;
//        this.user = user;
//    }



    public void sendEmail(Email email, String username)  {
        MimeMessage mail = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = sendEmailText(email,mail, username);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        javaMailSender.send(mail);
    }

    private MimeMessageHelper sendEmailText(Email email, MimeMessage mail, String username) throws MessagingException {

        MimeMessageHelper helper = new MimeMessageHelper(mail, true);
        helper.setTo(email.getTo());
        helper.setFrom(username);
        helper.setSubject(email.getSubcejt());
        helper.setText(email.getMesseges(), true);

        return helper;

    }


}

