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

package com.mytechia.commons.framework.i18n;


/** This class is used to manage the internacionalization support on an application.
 * An II18NInstance could be register and then, other classes could call the
 * getI18NInstance() method of this singleton to obtain the concrete implementation
 * of II18NInstance that should be used for the applications.
 * 
 * This is usefull when the II18NInstance should use an application's configuration
 * file to load language configuration, etc.
 * 
 *
 * @author  Gervasio Varela Fernandez
 * @version 1
 *
 * File: I18NManager.java
 * Date: 5/2/2008
 * Changelog:
 *
 *      5/2/2008  --  Initial version
 */
public class I18NManager 
{

    
    private II18NInstance registeredII18NInstance = null;
    
    
    private static I18NManager ref = null;
    
    
    private I18NManager() {}
    
    
    public static I18NManager getInstance()
    {
        
        if (ref == null)
            ref = new I18NManager();
        
        return ref;
        
    }
    
    
    public void registerI18NInstance(II18NInstance instance)
    {
        this.registeredII18NInstance = instance;
    }
    
    
    public II18NInstance getI18NInstance()
    {
        return registeredII18NInstance;
    }
    
    
}
