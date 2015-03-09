/*******************************************************************************
 *   
 *   Copyright (C) 2009-2013 Mytech Ingenieria Aplicada <http://www.mytechia.com>
 *   Copyright (C) 2009-2013 Gervasio Varela <gervarela@picandocodigo.com>
 *   Copyright (C) 2012-2013 Victor Sonora <victor@vsonora.com>
 *   Copyright (C) 2009-2013 Alejandro Paz <alejandropl@gmail.com>
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


package com.mytechia.commons.logger.protocol.udp;


import com.mytechia.commons.framework.simplemessageprotocol.udp.UDPCommunicationChannelImplementation;
import java.net.SocketException;
import java.net.UnknownHostException;


/**
 *
 * <p><b>Creation date:</b> 29-01-2010</p>
 *
 * <p><b>Changelog:</b></p>
 * <ul>
 * <li>1 - 29-01-2010 Initial release</li>
 * </ul>
 *
 *
 * @author Gervasio Varela
 * @version 1
 */
public class UDPLogCommunicationChannelImplementation extends UDPCommunicationChannelImplementation
{

    
    public static final int LOG_PROTOCOL_UDP_PORT = 3014;
  


    public UDPLogCommunicationChannelImplementation(int port) throws UnknownHostException, SocketException
    {
        super(port);
    }  


    public UDPLogCommunicationChannelImplementation() throws UnknownHostException, SocketException
    {
        this(LOG_PROTOCOL_UDP_PORT);
    }
	
	
}
