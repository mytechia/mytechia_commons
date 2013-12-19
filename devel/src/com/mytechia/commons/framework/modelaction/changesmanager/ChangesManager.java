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


package com.mytechia.commons.framework.modelaction.changesmanager;

import com.mytechia.commons.patterns.observer.AbstractSubject;

/** It is in charge of receive information about changes in the model
 * of an application and the notification of those changes to the
 * intereseted objects.
 * 
 * For example, it can be used to notifie an IU when the model has be changed
 * to change the buttons for the save operations.
 * 
 * @author  Gervasio Varela Fernandez
 * @version 1
 *
 * File: ChangesManager.java
 * Date: 06/08/2008
 * Changelog:
 *
 *      06/08/2008  --  Initial version
 */
public class ChangesManager extends AbstractSubject
{

    private boolean hasUnsavedChanges = false;
    
        
    
    public ChangesManager()
    {
        
    }
    
    
    /** Marks the model as changed
     * 
     */
    public void modelChanged()
    {
        hasUnsavedChanges = true;
        notify(this);
    }
    
    
    /** Marks the model as saved, so it doesn't contains unsaved changes.
     * 
     */
    public void modelSaved()
    {
        hasUnsavedChanges = false;
        notify(this);
    }
    
    
    /** Returns whether the model has changed or not
     * 
     * @return true if the model has changes, false otherwise
     */
    public boolean hasUnsavedChanges()
    {
        return hasUnsavedChanges;
    }
    
    
    
}
