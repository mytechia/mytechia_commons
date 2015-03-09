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


package com.mytechia.commons.di.container;


import com.mytechia.commons.patterns.prototype.IPrototype;
import com.mytechia.commons.patterns.prototype.PrototypeContainer;
import java.util.List;

import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;
import static org.picocontainer.Characteristics.CACHE;
import static org.picocontainer.Characteristics.SYNCHRONIZE;

/**
 * <p><b>Description:</b></p>
 * Implementation of an IDIContainer (Dependency Injection Container) using
 * PicoContainer (http://www.picocontainer.org) as backend.
 *
 * NOTE: A common error is to tray to resolve a dependency on construction time
 * that involves classes from a child and a parent. If the dependencies needed
 * to construct an instance of a class registered on the parent are registered
 * only on the child, the dependency resolution will fail.
 * To use construction time dependency resolution, all dependencies involved
 * must be on the same container or, at least the class that is be instantiated
 * must be on the child.
 *
 *
 *
 * <p><b>Creation date:</b> 03/07/2009</p>
 *
 * <p><b>Changelog:</b>
 *
 * <ul>
 * <li>1 - 03/07/2009 Initial release</li>
 * </ul>
 *
 * @author Gervasio Varela Fernandez
 * @version 1
 *
 */
public class PicoContainerWrapper implements IDIContainer
{
    
    /** Implementation of the manager role on the prototype pattern */
    private PrototypeContainer prototypeContainer = null;

    /** PicoContainer backend */
    private MutablePicoContainer picoContainer = null;

    /** Parent container for chained dependency resolution */
    private IDIContainer parent = null;



    public PicoContainerWrapper()
    {
        prototypeContainer = new PrototypeContainer();
        this.picoContainer = new DefaultPicoContainer();
    }


    /** Creates a new instances of PicoContainerWrapper using the specified
     * IDIContainer as parent container.
     *
     * All dependency searches that can'y be resolved locally will be redirected
     * to the parent container
     *
     * @param parent container to use as the parent of the new instance
     */
    public PicoContainerWrapper(IDIContainer parent)
    {
        this();
        this.parent = parent;
    }
    

    public synchronized <T> T getInstance(Class<T> c)
    {
        T instance = picoContainer.getComponent(c);

        if ((instance == null) && hasParent())
            instance = parent.getInstance(c);

        return instance;
    }

    
    public synchronized void registerClass(Class c)
    {
        picoContainer.addComponent(c);
    }


    public synchronized void registerSingleton(Class c)
    {
        /* provides synchronized access to the single instance to
         * avoid possible duplicates if to threads try to create the
         * first instance at the same time */
        picoContainer.as(SYNCHRONIZE, CACHE).addComponent(c);
    }
    

    public synchronized <T> void registerSingleton(Class<T> c, T obj)
    {
        picoContainer.as(SYNCHRONIZE, CACHE).addComponent(obj);
    }


    public synchronized void unregister(Class c)
    {
        picoContainer.removeComponent(c);
    }
    

    public synchronized void unregisterAll()
    {
        
        List<Object> components = picoContainer.getComponents();

        for(Object obj : components) {
            picoContainer.removeComponent(obj);
        }

    }
    

    public synchronized <T> T getClone(Class<T> c)
    {
        T clone = prototypeContainer.getClone(c);

        if ((clone == null) && hasParent())
            clone = parent.getClone(c);

        return clone;
    }


    public synchronized <T> T getClone(String key, Class<T> c)
    {
        T clone = prototypeContainer.getClone(key, c);

        if ((clone == null) && hasParent())
            clone = parent.getClone(key, c);

        return clone;
    }


    public synchronized void registerPrototype(IPrototype prototype)
    {
        prototypeContainer.registerPrototype(prototype);
    }


    public synchronized void unregisterPrototype(Class c)
    {
        prototypeContainer.unregisterPrototype(c);
    }


    public synchronized void registerPrototype(String key, IPrototype prototype)
    {
        prototypeContainer.registerPrototype(key, prototype);
    }


    public synchronized void unregisterPrototype(String key, Class c)
    {
        prototypeContainer.unregisterPrototype(key, c);
    }


    public IDIContainer makeChildContainer()
    {
        return new PicoContainerWrapper(this);
    }
    

    public IDIContainer getParent()
    {
        return parent;
    }


    public boolean hasParent()
    {
        return (getParent() != null);
    }
	
}
