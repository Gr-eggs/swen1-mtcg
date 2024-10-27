package org.example.models;

public class UserWithToken extends User{
    private String token;

    public UserWithToken(String username, String password, String token) {
        super(username, password);
        this.token = token;
    }


    public String getToken() {
        return token;
    }
}
