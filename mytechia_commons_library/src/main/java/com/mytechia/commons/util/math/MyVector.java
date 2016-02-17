/*******************************************************************************
 *
 *   Copyright 2008 Mytech Ingenieria Aplicada <http://www.mytechia.com>, Gervasio Varela
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

package com.mytechia.commons.util.math;

/** A vector with operations: normalize, module
 *
 * @author  Gervasio Varela Fernandez
 * @version 1
 *
 * File: Vector.java
 * Date: 06-oct-2008
 * Changelog:
 *
 *      06-oct-2008  --  Initial version
 */
public class MyVector 
{

    
    private final double [] vector;
    
    
    /** Creates a new Vector of dimension 'dim'
     * 
     * @param dim the dimension of the new Vector
     */
    public MyVector(int dim)
    {
        vector = new double[dim];
    }
    
    
    /** Creates a new Vector from the specified vector components.
     * The created Vector uses its own copy of the specified data, so it can
     * be modified without problems.
     * 
     * @param vector
     */
    public MyVector(double [] vector)
    {
        this.vector = new double[vector.length];
        System.arraycopy(vector, 0, this.vector, 0, this.vector.length);
    }
    
    
    public int getDimension()
    {
        return vector.length;
    }
    
    
    /** Returns the component 'index' of the vector
     * 
     * @param index index of the component to get
     * @return the component in dimension 'index' of the vector
     * @throws ArrayIndexOutOfBoundsException if the index is out of the dimension of the vector
     */
    public double get(int index)
    {
        return vector[index];
    }
    
    
    /** Calculates the module of this vector
     * 
     * @return the module of this vector
     */
    public double module()
    {
        
        double v = 0;
        
        for(int i=0; i<vector.length; i++) {
            v = Math.pow(vector[i], 2);
        }
        
        return Math.sqrt(v);
        
    }
    
    
    /** Calculates the associated normalized Vector of this Vector
     * 
     * @return the associated normalized Vector of this Vector
     */
    public MyVector normalize()
    {
        
        double vmodule = module();
        
        double [] associatedVector = new double [vector.length];
        
        for(int i=0; i<vector.length; i++) {
            associatedVector[i] = (1/vmodule) * vector[i];
        }
        
        return new MyVector(associatedVector);
        
    }
    
    
    
}
