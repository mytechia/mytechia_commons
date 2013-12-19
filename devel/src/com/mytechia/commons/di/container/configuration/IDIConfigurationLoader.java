/*******************************************************************************
 *   
 *   Copyright 2009 Mytech Ingenieria Aplicada <http://www.mytechia.com>, Gervasio Varela
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

package com.mytechia.commons.di.container.configuration;

import com.mytechia.commons.di.container.IDIContainer;

/**
 * <p><b>Description:</b></br>
 * Concrete implementations of this interface provide support for populating
 * an IDIContainer from a configuration source like a file, URL, etc.
 * </p>
 *
 * <p><b>Creation date:</b> 03/08/2009</p>
 *
 * <p><b>Changelog:</b></br>
 * <ul>
 * <li>1 - 03/08/2009<\br> Initial release</li>
 * </ul>
 * </p>
 *
 * @author Gervasio Varela Fernandez
 * @version 1
 *
 */
public interface IDIConfigurationLoader
{
    
	/** Populates an IDIContainer from a configuration source
     *
     * @param container the container to populate
	 */
	public void populateContainer( IDIContainer container )
            throws ClassNotFoundException;
	
}
