package com.check_in_system.demo.events;

import org.springframework.context.ApplicationEvent;

import com.check_in_system.demo.entities.Tables.User;

import lombok.Getter;
import lombok.Setter;


//1)we create an event 
//2) we publish the event
//3)we listen to the event 


@Getter
@Setter 
public class regCompletedEvent extends ApplicationEvent{

    private User user;
    private String ApplicationUrl;



    public regCompletedEvent(User user, String ApplicationUrl ) {
        /*If you don't call the constructor of the parent class (ApplicationEvent in this case) using super(user)
         in the constructor of regCompletedEvent, the parent class constructor will not be executed. This means that 
         the ApplicationEvent will not be properly initialized,
         and any logic in the ApplicationEvent constructor or any initialization it performs will not take place.*/
        super(user);
        this.user = user;
        this.ApplicationUrl = ApplicationUrl;
        
    }


    
}
