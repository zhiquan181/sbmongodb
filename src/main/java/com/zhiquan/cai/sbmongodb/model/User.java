package com.zhiquan.cai.sbmongodb.model;

import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Document(collection="user")
public class User {
    private String userId;
    private String userName;
    private String passWord;
    private String email;
    private Date birthday;
    private int age;
    private int dataStatus;

    public User() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getDataStatus() { return dataStatus; }

    public void setDataStatus(int dataStatus) {
        this.dataStatus = dataStatus;
    }
}