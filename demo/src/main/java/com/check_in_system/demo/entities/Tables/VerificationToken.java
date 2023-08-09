package com.check_in_system.demo.entities.Tables;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter 
public class VerificationToken {
    private static final int WAITING_TIME = 10;
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    private String token;
    private LocalDateTime expirationTime;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public VerificationToken() {
        // Empty constructor or initialize default values if needed
    }
    
    public VerificationToken(User user, String token) {
        super();
        this.token = token;
        this.user = user;
        this.expirationTime = calculateExpirationTime(WAITING_TIME);
    }

    private LocalDateTime calculateExpirationTime(int waitingTime) {
        // Get the current time
        LocalDateTime currentTime = LocalDateTime.now();

        // Add the defined minutes to the current time
        LocalDateTime expirationTime = currentTime.plusMinutes(WAITING_TIME);

        return expirationTime;
    }
    
}
