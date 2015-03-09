/*******************************************************************************
 *   
 *   Copyright 2009,2012 Mytech Ingenieria Aplicada <http://www.mytechia.com>, Gervasio Varela
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

package com.mytechia.commons.util.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.util.Arrays;


/**
 * <p><b>Description:</b></p>
 * Allows the loading of serialized objects using a custom class loader.
 *
 *
 * <p><b>Creation date:</b> 21-jul-2009</p>
 *
 * <p><b>Changelog:</b></p>
 * <ul>
 * <li>2 - 14-feb-2012 Fixed error while loading arrays of objects</li>
 * <li>1 - 21-jul-2009 Initial release</li>
 * </ul>
 *
 *
 * @author Gervasio Varela Fernandez
 * @version 2
 */
public class ClassLoaderObjectInputStream extends ObjectInputStream
{

    private ClassLoader[] classLoader;


    public ClassLoaderObjectInputStream(ClassLoader classLoader) throws IOException, SecurityException
    {
        super();
        this.classLoader = new ClassLoader[1];
        this.classLoader[0] = classLoader;
    }


    public ClassLoaderObjectInputStream(ClassLoader[] classLoaders) throws IOException
    {
        super();
        this.classLoader = Arrays.copyOf(classLoaders, classLoaders.length);
    }


    public ClassLoaderObjectInputStream(InputStream in, ClassLoader classLoader) throws IOException
    {
        super(in);
        this.classLoader = new ClassLoader[1];
        this.classLoader[0] = classLoader;
    }


    public ClassLoaderObjectInputStream(InputStream in, ClassLoader[] classLoaders) throws IOException
    {
        super(in);
        this.classLoader = Arrays.copyOf(classLoaders, classLoaders.length);
    }


    @Override
    protected Class<?> resolveClass(ObjectStreamClass desc) throws IOException, ClassNotFoundException
    {
        
        try {
            return super.resolveClass(desc);
        }
        catch(ClassNotFoundException ex) {
            //continue searching in other class loaders
        }

        for(int i=0; i<this.classLoader.length; i++)
        {
            try {
                //NO ESTA MUY CLARO QUE ESTO FUNCIONE --> PUEDE QUE FALLE CON TIPOS SIMPLES
                //ANTES SE HACIA DIRECTAMENTE COMO ABAJO PERO ESO NO FUNCIONABA FIJO --> NO CARGABA ARRAYS
                return Class.forName(desc.getName(), true, classLoader[i]);
            }
            catch(ClassNotFoundException ex) {
                try {
                    return (this.classLoader[i].loadClass(desc.getName()));
                }
                catch(ClassNotFoundException cnfex) {
                    continue;
                }
            }
        }

        //finally... not found
        throw new ClassNotFoundException(desc.getName());
        
    }


}
