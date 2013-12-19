/*******************************************************************************
 *   
 *   Copyright 2009 Mytech Ingenieria Aplicada <http://www.mytechia.com>, Gervasio Varela
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


package com.mytechia.commons.util.gps;

import java.io.Serializable;


/** Represents a GPS position by latitude and longitude values
 *
 * @author Gervasio Varela Fernandez
 */
public class GPSPosition implements Serializable
{
    
    
    private double latitude = 0;
    private double longitude = 0;
    
    
    public GPSPosition(double latitude, double longitude)
    {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() 
    {
        return latitude;
    }

    public double getLongitude() 
    {
        return longitude;
    }

    
    @Override
    public boolean equals(Object obj) {
        if (obj != null) {
            if (obj instanceof GPSPosition) {
                
                final GPSPosition other = (GPSPosition) obj;
                
                return ((this.latitude == other.latitude) &&
                        (this.longitude == other.longitude));
                
            }
        }
        
        return false;
        
    }

    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + (int) (Double.doubleToLongBits(this.latitude) ^ (Double.doubleToLongBits(this.latitude) >>> 32));
        hash = 23 * hash + (int) (Double.doubleToLongBits(this.longitude) ^ (Double.doubleToLongBits(this.longitude) >>> 32));
        return hash;
    }
    
    
    
    

}
