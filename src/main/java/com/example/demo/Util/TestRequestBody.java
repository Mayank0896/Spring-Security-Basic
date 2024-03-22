package com.example.demo.Util;

public class TestRequestBody {
    private String user;
    private String password;

    public TestRequestBody() {

    }

    public TestRequestBody(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "TestRequestBody [user=" + user + ", password=" + password + "]";
    }

    

}
