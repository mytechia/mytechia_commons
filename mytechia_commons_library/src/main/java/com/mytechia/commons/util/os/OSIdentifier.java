/*******************************************************************************
 *   
 *   Copyright 2008 Mytech Ingenieria Aplicada <http://www.mytechia.com>, Gervasio Varela
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


package com.mytechia.commons.util.os;


/** Simple class with methods to identify the OS of the JVM.
 *
 * @author  Gervasio Varela Fernandez
 * @version 1
 *
 * File: OSIdentifier.java
 * Date: 4/2/2008
 * Changelog:
 *
 *      4/2/2008  --  Initial version
 */
public class OSIdentifier
{
    
    private static final String LINUX_STR = "linux";
    private static final String WINDOWS_STR = "windows";
    private static final String MAC_STR = "mac";
    private static final String SOLARIS_STR = "solaris";    
    

    public static final int LINUX_OS = 1;
    public static final int WINDOWS_OS = 2;
    public static final int MAC_OS = 3;
    public static final int SOLARIS_OS = 4;
    public static final int UNIIX_OS = 5;
    public static final int OTHER_OS = 6;
    
    
    public static int indentifyOS() {
    
        String osName = System.getProperty("os.name").toLowerCase();
        
        if (osName.contains(WINDOWS_STR))
            return WINDOWS_OS;
        else if (osName.contains(LINUX_STR))
            return LINUX_OS;
        else if (osName.contains(MAC_STR))
            return MAC_OS;
        else if (osName.contains(SOLARIS_STR))
            return SOLARIS_OS;
        else
            return OTHER_OS;
        
    }
    
    
}
