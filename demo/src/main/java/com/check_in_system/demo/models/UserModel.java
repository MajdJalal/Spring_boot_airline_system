package com.check_in_system.demo.models;

import lombok.Data;

@Data

public class UserModel {
    private String name;
    private String email;
    private String password;
    private String matchingPassword;    
}
