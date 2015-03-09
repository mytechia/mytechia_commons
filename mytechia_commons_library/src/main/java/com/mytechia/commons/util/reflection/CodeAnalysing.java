/*******************************************************************************
 *   
 *   Copyright 2011 Mytech Ingenieria Aplicada <http://www.mytechia.com>, Gervasio Varela
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

package com.mytechia.commons.util.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;


/**
 * <p><b>Description:</b></p>
 *
 *
 *
 * <p><b>Creation date:</b> 08-feb-2011</p>
 *
 * <p><b>Changelog:</b></p>
 * <ul>
 * <li>1 - 08-feb-2011 Initial release.</li>
 * </ul>
 *
 *
 * @author Gervasio Varela
 * @version 1
 */
public class CodeAnalysing
{

    private static final ArrayList<String> BASETYPES = new ArrayList<String>(6); 
    
    static {
        
        BASETYPES.add("int");
        BASETYPES.add("long");
        BASETYPES.add("double");
        BASETYPES.add("float");
        BASETYPES.add("boolean");
        BASETYPES.add("char");
                
    }              
    

    /** Examines a class by using the reflection API an retrieves all the
     * direct dependencies with other classes (fields declarations, method
     * declarations and inner classes). Don't retrieve inheritance dependencies
     * nor recursively.
     *
     * @param c The class to examine
     * @return The class direct dependencies with other classes
     */
    public static Collection<Class> getClassSimpleDependencies(Class c)
    {
        
        
        
        HashSet<Class> result = new HashSet<Class>();

        result.add(c); //add the own class

        //declared subclasses
        Class[] declaredClasses = c.getDeclaredClasses();
        for(Class cl : declaredClasses) {
            result.add(cl);
        }

        //fields
        Field[] declaredFields = c.getDeclaredFields();
        for(Field f : declaredFields) {
            if (!BASETYPES.contains(f.getType().getName()))
                result.add(f.getType());
        }

        //method arguments/return
        Method[] declaredMethods = c.getDeclaredMethods();
        for(Method m : declaredMethods) {
            result.add(m.getReturnType());
            for(Class pc : m.getParameterTypes()) {
                if (!BASETYPES.contains(pc.getName()))
                    result.add(pc);
            }
        }

        return result;

    }


}
