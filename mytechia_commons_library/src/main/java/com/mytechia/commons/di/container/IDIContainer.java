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


/**
 * <p><b>Description:</b>
 * An IDIContainer (Dependency Injection Container) implements part of the
 * Dependency Injection architectural pattern, providing runtime access and
 * configuration of software dependencies.
 *
 * One can use an IDIContainer to obtain concrete instances of an Interface
 * (without knowledge of the concrete class) and it can also be used to
 * implement the singleton pattern.
 *
 * An IDIContainer also provides support for the prototype pattern, by providing
 * a general prototype manager that can be used to store/clone the diferente
 * prototypes.
 * </p>
 *
 * <p><b>Creation date:</b> 03/07/2009</p>
 *
 * <p><b>Changelog:</b>
 * <ul>
 * <li>1 - 03/07/2009 Initial release</li>
 * </ul>
 * </p>
 *
 * @author Gervasio Varela Fernandez
 * @version 1
 *
 */
public interface IDIContainer
{

    /**
     * Returns an instance of the specified class.
     *
     * If the specified class is configured as a Singleton in this container,
     * this method will return the same instance for every call.
     *
     * @param c the class wich instance is required
     * @return an instance of the specified class
     */
    public <T> T getInstance(Class<T> c);


    /**
     * Registers a class in the container. Instances of this class could be
     * obtainer through the getInstance() method.
     *
     * The IDIContainer will provide instances of this class (and all the
     * interfaces that it implement) through the getInstances() method.
     *
     * @param c the class that is been registered
     */
    public void registerClass(Class c);


    /**
     * Registres a class as a singleton object. All calls to the getInstance()
     * method with this class as argument will return the same instancee.
     *
     * @param c the class that is been registered
     *
     */
    public void registerSingleton(Class c);


    /**
     * Registers a singleton by providing a custom instance. All calls to
     * getInstance() method with this class as argument will return obj.
     *
     * @param c the class that is been registered
     * @param obj the custom instance to use as singleton
     *
     */
    public <T> void registerSingleton(Class<T> c, T obj);


    /**
     * Unregisters the specified class from the container
     *
     * @param c the class to be unregistered
     *
     */
    public void unregister(Class c);


    /**
     * Unregisters all the registered class from the container
     */
    public void unregisterAll();


    /**
     * Obtains a clone of the default prototype for the specified class.
     *
     * NOTE: IPrototype pattern
     *
     * @param c class of the clone to be obtained
     */
    public <T> T getClone(Class<T> c);


    /**
     * Obtains a key associated clone of the prototype for the specified class
     * and key
     *
     * NOTE: IPrototype pattern
     *
     * @param c class of the clone to be obtained
     */
    public <T> T getClone(String key, Class<T> c);


    /**
     * Registers the default prototype for a class
     *
     * NOTE: IPrototype pattern
     *
     * @param prototype protytype instance
     */
    public void registerPrototype(IPrototype prototype);


    /**
     * Unregisters the default prototype for the specified class
     *
     * NOTE: IPrototype pattern
     *
     * @param c the class with default prototype will be unregistered
     */
    public void unregisterPrototype(Class c);


    /**
     * Registers a new prototype associated to the specified key
     *
     * NOTE: IPrototype pattern
     *
     * @param key key for the new prototype
     * @param prototype the prototype to register
     */
    public void registerPrototype(String key, IPrototype prototype);


    /**
     * Unregisters a prototype associated to the specified key
     *
     * NOTE: IPrototype pattern
     *
     * @param key key of the prototype
     * @param prototype the prototype to unregister
     */
    public void unregisterPrototype(String key, Class c);


    /**
     * Established the parent of this container. Containers can be organized
     * hierachically.
     *
     * The childs of a container can resolve classes that are registered on its
     * parents. But the parents can't access its childs.
     *
     * NOTE: IPrototype pattern
     *
     * @param container the parent of this container
     */
    public IDIContainer makeChildContainer();


    /**
     * Obtains the parent container of this container
     *
     * NOTE: IPrototype pattern
     *
     * @return the parent of this container
     */
    public IDIContainer getParent();


    /**
     * Checks if this container has a parent container
     *
     * @return true if this container has a parent, false otherwise
     */
    public boolean hasParent();
}
