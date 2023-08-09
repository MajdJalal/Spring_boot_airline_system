package com.check_in_system.demo.listeners;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.check_in_system.demo.entities.Tables.User;
import com.check_in_system.demo.events.regCompletedEvent;
import com.check_in_system.demo.service.reg_ser;

@Component //must be a bean so publisher  can find it 
public class regCompletedListener implements ApplicationListener<regCompletedEvent> {
    
    @Autowired
    private reg_ser ser;

    @Override
    public void onApplicationEvent(regCompletedEvent event){


        //create verification token for teh user with link 
        User user = event.getUser();
        String token  = UUID.randomUUID().toString();

        ser.saveVerificationTokenForUser(user, token);//saving the token for the user so i cam check against when clicking the link by the user 

        //send the email to user 
        String url = event.getApplicationUrl()  + "verifyRegistration?token=" + token;
        ser.sendEmail("majdjalkhawaja@gmail.com", "verification", url);

    }
    
}
