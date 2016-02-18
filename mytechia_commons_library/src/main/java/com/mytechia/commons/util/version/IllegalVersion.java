

package com.mytechia.commons.util.version;

/**
 * <p></p>
 *
 * <p><b>Creation date:</b>04-feb-2015</p>
 *
 * @author Julio Alberto Gomez Fernandez
 * @version 1 
 */

public class IllegalVersion extends RuntimeException{

    public IllegalVersion(String message, Object...args) {
        super(String.format(message, args));
    }
    
    

}
