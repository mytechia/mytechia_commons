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
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;


/**
 *
 * @author  Alex
 * @version 1
 *
 * File: Protocol.java
 * Date: 22/02/2008
 * Changelog:
 *
 *      22/02/2008  --  Initial version
 */
public abstract class Message {
    public static final byte INIT_BYTE = 0x45;
    public static final int HEADER_CHECKSUM_SIZE = 1;
    public static final int DATA_CHECKSUM_SIZE = 1;

    private byte commandType = 0;
    private int sequenceNumber = 0;
    private byte headerChecksum = 0;
    private byte[] data = null;
    private byte dataChecksum = 0;

    
    
    public Message() {
    }

    
    public byte getCommandType() {
        return commandType;
    }

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    /**
     * Obtain user data size (data without checksum).
     * @return
     */
    public int getDataSize() {
        return (this.data == null) ? 0 : this.data.length;        
    }
    
    /**
     * Obtain data field size (include 'user data' and 'data checksum')
     * @return
     */
    public int getDataFieldSize() {
        int dataSize = getDataSize();
        
        if (dataSize > 0) {
            return dataSize + DATA_CHECKSUM_SIZE;
        } 
        else {
            return 0;
        }
    }
    
    public byte getHeaderChecksum() {
        return headerChecksum;
    }

    public byte[] getData() {
        return data;
    }

    public byte getDataChecksum() {
        return dataChecksum;
    }
    
    protected void setCommandType(byte commandType) {
        this.commandType = commandType;
    }

    protected void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    protected void setHeaderChecksum(byte headerChecksum) {
        this.headerChecksum = headerChecksum;
    }

    protected void setData(byte[] data) {
        this.data = data;
    }
    
    protected void setDataChecksum(byte dataChecksum) {
        this.dataChecksum = dataChecksum;
    }
  
   
    

    /**
     * Calculate checksum byte for 'length' bytes beginning at 'initIndex'.
     * 
     * @param data Array of data.
     * @param initIndex Array index for first byte.
     * @param length Length of data for that calculate checksum.
     * @return Calculated checksum byte.
     */
    protected byte calcChecksum(byte[] data, int initIndex, int length) {
        byte check = 0;

        for (int i = 0; i < length; i++) {
                check ^= data[initIndex + i];
        }

        return check;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Message other = (Message) obj;
        if (this.commandType != other.commandType) {
            return false;
        }
        if (this.sequenceNumber != other.sequenceNumber) {
            return false;
        }
        if (this.headerChecksum != other.headerChecksum) {
            return false;
        }
        if (!Arrays.equals(this.data, other.data)) {
            return false;
        }
        if (this.dataChecksum != other.dataChecksum) {
            return false;
        }
        return true;
    }


    protected int readString(StringBuilder string, byte[] data, int offset) throws MessageFormatException
    {
        return Message.readStringFromBytes(string, data, offset);
    }
    
    public static int readStringFromBytes(StringBuilder string, byte[] data, int offset) throws MessageFormatException
    {
        
        if(data.length<=offset){
            throw new  MessageFormatException("Invalid data size");
        }
        
        int localOffset = 0;
        int idLen = EndianConversor.byteArrayLittleEndianToShort(data, offset);
        localOffset += EndianConversor.SHORT_SIZE_BYTES;
        string.replace(0, idLen, new String(data, offset+localOffset, idLen));
        string.setLength(idLen);
        localOffset += idLen;
        return localOffset;
    }


    protected void writeString(ByteArrayOutputStream dataStream, String string) throws IOException
    {
        Message.writeStringInStream(dataStream, string);
    }

    public static void writeStringInStream(ByteArrayOutputStream dataStream, String string) throws IOException
    {
        byte[] lenData = new byte[2];
        byte[] idData = string.getBytes();
        EndianConversor.shortToLittleEndian((short) idData.length, lenData, 0);
        dataStream.write(lenData);
        dataStream.write(idData);
    }
    
}


