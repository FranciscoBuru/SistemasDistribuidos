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
 * @author JGUTIERRGARC
 */
public class MulticastSenderPeer {
    	public static void main(String args[]){ 
  	 
	MulticastSocket s =null;
   	 try {
                //Grupos son dinámicos
                InetAddress group = InetAddress.getByName("228.5.6.7"); // destination multicast group 
	    	//Puerto en donde recives
                s = new MulticastSocket(6791);
	   	s.joinGroup(group); 
                //s.setTimeToLive(10);
                System.out.println("Messages' TTL (Time-To-Live): "+ s.getTimeToLive());
                String myMessage="Hello";
                byte [] m = myMessage.getBytes();
	    	//Tambien cambia el socket de abajo.
                DatagramPacket messageOut = 
			new DatagramPacket(m, m.length, group, 6791);
	    	//Manda mensajes
                s.send(messageOut);

	    	s.leaveGroup(group);		
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
