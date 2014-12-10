/*******************************************************************************
 *   
 *   Copyright 2013 Mytech Ingenieria Aplicada <http://www.mytechia.com>
 * 
 *   This file is part of Mytechia Commons.
 *
 *   Mytechia Commons is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU Lesser General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   Mytechia Commons is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU Lesser General Public License for more details.
 *
 *   You should have received a copy of the GNU Lesser General Public License
 *   along with Mytechia Commons.  If not, see <http://www.gnu.org/licenses/>.
 * 
 ******************************************************************************/

package com.mytechia.commons.patterns.observer;

import java.util.Collection;
import java.util.ArrayList;


/**
 * A subject that can be observed
 * 
 * @author Alejandro Paz, Gervasio Varela 
 * 
 */
public  class AbstractSubject implements Subject 
{
    
    private Collection<Observer> observers = new ArrayList<Observer>();
    
    /**
     * Registers an observer
     */
    public void add(Observer observer) {
        synchronized (observers) {
            observers.add(observer);
        }
    }
    
    /**
     * Unregisters an observer
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
     * Notifes changes in this subject to observer objects
     */
    public void notify(Object obj) {
        synchronized (observers) {
            for(Observer o : observers) {
                o.update(obj);
            }
        }
    }
}
