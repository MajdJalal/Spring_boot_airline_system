package com.check_in_system.demo.configuration;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import static  org.springframework.security.config.Customizer.*;


@Configuration
@EnableWebSecurity
public class config extends WebSecurityConfigurerAdapter{
    
   
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Define the security rules for the first filter chain
        
        
        http.
        authorizeHttpRequests((requests) -> requests.requestMatchers("/register", "/verifyRegistration", "/resetPassword/{email}", "/login", "/oauth/**").permitAll().anyRequest().
        authenticated()).formLogin(withDefaults()).oauth2Login(withDefaults());
        
        
        return http.build();
        
    }
    
   
}
