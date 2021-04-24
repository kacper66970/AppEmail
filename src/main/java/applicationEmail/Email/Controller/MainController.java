package applicationEmail.Email.Controller;

import applicationEmail.Email.Model.Email;
import applicationEmail.Email.Model.User;
import applicationEmail.Email.Model.UserRole;
import applicationEmail.Email.Repository.UserRepository;
import applicationEmail.Email.Repository.UserRoleRepository;
import applicationEmail.Email.Service.BulidEmailProperties;
import applicationEmail.Email.Service.SenderMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class MainController {

    UserRepository userRepository;
    UserRoleRepository userRoleRepository;
    SenderMail senderMail;

    @Autowired
    public MainController(UserRepository userRepository, UserRoleRepository userRoleRepository, SenderMail senderMail) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.senderMail = senderMail;

    }

    @GetMapping("/")
    public String home(Model model){

        model.addAttribute("email", new Email());
        return "index";
    }

    @GetMapping("/addUser")
    public String registry(Model model){
        model.addAttribute("user", new User());
        return "registry";
    }

    @PostMapping("/registry")
    public String addUser(@ModelAttribute User user, Model model){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);

        userRepository.save(user);
        UserRole userRole = new UserRole(user.getUsername(),"USER");
        userRoleRepository.save(userRole);

        return "redirect:/login";
    }

    @PostMapping("/sendEmail")
    public String sendEmail(@ModelAttribute Email email, Principal principal){
        String username = principal.getName();
        User user = userRepository.findByUsername(username);
        BulidEmailProperties.setProperties("QSDweffr23",user);
        senderMail.sendEmail(email, username);
        return "redirect:/";
    }


}
