/*******************************************************************************
 *   
 *   Copyright (C) 2013 Mytech Ingenieria Aplicada <http://www.mytechia.com>
 *   Copyright (C) 2013 Victor Sonora <victor@vsonora.com>
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


package com.mytechia.commons.logger.protocol;

import com.mytechia.commons.framework.simplemessageprotocol.Command;
import com.mytechia.commons.framework.simplemessageprotocol.exception.MessageFormatException;
import com.mytechia.commons.logger.LogInfo;
import com.mytechia.commons.util.conversion.EndianConversor;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collection;



/**
 *
 * @author Victor Sonora
 */
public class LogMessage extends Command
{
    
    public static final byte LOG_MESSAGE_TYPE = 106;
    
    
    private LogAddress id;
    private Collection<LogInfo> logInfos;
    
    
    public LogMessage(Collection<LogInfo> logInfos, LogAddress id)
    {
        super();
        this.setCommandType(LOG_MESSAGE_TYPE);
        
        this.id = id;
        this.logInfos = new ArrayList<>();
        this.logInfos.addAll(logInfos);
    }
    
    
    public LogMessage(byte[] message) throws MessageFormatException
    {
        super();
        super.decodeMessage(message);
        this.setCommandType(LOG_MESSAGE_TYPE);
    }

    
    public LogAddress getId()
    {
        return id;
    }

    public Collection<LogInfo> getLogInfos()
    {
        return logInfos;
    }
    
    

    @Override
    protected int decodeMessageData(byte[] bytes, int initIndex) throws MessageFormatException
    {
        int offset = initIndex;
        logInfos = new ArrayList<>();
        
        short ad1 = EndianConversor.byteArrayLittleEndianToShort(bytes, offset);
        offset += EndianConversor.SHORT_SIZE_BYTES;        
        short ad2 = EndianConversor.byteArrayLittleEndianToShort(bytes, offset);
        offset += EndianConversor.SHORT_SIZE_BYTES;        
        this.id = new LogAddress(ad1, ad2);

        short logInfoNumber = EndianConversor.byteArrayLittleEndianToShort(bytes, offset);
        offset += EndianConversor.SHORT_SIZE_BYTES;

        for (int i = 0; i < logInfoNumber; i++)
        {
            LogInfo logInfo = new LogInfo();
            logInfo.setTime(EndianConversor.byteArrayLittleEndianToShort(bytes, offset));
            offset += EndianConversor.SHORT_SIZE_BYTES;
                        
            logInfo.setKey(EndianConversor.byteArrayLittleEndianToShort(bytes, offset));
            offset += EndianConversor.SHORT_SIZE_BYTES;
            
            logInfo.setValue(EndianConversor.byteArrayLittleEndianToUInt(bytes, offset));
            offset += EndianConversor.INT_SIZE_BYTES;
            
            logInfos.add(logInfo);
        }

        return offset;
    }
    
    @Override
    protected final byte[] codeMessageData() 
    {
        ByteArrayOutputStream dataStream = new ByteArrayOutputStream();        
        
        byte[] idData = new byte[EndianConversor.INT_SIZE_BYTES];
        
        EndianConversor.shortToLittleEndian((short) this.id.getAd1(), idData, 0);
        dataStream.write(idData, 0, EndianConversor.SHORT_SIZE_BYTES);
        
        EndianConversor.shortToLittleEndian((short) this.id.getAd1(), idData, 0);
        dataStream.write(idData, 0, EndianConversor.SHORT_SIZE_BYTES);

        EndianConversor.shortToLittleEndian((short) logInfos.size(), idData, 0);
        dataStream.write(idData, 0, EndianConversor.SHORT_SIZE_BYTES);

        for (LogInfo logInfo : logInfos)
        {
            EndianConversor.shortToLittleEndian(logInfo.getTime(), idData, 0);
            dataStream.write(idData, 0, EndianConversor.SHORT_SIZE_BYTES);

            EndianConversor.shortToLittleEndian(logInfo.getKey(), idData, 0);
            dataStream.write(idData, 0, EndianConversor.SHORT_SIZE_BYTES);

            EndianConversor.uintToLittleEndian(logInfo.getValue(), idData, 0);
            dataStream.write(idData, 0, EndianConversor.INT_SIZE_BYTES);
        }
        
        return dataStream.toByteArray();
    }

}
