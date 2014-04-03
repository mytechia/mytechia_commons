/*******************************************************************************
 *   
 *   Copyright 2010 Mytech Ingenieria Aplicada <http://www.mytechia.com>, Alejandro Paz, Gervasio Varela
 *   Victor Sonora
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
import java.util.Arrays;


/** 
 *
 * @author  Alex
 * @version 1
 *
 * File: Command.java
 * Date: 22/02/2008
 * Changelog:
 *      26/01/2010  --  gervasio - Change in the size of the header
 *      22/02/2008  --  Initial version
 */
public abstract class Command extends Message
{
    
    public static final int MAX_MESSAGE_SIZE = 2500; //bytes
    
    
    public static final int COMMAND_HEADER_SIZE = 8;
   
    private static final int INIT_BYTE_INDEX = 0;
    private static final int COMMAND_TYPE_INDEX = 1;
    private static final int SEQUENCE_NUMBER_INDEX = 2;
    private static final int ERROR_CODE_INDEX = 4;
    /** Index of Data Size field. Field of 2 bytes. */    
    private static final int DATA_SIZE_INDEX = 5;
    private static final int HEADER_CHECKSUM_INDEX = 7;
    private static final int DATA_INDEX = COMMAND_HEADER_SIZE;


    private byte errorCode;
    private int dataSize;

    protected int decodingIndex;

    
    public Command() {
    }


    public Command(byte [] message) throws MessageFormatException
    {
        this.decodeMessage(message);
    }

    
    
    @Override
    public void setCommandType(byte commandType) {
       super.setCommandType(commandType);
    }


    @Override
    public void setData(byte[] data) {
        super.setData(data);
        this.dataSize = data.length;
    }

    
    @Override
    public void setSequenceNumber(int sequenceNumber) {
        super.setSequenceNumber(sequenceNumber);
    }    


    public void setErrorCode(byte errorCode)
    {
        this.errorCode = errorCode;
    }


    public byte getErrorCode()
    {
        return this.errorCode;
    }
   
    
    
    /**
     * 
     * @return
     */
    public byte[] codeMessage() throws MessageFormatException
    {

        setData(codeMessageData());

        byte[] bytes = new byte[COMMAND_HEADER_SIZE + getDataFieldSize()];
        
        // Init byte
        bytes[INIT_BYTE_INDEX] = Message.INIT_BYTE;
        // Command type
        bytes[COMMAND_TYPE_INDEX] = getCommandType();
        // Secuence number   
        if (getSequenceNumber() > 32767) {
            System.out.println("");
        }
        EndianConversor.ushortToLittleEndian(getSequenceNumber(), bytes, SEQUENCE_NUMBER_INDEX);
        
        int sequenceNumber = EndianConversor.byteArrayLittleEndianToUShort(bytes, SEQUENCE_NUMBER_INDEX);
        
        
        
        //error code
        bytes[ERROR_CODE_INDEX] = getErrorCode();


        // Data size (2 bytes)        
        EndianConversor.ushortToLittleEndian(getDataSize(), bytes, DATA_SIZE_INDEX);
        
        // Message data        
        if (getDataSize() > 0) {            
            System.arraycopy(getData(), 0, bytes, COMMAND_HEADER_SIZE, getDataSize());
            // Data checksum byte
            setDataChecksum(calcChecksum(bytes, COMMAND_HEADER_SIZE, getDataSize()));
            bytes[COMMAND_HEADER_SIZE + getDataSize()] = getDataChecksum();            
        }
        
        // Head checksum byte
        setHeaderChecksum(calcChecksum(bytes, 0, COMMAND_HEADER_SIZE - HEADER_CHECKSUM_SIZE));
        bytes[HEADER_CHECKSUM_INDEX] = getHeaderChecksum();

        return bytes;
    }


    protected byte[] codeMessageData() throws MessageFormatException
    {

        return new byte[0];

    }


