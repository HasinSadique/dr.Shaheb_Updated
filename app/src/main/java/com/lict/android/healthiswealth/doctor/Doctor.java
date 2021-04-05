package com.lict.android.healthiswealth.doctor;

public class Doctor {
    private String name;
    private String email;
    private String BMDC;
    private String mobile;
    private String pass;


    public Doctor(){


    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBMDC() {
        return BMDC;
    }

    public void setBMDC(String BMDC) {
        this.BMDC = BMDC;
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

    public Doctor(String name, String email, String BMDC, String mobile, String pass) {
        this.name = name;
        this.email = email;
        this.BMDC = BMDC;
        this.mobile = mobile;
        this.pass = pass;
    }
}
