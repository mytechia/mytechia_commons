/*******************************************************************************
 *   
 *   Copyright 2008 Mytech Ingenieria Aplicada <http://www.mytechia.com>, Julio Alberto Gomez Fernandez
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

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <p>
 * Loads the language configuration from a configuration file and
 * loads the adecuate bundle for obtaining internacionalized strings. If the
 * configuration file doesn't exists, it uses the system locale.
 * </p>
 *
 * <p>
 * <b>Creation date:</b>29-ene-2014</p>
 *
 * @author Julio Alberto Gomez Fernandez
 * @version 1
 */
public abstract class AbstractI18n implements II18NInstance {

    private final String langBundleName;

    private ResourceBundle messages = null;


    public AbstractI18n(String bundleName) {
        this.langBundleName= bundleName;
    }

    private void loadMessages(Locale locale) {
        try {

            messages = ResourceBundle.getBundle(langBundleName, locale);

        } catch (MissingResourceException ex) {
            String msgError= String.format("Error trying to load locale file: %s_%s ", langBundleName, locale);
            Logger.getLogger(AbstractI18n.class.getName()).log(Level.FINE, msgError, ex);
        }

    }

    @Override
    public String getMessage(String key) {
        
        if(messages==null){
            this.loadMessages(Locale.getDefault());
        }
                
        return messages.getString(key);
    }

    @Override
    public String getMessage(String key, Locale locale) {
        return ResourceBundle.getBundle(langBundleName, locale).getString(key);
    }

    @Override
    public Locale getLocale() {

        if (messages != null) {
            return this.messages.getLocale();
        } else {
            return Locale.getDefault();
        }

    }

    @Override
    public void setLocale(Locale locale) {
        this.loadMessages(locale);
    }

}
