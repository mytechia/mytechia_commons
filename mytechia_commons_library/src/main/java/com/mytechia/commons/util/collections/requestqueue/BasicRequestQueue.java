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

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Gervasio Varela Fernandez
 */
public class BasicRequestQueue<E> implements RequestQueue<E>
{

    private Queue<E> queue = null;
    
    
    public BasicRequestQueue()
    {
        queue = new LinkedList<E>();
    }
    
    
    public void push(E r) 
    {
        
        synchronized(queue) {
            queue.add(r);
            queue.notify();
        }
        
    }

    public E blockingPop() 
    {
        
        synchronized(queue) {
            try {
                
                if (queue.isEmpty()) {
                    queue.wait();
                }
                
                return queue.poll();
                
            } catch (InterruptedException ex) 
            {
                return null;
            }            
        }
        
    }

    public E pop() 
    {
        synchronized(queue) {
            
            if (!queue.isEmpty())
                return queue.poll();
            else
                return null;
            
        }
    }

    public boolean isEmpty() 
    {
        return queue.isEmpty();
    }

    public int size() 
    {
        return queue.size();
    }
    
    public boolean contains(E r) 
    {
        synchronized(queue) {
            return queue.contains(r);
        }
    }

}
