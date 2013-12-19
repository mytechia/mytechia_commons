/*******************************************************************************
 *   
 *   Copyright 2008 Mytech Ingenieria Aplicada <http://www.mytechia.com>, Gervasio Varela, Alejandro Paz
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

package com.mytechia.commons.util.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;


/** This class should be subclassed to provide access to the user preferences that are stored
 * in a configuration file.
 * If the file doesn't exists, it is created with default values.
 *
 * @author  Gervasio Varela Fernandez, Alejandro Paz
 * @version 1
 *
 * File: UserConfiguration.java
 * Date: 10/07/2008
 * Changelog:
 *
 *      10/07/2008  --  Initial version
 */
public abstract class UserConfiguration implements IUserConfiguration
{

    
    protected String configFilePath = null;
    
    protected String automaticFileMsg = "File automatically created.";
    
    protected Properties configuration = null;

    
        
    protected UserConfiguration(String configFilePath, String automaticFileMsg) throws IOException
    { 
        this.configFilePath = configFilePath;
        this.automaticFileMsg = automaticFileMsg;
        loadConfiguration();
    }
    
    
    /** 
     * 
     * @throws java.io.IOException If the config file couldn't be read and
     *                             a default config file coulnd't be created
     */
    private void loadConfiguration() throws IOException
    {
        
        configuration = new Properties();
        
        File configFile = new File(configFilePath);

        if (!configFile.exists()) { 
            createDefaultConfigurationFile();
        }           

        InputStream configInput = 
                    new FileInputStream(configFile);

        configuration.load(configInput);
        
    }
    
    
    /** Saves the configuration
     * 
     * @throws java.io.IOException If there was a problem while writting the config file
     */
    public synchronized void storeConfiguration() throws IOException
    {
        OutputStream out = new FileOutputStream(configFilePath);
        configuration.store(out, configFilePath);
    }
    
    
    /** Fills the configuration file (and creates it if doesn't exists) with
     * the default configuration values.
     * 
     * @throws java.io.IOException
     */
    private void createDefaultConfigurationFile() throws IOException
    {
        
        File configFile = new File(configFilePath);
        
        if (configFile.exists()) {
            //seems a problem with access rights, report error
            throw new IOException();
        }
        else {
            //create the default file
            
            //create config dir if not exists yet
            File parentDir = configFile.getParentFile();
            if (!parentDir.exists())
                parentDir.mkdirs();
            
            //create config file
            configFile.createNewFile();
            
            Properties config = new Properties();
            
            fillDefaultProperties(config);
            
            OutputStream fileOutput = new FileOutputStream(configFile);
            config.store(fileOutput, automaticFileMsg);
        }
        
    }
    
    
    /** This method must be implemented to fill the configuration file
     * with default values.
     * 
     * @param config
     */
    protected abstract void fillDefaultProperties(Properties config);
    
    
    public String getParam(String key)
    {
        return configuration.getProperty(key);
    }
    
    
    public void setParam(String key, String value)
    {
        configuration.setProperty(key, value);
    }

    
}
