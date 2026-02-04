/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vuk
 */
public class Server extends Thread {
    private ServerSocket serverSocket;
    private final int PORT = 9000;
    private List<ClientHandler> clients = new ArrayList<ClientHandler>();

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(PORT);
            while (!isInterrupted()) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Klijent povezan!");
                
                ClientHandler handler = new ClientHandler(clientSocket);
                clients.add(handler);
                new Thread(handler).start();
            }
        } catch (IOException e) {
            if (serverSocket.isClosed()) {
                System.out.println("Server socket zatvoren");
            } else {
                e.printStackTrace();
            }
        }
    }

    public void stopServer() {
        try {
            
            this.interrupt();
            
            for (ClientHandler handler : clients) {
            handler.zatvoriSve();
            }
            
            if (serverSocket != null) {
                serverSocket.close();
            }
            
            System.out.println("Server uspešno stopiran.");
        } catch (IOException e) {
            System.err.println("Greška pri gašenju: " + e.getMessage());
        }
    }
}
