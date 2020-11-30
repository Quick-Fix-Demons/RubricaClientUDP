/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientudp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;

/**
 *
 * @author Utente
 */
public class ClientUDP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int port = 1051;
        try {
            //creazione di tutti i parametri che mi servono
            InetAddress address = InetAddress.getByName("127.0.0.1");
            DatagramSocket socket = new DatagramSocket();
            byte[] buffer = new byte[1024];
            DatagramPacket request = new DatagramPacket(new byte[1], 1, address, port);
            DatagramPacket response = new DatagramPacket(buffer, buffer.length);
            String msg;
            
            System.out.println(" per scrivere dentro al programma dovrai inserire il numero di azione e il messaggio per interagire nella tua libreria ");
                    
            while (true) {
                //connessione al server
                socket.send(request);
                
                
                
                socket.receive(response); //ricevo il messaggio
                //messaggio che vado a prendere dal server (che ricevo)
                msg = new String(response.getData());
                //invio messaggio
                System.out.println(msg);
                
                
                
            }
 
        } catch (SocketTimeoutException ex) {
            System.out.println("Timeout error: " + ex.getMessage());
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Client error: " + ex.getMessage());
            ex.printStackTrace();
        }
   }
}
    

