/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javatcpsockets;

import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.Person;

public class TCPClient {

    public static void main (String args[]) {

	Socket s = null;
	    try {
	    	int serverPort = 7896;
	   	Person p = new Person(1,"Pedro");
                s = new Socket("localhost", serverPort);    
                 
		ObjectInputStream in = new ObjectInputStream( s.getInputStream());
		ObjectOutputStream out =
			new ObjectOutputStream( s.getOutputStream());
		   	 
                out.writeObject(p);
		Object data = in.readObject();	      
                System.out.println("Received: "+ ((Person)data).getName()) ;      
       	    } 
            catch (UnknownHostException e) {
		System.out.println("Sock:"+e.getMessage()); 
	    }
            catch (EOFException e) {
                System.out.println("EOF:"+e.getMessage());
    	    } 
            catch (IOException e) {
                System.out.println("IO:"+e.getMessage());
            } catch (ClassNotFoundException ex) {
            Logger.getLogger(TCPClient.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
                if(s!=null) 
                    try {
                        s.close();
                    } catch (IOException e){
                    System.out.println("close:"+e.getMessage());}
                    }
            }
}
