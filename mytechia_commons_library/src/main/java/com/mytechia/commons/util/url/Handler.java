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

package com.mytechia.commons.util.url;

import com.mytechia.commons.util.url.ClasspathURLConnection;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;


/**
 * <p><b>Description:</b></p>
 * Implements an URLStreamHandler to manage classpath URLs of the form
 * classpath://some.package.xxx
 *
 * The name of this class is EXTREMENLY important. The Java VM has a factory
 * that searches for URLStreamHandler implementations on demand. It uses a system
 * property that specify the package in wich handlers must be and uses the protocol
 * name to search for the handler. So for the classpath:// protocol it will search
 * a class whose name ends in classpath.Handler.
 *
 * java.protocol.handler.pkgs is the system property that must point to 
 * a package list (sperated by |) that contains the Handler classes.
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
public class Handler extends URLStreamHandler
{


    @Override
    protected URLConnection openConnection(URL u) throws IOException
    {
        return new ClasspathURLConnection(u);
    }
    

}
