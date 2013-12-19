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

import com.mytechia.commons.framework.simplemessageprotocol.exception.MessageFormatException;
import java.util.Arrays;



/** 
 *
 * @author  Alex
 * @version 1
 *
 * File: CompleteReply.java
 * Date: 13/03/2008
 * Changelog:
 *
 *      13/03/2008  --  Initial version
 */
public class CompleteReply extends HeaderReply {
    
    
    /**
     * Constructor.
     * 
     * @param message Complete raw data for the reply (head field + data field).
     * @throws MessageFormatException
     */
    public CompleteReply(byte[] message) throws MessageFormatException {
        super(message);

        decodeMessageData(message, REPLY_HEADER_SIZE);
    }

    
    /**
     * Constructor.
     * 
     * @param headerField Message head, including head checksum.
     * @param dataField Message data field (user data + data checksum).
     * @throws MessageFormatException
     */
    public CompleteReply(byte[] headerField, byte[] dataField) throws MessageFormatException {
        super(headerField);
        
        decodeMessageData(dataField, 0);
    }

    
    
    

    /**
     * Decode data field of the message. 
     * This method needs that first decode the head field of the message.
     * 
     * @param bytes
     * @param initIndex Indicates index where dataField begins.
     * @throws MessageFormatException
     */
    private void decodeMessageData(byte[] bytes, int initIndex) throws MessageFormatException {

        int dataSize = getDataSize();
        
        if ((dataSize > 0) && (bytes == null)) {
            throw new MessageFormatException("Invalid message size.");
        }
        
        if (dataSize == 0) {
            setData(new byte[0]);
            setDataChecksum((byte) 0);
        } 
        else {
            if (bytes.length != (initIndex + getDataFieldSize())) {
                throw new MessageFormatException("Invalid message size.");
            } 
            else {
                // Calculate and verify data checksum (last data field byte)
                byte dataChecksum = calcChecksum(bytes, initIndex, dataSize);
                if (bytes[initIndex + dataSize] != dataChecksum) {
                    throw new MessageFormatException("Data checksum error.");
                }

                setData(Arrays.copyOfRange(bytes, initIndex, initIndex + dataSize));
                setDataChecksum(dataChecksum);
            }
        }

    }

    

}

