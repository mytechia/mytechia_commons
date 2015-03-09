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
 * <p><b>Description:</b></p>
 *
 *
 *
 *
 * <p><b>Creation date:</b> 21-jul-2009</p>
 *
 * <p><b>Changelog:</b></p>
 * <ul>
 * <li>1 - 21-jul-2009 Initial release</li>
 * </ul>
 *
 *
 * @author Gervasio Varela Fernandez
 * @version 1
 */
public class TreeClassLoader extends URLClassLoader
{

    private ClassLoader child = null;


    public TreeClassLoader(URL[] urls, ClassLoader parent, ClassLoader child)
    {
        super(urls, parent);
        this.child = child;
    }


    public TreeClassLoader(URL[] urls, ClassLoader child)
    {
        super(urls);
        this.child = child;
    }


    public TreeClassLoader(URL[] urls, ClassLoader parent, URLStreamHandlerFactory factory, ClassLoader child)
    {
        super(urls, parent, factory);
        this.child = child;
    }


    public void setChild(ClassLoader child)
    {
        this.child = child;
    }


    public ClassLoader getChild()
    {
        return this.child;
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException
    {
        Class c = null;    
        try {
         c = super.findClass(name);
        }
        catch(ClassNotFoundException ex) { }

        if (c == null)
            c = super.loadClass(name);

        return c;
    }


//    @Override
//    public Class<?> findClass(String name) throws ClassNotFoundException
//    {
//
//        return super.findClass(name);

//        if (name.equalsIgnoreCase("org.semanticweb.owl.model.OWLDescription")) {
//            System.out.println("*****************************************JARL!!!!");
//        }
//
//        Class loadedClass = findLoadedClass(name);
//
//        try {
//            if ((loadedClass == null) && (this.child != null)) {
//                loadedClass = this.child.loadClass(name);
//            }
//        }
//        catch(ClassNotFoundException ex) {
//            //try in this classloader
//        }
//
//        if (loadedClass == null) {
//            loadedClass = super.loadClass(name);
//        }
//
//        return loadedClass;

//    }


}
