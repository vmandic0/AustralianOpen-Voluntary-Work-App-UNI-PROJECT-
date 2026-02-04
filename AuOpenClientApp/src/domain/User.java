/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author vuk
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String username;
    private String password;
    private String jmbg;
    private String email;
    private String ime;
    private String prezime;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, String jmbg, String email, String ime, String prezime) {
        this.username = username;
        this.password = password;
        this.jmbg = jmbg;
        this.email = email;
        this.ime = ime;
        this.prezime = prezime;
    }

    public User() {
    }
    
    

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getJmbg() {
        return jmbg;
    }

    public String getEmail() {
        return email;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setUsername(String username) throws Exception {
        if(username.length() < 3 || username == null){
            throw new Exception("Username mora da ima bar 3 karaktera");
        }
        this.username = username;
    }

    public void setPassword(String password) throws Exception {
        if(password == null || password.length() < 3){
            throw new Exception("Password mora da ima bar 3 karaktera");
        }
        this.password = password;
    }

    public void setJmbg(String jmbg) throws Exception {
        if(jmbg != null && jmbg.length() == 13 && jmbg.matches("\\d+")){
            this.jmbg = jmbg;
        }
        else throw new Exception("JMBG nema 13 cifara ili sadrzi neki karakter koji nije cifra");
    }

    public void setEmail(String email) throws Exception {
        if(email != null && email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}$")){
            this.email = email;
        }
        else throw new Exception("Email nije unet u korektnom formatu");
    }

    public void setIme(String ime) throws Exception {
        if(ime == null || ime.length() < 2){
            throw new Exception("Ime mora da ima bar 2 karaktera");
        }
        this.ime = ime;
    }

    public void setPrezime(String prezime) throws Exception {
        if(prezime == null || prezime.length() < 2){
            throw new Exception("Prezime mora da ima bar 2 karaktera");
        }
        this.prezime = prezime;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        return Objects.equals(this.username, other.username);
    }

     @Override
    public String toString() {
        return "User{" + "username=" + username + ", ime=" + ime + ", prezime=" + prezime + '}';
    }
    
}
