package com.nine.mvp.bean;

/**
 * Created by Just For Mr.Jiu on 18/1/19.
 */

public class User {

    //    "status_code": "success",
//            "user_id": 1,
//            "user_name": "ABC2 XYZ",
//            "email": "abc2@xyz.com",
//            "access_token": "demo.token.from.mock.server",
//            "message": "Login Success"
    private String status_code;
    private String user_id;
    private String user_name;
    private String email;
    private String access_token;
    private String message;

    public String getStatus_code() {
        return status_code;
    }

    public void setStatus_code(String status_code) {
        this.status_code = status_code;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
