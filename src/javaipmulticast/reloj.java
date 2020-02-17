/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaipmulticast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;

/**
 *
 * @author sdist
 */
public class reloj {
    public static void main(String[] args) {
     
	MulticastSocket s =null;
   	 try {
                InetAddress group = InetAddress.getByName("228.5.6.7"); // destination multicast group 
	    	//Puerto en donde escuchas
                s = new MulticastSocket(6791);
	   	s.joinGroup(group); 

	    	byte[] buffer = new byte[1000];
 	   	//Receive bloqueante
                //Recive 3 
                for(int i=0; i< 200; i++) {
                    System.out.println("Waiting for messages " + i);
                    DatagramPacket messageIn = 
			new DatagramPacket(buffer, buffer.length);
 		    s.receive(messageIn);
 		    //System.out.println("Hora: " + new String(messageIn.getData()));
  	     	}
	    	s.leaveGroup(group);
                //Salida de grupo
 	    }
         catch (SocketException e){
             System.out.println("Socket: " + e.getMessage());
	 }
         catch (IOException e){
             System.out.println("IO: " + e.getMessage());
         }
	 finally {
            if(s != null) s.close();
        }    
    }
    
}
