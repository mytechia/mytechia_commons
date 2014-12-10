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
import com.mytechia.commons.util.conversion.EndianConversor;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Victor Sonora
 */
public class InitLogMessage extends Command
{

    public static final byte INIT_LOG_MESSAGE_TYPE = 101;
    
    
    private LogAddress id;
    
    
    public InitLogMessage(LogAddress id)
    {
        super();
        this.setCommandType(INIT_LOG_MESSAGE_TYPE);
        
        this.id = id;
    }
    
    
    public InitLogMessage(byte[] message) throws MessageFormatException
    {
        super();
        super.decodeMessage(message);
        this.setCommandType(INIT_LOG_MESSAGE_TYPE);
    }

    
    public LogAddress getId()
    {
        return id;
    }
            
    
    @Override
    protected int decodeMessageData(byte[] bytes, int initIndex) throws MessageFormatException
    {
        int offset = initIndex;        
        StringBuilder string = new StringBuilder();
        
        short ad1 = EndianConversor.byteArrayLittleEndianToShort(bytes, offset);
        offset += EndianConversor.SHORT_SIZE_BYTES;        
        short ad2 = EndianConversor.byteArrayLittleEndianToShort(bytes, offset);
        offset += EndianConversor.SHORT_SIZE_BYTES;        
        this.id = new LogAddress(ad1, ad2);

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
        
        return dataStream.toByteArray();
    }
    
    
}
