package com.lict.android.healthiswealth;

class User {
    private String email,pass,type;

    public User(String email, String pass,String type) {
        this.email = email;
        this.pass = pass;
        this.type=type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
