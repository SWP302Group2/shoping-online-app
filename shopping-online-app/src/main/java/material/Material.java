/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package material;

/**
 *
 * @author hai
 */
public enum Material {
    EXIST_EMAIL("Email already exist!"),
    INCORRECTFORMAT_EMAIL("Email incorrect format!"),
    INCORRECTFORMAT_PASSWORD("Password incorrect format!"),
    INCORRECTCONFIRM_PASSWORD("Confirm not match!"),
    INCORRECTFORMAT_FIRSTNAME("First name incorrect format!"),
    INCORRECTFORMAT_LASTNAME("Last name incorrect format!"),
    INCORRECTFORMAT_PHONE("Phone number incorrect format!"),
    INCORRECTFORMAT_BIRTHDAY("Your birth day is invalid!"),
    ERROR_BIRTHDAY("Please select your birthday!"),
    INCORRECT_OLDPASSWORD("Your password is incorrect!"),
    PASSWORD_CHANGE("Your new password is same as old password"),
    SUCCEED_MESSAGE("Success"),
    FAIL_MESSAGE("Fail"),
    UPDATE_FIRSTNAME("New firstname is same as old firstname!"),
    UPDATE_LASTNAME("New last is same as old Lastname!"),
    UPDATE_PHONE("New phone is same as old phone!"),
    UPDATE_BIRTHDAY("New birthday is same as old birthday!"),
    INVALID_EMAILORPASSWORD("Wrong user name or password!"),
    BANNED_MESSAGE("Your account is banned"),
    ;
    private String text;

    private Material() {
    }

    private Material(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    
}
