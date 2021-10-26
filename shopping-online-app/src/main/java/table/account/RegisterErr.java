/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package table.account;

/**
 *
 * @author hai
 */
public class RegisterErr {
    private String emailFormatError;
    private String passwordFormatError;
    private String confirmNotMatch;
    private String firstnameLengthError;
    private String lastnameLengthError;

    public String getFirstnameLengthError() {
        return firstnameLengthError;
    }

    public void setFirstnameLengthError(String firstnameLengthError) {
        this.firstnameLengthError = firstnameLengthError;
    }

    public String getLastnameLengthError() {
        return lastnameLengthError;
    }

    public void setLastnameLengthError(String lastnameLengthError) {
        this.lastnameLengthError = lastnameLengthError;
    }

    public String getExErr() {
        return exErr;
    }

    public void setExErr(String exErr) {
        this.exErr = exErr;
    }
    private String birthdayFormatError;
    private String phoneFormatError;
    private String emailAlreadyExist;
    private String exErr;

    public String getEmailFormatError() {
        return emailFormatError;
    }

    public void setEmailFormatError(String emailFormatError) {
        this.emailFormatError = emailFormatError;
    }

    public String getPasswordFormatError() {
        return passwordFormatError;
    }

    public void setPasswordFormatError(String passwordFormatError) {
        this.passwordFormatError = passwordFormatError;
    }

    public String getConfirmNotMatch() {
        return confirmNotMatch;
    }

    public void setConfirmNotMatch(String confirmNotMatch) {
        this.confirmNotMatch = confirmNotMatch;
    }

    public String getBirthdayFormatError() {
        return birthdayFormatError;
    }

    public void setBirthdayFormatError(String birthdayFormatError) {
        this.birthdayFormatError = birthdayFormatError;
    }

    public String getPhoneFormatError() {
        return phoneFormatError;
    }

    public void setPhoneFormatError(String phoneFormatError) {
        this.phoneFormatError = phoneFormatError;
    }

    public String getEmailAlreadyExist() {
        return emailAlreadyExist;
    }

    public void setEmailAlreadyExist(String emailAlreadyExist) {
        this.emailAlreadyExist = emailAlreadyExist;
    }
}
