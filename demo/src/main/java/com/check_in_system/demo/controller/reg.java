package com.check_in_system.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import com.check_in_system.demo.service.reg_ser;
import com.check_in_system.demo.entities.Tables.User;
import com.check_in_system.demo.events.regCompletedEvent;
import com.check_in_system.demo.models.Password;
import com.check_in_system.demo.models.UserModel;


@RestController
public class reg {

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private reg_ser service;

    

    @PostMapping("/register")
    public String registerUser(@Valid @RequestBody UserModel UserModel, final HttpServletRequest request) {
        User user = service.regUser(UserModel);
        publisher.publishEvent(new regCompletedEvent(user, getUrl(request)));
        return "Success";
        
    }
    @GetMapping("/verifyRegistration")
    public String verify(@RequestParam("token") String token , final HttpServletRequest request) {
        return service.verify(token);
        
        
    }
    @PutMapping("/resetPassword/{email}")
    public String resetPassword(@PathVariable("email") String email,@RequestBody  Password updated) {
        return service.resetPassword(email, updated);
    }

    private String getUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + "/" + request.getContextPath() ;
                
    }

    ///
    @GetMapping("/log_in_google")
    public String log_in_google(HttpServletRequest request, @AuthenticationPrincipal OAuth2User oauth2User){
        Map<String, Object> m =  oauth2User.getAttributes();
        service.reg_auth_user(m);
        return "hello after google";
    }
    
    
}
