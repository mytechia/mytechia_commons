/*******************************************************************************
 *   
 *   Copyright 2008 Mytech Ingenieria Aplicada <http://www.mytechia.com>, Gervasio Varela
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

package com.mytechia.commons.util.collections.requestqueue;

import java.util.LinkedHashSet;
import java.util.Set;

/** This class extends the behaviour of BasicRequestQueue by adding
 * pop confirmation.
 * 
 * When the user do a pop operation, the popped object is marked and the
 * queue will not accept that objetc again (using the equals operation) until
 * the user confirms the processing of the object throught the confirm() method.
 *
 * @author Gervasio Varela
 */
public class RequestQueueWithProcessingConfirmation<E> extends BasicRequestQueue<E> 
{
    
    
    private Set<E> unconfirmedObjects = null;
    
    
    public RequestQueueWithProcessingConfirmation()
    {
        
        super();
        
        unconfirmedObjects = new LinkedHashSet<E>();
        
    }

    
    public void confirm(E e)
    {
        
        synchronized(unconfirmedObjects) {
            unconfirmedObjects.remove(e);
        }
        
    }
    
    
    @Override
    public E blockingPop() 
    {
        
        E e = super.blockingPop();
    
        synchronized(unconfirmedObjects) {
            unconfirmedObjects.add(e);
        }
        
        return e;
        
    }

    
    @Override
    public E pop() 
    {
        
        E e = super.pop();
        
        synchronized(unconfirmedObjects) {
            unconfirmedObjects.add(e);
        }
        
        return e;
        
    }

        
    @Override
    public void push(E r) 
    {
        
        if (!unconfirmedObjects.contains(r) && !super.contains(r)) {
            super.push(r);
        }
        
    }

    
    @Override
    public boolean contains(E r) 
    {
        
        return (super.contains(r) || unconfirmedObjects.contains(r));
        
    }

    
    @Override
    public int size() 
    {
        
        return (super.size() + unconfirmedObjects.size());
        
    }
    
    
    
    

}
