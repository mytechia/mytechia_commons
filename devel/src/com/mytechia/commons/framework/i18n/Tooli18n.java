package com.mytechia.commons.framework.i18n;

import java.util.ArrayList;
import java.util.Collection;
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
public class Tooli18n implements II18NInstance {

    private final String langBundleName;

    private ResourceBundle messages = null;


    public Tooli18n(String bundleName) {
        this.langBundleName= bundleName;
    }

    private void loadMessages(Locale locale) {
        try {

            messages = ResourceBundle.getBundle(langBundleName, locale);

        } catch (MissingResourceException ex) {
            String msgError= String.format("Error trying to load locale file: %s_%s ", langBundleName, locale);
            Logger.getLogger(Tooli18n.class.getName()).log(Level.SEVERE, msgError, ex);
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
    @Deprecated
    public Collection<Locale> getAvailableLocales() {
        return new ArrayList<Locale>(); 
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

    @Override
    @Deprecated
    public boolean isDefault() {

        Locale localeUsedByBundle = this.getLocale();

        return Locale.getDefault().equals(localeUsedByBundle);
    }

}
