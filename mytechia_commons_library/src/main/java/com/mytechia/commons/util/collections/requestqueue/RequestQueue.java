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

/** The implementors of this interface are generic queue classes that support
 * concurrent access to its contents, with the posibility of blocking behaviour
 * when the queue is empty.
 * 
 * They can be used to build multithread programs that use multiple worker
 * threads that share a common request queue.
 *
 * @author Gervasio Varela Fernandez
 * @version 1
 *
 * File: RequestQueue.java
 * Date: 11/7/2008
 * Changelog:
 *
 *      11/7/2008  --  Initial version
 */
public interface RequestQueue<E>
{

    
    public void push(E r);
    
    
    public E blockingPop();
    
    
    public E pop();
    
    
    public boolean isEmpty();
    
    
    public int size();
    
    
    public boolean contains(E r);
    
    
    
}
