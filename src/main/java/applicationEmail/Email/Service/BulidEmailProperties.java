package applicationEmail.Email.Service;

import applicationEmail.Email.Model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Properties;

public class BulidEmailProperties {

    public static Properties setProperties(String password, User user){

        Properties  properties = new Properties();
        properties.put("mail.username", user.getUsername());
        properties.put("mail.password", "");
        properties.put("mail.smtp.host", user.getHostName());
        properties.put("mail.smtp.port", user.getPortHost());
        return properties;
    }
}
