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

package com.mytechia.commons.util.classloading;

import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandlerFactory;


/**
 * <p><b>Description:</b></br>
 * An specialization of the URLClassLoader that first looks for clases in the
 * local classpath, and if the class is not found, delegates on the parents.
 *
 * </p>
 *
 * <p><b>Creation date:</b> 21-jul-2009</p>
 *
 * <p><b>Changelog:</b></br>
 * <ul>
 * <li>1 - 21-jul-2009<\br> Initial release</li>
 * </ul>
 * </p>
 *
 * @author Gervasio Varela Fernandez
 * @version 1
 */
public class LocalPriorityURLClassLoader extends URLClassLoader
{


    public LocalPriorityURLClassLoader(URL[] urls, ClassLoader parent, URLStreamHandlerFactory factory)
    {
        super(urls, parent, factory);
    }


    public LocalPriorityURLClassLoader(URL[] urls)
    {
        super(urls);
    }


    public LocalPriorityURLClassLoader(URL[] urls, ClassLoader parent)
    {
        super(urls, parent);
    }




//    @Override
//    public Class<?> findClass(String name) throws ClassNotFoundException {

        // First check whether it's already been loaded, if so use it
//        Class loadedClass = findLoadedClass(name);
//
//        // Not loaded, try to load it
//        if (loadedClass == null) {
//            try {
//                // Ignore parent delegation and just try to load locally
//                loadedClass = super.findClass(name);
//            } catch (ClassNotFoundException e) {
//                //does not exist locally
//            }
//        }
//
//        return loadedClass;
//    }



    @Override
    public URL findResource(String name)
    {
        return super.findResource(name);
    }


}
