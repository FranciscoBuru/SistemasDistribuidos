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
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sdist
 */
public class relojSender {
    	public static void main(String args[]){ 
  	 
	MulticastSocket s =null;
   	 try {
                //Grupos son dinámicos
                InetAddress group = InetAddress.getByName("228.5.6.7"); // destination multicast group 
	    	//Puerto en donde recives
                s = new MulticastSocket(6791);
	   	s.joinGroup(group); 
                //s.setTimeToLive(10);
                String currentTime;
                while(true){
                    System.out.println("Messages' TTL (Time-To-Live): "+ s.getTimeToLive());
                    currentTime = (new Date()).toString();
                    byte [] m = currentTime.getBytes();
                    //Tambien cambia el socket de abajo.
                    DatagramPacket messageOut = 
                            new DatagramPacket(m, m.length, group, 6791);
                    //Manda mensajes
                    s.send(messageOut);
                    Thread.sleep(10);
                }
	    	//s.leaveGroup(group);		
 	    }
         catch (SocketException e){
            System.out.println("Socket: " + e.getMessage());
	 }
         catch (IOException e){
            System.out.println("IO: " + e.getMessage());
         }      
         catch (InterruptedException ex) {
            Logger.getLogger(relojSender.class.getName()).log(Level.SEVERE, null, ex);
        }
	 finally {
            if(s != null) s.close();
        }
    }		     
}

