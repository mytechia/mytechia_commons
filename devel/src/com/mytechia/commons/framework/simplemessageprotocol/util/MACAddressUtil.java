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

package com.mytechia.commons.framework.simplemessageprotocol.util;

/**
 *
 * @author gervasio
 */
public class MACAddressUtil 
{
    
    private static int MAC_CHAR_LENGTH = 12;
    
    private static String MAC_SEPARATOR_UNIX = ":";
    private static String MAC_SEPARATOR_WINDOWS = "-";
    
    
    
    public static String addressWithoutColon(String mac)
    {
        String macWithoutColon = mac.replaceAll(MAC_SEPARATOR_UNIX, "");
        return macWithoutColon.replaceAll(MAC_SEPARATOR_WINDOWS, "");
    }
    
    
    public static String addressWithColon(String mac)
    {
        String macWithoutColon = addressWithoutColon(mac);
        
        if (mac.length() == MAC_CHAR_LENGTH) {
            char [] macChars = macWithoutColon.toCharArray();
            StringBuffer macWithColon = new StringBuffer(MAC_CHAR_LENGTH+5);
            for(int i=0; i<macChars.length; i+=2) {
                macWithColon.append(macChars[i]);
                macWithColon.append(macChars[i+1]);
                macWithColon.append(MAC_SEPARATOR_UNIX);
            }
            macWithColon.deleteCharAt(macWithColon.lastIndexOf(MAC_SEPARATOR_UNIX));
            
            return macWithColon.toString();
        }
        else {
            return null;
        }
        
    }
    

}
