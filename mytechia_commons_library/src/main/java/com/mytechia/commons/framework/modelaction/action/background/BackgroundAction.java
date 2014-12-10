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

import com.mytechia.commons.framework.modelaction.action.ModelAction;
import java.util.ArrayList;
import java.util.Collection;

/** This speciallization of ModelAction supports its execution in a background thread.
 * Is usefull when an action has a ver large execution time and the developer wants
 * that the GUI continues responding while the acction is running.
 *
 * @author  Gervasio Varela Fernandez
 * @version 1
 *
 * File: BackgroundAction.java
 * Date: 6/3/2008
 * Changelog:
 *
 *      6/3/2008  --  Initial version
 */
public abstract class BackgroundAction extends ModelAction
{
    
    
    private Collection<BackgroundActionListener> listeners = 
            new ArrayList<BackgroundActionListener>(1);

    
    
    public BackgroundAction(String name) {
        super(name);
    }
    
    
    
    public void addListener(BackgroundActionListener listener)
    {
        listeners.add(listener);
    }
    
    
    public void removeListener(BackgroundActionListener listener)
    {
        listeners.remove(listener);
    }

    
    
    protected void notifyCompletion()
    {
        for(BackgroundActionListener l : listeners)
            l.actionCompleted(this);
    }
    
    
    protected void notifyException(Exception e)
    {
        for(BackgroundActionListener l : listeners)
            l.exceptionThrown(this,e);
    }
    
    
    
    /** A new thread is automatically created to execute the action */
    public void executeInBackground() 
    {
        
        Thread t = new Thread() {

            public void run() {
                try{
                    execute();
                }catch(Exception e) {
                    notifyException(e);
                }
            }
        };
        
        t.start();
        
    }
    
    
}
