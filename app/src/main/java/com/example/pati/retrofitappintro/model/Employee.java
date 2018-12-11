package com.example.pati.retrofitappintro.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Pati on 25.11.2018.
 */

public class Employee {

    private Long id;
    @SerializedName("employeeId")
    private Long employeeId;

    @SerializedName("name")
    private String name;

    @SerializedName("surname")
    private String surname;

    @SerializedName("login")
    private String login;

    @SerializedName("position")
    private String position;

    @SerializedName("password")
    private String password;

    @SerializedName("mail")
    private String email;

    @SerializedName("balance")
    private double balanace;

    public Employee(Long id, Long employeeId, String name, String surname, String login, String position, String password, String email, double balanace) {
        this.id = id;
        this.employeeId = employeeId;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.position = position;
        this.password = password;
        this.email = email;
        this.balanace = balanace;
    }

    public Employee() {
    }

    public Employee(String name, String surname, String login, String position, String password, String email, double balanace) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.position = position;
        this.password = password;
        this.email = email;
        this.balanace = balanace;
    }

    public Employee(Long employeeId, String name, String surname, String login, String position, String password, String email, double balanace) {
        this.employeeId = employeeId;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.position = position;
        this.password = password;
        this.email = email;
        this.balanace = balanace;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getBalanace() {
        return balanace;
    }

    public void setBalanace(double balanace) {
        this.balanace = balanace;
    }
}
