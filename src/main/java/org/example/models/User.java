package org.example.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.exceptions.InvalidDataException;

public class User {
    @JsonProperty("Username")
    private String username;
    @JsonProperty("Password")
    private String password;

    public User(){}

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) throws InvalidDataException {
        if(username != null){
            if(username.length()  > 3){
                this.username = username;
            }else{
                throw new InvalidDataException("Username may be larger than 3");
            }
        }else{
            throw new InvalidDataException("Username may not be null");
        }

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if(password != null){
            if(password.length()  > 3){
                this.password = password;
            }

        }
    }
}
