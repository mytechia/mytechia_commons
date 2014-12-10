/*******************************************************************************
 *   
 *   Copyright 2008 Mytech Ingenieria Aplicada <http://www.mytechia.com>, Gervasio Varela, Alejandro Paz
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

package com.mytechia.commons.util.configuration;

import java.io.IOException;

/** Implementations of this interface provide access to the user preferences 
 *
 * @author  Gervasio Varela Fernandez, Alejandro Paz
 * @version 1
 *
 * File: UserConfiguration.java
 * Date: 10/07/2008
 * Changelog:
 *
 *      10/07/2008  --  Initial version
 */
public interface IUserConfiguration 
{

    
    public void storeConfiguration() throws IOException;
    
    
    public String getParam(String key);
    
    
    public void setParam(String key, String value);
    
    
}
