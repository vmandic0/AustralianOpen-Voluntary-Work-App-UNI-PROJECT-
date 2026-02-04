/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package network;

import domain.Prijava;
import domain.User;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author vuk
 */
public class Response implements Serializable {
   private static final long serialVersionUID = 1L;
    private boolean success;
    private String message;
    private User user;
    private Prijava prijava;
    private List<Prijava> prijave;

    public Response(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public Response(boolean success, String message, User user) {
        this.success = success;
        this.message = message;
        this.user = user;
    }
    public Response(boolean success, String message, Prijava prijava) {
        this.success = success;
        this.message = message;
        this.prijava = prijava;
    }
    
    public Response(boolean success, String message, List<Prijava> prijave) {
        this.success = success;
        this.message = message;
        this.prijave = prijave;
    }

    public Response() {
    }
    
    

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public User getUser() {
        return user;
    }

    public Prijava getPrijava() {
        return prijava;
    }

    public List<Prijava> getPrijave() {
        return prijave;
    }

    public void setPrijave(List<Prijava> prijave) {
        this.prijave = prijave;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setPrijava(Prijava prijava) {
        this.prijava = prijava;
    }
    
    
}
