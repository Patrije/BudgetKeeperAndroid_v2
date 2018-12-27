package com.example.pati.retrofitappintro.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Pati on 12.11.2018.
 */


public class LoginCredentials {

    @SerializedName("login")
    private String login;
    @SerializedName("password")
    private String password;

    public LoginCredentials(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
