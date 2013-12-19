package com.mytechia.commons.patterns.observer;

import java.util.Collection;
import java.util.ArrayList;


/**
 * Clase que factoriza el comportamiento de un sujeto observado.
 * 
 * @author Alejandro Paz, Gervasio Varela 
 * 
 */
public  class AbstractSubject implements Subject 
{
    
    private Collection<Observer> observers = new ArrayList<Observer>();
    
    /**
     * M?todo para registrar un objeto como observador de este.
     * @param observador Observador a a?adir
     */
    public void add(Observer observer) {
        synchronized (observers) {
            observers.add(observer);
        }
    }
    
    /**
     * M?todo para desregistrar un observador del objeto.
     * @param observador Observador a eliminar
     */
    public void remove(Observer observer) {
        synchronized (observers) {
            try{
                observers.remove(observer);
            } catch(Exception e){
            }
        }
    }
    
    /**
     * M?todo para invocado por los observadores para indicar la ocurrencia de un cambio.
     * @param o Objeto a notificar como actualizado a los observadores
     */
    public void notify(Object obj) {
        synchronized (observers) {
            for(Observer o : observers) {
                o.update(obj);
            }
        }
    }
}
