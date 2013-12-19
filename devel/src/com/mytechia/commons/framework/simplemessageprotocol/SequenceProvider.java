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
    /** Instance of the singleton. */
    private static SequenceProvider _instance = new SequenceProvider();
    
    /** Sequence value. */
    private byte value = 0;
    
    
    /**
     * Private constructor.
     */
    private SequenceProvider() {
    }
    

    public static SequenceProvider getInstance() {
        return _instance;
    }
    
    
    public void initializeSequence(byte value) {
        this.value = value;
    }
    
    /**
     * Obtain next sequence value and increment sequence.
     * 
     * @return
     */
    public byte getNewValue() {
        this.value++;
        return this.value;
    }
    
    /**
     * Get last sequence value.
     * Not modify sequence value.
     * 
     * @return
     */
    public byte getLastValue() {
        return this.value;
    }
    
}
