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

package com.mytechia.commons.di.container.configuration;

import com.mytechia.commons.di.container.IDIContainer;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

/**
 * <p><b>Description:</b>
 * Populates an IDIContainer from a configuration stored on a Properties file
 * </p>
 *
 * <p><b>Creation date:</b> 03/08/2009</p>
 *
 * <p><b>Changelog:</b>
 * <ul>
 * <li>1 - 03/08/2009 Initial release</li>
 * </ul>
 * </p>
 *
 * @author Gervasio Varela Fernandez
 * @version 1
 *
 */
public class DIPropertiesConfigurationLoader implements IDIConfigurationLoader
{


    public static final String SINGLETON_CLASS = "SINGLETON";
    public static final String INSTANTIABLE_CLASS = "INSTANTIABLE";


    private Properties properties = null;

    private ClassLoader classLoader = null;
    

    private DIPropertiesConfigurationLoader()
    {
        this.properties = new Properties();
    }


	/** Creates an instance of IDIPropertiesConfigurationLoader by specifying
     * the file that contains the configuration
     *
     * @param file properties configuration file
     *
     */
    public DIPropertiesConfigurationLoader(File file) throws IOException
    {

        this();
        properties.load(new FileInputStream(file));

    }

    public DIPropertiesConfigurationLoader(File file, ClassLoader cl) throws IOException
    {

        this(file);
        this.classLoader = cl;

    }

    /** Creates an instance of IDIPropertiesConfigurationLoader by specifying
     * the path to the configuration file
     *
     * @param filePath path of the properties configuration file
     *
     */
    public DIPropertiesConfigurationLoader(String resourcePath) throws IOException
    {

        this();
        loadPropertiesFile(resourcePath);

    }

    public DIPropertiesConfigurationLoader(String resourcePath, ClassLoader cl) throws IOException
    {

        this();
        this.classLoader = cl;
        loadPropertiesFile(resourcePath);
        
    }


    private void loadPropertiesFile(String resourcePath) throws FileNotFoundException, IOException
    {
        InputStream propertiesStream = null;
        if (this.classLoader != null)
            propertiesStream = this.classLoader.getResourceAsStream(resourcePath);
        else
            propertiesStream = getClass().getResourceAsStream(resourcePath);

        if (propertiesStream != null)
            this.properties.load(propertiesStream);
        else
            throw new FileNotFoundException(resourcePath);
    }
    



    public void populateContainer(IDIContainer container)
            throws ClassNotFoundException
    {

        populateContainer(container, false);
        
    }
    
    
    public void populateContainer(IDIContainer container, boolean ignoreErrors)
            throws ClassNotFoundException, NoClassDefFoundError
    {
        
        Set<Object> keys = properties.keySet();

        for(Object o : keys) {
            try {

                String k = (String) o;
                String v = properties.getProperty(k);

                Class c = loadClass(k);

                if (v.equalsIgnoreCase(INSTANTIABLE_CLASS)) {
                    container.registerClass(c);
                }
                else if (v.equalsIgnoreCase(SINGLETON_CLASS)) {
                    container.registerSingleton(c);
                }

            }
            catch(ClassCastException cce) {
                //some key wasn't a String -> jump over
            }
            catch(NoClassDefFoundError ex) {
                if (!ignoreErrors) throw new ClassNotFoundException("", ex);
            }
            catch(ClassNotFoundException ex) {
                if (!ignoreErrors) throw ex;
            }
        }
        
    }


    private Class loadClass(String className) throws ClassNotFoundException
    {
        if (this.classLoader != null)
            return this.classLoader.loadClass(className);
        else
            return Class.forName(className);
    }
	
	
}
