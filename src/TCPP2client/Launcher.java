/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TCPP2client;

/**
 *
 * @author sdist
 */
public class Launcher {

        static int count = 0; 
  
    // The method that calls the main() method 
    public static void main(String[] args) {
        int n = 100;
        for(int i = 0; i < n ; i++){
            TCPClient cliente = new TCPClient();
            cliente.start();
        }
    }   
}



