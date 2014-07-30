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


package com.mytechia.commons.util.reflection;

import com.mytechia.commons.framework.exception.reflection.MethodNotFoundException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;


/**
 * <p><b>Description:</b>
 * Contains methods to search & manage methods by reflection.
 *
 * </p>
 *
 * <p><b>Creation date:</b> 15-may-2009</p>
 *
 * <p><b>Changelog:</b>
 * <ul>
 * <li>1 - 15-may-2009 Initial release</li>
 * </ul>
 * </p>
 *
 * @author Gervasio Varela Fernandez
 * @version 1
 */
public final class MethodReflectionUtilities
{


    public static final Method getMethodByReturnType(
            Class<?> c, Class<?> returnType) throws MethodNotFoundException
    {

        Method [] publicMethods = c.getMethods();

        for(Method m : publicMethods) {
            if (m.getReturnType().equals(returnType))
                return m;
        }

        throw new MethodNotFoundException(
                "Couldn't found a method that returns '"+
                returnType.getCanonicalName()+
                "' in class '"+c.getCanonicalName()+"'.");

    }



    public static final Method getMethodByName(
            Class<?> c, String name) throws MethodNotFoundException
    {

        Method [] publicMethods = c.getMethods();

        for(Method m : publicMethods) {
            if (m.getName().equals(name))
                return m;
        }

        throw new MethodNotFoundException(
                "Couldn't found a method with name '"+name+
                "' in class '"+c.getCanonicalName()+"'.");

    }


    public static final Method getGetterMethod(Class<?> c, String propertyName)
            throws MethodNotFoundException
    {
        String getterName = "get"+propertyName;

        return getMethodByName(c, getterName);
    }


    public static final Method getGetterMethod(
            Class<?> c, String propertyName, Class<?> propertyClass)
                throws MethodNotFoundException
    {
      
        Method m = getGetterMethod(c, propertyName);

        if (m.getReturnType().equals(propertyClass))
            return m;
        else
            throw new MethodNotFoundException(
                "Couldn't found a getter method for property '"+propertyName+
                "' in class '"+c.getCanonicalName()+"'.");

    }


    public static final Method getSetterMethod(Class<?> c, String propertyName)
            throws MethodNotFoundException
    {
        String setterName = "set"+propertyName;

        return getMethodByName(c, setterName);
    }


    public static final Method getSetterMethod(
            Class<?> c, String propertyName, Class<?> propertyClass)
                throws MethodNotFoundException
    {

        Method m = getSetterMethod(c, propertyName);

        Class<?> [] paramTypes = m.getParameterTypes();

        if ((paramTypes.length == 1) && (paramTypes[0].equals(propertyClass))) {
            return m;
        }
        else {
            throw new MethodNotFoundException(
                "Couldn't found a setter method for property '"+propertyName+
                "' in class '"+c.getCanonicalName()+"'.");
        }

    }


    public static final List<Method> getMethodsByPrefix(Class<?> c, String prefix)
    {

        List<Method> prefixMethods = new LinkedList<Method>();

        Method [] publicMethods = c.getMethods();

        for(Method m : publicMethods) {
            if (m.getName().startsWith(prefix))
                prefixMethods.add(m);
        }

        return prefixMethods;

    }

    
}
