package com.mytechia.commons.patterns.observer;

/**
 * Interfaz que deben implementar los sujetos observados del patr?n observador.
 * 
 * @author Alejandro Paz
 * 
 */
public interface Subject 
{
    
    
	/**
	 * Registrar un objeto observador
	 * @param observador 
	 */
	void add(Observer observer);

        
	 /** 
	  * Desregistrar un observador de este objeto
	  * @param observador
	  */
	void remove(Observer observer);
	
	
        /**
	 * M?todo que invocan los observadores para notificar la ocurrencia de
         * un cambio en su estado.
	 * @param objeto
	 */
	void notify(Object object);
        
        
        
}
