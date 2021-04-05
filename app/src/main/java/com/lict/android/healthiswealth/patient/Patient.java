package com.lict.android.healthiswealth.patient;

public class Patient {

    private String Name,email,mobile,pass;
    private int Age;

    public Patient(String name, String email, int age, String mobile, String pass) {
        Name = name;
        this.email = email;
        Age = age;
        this.mobile = mobile;
        this.pass = pass;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
