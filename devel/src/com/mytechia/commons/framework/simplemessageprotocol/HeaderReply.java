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
import com.mytechia.commons.util.conversion.EndianConversor;



/** 
 *
 * @author  Alex
 * @version 1
 *
 * File: HeaderReply.java
 * Date: 22/02/2008
 * Changelog:
 *
 *      22/02/2008  --  Initial version
 */
public class HeaderReply extends Message {
    public static final int REPLY_HEADER_SIZE = 7;
    
    protected static final int INIT_BYTE_INDEX = 0;
    protected static final int COMMAND_TYPE_INDEX = 1;
    protected static final int SEQUENCE_NUMBER_INDEX = 2;
    /** Index of Data Size field. Field of 2 bytes. */ 
    protected static final int DATA_SIZE_INDEX = 3;  
    protected static final int REPLY_TYPE_INDEX = 5;
    protected static final int HEADER_CHECKSUM_INDEX = 6;

    private byte replyType;
    private int dataSize;
    
    
    
    /**
     * Constructor.
     * 
     * @param messageHeaderData Raw data for the message head (including head checksum).
     * @throws MessageFormatException
     */
    public HeaderReply(byte[] messageHeaderData) throws MessageFormatException {
        decodeMessageHead(messageHeaderData);
    }

    
    public HeaderReply(byte replyType)
    {
        this.replyType = replyType;
    }

    
    
    @Override
    public int getDataSize() {
        return this.dataSize;
    }
    
    protected void setDataSize(int dataSize) {
        this.dataSize = dataSize;
    }
    
    public byte getReplyType() {
        return replyType;
    }
    
    

    /**
     * 
     * @param messageHeaderData
     * @throws MessageFormatException
     */
    private void decodeMessageHead(byte[] messageHeaderData) throws MessageFormatException {

        if (messageHeaderData == null) {
            throw new MessageFormatException("Null data.");
        }
        if (messageHeaderData.length < REPLY_HEADER_SIZE) {
            throw new MessageFormatException("Invalid message size.");
        }
        if (messageHeaderData[INIT_BYTE_INDEX] != Message.INIT_BYTE) {
            throw new MessageFormatException("Checksum error.");
        }
        
        // Calculate and verify head checksum
        byte headChecksum = calcChecksum(messageHeaderData, 0, REPLY_HEADER_SIZE - HEADER_CHECKSUM_SIZE);
        if (messageHeaderData[HEADER_CHECKSUM_INDEX] != headChecksum) {
            throw new MessageFormatException("Head checksum error.");
        }

        // Obtain data size value
        int dataSizeValue = EndianConversor.byteArrayLittleEndianToShort(messageHeaderData, DATA_SIZE_INDEX);
        if (dataSizeValue >= 0) {
            this.dataSize = dataSizeValue;
        }
        else {
            throw new MessageFormatException("Invalid data size value.");
        }
        
        // Command type
        setCommandType(messageHeaderData[COMMAND_TYPE_INDEX]);
        // HeaderReply type
        this.replyType = messageHeaderData[REPLY_TYPE_INDEX];
        // Secuence number
        setSequenceNumber(messageHeaderData[SEQUENCE_NUMBER_INDEX]);
        // Head checksum
        setHeaderChecksum(headChecksum);
        // Data checksum
        setDataChecksum((byte) 0);
        // Data
        setData(new byte[0]);
    }
    

}

