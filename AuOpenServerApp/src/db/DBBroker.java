/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import domain.Prijava;
import domain.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vuk
 */
public class DBBroker {
    
    private Connection connection;
    
    public void connect() throws SQLException {
        try {
            String url = "jdbc:mysql://localhost/AustralianOpen2k26";
            String user = "root";
            String password = "";
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Konekcija sa bazom podataka uspesno uspostavljena!");

        } catch (SQLException ex) {
            System.out.println("Greska! Konekcija sa bazom nije uspesno uspostavljena!\n" + ex.getMessage());
            ex.printStackTrace();
            throw ex;
        }
    }

     public void disconnect() throws SQLException {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Konekcija sa bazom uspesno raskinuta!");
            }
        } catch (SQLException ex) {
            System.out.println("Greska! Konekcija sa bazom nije uspesno raskinuta!\n" + ex.getMessage());
            ex.printStackTrace();
            throw ex;
        }
    }

    public User loginUser(User user) throws Exception {
        try {
            String query = "SELECT * FROM AustralianOpen2k26.Users u WHERE u.username = ? AND u.password = ? ";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());

            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                user.setId(rs.getInt("userId"));
                user.setJmbg(rs.getString("jmbg"));
                user.setEmail(rs.getString("email"));
                user.setIme(rs.getString("ime"));
                user.setPrezime(rs.getString("prezime"));
            }else{
                throw new Exception("Ne postoji korisnik sa zadatim kredencijalima!");
            }
            
            rs.close();
            statement.close();
            System.out.println("Uspesno ucitavanje objekta User iz baze!");
            return user;
        } catch (SQLException ex) {
            System.out.println("Objekat User nije uspesno ucitan iz baze!");
            ex.printStackTrace();
            throw ex;
        }
    }

    public void createUser(User user) throws SQLException, Exception {
        proveriJedinstvenost(user);
        
        try {
            String query ="INSERT INTO AustralianOpen2k26.Users (username, password, jmbg, email, ime, prezime) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";
            
            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getJmbg());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getIme());
            ps.setString(6, user.getPrezime());

        int result = ps.executeUpdate();
        System.out.println("Objekat User je uspesno dodat u bazu!");
        ps.close();

        } catch (SQLException ex) {
            System.out.println("Neuspesno dodavanje objekta User u bazu!\n" + ex.getMessage());
            ex.printStackTrace();
            throw ex;
        }
    }

    public Prijava addPrijava(Prijava prijava) throws SQLException, Exception {
        proveriLimitDana(prijava);
        proveriLimitPozicija(prijava);
        proveriLimitSmena(prijava);
        try {
            String query ="INSERT INTO AustralianOpen2k26.Prijave (ime, prezime, jmbg, DatumVolontiranja, smena, pozicija, "
                    + "DatumPrijave, email) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            
            PreparedStatement ps = connection.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, prijava.getIme());
            ps.setString(2, prijava.getPrezime());
            ps.setString(3, prijava.getJmbg());
            ps.setDate(4, java.sql.Date.valueOf(prijava.getDatumVol()));
            ps.setString(5, prijava.getSmena());
            ps.setString(6, prijava.getPozicija());
            ps.setDate(7, java.sql.Date.valueOf(prijava.getDatumPrij()));
            ps.setString(8, prijava.getEmail());

            int result = ps.executeUpdate();
            System.out.println("Objekat Prijava je uspesno dodat u bazu!");
            if (result > 0) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        prijava.setId(generatedKeys.getInt(1)); 
                    }
                }
                System.out.println("Objekat Prijava je uspesno dodat u bazu sa ID: " + prijava.getId());
            }
            ps.close();
            return prijava;

        } catch (SQLException ex) {
            System.out.println("Neuspesno dodavanje objekta User u bazu!\n" + ex.getMessage());
            ex.printStackTrace();
            throw ex;
        }
    }
    
    
    public boolean proveriGosta(String jmbg, String email) throws SQLException {
        String query = "SELECT COUNT(*) FROM AustralianOpen2k26.Prijave WHERE jmbg = ? AND email = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, jmbg);
            ps.setString(2, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }
    
    public List<Prijava> getPrijave(String jmbg, String email) throws SQLException, Exception {
        List<Prijava> lista = new ArrayList<>();
        String query = "SELECT * FROM AustralianOpen2k26.Prijave WHERE jmbg = ? AND email = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, jmbg);
            ps.setString(2, email);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Prijava p = new Prijava();
                    p.setId(rs.getInt("prijavaId"));
                    p.setIme(rs.getString("ime"));
                    p.setPrezime(rs.getString("prezime"));
                    p.setJmbg(rs.getString("jmbg"));
                    p.setDatumVol(rs.getDate("DatumVolontiranja").toLocalDate());
                    p.setSmena(rs.getString("smena"));
                    p.setPozicija(rs.getString("pozicija"));
                    p.setDatumPrij(rs.getDate("DatumPrijave").toLocalDate());
                    p.setEmail(rs.getString("email"));
                    lista.add(p);
                }
            }
        }
        return lista;
    }
    
    public boolean obrisiPrijavu(Prijava p) throws SQLException {
        String upit = "DELETE FROM AustralianOpen2k26.Prijave WHERE prijavaId=?;";
        PreparedStatement ps = connection.prepareStatement(upit);
        ps.setInt(1, p.getId());
        
        int rows = ps.executeUpdate();
        return rows > 0;
    }

    public boolean izmeniPrijavu(Prijava p) throws Exception {
        proveriLimitDanaUpdate(p);
        proveriLimitPozicijaUpdate(p);
        proveriLimitSmenaUpdate(p);
        
        String upit = "UPDATE Prijave SET DatumVolontiranja = ?, smena = ?, pozicija = ? WHERE prijavaId = ?";
    
        try (PreparedStatement ps = connection.prepareStatement(upit)) {
          
            ps.setDate(1, java.sql.Date.valueOf(p.getDatumVol()));
            ps.setString(2, p.getSmena());
            ps.setString(3, p.getPozicija());
            ps.setInt(4, p.getId()); 

            int rezultat = ps.executeUpdate();
            return rezultat > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    private void proveriLimitDana(Prijava prijava) throws Exception {
        String query = "SELECT COUNT(*) FROM AustralianOpen2k26.Prijave WHERE jmbg = ? AND DatumVolontiranja = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, prijava.getJmbg());
            ps.setDate(2, java.sql.Date.valueOf(prijava.getDatumVol()));

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    throw new Exception("Ograničenje: Već imate prijavu za datum " + prijava.getDatumVol());
                }
            }
        }
    }
    
    private void proveriLimitPozicija(Prijava prijava) throws Exception {
        List<String> postojecePozicije = new ArrayList<>();
        String query = "SELECT DISTINCT pozicija FROM AustralianOpen2k26.Prijave WHERE jmbg = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, prijava.getJmbg());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    postojecePozicije.add(rs.getString("pozicija"));
                }
            }
        }

        if (postojecePozicije.size() >= 2) {
            if (!postojecePozicije.contains(prijava.getPozicija())) {
                throw new Exception("Ograničenje: Već ste prijavljeni za dve različite pozicije: " 
                        + postojecePozicije.get(0) + " i " + postojecePozicije.get(1) 
                        + ". Ne možete odabrati treću različitu poziciju.");
            }
        }
    }
    
    private void proveriLimitSmena(Prijava prijava) throws Exception {
        String smena = prijava.getSmena();

        if ("Jutarnja".equalsIgnoreCase(smena)) {
            return;
        }

        String query = "SELECT COUNT(*) FROM AustralianOpen2k26.Prijave WHERE jmbg = ? AND smena = ?";
        int brojSmena = 0;

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, prijava.getJmbg());
            ps.setString(2, smena);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    brojSmena = rs.getInt(1);
                }
            }
        }

        if ("Vecernja".equalsIgnoreCase(smena) && brojSmena >= 3) {
            throw new Exception("Ograničenje: Već ste prijavljeni za maksimalnih 3 večernje smene.");
        }

        if ("Popodnevna".equalsIgnoreCase(smena) && brojSmena >= 5) {
            throw new Exception("Ograničenje: Već ste prijavljeni za maksimalnih 5 popodnevnih smena.");
        }
    }
    
    private void proveriLimitDanaUpdate(Prijava p) throws Exception {
        String query = "SELECT COUNT(*) FROM Prijave WHERE jmbg = ? AND DatumVolontiranja = ? AND prijavaId != ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, p.getJmbg());
            ps.setDate(2, java.sql.Date.valueOf(p.getDatumVol()));
            ps.setInt(3, p.getId());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    throw new Exception("Već imate prijavu za odabrani datum: " + p.getDatumVol());
                }
            }
        }
    }
    private void proveriLimitSmenaUpdate(Prijava p) throws Exception {
        if ("Jutarnja".equalsIgnoreCase(p.getSmena())) return;

        String query = "SELECT COUNT(*) FROM Prijave WHERE jmbg = ? AND smena = ? AND prijavaId != ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, p.getJmbg());
            ps.setString(2, p.getSmena());
            ps.setInt(3, p.getId());

            int broj = 0;
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) broj = rs.getInt(1);
            }

            if ("Vecernja".equalsIgnoreCase(p.getSmena()) && broj >= 3) 
                throw new Exception("Maksimalno 3 večernje smene dozvoljene.");
            if ("Popodnevna".equalsIgnoreCase(p.getSmena()) && broj >= 5) 
                throw new Exception("Maksimalno 5 popodnevnih smena dozvoljeno.");
        }
    }
    private void proveriLimitPozicijaUpdate(Prijava p) throws Exception {
        List<String> postojecePozicije = new ArrayList<>();
        String query = "SELECT DISTINCT pozicija FROM Prijave WHERE jmbg = ? AND prijavaId != ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, p.getJmbg());
            ps.setInt(2, p.getId());

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    postojecePozicije.add(rs.getString("pozicija"));
                }
            }
        }
        if (postojecePozicije.size() >= 2) {
            if (!postojecePozicije.contains(p.getPozicija())) {
                throw new Exception("Ograničenje: Već ste prijavljeni za dve različite pozicije: " 
                        + postojecePozicije.get(0) + " i " + postojecePozicije.get(1) 
                        + ". Ne možete promeniti na treću različitu poziciju.");
            }
        }
    }

    private void proveriJedinstvenost(User user) throws Exception {
        String checkQuery = "SELECT username, jmbg FROM AustralianOpen2k26.Users WHERE username = ? OR jmbg = ?";
    
        try (PreparedStatement checkPs = connection.prepareStatement(checkQuery)) {
            checkPs.setString(1, user.getUsername());
            checkPs.setString(2, user.getJmbg());

            try (ResultSet rs = checkPs.executeQuery()) {
                if (rs.next()) {
                    String postojeciUsername = rs.getString("username");
                    String postojeciJmbg = rs.getString("jmbg");

                    if (postojeciUsername.equals(user.getUsername())) {
                        throw new Exception("Greška: Korisničko ime '" + user.getUsername() + "' je zauzeto.");
                    }
                    if (postojeciJmbg.equals(user.getJmbg())) {
                        throw new Exception("Greška: Korisnik sa JMBG-om " + user.getJmbg() + " već postoji.");
                    }
                }
            }
        }
    }
}
