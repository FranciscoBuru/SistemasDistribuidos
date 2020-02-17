package TCPP2server;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author JGUTIERRGARC
 */

import java.net.*;
import java.io.*;


public class TCPServer {
    
    public static void main (String args[]) {
	
        try{
		int serverPort = 7896; 
		ServerSocket listenSocket = new ServerSocket(serverPort);
		while(true) {
			System.out.println("Waiting for messages..."); 
                        //Accept crea el puente
                        
                        Socket clientSocket = listenSocket.accept();  
                        // Listens for a connection to be made to this socket and accepts it. The method blocks until a connection is made. 
			//Connection no es de Java, es un hilo en realidad.
                        //Su pusiera solo el rad entonces no podría escuchar a
                        //Otros clientes, por esto usamos Connection
                        Connection c = new Connection(clientSocket);
                        c.start();
		}
	} catch(IOException e) {System.out.println("Listen :"+ e.getMessage());}
    }
   
}

class Connection extends Thread {
	DataInputStream in;
	DataOutputStream out;
	Socket clientSocket;
        AddressBook addressBook = new AddressBook();
	public Connection (Socket aClientSocket) {
	    try {
		clientSocket = aClientSocket;
		in = new DataInputStream(clientSocket.getInputStream());
		out =new DataOutputStream(clientSocket.getOutputStream());
	     } catch(IOException e)  {System.out.println("Connection:"+e.getMessage());}
	}
        
        @Override
	public void run(){
	    
            try {			                 // an echo server
		int data = in.readInt();
                while(data>=0 && data<=4){
                    System.out.println("Numero: "+ data + " Nombre: " + addressBook.getRecord(data).getName());
                    out.writeUTF(addressBook.getRecord(data).getName());
                    data = in.readInt();
                }
	    } 
            catch(EOFException e) {
                System.out.println("EOF:"+e.getMessage());
	    } 
            catch(IOException e) {
                System.out.println("IO:"+e.getMessage());
	    } finally {
                try {
                    clientSocket.close();
                } catch (IOException e){
                    System.out.println(e);
                }
                }
            }
}


