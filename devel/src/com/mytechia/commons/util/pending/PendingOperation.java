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


/** A PendingOperation represents an asynchronous action or operation for which
 * a response is expected in a specied period of time (timeout).
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      21-may-2014
 */
public class PendingOperation<T> 
{
    
    private final long operationTime;
    private final long expirationTimeout;
    
    private final long operationId;
    
    private final T operationData;
    
    
    private IPendingOperationsManagerListener listener;

    
    
    public PendingOperation(
            long operationTime, long expirationTimeout, 
            long operationId, T operationData,
            IPendingOperationsManagerListener listener)
    {
        this.operationTime = operationTime;
        this.expirationTimeout = expirationTimeout;
        this.operationId = operationId;
        this.operationData = operationData;
        this.listener = listener;
    }

    public long getOperationTime()
    {
        return operationTime;
    }

    public long getExpirationTimeout()
    {
        return expirationTimeout;
    }

    public long getOperationId()
    {
        return operationId;
    }

    public T getOperationData()
    {
        return operationData;
    }

    public IPendingOperationsManagerListener getListener()
    {
        return listener;
    }
  

}
