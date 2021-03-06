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

package com.mytechia.commons.util.xml.contentparser;

import com.mytechia.commons.framework.exception.xml.XMLParsingErrorException;


/**
 * <p><b>Description:</b></p>
 *
 *
 *
 *
 * <p><b>Creation date:</b> 02-may-2009</p>
 *
 * <p><b>Changelog:</b></p>
 * <ul>
 * <li>1 - 02-may-2009 Initial release</li>
 * </ul>
 *
 *
 * @author Gervasio Varela Fernandez
 * @version 1
 */
public class XMLBooleanContentParser
{


    private final static String TRUE = "true";
    private final static String FALSE = "false";


    public boolean parseContent(String xmlElementContent) throws XMLParsingErrorException
    {
        if (xmlElementContent.equalsIgnoreCase(TRUE))
            return true;
        else if (xmlElementContent.equalsIgnoreCase(FALSE))
            return false;
        else
            throw new XMLParsingErrorException("Error parsing '"+
                    xmlElementContent+"' XML boolean element content. " +
                    "It should be 'true' or 'false'.");
    }


}
