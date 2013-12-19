/*******************************************************************************
 *   
 *   Copyright 2008 Mytech Ingenieria Aplicada <http://www.mytechia.com>, Alejandro Paz
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

package com.mytechia.commons.framework.simplemessageprotocol.exception;

import com.mytechia.commons.framework.simplemessageprotocol.exception.CommunicationException;

/** 
 *
 * @author Alex
 * @version 1
 *
 * File: UnsupportedValueException.java
 * Date: 10/03/2008
 * Changelog:
 *
 *      10/03/2008  --  Initial version
 * 
 * This exception can be used by client for manage unexpected values for
 * commands or replies.
 */
public class UnsupportedValueException extends CommunicationException {
   
    public UnsupportedValueException() {
        super("");
    }
}
