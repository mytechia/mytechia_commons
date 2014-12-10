/*******************************************************************************
 *   
 *   Copyright 2008 Mytech Ingenieria Aplicada <http://www.mytechia.com>, Gervasio Varela
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


package com.mytechia.commons.framework.i18n;

import java.util.Collection;
import java.util.Locale;


/** This interface should be implemented by classes that provide
 * access to internacionlized strings for an application.
 *
 * @author  Gervasio Varela Fernandez
 * @version 1
 *
 * File: II18NInstance.java
 * Date: 5/3/2008
 * Changelog:
 *
 *      5/3/2008  --  Initial version
 */
public interface II18NInstance 
{

    
     /** Returns a collection with all the locales (translations) availables
     * for the application
     */
    public Collection<Locale> getAvailableLocales();
    
    
    
    /** Returns the current active locale for this II18NInstance
     * 
     * @return The current active locale
     */
    public Locale getLocale();
    
    
    
    /** Establishes the active locale of this II18NInstance
     * 
     * It could fail if the specified locale isn't supported by this II18NInstance.
     * Is not present in the collection returned by the getAvailableLocales() method.
     * 
     * @param locale The new active locale
     */
    public void setLocale(Locale locale);
    
    
    
    /** Returns the internacionalized message of the specified key
     * 
     * @param key key of the message to obtain
     * @return The internacionalized message for the speficied key
     */
    public String getMessage(String key);
    
    
    
    /**
     * 
     * @param key
     * @param locale
     * @return
     */
    public String getMessage(String key, Locale locale);
    
    
    
    /** Test if the current locale is the default locale
     * 
     * @return return true if the current locale is the default locale, false otherwise
     */
    public boolean isDefault();
    
    
}
