/*******************************************************************************
 *   
 *   Copyright 2008 Mytech Ingenieria Aplicada <http://www.mytechia.com>, Gervasio Varela, Alejandro Paz
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

package com.mytechia.commons.util.conversion;

/** Common utility methos for manipulate hexadecimal numbers
 *
 * @author Gervasio Varela, Alejandro Paz
 */
public class HexConversor 
{
    
    
    public static byte[] hexStringToBytes(String s) 
    {
        byte[] data = new byte[s.length()/2];
        
        for (int i=0; i<data.length; i++) {
            byte n1 = hexCharToByte(s.charAt(2*i));
            byte n2 = hexCharToByte(s.charAt(2*i+1));
            data[i] = (byte) ((n1<<4) + n2);
        }
        return data;
    }
    
    
    public static byte hexCharToByte(char c) 
    {
        if (c >= 65) {
            return (byte) (c - 'A' + 10);
        } else {
            return (byte) (c - '0');
        }
    }
    

}
