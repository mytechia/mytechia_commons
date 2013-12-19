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



package com.mytechia.commons.logger;

import com.mytechia.commons.framework.simplemessageprotocol.Command;
import com.mytechia.commons.framework.simplemessageprotocol.channel.INetworkBasicCommunicationChannel;
import com.mytechia.commons.framework.simplemessageprotocol.exception.CommunicationException;
import com.mytechia.commons.framework.simplemessageprotocol.exception.MessageFormatException;
import com.mytechia.commons.logger.io.CompositeLogWriter;
import com.mytechia.commons.logger.io.ILogWriter;
import com.mytechia.commons.logger.io.SimpleBinaryLogWriter;
import com.mytechia.commons.logger.protocol.InitLogMessage;
import com.mytechia.commons.logger.protocol.LogMessage;
import com.mytechia.commons.logger.protocol.udp.UDPLogCommunicationChannelImplementation;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Victor Sonora
 */
public class LogsReceiver extends Thread
{

    private boolean receiving = false;
    private INetworkBasicCommunicationChannel commChannel;
    private ILogWriter logWriter;
    

    public LogsReceiver(INetworkBasicCommunicationChannel commChannel,
            ILogWriter logWriter)
    {
        super("Logs-Receiver");
        this.commChannel = commChannel;
        this.logWriter = logWriter;
    }
    
    
    public static LogsReceiver initLogsReceiver(String logsPath) throws UnknownHostException, SocketException
    {
        return new LogsReceiver(new UDPLogCommunicationChannelImplementation(),
                new CompositeLogWriter(logsPath, new SimpleBinaryLogWriter(logsPath)));
    }
    
    

    public void startReception()
    {
        this.receiving = true;
        this.start();
    }

    public void stopReception()
    {
        this.receiving = false;
    }

    @Override
    public void run()
    {

        while (this.receiving)
        {
            try
            {
                receiveMessage();
            } catch (CommunicationException ex)
            {
                Logger.getLogger(LogsReceiver.class.getName()).log(Level.WARNING, null, ex);
            }
        }

    }

    private void receiveMessage() throws CommunicationException
    {
        byte[] receivedData = this.commChannel.receive().getData();
        
        LogMessage receivedLogMessage = createLogMessageFromBytes(receivedData);
        if (null != receivedLogMessage)
        {
            this.logWriter.write(receivedLogMessage.getLogInfos(), receivedLogMessage.getId().toString());
        } else
        {
            InitLogMessage receivedInitLogMessage = createInitLogMessageFromBytes(receivedData);
            if (null != receivedInitLogMessage) 
            {
                this.logWriter.addTimeMark(receivedInitLogMessage.getId().toString(), new Date());
            }
        }
    }

    
    private LogMessage createLogMessageFromBytes(byte[] msgData) throws MessageFormatException
    {
        byte msgType = Command.getMessageType(msgData);
        if (msgType == LogMessage.LOG_MESSAGE_TYPE)
        {
            return new LogMessage(msgData);
        }
        return null;
    }
    
    
    private InitLogMessage createInitLogMessageFromBytes(byte[] msgData) throws MessageFormatException
    {
        byte msgType = Command.getMessageType(msgData);
        if (msgType == InitLogMessage.INIT_LOG_MESSAGE_TYPE)
        {
            return new InitLogMessage(msgData);
        }
        return null;
    }
    
}
