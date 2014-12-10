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

import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Victor Sonora
 */
public class LogsServer 
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        switch (args.length)
        {
            case 0:
                launch(System.getProperty("user.dir"));
                break;
            case 1:
                launch(args[0]);
                break;
        }        
    }
    
    
    private static void launch(String path)
    {
        try
            {
                LogsReceiver logsReceiver = LogsReceiver.initLogsReceiver(path);
                logsReceiver.startReception();
                
            } catch (UnknownHostException | SocketException ex)
            {
                Logger.getLogger(LogsServer.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

}
