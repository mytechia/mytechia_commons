/*******************************************************************************
 *   
 *   Copyright 2008 Mytech Ingenieria Aplicada <http://www.mytechia.com>, Gervasio Varela, Alejandro Paz
 *   Victor Sonora
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


package com.mytechia.commons.util.net;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;


public class IPUtil {
    
    private IPUtil() {
    }
    
    /**
     * Retrieves the IP address of the local computer:
     * - If there exists an interface different from "loopback" it will never return "127.0.0.1"
     * - If there exists multiple network interfaces, the first one will be returned
     */
    public static InetAddress getLocalIP() {
        try {
            Collection ips = getAllIPAddresses();
            if (ips.size() > 0) {
                return (InetAddress) ips.iterator().next();
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
    

    /**
     * Retrieves all the IP addresses of the local computer.
     * The loopback address (127.0.0.1) wil only be include if it is the only
     * address available.
     */
    public static Collection getAllIPAddresses() throws Exception {
        ArrayList direcciones = new ArrayList();
        InetAddress ip = null;
        InetAddress ipLoopback = null;
        
        try {
            Enumeration ifaces = NetworkInterface.getNetworkInterfaces();
            while (ifaces.hasMoreElements()) {
                NetworkInterface iface = (NetworkInterface) ifaces.nextElement();
                Enumeration ips = iface.getInetAddresses();
                while (ips.hasMoreElements()) {
                    InetAddress ia = (InetAddress) ips.nextElement();
                    if (ia.getHostAddress().indexOf(":")==-1) {
                        if (ia.isLoopbackAddress()) {
                            ipLoopback = ia;
                        } else {
                            direcciones.add(ia);
                        }
                    }
                }
            }
            
            if ((direcciones.size() == 0) && (ipLoopback != null)) {
                direcciones.add(ipLoopback);
            }
        } catch (Exception e) {
            throw e;
        }
        
        return direcciones;
    }


    /**
     * Decides whether an IP is local or not
     */
    public static boolean isValidLocalIP(InetAddress ip) throws Exception {
        String ipStr = ip.getHostAddress();
        
        Iterator ips = getAllIPAddresses().iterator();
        while (ips.hasNext()) {
            InetAddress ip2 = (InetAddress) ips.next();
            if (ip2.getHostAddress().equals(ipStr)) {
                return true;
            }
        }
        
        return false;
    }

    
    
}
