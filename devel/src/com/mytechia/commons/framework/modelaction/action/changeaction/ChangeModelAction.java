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

package com.mytechia.commons.framework.modelaction.action.changeaction;

import com.mytechia.commons.framework.modelaction.action.ModelAction;
import com.mytechia.commons.framework.modelaction.changesmanager.ChangesManager;


/** An action that makes changes on the model of the application. It will
 * automatically notify changes to a ChangesManager object.
 * 
 * 
 * @author  Gervasio Varela Fernandez
 * @version 1
 *
 * File: ChangeModelAction.java
 * Date: 06/08/2008
 * Changelog:
 *
 *      06/08/2008  --  Initial version
 */
public abstract class ChangeModelAction extends ModelAction
{
    
    
    private ChangesManager changesManager = null;

    
    
    /**
     * 
     * @param name
     * @param changesManager It can be null, in that case the notification wouldn't occur
     */
    public ChangeModelAction(String name, ChangesManager changesManager) 
    {
        super(name);
        this.changesManager = changesManager;
    }

    
    
    /** Template method to factorize the execution of the change model
     * notification.
     * 
     * @return 
     * @throws java.lang.Exception
     */
    @Override
    public Object execute() throws Exception 
    {
        Object ret = executeChange();
        if (changesManager != null)
            changesManager.modelChanged();
        return ret;
    }
    
    
    /** In this method should be implemented the concrete behaviour of
     * the Action classes.
     */
    public abstract Object executeChange() throws Exception;


}
