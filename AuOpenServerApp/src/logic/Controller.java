/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logic;

import db.DBBroker;
import domain.Prijava;
import domain.User;
import java.util.List;

/**
 *
 * @author vuk
 */
public class Controller {
    private DBBroker dbbr;
    
    public Controller(){
        dbbr=new DBBroker();
    }

    public User login(User user) throws Exception {
        try {
            dbbr.connect();
        } catch (Exception ex) {
            throw new Exception("Neuspesno povezivanje sa bazom!");
        }
        try {
            User dbUser=dbbr.loginUser(user);
            return dbUser;
        } catch (Exception ex) {
            throw new Exception("Neuspesno prijavljivanje na sistem! Proverite username i passsword!");
        }finally{
            dbbr.disconnect();
        }
    }

    public void register(User user) throws Exception {
        try {
             dbbr.connect();
        } catch (Exception ex) {
             throw new Exception("Neuspesno povezivanje sa bazom!");
        }
        try {
             dbbr.createUser(user);
        } catch (Exception ex) {
             throw new Exception("Neuspesno kreiranje korisnija! " + ex);
        }finally{
           dbbr.disconnect();
        }
    }

    public Prijava addPrijava(Prijava prijava) throws Exception {
        try {
             dbbr.connect();
        } catch (Exception ex) {
             throw new Exception("Neuspesno povezivanje sa bazom!");
        }
        try {
             prijava = dbbr.addPrijava(prijava);
             return prijava;
        } catch (Exception ex) {
             throw new Exception(ex.getMessage());
        }finally{
           dbbr.disconnect();
        }

    }

    public boolean proveriGosta(String jmbg, String email) throws Exception {
        try {
             dbbr.connect();
        } catch (Exception ex) {
             throw new Exception("Neuspesno povezivanje sa bazom!");
        }
        try {
             return dbbr.proveriGosta(jmbg, email);
        } catch (Exception ex) {
             throw new Exception(ex.getMessage());
        }finally{
           dbbr.disconnect();
        }
    }

    public List<Prijava> getPrijave(String jmbg, String email) throws Exception {
        try {
             dbbr.connect();
        } catch (Exception ex) {
             throw new Exception("Neuspesno povezivanje sa bazom!");
        }
        try {
             return dbbr.getPrijave(jmbg, email);
        } catch (Exception ex) {
             throw new Exception(ex.getMessage());
        }finally{
           dbbr.disconnect();
        }
    }
    
    public boolean obrisiPrijavu(Prijava p) throws Exception{
        try {
             dbbr.connect();
        } catch (Exception ex) {
             throw new Exception("Neuspesno povezivanje sa bazom!");
        }
        try {
             return dbbr.obrisiPrijavu(p);
        } catch (Exception ex) {
             throw new Exception(ex.getMessage());
        }finally{
           dbbr.disconnect();
        }
    }

    public boolean izmeniPrijavu(Prijava p) throws Exception {
          try {
             dbbr.connect();
        } catch (Exception ex) {
             throw new Exception("Neuspesno povezivanje sa bazom!");
        }
        try {
             return dbbr.izmeniPrijavu(p);
        } catch (Exception ex) {
             throw new Exception(ex.getMessage());
        }finally{
           dbbr.disconnect();
        }
    }
}
