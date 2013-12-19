package com.mytechia.commons.patterns.observer;

/**
 * Clase que factoriza el comportamiento de el sujeto Observador.
 */
public class ConcreteObserver implements Observer {
    /** Sujeto observado */
    private Subject observado;
    
    
    /**
     * Constructor de clase.
     * @param observado Objeto observado relacionado con este observador.
     */
    public ConcreteObserver(Subject observado){
        this.observado = observado;
    }
    
    /**
     * Implementaci?n vac?a del m?todo actualizar
     */
    public void update(Object o){
    }
}
