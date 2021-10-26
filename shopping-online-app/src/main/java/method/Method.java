/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package method;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author hai
 */
public class Method {
    //email checking
    public static boolean checkEmail(String email) { 
        return email.matches("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?");

    }

//    public static boolean checkPassword(String password) {
//        if (password.length() < 8 || password.length() > 20) {
//            return false; // length 8-20
//        }
//        if (!password.matches("[A-Z]+")) {
//            return false; // at least 1 Uppercase char
//        }
//        if (password.contains(" ")) {
//            return false; // no white space
//        }
//        Pattern pt = Pattern.compile("\\p{Punct}");
//        Matcher match = pt.matcher(password);
//        if (match.find() == false) {
//            return false; // at least 1 special char 
//        }
//        return true;
//    }
    //password checking
    public static boolean checkPassword(String password){ 
        if(password.length() >5 && password.length() < 50) return true;
        else return false;
    }
    
    //firstname checking
    public static boolean checkFirstname(String firstname){
        if(firstname.length() > 2 && firstname.length() < 50) return true;
        else return false;
    }
    
    // lastname checking 
    public static boolean checkLastname(String lastname){
        if(lastname.length() > 2 && lastname.length() < 50) return true;
        else return false;
    } 
    
    // phone checking
    public static boolean checkPhone(String phone) {
        return phone.matches("^[0-9]+$");
    }
    
    //birthday checking
    public static boolean checkBirthday(String birthday){
        LocalDate localDateTime = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(birthday, formatter);
        if(localDateTime.compareTo(date) >= 0 ){
            return true;
        }else{
            return  false;
        }
        
    }
}
