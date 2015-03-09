/*******************************************************************************
 *   
 *   Copyright 2007 Mytech Ingenieria Aplicada <http://www.mytechia.com>, Gervasio Varela
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


package com.mytechia.commons.util.draw;

import java.awt.Point;


/** This class extends the <code>java.awt.Point</code> class of the JAVA official API 
 * to provide methods that return the coordinates as ints.
 *
 * @author  Gervasio Varela Fernandez
 * @version 1
 *
 * Changelog:
 *
 *      17-12-2007  --  Added clone method
 *      27-11-2007  --  Initial version
 */
public class MyPoint extends Point {
    

    /** Constructs and initializes a point at the origin (0, 0) 
     * of the coordinate space.
     */
    public MyPoint()
    {
        super();
    }
    
    
    /** Constructs and initializes a point at the specified (x,y) 
     * location in the coordinate space.
     *
     * @param   x   the X coordinate of the newly constructed MyPoint
     * @param   y   the Y coordinate of the newly constructed MyPoint
     */
    public MyPoint(int x, int y)
    {
        super(x,y);
    }
    
    
    /** Constructs and initializes a point with the same 
     * location as the specified Point object.
     *
     * @param   p   a point
     */
    public MyPoint(Point p)
    {
        super(p);
    }
    
    
    /** Returns the x coordinate of the point
     *
     * @return The x coordinate of the point
     */
    public int getXCoordinate()
    {
        return this.x;
    }
    
    
    /** Returns the y coordinate of the point
     *
     * @return The y coordinate of the point
     */
    public int getYCoordinate()
    {
        return this.y;
    }
    
    
    @Override
    public MyPoint clone()
    {
        return new MyPoint(x, y);
    }
    
    
}
