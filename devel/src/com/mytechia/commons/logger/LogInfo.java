/*******************************************************************************
 *   
 *   Copyright 2013 Mytech Ingenieria Aplicada <http://www.mytechia.com>
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

/**
 * @author Victor Sonora
 */
public class LogInfo 
{
    
    private short time;
    private short key;
    private long value;

    public short getTime()
    {
        return time;
    }

    public void setTime(short time)
    {
        this.time = time;
    }

    public short getKey()
    {
        return key;
    }

    public void setKey(short key)
    {
        this.key = key;
    }

    public long getValue()
    {
        return value;
    }

    public void setValue(long value)
    {
        this.value = value;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 67 * hash + this.time;
        hash = 67 * hash + this.key;
        hash = 67 * hash + (int)this.value;
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final LogInfo other = (LogInfo) obj;
        if (this.time != other.time)
        {
            return false;
        }
        if (this.key != other.key)
        {
            return false;
        }
        if (this.value != other.value)
        {
            return false;
        }
        return true;
    }
    
}
