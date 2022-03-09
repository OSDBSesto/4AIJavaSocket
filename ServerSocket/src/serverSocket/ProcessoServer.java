/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverSocket;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pogliani.mattia
 */
public class ProcessoServer implements Runnable {

    private final Socket client;
    private final PrintWriter out;
    private final BufferedReader in;

    public ProcessoServer(Socket client) throws IOException {
        this.client = client;
        this.out = new PrintWriter(client.getOutputStream(), true);
        this.in = new BufferedReader(new InputStreamReader(client.getInputStream()));
    }

    @Override
    public void run() {
        boolean fine = false;
        String messaggio;
        try {
            while (!fine) {
                messaggio = in.readLine();
                if (messaggio.equals("fine")) {
                    fine = true;
                }
                out.println(messaggio.toUpperCase());
            }
            in.close();
            out.close();
            client.close();
        } catch (IOException ex) {
            Logger.getLogger(ProcessoServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
