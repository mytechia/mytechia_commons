/*******************************************************************************
 *   
 *   Copyright 2014 Mytech Ingenieria Aplicada <http://www.mytechia.com>
 *   Copyright 2014 Gervasio Varela <gervasio.varela@udc.es>
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

package com.mytechia.commons.util.pending;

/** This interface must be implemented in order to be notified of expirations
 * of pending operations.
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      21-may-2014
 */
public interface IPendingOperationsManagerListener<E>
{
    
    
    public void notifyPendingOperationExpiration(long pendingOperationId, E pendingOperationData);
    

}
