/*******************************************************************************
 *   
 *   Copyright 2007 Mytech Ingenieria Aplicada <http://www.mytechia.com>, Gervasio Varela
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

package com.mytechia.commons.framework.modelaction.action;


/** This class should be extended by all classes that implement model operations.
 *  
 *
 *
 * @author  Gervasio Varela Fernandez
 * @version 1
 *
 * File: ModelAction.java
 * Date: 27 de noviembre de 2007
 * Changelog:
 *
 *      27 de noviembre de 2007  --  Initial version
 */
public abstract class ModelAction
{
    
    
    private String name = null;
    
    /**
     * @param name The name may be used by GUI elements to display the action performed.
     */
    public ModelAction(String name)
    {
        this.name = name;
    }


    public ModelAction()
    {
        this("");
    }
    
    
    public String getName()
    {
        return name;
    }
    
    
    public abstract Object execute() throws Exception;
    
    
}
