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

package com.mytechia.commons.framework.simplemessageprotocol.channel;

import com.mytechia.commons.framework.simplemessageprotocol.exception.CommunicationException;
import com.mytechia.commons.framework.simplemessageprotocol.exception.TimeoutException;

/** One-to-one communication channel
 *
 *
 * @author alex
 * @version 1
 *
 * File: IBasicCommunicationChannel.java
 * Date: 12-mar-2008
 * Changelog:
 *
 *      12-mar-2008  --  Initial version
 */
public interface IBasicCommunicationChannel {
    
    public void send(byte [] data, int offset, int count) throws CommunicationException;
    
    public void send(byte [] data) throws CommunicationException;
    
    public int receive(byte [] data, int offset, int count, long timeout) throws CommunicationException;
    
    public int receive(byte [] data, int offset, int count) throws CommunicationException;
    
    public int receive(byte[] data) throws CommunicationException;
    
    public void receiveComplete(byte[] data) throws CommunicationException, TimeoutException;
    
    public int sendReceive(
            byte[] sendData, int sendOffset, int sendCount,
            byte[] recvData, int recvOffset, int recvCount, long timeout)
                throws CommunicationException;
    
     public int sendReceive(
            byte [] sendData, int sendOffset, int sendCount, 
            byte [] recvData, int recvOffset, int recvCount) 
                throws CommunicationException;
     
     public int sendReceive(byte [] sendData, byte [] recvData) 
             throws CommunicationException;
    
    
}
