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

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownServiceException;


/**
 * <p><b>Description:</b></p>
 * Support for URLs that point to resources accesible through the classpath like:
 * classpath://some.package.Something.properties
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
public class ClasspathURLConnection extends URLConnection
{

    private InputStream cpElementInputStream = null;


    public ClasspathURLConnection(URL url)
    {
        super(url);
    }


    @Override
    public void connect() throws IOException
    {
        InputStream in = getClass().getResourceAsStream(url.getPath());

        if (in != null) {
            cpElementInputStream = in;
            this.connected = true;
        }
        else
            throw new IOException("The specified resource ('"+
                    url.getPath()+"' couldn't be found on the classpath.");
    }


    @Override
    public InputStream getInputStream() throws IOException
    {
        if (!connected)
            connect();

        return cpElementInputStream;        
    }


    @Override
    public void setRequestProperty(String key, String value)
    {
        //this URL type doesn't support any request property
    }


    @Override
    public Object getContent() throws IOException
    {
        throw new UnknownServiceException();
    }


    protected void setInputStream(InputStream in)
    {
        this.cpElementInputStream = in;
    }


}
