package com.example.ttest.model;

public class UserLoginRequest {
    private Integer id;//要跟request body裡的key一樣
    private String name;
    private String userPwd;
    private String userAuth;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserPwd() {
        return userPwd;
    }


    public String getUserAuth() {
        return userAuth;
    }

    public void setUserAuth(String userAuth) {
        this.userAuth = userAuth;
    }
}
