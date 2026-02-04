/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import java.util.Scanner;
import network.Server;

/**
 *
 * @author vuk
 */
public class AuOpenServerMain {

    /**
     * @param args the command line arguments
     */
    
    private static final int PORT = 9000;
    
    public static void main(String[] args) {
        Server server = new Server();
        server.start();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Server pokrenut. Ukucaj 'KRAJ' za gašenje.");

        while (true) {
            String komanda = scanner.nextLine();
            if ("KRAJ".equalsIgnoreCase(komanda)) {
                server.stopServer();
                break;
            }
        }
        System.out.println("Glavni program završen.");
    }
    
}