    /**
     * 
     * @param messageHeaderData
     * @throws MessageFormatException
     */
    protected void decodeMessageHead(byte[] messageHeaderData) throws MessageFormatException {

        if (messageHeaderData == null) {
            throw new MessageFormatException("Null data.");
        }
        if (messageHeaderData.length < COMMAND_HEADER_SIZE) {
            throw new MessageFormatException("Invalid message size.");
        }
        if (messageHeaderData[INIT_BYTE_INDEX] != Message.INIT_BYTE) {
            throw new MessageFormatException("Checksum error.");
        }
        
        // Calculate and verify head checksum
        byte headChecksum = calcChecksum(messageHeaderData, 0, COMMAND_HEADER_SIZE - HEADER_CHECKSUM_SIZE);
        if (messageHeaderData[HEADER_CHECKSUM_INDEX] != headChecksum) {
            throw new MessageFormatException("Head checksum error.");
        }

        // Obtain data size value
        int dataSizeValue = EndianConversor.byteArrayLittleEndianToUShort(messageHeaderData, DATA_SIZE_INDEX);
        if (dataSizeValue >= 0) {
            this.dataSize = dataSizeValue;
        }
        else {
            throw new MessageFormatException("Invalid data size value.");
        }
        
        // Command type
        setCommandType(messageHeaderData[COMMAND_TYPE_INDEX]);
        // HeaderReply type
        this.errorCode = messageHeaderData[ERROR_CODE_INDEX];
        // Secuence number
        int sequenceNumber = EndianConversor.byteArrayLittleEndianToUShort(messageHeaderData, SEQUENCE_NUMBER_INDEX);
        if (sequenceNumber >= 0) {
            setSequenceNumber(sequenceNumber);
        }
        else {
            throw new MessageFormatException("Invalid sequence number value.");
        }        
        // Head checksum
        setHeaderChecksum(headChecksum);
        // Data checksum
        setDataChecksum((byte) 0);
        // Data
        setData(new byte[0]);
    }


    /**
     * Decode data field of the message.
     * This method needs that first decode the head field of the message.
     *
     * @param bytes
     * @param initIndex Indicates index where dataField begins.
     * @throws MessageFormatException
     */
    protected void decodeMessageDataChecksum(byte[] bytes, int initIndex) throws MessageFormatException
    {

        int dataLen = getDataSize();

        if ((dataLen > 0) && (bytes == null)) {
            throw new MessageFormatException("Invalid message size.");
        }

        if (dataLen == 0) {
            setData(new byte[0]);
            setDataChecksum((byte) 0);
        }
        else {
            if (bytes.length != (initIndex + getDataFieldSize())) {
                throw new MessageFormatException("Invalid message size.");
            }
            else {
                // Calculate and verify data checksum (last data field byte)
                byte dataChecksum = calcChecksum(bytes, initIndex, dataLen);
                if (bytes[initIndex + dataLen] != dataChecksum) {
                    throw new MessageFormatException("Data checksum error.");
                }

                setData(Arrays.copyOfRange(bytes, initIndex, initIndex + dataLen));
                setDataChecksum(dataChecksum);
            }
        }

    }


    protected abstract int decodeMessageData(byte[] bytes, int initIndex) throws MessageFormatException;


    public final void decodeMessage(byte [] message) throws MessageFormatException
    {

        this.decodeMessageHead(message); //decode the header
        this.decodeMessageDataChecksum(message, DATA_INDEX); //check the data checksum
        this.decodeMessageData(message, DATA_INDEX); //decode the message

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
        final Command other = (Command) obj;
        if (this.errorCode != other.errorCode) {
            return false;
        }
        if (this.dataSize != other.dataSize) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 37 * hash + this.errorCode;
        hash = 37 * hash + this.dataSize;
        return hash;
    }

    
    /**
     *
     * @param msgData
     * @return
     * @throws MessageFormatException
     */
    public static byte getMessageType(byte[] msgData) throws MessageFormatException
    {
        if (msgData.length >= COMMAND_HEADER_SIZE) {
            return msgData[COMMAND_TYPE_INDEX];
        }
        else {
            throw new MessageFormatException("Message header is too short.");
        }
    }


    /**
     *
     * @param msgData
     * @return
     * @throws MessageFormatException
     */
    public static byte getMessageErrorCode(byte[] msgData) throws MessageFormatException
    {
        if (msgData.length >= COMMAND_HEADER_SIZE) {
            return msgData[ERROR_CODE_INDEX];
        }
        else {
            throw new MessageFormatException("Message header is too short.");
        }
    }

}
