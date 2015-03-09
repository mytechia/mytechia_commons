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

package com.mytechia.commons.patterns.prototype;

/**
 * <p><b>Description:</b></p>
 * Indicates that an object supports the clone() operation to act as a
 * prototype object.
 *
 *
 * <p><b>Creation date:</b> 03/07/2009</p>
 *
 * <p><b>Changelog:</b></p>
 *
 * <ul>
 * <li>1 - 03/07/2009 Initial release</li>
 * </ul>
 *
 * @author Gervasio Varela Fernandez
 * @version 1
 *
 */
public interface IPrototype extends Cloneable
{


    /** Creates a new instance that is an identical copy of this instance.
     *
     * @return a new instance that is an identical copy of this instance
     */
    public Object clone();


    public boolean equals(Object obj);


    public int hashCode();
    

}
