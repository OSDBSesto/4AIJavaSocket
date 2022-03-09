/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverSocket;

import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pogliani.mattia
 */
public class Server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("Server partito");
            Socket clientSocket;
            ProcessoServer processo;
            Thread T;
            while (true) {
                clientSocket = serverSocket.accept();
                System.out.println("CONNESSIONE RICEVUTA: " + clientSocket.getInetAddress());
                processo = new ProcessoServer(clientSocket);
                T = new Thread(processo);
                T.start();
            }
            //serverSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
