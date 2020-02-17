/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hilos;

/**
 *
 * @author sdist
 */
//Aqui hago que dos instancias de thread tienen acceso a un mismo counter
//de manera concurrente
public class SynchronizedThread extends Thread{
    
    private Counter counter;
    //Solo nos pasa la referencia
    public SynchronizedThread(Counter counter){
        this.counter = counter;
    }
    
    @Override
    public void run(){
        counter.aMethod();
    }
}
