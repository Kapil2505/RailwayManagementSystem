package com.RailwayManagementSystem.Authentication.PayLoad;

import lombok.Data;

@Data
public class SignupDto {
    private String email;
    private String name;
    private String password;
    private String username;
}
