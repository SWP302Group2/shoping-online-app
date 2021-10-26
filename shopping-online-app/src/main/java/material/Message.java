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
public enum Message {
    //Message
    INITIALIZE(" :Initialize."),
    IP_ADDRESS("Ip Address: "),
    LOGIN_ACTION("-Login"),
    LOGOUT_ACTION("-Logout")
    ;
    
    private String message;

    private Message() {
    }

    private Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    
}
