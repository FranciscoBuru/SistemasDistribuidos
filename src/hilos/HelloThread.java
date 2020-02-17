/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hilos;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sdist
 */
public class HelloThread  extends Thread{
    //Implementación extendiendo clase Thread, es la forma "Impura"
    //El Try-catch va por el sleep, aveces no puedes dormir el hilo
    @Override
    public void run(){
        for(int i=0; i<100;i++){
            try {
                System.out.println(Thread.currentThread().getName() + " "+ i);
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(HelloThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
