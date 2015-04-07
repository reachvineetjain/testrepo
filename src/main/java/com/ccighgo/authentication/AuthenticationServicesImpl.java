package com.ccighgo.authentication;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class AuthenticationServicesImpl implements AuthenticationServices {

    public boolean isValidUser(String username) {
        // TODO Auto-generated method stub
        return false;
    }

    public <T> List<T> getPermissions() {
        // TODO Auto-generated method stub
        return null;
    }

    public boolean hasPermission(String username, String resource, String type) {
        // TODO Auto-generated method stub
        return false;
    }

}
