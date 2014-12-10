/*******************************************************************************
 *   
 *   Copyright 2008 Mytech Ingenieria Aplicada <http://www.mytechia.com>, Alejandro Paz
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

package com.mytechia.commons.framework.simplemessageprotocol;

/** 
 *
 * @author Alex
 * @version 1
 *
 * File: SequenceProvider.java
 * Date: 10/03/2008
 * Changelog:
 *
 *      10/03/2008  --  Initial version
 */
public class SequenceProvider {
    
    /**
     * The sequence number is an 16 bit unsigned short.
     */
    private final int MAX_SEQ_NUMBER = ((int) Math.pow(2.0, 16.0)) -1;
    
    /** Instance of the singleton. */
    private static SequenceProvider _instance = new SequenceProvider();
    
    /** Sequence value. */
    private int value = 0;
    
    
    /**
     * Private constructor.
     */
    private SequenceProvider() {
    }
    

    public static SequenceProvider getInstance() {
        return _instance;
    }
    
    
    public void initializeSequence(int value) {
        setValue(value);
    }
    
    /**
     * Obtain next sequence value and increment sequence.
     * 
     * @return
     */
    public int getNewValue() {
        setValue(value+1);
        return this.value;
    }
    
    /**
     * Sets the value checking the is not greater than
     * the maximun value of an unsigned short
     * @param value 
     */
    private void setValue(int value) {
        if (value > MAX_SEQ_NUMBER) {
            this.value = 0;
        }else {
            this.value = value;
        }        
    }
    
    /**
     * Get last sequence value.
     * Not modify sequence value.
     * 
     * @return
     */
    public int getLastValue() {
        return this.value;
    }
    
}
