/*******************************************************************************
 *   
 *   Copyright (C) 2009-2013 
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


package com.mytechia.commons.framework.simplemessageprotocol.udp;

import com.mytechia.commons.framework.simplemessageprotocol.channel.IAddress;
import java.net.InetAddress;
import java.net.UnknownHostException;


/**
 * <p><b>
 * </b>
 *
 *
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
public class UDPAddress implements IAddress
{

    private InetAddress addr;
    private String ip;
    private int port;



    public UDPAddress(String ip, int port)
    {
        this.ip = ip;
        this.port = port;
    }


    public UDPAddress(InetAddress addr, int port)
    {
        this.addr = addr;
        this.port = port;
    }            


    public String getIp()
    {
        return ip;
    }


    public int getPort()
    {
        return port;
    }


    public InetAddress getAddress() throws UnknownHostException
    {
        if ((this.addr == null) && (this.ip != null)) {
            return InetAddress.getByName(ip);
        }
        else {
            return this.addr;
        }
    }


    @Override
    public String getId()
    {
        if (this.ip != null) {
            return ip+":"+port;
        }
        else {
            return this.addr.getHostAddress()+":"+port;
        }
    }


    @Override
    public String getName()
    {
        if (this.ip != null) {
            return ip;
        }
        else {
            return this.addr.getHostAddress();
        }
    }

    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + (this.addr != null ? this.addr.hashCode() : 0);
        hash = 47 * hash + (this.ip != null ? this.ip.hashCode() : 0);
        hash = 47 * hash + this.port;
        return hash;
    }

    @Override
    public boolean equals(IAddress obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UDPAddress other = (UDPAddress) obj;
        if (this.addr != other.addr && (this.addr == null || !this.addr.equals(other.addr))) {
            return false;
        }
        if ((this.ip == null) ? (other.ip != null) : !this.ip.equals(other.ip)) {
            return false;
        }
        if (this.port != other.port) {
            return false;
        }
        return true;
    }

}
