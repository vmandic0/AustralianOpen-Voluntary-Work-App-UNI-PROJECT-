/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author vuk
 */
public class Prijava implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String ime;
    private String prezime;
    private String jmbg;
    private LocalDate datumVol;
    private String smena;
    private String pozicija;
    private LocalDate datumPrij;
    private String email;

    public Prijava(String ime, String prezime, String jmbg, LocalDate datumVol, String smena, String pozicija, LocalDate datumPrij, String email) {
        this.ime = ime;
        this.prezime = prezime;
        this.jmbg = jmbg;
        this.datumVol = datumVol;
        this.smena = smena;
        this.pozicija = pozicija;
        this.datumPrij = datumPrij;
        this.email = email;
    }

    public Prijava() {
    }
    

    public int getId() {
        return id;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public String getJmbg() {
        return jmbg;
    }

    public LocalDate getDatumVol() {
        return datumVol;
    }

    public String getSmena() {
        return smena;
    }

    public String getPozicija() {
        return pozicija;
    }

    public LocalDate getDatumPrij() {
        return datumPrij;
    }

    public String getEmail() {
        return email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public void setDatumVol(LocalDate datumVol) {
        this.datumVol = datumVol;
    }

    public void setSmena(String smena) {
        this.smena = smena;
    }

    public void setPozicija(String pozicija) {
        this.pozicija = pozicija;
    }

    public void setDatumPrij(LocalDate datumPrij) {
        this.datumPrij = datumPrij;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    

   
    
    

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Prijava other = (Prijava) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.ime, other.ime)) {
            return false;
        }
        if (!Objects.equals(this.prezime, other.prezime)) {
            return false;
        }
        if (!Objects.equals(this.jmbg, other.jmbg)) {
            return false;
        }
        return Objects.equals(this.datumVol, other.datumVol);
    }
    
    public String getStatus() {
    LocalDateTime sada = LocalDateTime.now();
        int satPocetka = switch (smena) {
            case "Jutarnja" -> 8;
            case "Popodnevna" -> 14;
            case "Večernja" -> 20;
            default -> 0;
        };

        LocalDateTime tacnoVremeSmene = datumVol.atTime(satPocetka, 0);
        long satiDoSmene = java.time.Duration.between(sada, tacnoVremeSmene).toHours();

        if (sada.isAfter(tacnoVremeSmene.plusHours(8))) return "Završena";
        if (satiDoSmene < 24 && satiDoSmene >= 0) return "Zaključana";
        if (satiDoSmene < 0) return "U toku (Zaključana)";

        return "U obradi";
    }
}
