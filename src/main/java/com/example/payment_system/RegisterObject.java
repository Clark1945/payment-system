package com.example.payment_system;

import lombok.Getter;

import java.util.HashMap;
public class RegisterObject {
    @Getter
    private String name;
    @Getter
    private String phone;
    @Getter
    private String email;
    @Getter
    private String password;

    private RegisterObject(String name,String phone,String email,String password) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    private RegisterObject(String email,String password) {
        this.email = email;
        this.password = password;
    }

    public static RegisterObject createRegister(HashMap requestMap) {
        String name = (String) requestMap.get("name");
        String phone = (String) requestMap.get("phone");
        String email = (String) requestMap.get("email");
        String password = (String) requestMap.get("password");

        return new RegisterObject(name,phone,email,password);
    }
    public static RegisterObject createLogin(HashMap requestMap) {
        String email = (String) requestMap.get("email");
        String password = (String) requestMap.get("password");

        return new RegisterObject(email,password);
    }
}
