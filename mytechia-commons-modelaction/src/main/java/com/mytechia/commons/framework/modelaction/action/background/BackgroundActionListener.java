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

package com.mytechia.commons.framework.modelaction.action.background;



/** Implementos of this interface will be notified of the running state
 * of a BackgroundAction
 *
 * @author  Gervasio Varela Fernandez
 * @version 1
 *
 * File: BackgroundActionListener.java
 * Date: 6/3/2008
 * Changelog:
 *
 *      6/3/2008  --  Initial version
 */
public interface BackgroundActionListener 
{
    
    
    /** The BackgroundAction has completed its execution */
    public void actionCompleted(BackgroundAction action);
    
    
    /** The BackgroundAction thrown an exception */
    public void exceptionThrown(BackgroundAction action, Exception e);
    

}
