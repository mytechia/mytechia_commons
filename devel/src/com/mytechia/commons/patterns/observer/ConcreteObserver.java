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

/**
 * An observer object
 */
public class ConcreteObserver implements Observer {
    /** observer subject */
    private Subject observado;
    
    

    public ConcreteObserver(Subject observado){
        this.observado = observado;
    }
    
    /**
     * This method is called when a change in the observer object is detected
     */
    public void update(Object o){
    }
}
