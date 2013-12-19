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


package com.mytechia.commons.logger.io;

import com.mytechia.commons.logger.LogInfo;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author Victor Sonora
 */
public class CompositeLogWriter implements ILogWriter
{
    
    String filePath;
    
    private HashMap<String,ILogWriter> logWriters;
    
    private ILogWriter defaultLogWriterPrototype;
    
    
    public CompositeLogWriter(String filePath, ILogWriter logWriter)
    {
        this.logWriters = new HashMap<>();
        this.defaultLogWriterPrototype = logWriter;
        this.filePath = filePath;
    }        
    

    @Override
    public void write(Collection<LogInfo> logs, String sourceId)
    {
        getLogWriter(sourceId).write(logs, sourceId);
    }

    @Override
    public void addTimeMark(String sourceId, Date time)
    {
        getLogWriter(sourceId).addTimeMark(sourceId, time);
    }

    @Override
    public ILogWriter newInstance(String path)
    {
        return new CompositeLogWriter(path, defaultLogWriterPrototype);
    }
    
    
    private ILogWriter getLogWriter(String id)
    {
        ILogWriter logWriter = logWriters.get(id);
        if (null == logWriter)
        {
            logWriter = defaultLogWriterPrototype.newInstance(this.filePath);
            this.logWriters.put(id, logWriter);
            return logWriter;
        }
        return logWriter;
    }

}
