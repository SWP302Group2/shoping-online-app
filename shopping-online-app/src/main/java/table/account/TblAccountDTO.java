/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package table.account;

import java.util.Date;

/**
 *
 * @author hai
 */
public class TblAccountDTO {

    private int userId;
    private String email;
    private String password;
    private String lastname;
    private String firstname;
    private String birthday;
    private String phone;
    private String avatar;
    private int role;
    private int status;
    private String accessToken;
    private String userip;

    public TblAccountDTO() {
    }

    public TblAccountDTO(int userId, String email, String password, String lastname, String firstname, String birthday, String phone, String avatar, int role, int status, String accessToken, String userip) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.lastname = lastname;
        this.firstname = firstname;
        this.birthday = birthday;
        this.phone = phone;
        this.avatar = avatar;
        this.role = role;
        this.status = status;
        this.accessToken = accessToken;
        this.userip = userip;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getUserip() {
        return userip;
    }

    public void setUserip(String userip) {
        this.userip = userip;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String fullName() {
        return lastname + firstname;
    }
}
