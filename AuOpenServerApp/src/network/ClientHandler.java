/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package network;


import domain.Prijava;
import domain.User;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;
import logic.Controller;

/**
 *
 * @author vuk
 */
public class ClientHandler implements Runnable {
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private Controller controller = new Controller();

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in  = new ObjectInputStream(socket.getInputStream());

            while (true) {
                System.out.println("Server: Čekam Request...");
                Request req = (Request) in.readObject();
                System.out.println(req.getData());
                System.out.println("Server: Primio request: " + req.getType());
                
                if (req.getType() == RequestType.LOGOUT) {
                    System.out.println("Klijent je poslao zahtev za odjavu.");
                    break;
                }
                switch(req.getType()){
                    case LOGIN:
                        System.out.println("Server: Obradjuje request");
                        handleLogin(req);
                        break;
                    case REGISTER:
                        System.out.println("Server: Obradjuje request");
                        handleRegister(req);
                        break;
                    case ADD:
                        System.out.println("Server: Obradjuje request");
                        handleAdd(req);
                        break;
                    case CHECK:
                         System.out.println("Server: Obradjuje request");
                        handleCheck(req);
                        break;
                    case LOAD:
                        System.out.println("Server: Obradjuje request");
                        handleLoad(req);
                        break;
                    case DEL:
                        System.out.println("Server: Obradjuje request");
                        handelDel(req);
                        break;
                    case EDIT:
                        System.out.println("Server: Obradjuje request");
                        handelEdit(req);
                        break;
                    default:
                        System.out.println("Nepoznati poziv za opbradu");
                        break;
                }
            }

        } catch (Exception e) {
            if(e instanceof SocketException){
                System.out.println("Klijent isključen (Socket zatvoren).");
            }else if(e instanceof EOFException){
                System.out.println("Klijent je iznenada zatvorio aplikaciju");
            }else{
                System.out.println("Klijent prekinuo vezu");
                e.printStackTrace();
            }
        }
    }

    private void handleLogin(Request req) throws IOException, Exception {
        try{
            User user = (User) req.getData();
            boolean success = false;
            user = controller.login(user);

            if(user.getId() != 0){
                success = true;
            }

            if (success) {
                out.writeObject(new Response(true, "Uspešan login", user));
            }else {
            out.writeObject(new Response(false, "Pogrešan username ili lozinka"));
            }
        }catch (Exception e) {
        e.printStackTrace();
        out.writeObject(new Response(false, "Greska pri prijavljivanju: " + e.getMessage()));
    } finally {
        out.flush();
    }
         
    }

    private void handleRegister(Request req) throws IOException, Exception {
        User user = (User) req.getData();

        controller.register(user);
        user = controller.login(user);
        boolean success = false;
        if(user.getId() != 0){
            success = true;
        }

        if (success) {
            out.writeObject(new Response(true, "Uspešano kreiran korisnik", user));
        } else {
            out.writeObject(new Response(false, "Korisnik već postoji"));
        }
        out.flush();
    }

    private void handleAdd(Request req) throws IOException {
        Response resp = new Response();
        try {
            Prijava prijava = (Prijava) req.getData();

            prijava = controller.addPrijava(prijava);

            resp.setSuccess(true);
            resp.setMessage("Uspešno dodata prijava!");
            resp.setPrijava(prijava);

        } catch (Exception e) {
            resp.setSuccess(false);
            resp.setMessage(e.getMessage()); 
            System.out.println("Greška pri dodavanju prijave: " + e.getMessage());
        }
        out.writeObject(resp);
        out.flush();
    }

    private void handleCheck(Request req) throws IOException, Exception {
        String[] podaci = (String[]) req.getData(); 
        boolean postoji = controller.proveriGosta(podaci[0], podaci[1]);
        if(postoji) {
            out.writeObject(new Response(true, "Pronađene su prijave."));
        } else {
            out.writeObject(new Response(false, "Ne postoji ni jedna prijava sa ovim podacima!"));
        }
        out.flush();
    }

    private void handleLoad(Request req) throws IOException, Exception {
        String[] parametri = (String[]) req.getData();
        try {
            List<Prijava> listaPrijava = controller.getPrijave(parametri[0], parametri[1]);
            out.writeObject(new Response(true, "Podaci su učitani.", listaPrijava));
        } catch (Exception e) {
            out.writeObject(new Response(false, e.getMessage()));
        }
        out.flush();
    }
    
    public void zatvoriSve() {
        try {
            if (socket != null) socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void handelDel(Request req) throws IOException, Exception {
        Prijava p = (Prijava) req.getData();
        try {
           boolean obrisano = controller.obrisiPrijavu(p);
            out.writeObject(new Response(true, "Prijava uspešno obrisana"));
        } catch (Exception e) {
            out.writeObject(new Response(false, e.getMessage()));
        }
        out.flush();
    }
    
     private void handelEdit(Request req) throws IOException, Exception {
        Prijava p = (Prijava) req.getData();
        try {
           boolean obrisano = controller.izmeniPrijavu(p);
            out.writeObject(new Response(true, "Prijava uspešno izmenjena"));
        } catch (Exception e) {
            out.writeObject(new Response(false, e.getMessage()));
        }
        out.flush();
    }
}
