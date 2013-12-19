package com.mytechia.commons.patterns.observer;


/**
 * Interfaz que deben implementar sujetos observadores.
 * 
 * @author Alejandro Paz, Gervasio Varela
 * 
 */
public interface Observer
{
    /**
     * M?odo que invoca el sujeto observado cuando se produce una notificaci?n.
     * @param obj Objeto observado
     */
    void update(Object obj);
            
    
}