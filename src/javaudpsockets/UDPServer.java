
// "contenido", localhost


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaudpsockets;

import java.net.*;
import java.io.*;

public class UDPServer{
	
        public static void main(String args[]){ 
	DatagramSocket aSocket = null;
	   try{
	    	int serverPort = 6789;
                aSocket = new DatagramSocket(serverPort); 
		byte[] buffer = new byte[1000]; // buffer encapsulará mensajes
 		while(true){
                   System.out.println("Waiting for messages..."); 
 		   //Aquí no está dormido, está esperando; es un metodo bloqueante.
                   DatagramPacket request = new DatagramPacket(buffer, buffer.length);
  		   aSocket.receive(request);     
                   
    		   DatagramPacket reply = new 
                        DatagramPacket( request.getData(), 
                                        request.getLength(),
                                        request.getAddress(),
                                        request.getPort());
                   
                   System.out.println("Server received a request from "+ request.getAddress());
		   aSocket.send(reply);
		}
	   }
           catch (SocketException e){
                System.out.println("Socket: " + e.getMessage());
	   }
           catch (IOException e) {
               System.out.println("IO: " + e.getMessage());
           }
           finally {
               //Ciera el Socket, si no lo cierras se queda reservado el recurso
                if(aSocket != null) 
                    aSocket.close();
           }
    }
}
