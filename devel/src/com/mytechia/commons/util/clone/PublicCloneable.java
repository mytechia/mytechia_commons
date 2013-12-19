/*******************************************************************************
 *   
 *   Copyright 2010 Mytech Ingenieria Aplicada <http://www.mytechia.com>, Alejandro Paz
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

package com.mytechia.commons.util.clone;

/**
 * <p><b>Description:</b></br>
 *
 * </p>
 *
 * <p><b>Creation date:</b> 19-05-2010</p>
 *
 * <p><b>Changelog:</b></br>
 * <ul>
 * <li>1 - 19-05-2010<\br> Initial release</li>
 * </ul>
 * </p>
 *
 * @author Alejandro Paz
 * @version 1
 */
public interface PublicCloneable extends Cloneable
{

    public Object clone() throws CloneNotSupportedException;
    
}
