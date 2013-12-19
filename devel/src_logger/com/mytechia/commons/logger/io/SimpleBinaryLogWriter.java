/**
 * *****************************************************************************
 *
 * Copyright (C) 2013 Mytech Ingenieria Aplicada <http://www.mytechia.com>
 * Copyright (C) 2013 Victor Sonora <victor@vsonora.com>
 *
 * This file is part of Mytechia Commons.
 *
 * Mytechia Commons is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * Mytechia Commons is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Mytechia Commons. If not, see <http://www.gnu.org/licenses/>.
 *
 *****************************************************************************
 */
package com.mytechia.commons.logger.io;

import com.mytechia.commons.logger.LogInfo;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Victor Sonora
 */
public class SimpleBinaryLogWriter implements ILogWriter
{

    private String filePath = null;
    private PrintWriter pw = null;

    public SimpleBinaryLogWriter(String path)
    {
        this.filePath = path;
    }

    public PrintWriter getPw(String sourceId)
    {
        if (null == pw)
        {
            try
            {
                pw = new PrintWriter(new BufferedWriter(new FileWriter(filePath + File.separator + sourceId)));
            } catch (FileNotFoundException ex)
            {
                Logger.getLogger(SimpleBinaryLogWriter.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex)
            {
                Logger.getLogger(SimpleBinaryLogWriter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return pw;
    }

    @Override
    public void write(Collection<LogInfo> logs, String sourceId)
    {

        getPw(sourceId).printf("Timestamp: %s %n", new Date().toString());
        for (LogInfo logInfo : logs)
        {
            getPw(sourceId).printf("%d;%d;%d %n", logInfo.getTime(), logInfo.getKey(), logInfo.getValue());
        }
        getPw(sourceId).flush();

    }

    @Override
    public void addTimeMark(String sourceId, Date time)
    {

        getPw(sourceId).printf("Timestamp: %s %n", time.toString());
        getPw(sourceId).flush();

    }

    @Override
    public ILogWriter newInstance(String filePath)
    {
        return new SimpleBinaryLogWriter(filePath);
    }
}
