package com.check_in_system.demo.service;



import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import com.check_in_system.demo.repository.VerificationUserRep;
import com.check_in_system.demo.repository.reg_rep;
import com.check_in_system.demo.models.Password;
import com.check_in_system.demo.models.UserModel;
import com.check_in_system.demo.entities.Tables.User;
import com.check_in_system.demo.entities.Tables.VerificationToken;

@Service
public class reg_ser {
    @Autowired
    private JavaMailSender mailSender;


    @Autowired
    private reg_rep rep ;

    @Autowired
    private VerificationUserRep V_rep;
    
    @Autowired
    private PasswordEncoder  pass_encoder ;


    public User regUser(UserModel UserModel){
        User user = new User();
        user.setName(UserModel.getName());
        user.setEmail(UserModel.getEmail());
        user.setRole("user");
        user.setPassword(pass_encoder.encode(UserModel.getPassword()) );

       return rep.save(user);
        
    }


    public void saveVerificationTokenForUser(User user, String token) {
        VerificationToken verificationToken = new VerificationToken(user, token);
        V_rep.save(verificationToken);

    }


    public void sendEmail(String to, String subject, String body) {
        System.out.println("majd");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("majdalkwaja@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        try{
            
            System.out.println("baraa");
            mailSender.send(message);
            System.out.println("sent successfully");
        } catch( Exception e){
            System.out.println(e);

        }
         
    }


    public String verify(String token) {
        VerificationToken record =  V_rep.findByToken(token);
        if(record == null) return "invalid";
        LocalDateTime currentTime = LocalDateTime.now();
        int res = currentTime.compareTo(record.getExpirationTime());
        if(res > 0){
            V_rep.delete(record); 
            return "expired";
        }
        record.getUser().setEnabled( true);
        V_rep.save(record);
        return "verified";
    }

    public String resetPassword(String email, Password updated){
        //check if user exist 
        User user = rep.findByEmail(email);
        if (user == null) return "invalid user email";
        if(!pass_encoder.matches(updated.getPassword(), user.getPassword())) return "invalid user";
        //else, then user exists so 
        //set password to the sent updated password
        user.setPassword(pass_encoder.encode(updated.getNewPassword()));
        return "password updated properly";
    }


  

    public User regAuthUser(OAuth2User oauth2User) {
        System.out.print("hiiiiiiiiiiiiiiiiiiiiiiiiii");
        String email = oauth2User.getAttribute("email");
        String username = oauth2User.getAttribute("name");

        // Create a new User entity and populate it with retrieved data
        User user = new User();
        user.setEmail(email);
        user.setName(username);

        return rep.save(user);
    }


    public  String  reg_auth_user(Map<String, Object> m) {
        if (rep.findByEmail(m.get("email").toString()) != null ){
            return "stop";
        }

        User user = new User();
        user.setName(m.get("given_name").toString());
        user.setEmail(m.get("email").toString());
        user.setOnline_reg(true);
        rep.save(user);
        return "saved";
    }


    
}
