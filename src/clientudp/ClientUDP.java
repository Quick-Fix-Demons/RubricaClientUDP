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
import java.util.Scanner;

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
            
            
            String msg=null;
            String numero;
            String sceltacasomodifica;
           
            Scanner sc=new Scanner(System.in);
            
            
            while (true) {
                System.out.println(" per scrivere dentro al programma dovrai inserire il numero di azione e il messaggio per interagire nella tua rugbrica  ");
                System.out.println(" 0 . ricerca per nome nella tua rubrica \n 1 . ricerca per numero nella tua rubrica \n 2 . aggiungi un numero dentro la tua rubrica \n 3 . modifica un numero dentro al tua rubrica \n");

                //scrivo quello che voglio fare,cioè il numero dell'operazione e il messaggio
                numero=sc.nextLine();
                
                if(numero.equals("0")){//ricerca per nome
                   System.out.println("hai scelto di ricercare nella tua rubrica per nome, scrivi il nome di chi vuoi ricercare \n");
                   String nomedacercare=sc.nextLine();
                   msg=numero+" - "+nomedacercare;
                }
                else if(numero.equals("1")){//ricerca per numero di telefono
                   System.out.println("hai scelto di ricercare nella tua rubrica per numero di telefono, scrivi il numero di telefono di chi vuoi ricercare \n");
                   String numeroditelefono=sc.nextLine();
                   msg=numero+" - "+numeroditelefono;
                }
                else if(numero.equals("2")){//aggiungi numero
                    System.out.println("hai scelto di inserire un nuovo numero nella rubrica bene, inserisci il numero di telefono e poi il nome per salvarlo \n");
                    String numerotelefono=sc.nextLine();
                    
                    String nomenellarubrica=sc.nextLine();
                    msg=numero+" - "+nomenellarubrica+" - "+numerotelefono;
                }
                else if(numero.equals("3")){//modifica
                    System.out.println("hai scelto di modificare un contatto, ora dovrai decidere se modificare il nome del contatto oppure il numero di telefono \n");
                    sceltacasomodifica=sc.nextLine();
                    if(sceltacasomodifica.equals("0")){//modifica  per nome
                        System.out.println("hai scelto di modificare il nome, bene ora scrivi il vecchio nome da modificare \n");
                        String vecchionome=sc.nextLine();
                        sc.nextLine();
                        System.out.println("\n ora scivi il nome nuovo che vuoi sostituire a quello vecchio");
                        String nomenuovo=sc.nextLine();
                        msg=numero+" - "+sceltacasomodifica+" - "+vecchionome+" - "+nomenuovo;
                    }
                    else if(sceltacasomodifica.equals("1")){//modifica per numero
                        System.out.println("hai scelto di modificare il numero di telefono, bene ora scrivi il vecchio numero di telefono da modificare \n");
                        String vecchionumero=sc.nextLine();
                        sc.nextLine();
                        System.out.println("\n ora scivi il nuovo numero di telefono che vuoi sostituire a quello vecchio");
                        String nuovonumero=sc.nextLine();
                        msg=numero+" - "+sceltacasomodifica+" - "+vecchionumero+" - "+nuovonumero;
                    }
                }
                else if(numero.equals("4")) {
                    msg = numero;
                }
                
                buffer=msg.getBytes();//metto dentro il messaggio che ho costruito in tutti gli if
                
                //connessione al server
                //invio i dati dalla socket
                DatagramPacket request = new DatagramPacket(buffer, buffer.length, address, port);
                socket.send(request);
                
                //creo l'array per ricevere i dati nella socket
                byte[] ricevirisposta = new byte[1024];
                DatagramPacket response = new DatagramPacket(ricevirisposta, ricevirisposta.length);
                socket.receive(response); //ricevo il messaggio
                System.out.println(new String(response.getData()));
                
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
    

