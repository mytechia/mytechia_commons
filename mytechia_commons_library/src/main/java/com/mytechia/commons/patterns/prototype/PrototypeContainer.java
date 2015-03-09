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


package com.mytechia.commons.patterns.prototype;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * <p><b>Description:</b></p>
 * IPrototype manager for the prototype desing pattern.
 * Provides a container to store and clone prototypes
 *
 *
 * <p><b>Creation date:</b> 03/07/2009</p>
 *
 * <p><b>Changelog:</b></p>
 * <ul>
 * <li>1 - 03/07/2009 Initial release</li>
 * </ul>
 *
 *
 * @author Gervasio Varela Fernandez
 * @version 1
 *
 */
public class PrototypeContainer
{

    private static final String DEFAULT_CLONE_KEY = "default";


    /**
     * Map to store prototypes
     */
    private Map<PrototypeKey, IPrototype> prototypes = null;
	


    public PrototypeContainer()
    {

        prototypes = new ConcurrentHashMap<PrototypeKey, IPrototype>();

    }


    /** Obtains a clone of the default prototype for the specified class.
     *
     * NOTE: IPrototype pattern
	 *
     * @param c class of the clone to be obtained
	 */
	public <T> T getClone( Class<T> c )
    {

        return getClone(DEFAULT_CLONE_KEY, c);

    }


    /** Obtains a key associated clone of the prototype for
     * the specified class and key
     *
     * NOTE: IPrototype pattern
	 *
     * @param c class of the clone to be obtained
	 */
	public <T> T getClone( String key, Class<T> c )
    {

        IPrototype prototype = prototypes.get(new PrototypeKey(key, c));

        if (prototype != null) {
            return (T) prototype.clone();
        }
        else
            return null;

    }


	/** Registers the default prototype for a class
     *
     * NOTE: IPrototype pattern
     *
     * @param prototype protytype instance
	 */
	public void registerPrototype( IPrototype prototype )
    {

        registerPrototype(DEFAULT_CLONE_KEY, prototype);

    }


	/** Unregisters the default prototype for the specified class
	 *
     * NOTE: IPrototype pattern
     *
     * @param c the class with default prototype will be unregistered
	 */
	public void unregisterPrototype( Class c )
    {

        unregisterPrototype(DEFAULT_CLONE_KEY, c);

    }


	/** Registers a new prototype associated to the specified key
	 *
     * NOTE: IPrototype pattern
     *
     * @param key key for the new prototype
     * @param prototype the prototype to register
	 */
	public void registerPrototype( String key, IPrototype prototype )
    {

        prototypes.put(new PrototypeKey(key, prototype.getClass()), prototype);

    }


	/** Unregisters a prototype associated to the specified key
	 *
     * NOTE: IPrototype pattern
     *
     * @param key key of the prototype
     * @param c the prototype to unregister
	 */
	public void unregisterPrototype( String key, Class c )
    {

        prototypes.remove(new PrototypeKey(key, c));

    }


    

    /** Nested private class to implement a key for a Map based on a
     * prototype's key and class objects.
     */
    private class PrototypeKey
    {

        private String key = null;
        private Class c = null;


        private PrototypeKey(String key, Class c)
        {
            this.key = key;
            this.c = c;
        }


        @Override
        public boolean equals(Object obj)
        {

            if (obj instanceof PrototypeKey) {
                PrototypeKey pk = (PrototypeKey) obj;
                return (pk.key.equals(key) && pk.c.equals(c));
            }
            else
                return false;
            
        }

        
        @Override
        public int hashCode()
        {
            return toString().hashCode();
        }


        @Override
        public String toString()
        {

            String className = c.getName();

            StringBuffer b = new StringBuffer(key.length() + className.length());

            b.append(key);
            b.append("|");
            b.append(className);

            return b.toString();

        }




    }

}
