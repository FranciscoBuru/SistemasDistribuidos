/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TCPP2client;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class TCPClient extends Thread {
    @Override
    public void run(){
        int m = 10000;
        long arre[] = new long[m];
	Socket s = null;
        Random r = new Random();
	
	    try {
	    	int serverPort = 7896;
	   	
                s = new Socket("localhost", serverPort);    
             //   s = new Socket("127.0.0.1", serverPort);   
             //   Los de abajo se usan para mandar datos "simple"
		DataInputStream in = new DataInputStream( s.getInputStream());
	     //   Ya nos deja mandar tipos de datos primitivos, UTF es casi str
                String data;
                DataOutputStream out =
			new DataOutputStream( s.getOutputStream());
                
                
                long tiempo = 0;
                int env = 0;
                for(int i = 0; i<m ; i++){
                    env = (int) (Math.random() * 5);
                    long startTime = System.currentTimeMillis(); 
                    out.writeInt(env);
                    data = in.readUTF();
                    long endTime = System.currentTimeMillis()-startTime; 
                    tiempo = tiempo + endTime;
                    arre[i]=endTime;   
                }
                //System.out.println(tiempo);
                out.writeInt(5);
                float d = (float)m;
                float prom = (float)tiempo/d;
                float desv = (float) stdDev(arre);
                System.out.println(prom +","+ desv);
                // UTF is a string encoding 
                //Aqui el read y el write son bloqueantes pero el write a nivel
                //de capa de red
       	    } 
            catch (UnknownHostException e) {
		System.out.println("Sock:"+e.getMessage()); 
	    }
            catch (EOFException e) {
                System.out.println("EOF:"+e.getMessage());
    	    } 
            catch (IOException e) {
                System.out.println("IO:"+e.getMessage());
            } finally {
                if(s!=null) 
                    try {
                        s.close();
                    } catch (IOException e){
                    System.out.println("close:"+e.getMessage());}
                    }
            }
    
    private double stdDev(long[] list){
        double sum = 0.0;
        double num = 0.0;

        for (int i=0; i < list.length; i++)
        sum+=list[i];

        double mean = sum/list.length;
        for (int i=0; i <list.length; i++)
        num+=Math.pow((list[i] - mean),2);
        return Math.sqrt(num/list.length);
 }
}
