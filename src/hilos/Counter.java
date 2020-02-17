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

//Se comprate este counter entre los dos threads (o los n threads)
public class Counter {
    private int n = 0;
    
    public Counter (int n){
        this.n = n;
    }
    //Se agrega synchronized para que las intrucciones de este método
    //solo las pueda ejecutar un hilo a la vez.
    //Los hilos compiten por un recurso y el primero que llegue es el que se
    //queda el recurso, no se entrelazan. Es como si los cuatro baños fueran
    // región crítica. Synch en aMethod e imprimir no existe con sout en aMe..()
    
    
    //Para que se entrelazen los hilos se piden regiones críticas por baño, asi
    //se entrelazan los threads. Para este paso se agregaron imprimir() y se 
    //quitó el sout y n++ y se movió a Imprimir()
    
    //Si quieres ver más de esto investiga de manejo de concurrencia da Java.
    public synchronized void imprimir(){
        n++;
        System.out.println(n + " " + Thread.currentThread().getName());
    }
    
    public void aMethod(){
        for(int i =0; i < 1000 ; i++){
            imprimir();
        }
    }
    
}
