package com.cgapp.bean;

/**
 * Created by asus on 2017/4/10.
 */

public class UserBean {
    private String name;
    private String id;
    private String password;
    public UserBean(String id,String password)
    {
        this.id = id;
        this.password = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserBean(String name, String id, String password)
    {
        this.name = name;
        this.id = id;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
