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

/**
 *
 * @author Victor Sonora
 */
public class LogAddress 
{
    
    private int ad1;
    private int ad2;
    
    
    public LogAddress(int ad1, int ad2)
    {
        this.ad1 = ad1;
        this.ad2 = ad2;
    }

    public int getAd1()
    {
        return ad1;
    }

    public int getAd2()
    {
        return ad2;
    }
    
    
    @Override
    public String toString() 
    {
        return String.valueOf(this.ad1) + "-" + String.valueOf(this.ad2);
    }

}
