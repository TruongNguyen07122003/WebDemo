/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registration;

import java.io.Serializable;

/**
 *
 * @author GIGABYTE
 */
public class RegistrationCreateErrors implements Serializable{
    private String usernameLengthErr;
    private String passworrdLengthErr;
    private String fullNameLengthErr;
    private String confirmLengthErr;
    private String usernameIsExisted;
    
    

    public RegistrationCreateErrors(String usernameLengthErr, String passworrdLengthErr, String fullNameLengthErr, String confirmLengthErr) {
        this.usernameLengthErr = usernameLengthErr;
        this.passworrdLengthErr = passworrdLengthErr;
        this.fullNameLengthErr = fullNameLengthErr;
        this.confirmLengthErr = confirmLengthErr;
        this.usernameLengthErr = usernameLengthErr;
    }

    public RegistrationCreateErrors() {
        
    }

    public String getUsernameLengthErr() {
        return usernameLengthErr;
    }

    public void setUsernameLengthErr(String usernameLengthErr) {
        this.usernameLengthErr = usernameLengthErr;
    }

    public String getPassworrdLengthErr() {
        return passworrdLengthErr;
    }

    public void setPassworrdLengthErr(String passworrdLengthErr) {
        this.passworrdLengthErr = passworrdLengthErr;
    }

    public String getFullNameLengthErr() {
        return fullNameLengthErr;
    }

    public void setFullNameLengthErr(String fullNameLengthErr) {
        this.fullNameLengthErr = fullNameLengthErr;
    }

    public String getConfirmLengthErr() {
        return confirmLengthErr;
    }

    public void setConfirmLengthErr(String confirmLengthErr) {
        this.confirmLengthErr = confirmLengthErr;
    }
    public String getUsernameIsExisted() {
        return usernameIsExisted;
    }

    public void setUsernameIsExisted(String usernameIsExisted) {
        this.usernameIsExisted = usernameIsExisted;
    }
    
    
}
