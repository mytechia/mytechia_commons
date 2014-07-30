/*******************************************************************************
 *   
 *   Copyright 2010 Mytech Ingenieria Aplicada <http://www.mytechia.com>, Gervasio Varela
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

package com.mytechia.commons.framework.exception.db;

import com.mytechia.commons.framework.exception.InternalErrorException;


/**
 * <p><b>Description:</b>
 *
 * </p>
 *
 * <p><b>Creation date:</b> 29-nov-2010</p>
 *
 * <p><b>Changelog:</b>
 * <ul>
 * <li>1 - 29-nov-2010 Initial release.</li>
 * </ul>
 * </p>
 *
 * @author Gervasio Varela
 * @version 1
 */
public class DatabaseFieldValueInconsistencyException extends InternalErrorException
{

    public DatabaseFieldValueInconsistencyException(Exception exception, String message)
    {
        super(exception, message);
    }

    public DatabaseFieldValueInconsistencyException(String message)
    {
        super(message);
    }

    public DatabaseFieldValueInconsistencyException(Exception exception)
    {
        super(exception);
    }

    

}
