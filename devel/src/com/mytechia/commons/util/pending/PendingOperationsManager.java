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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;


/** Checks whether a pending operation has expired and notifies its associated
 * listener in that case.
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      21-may-2014
 */
public class PendingOperationsManager<T> 
{
    
    private final ArrayList<PendingOperation> pendingOperations;
    
    private final long checkPeriod;

    
    
    public PendingOperationsManager(long checkPeriod)
    {
        this.pendingOperations = new ArrayList<PendingOperation>();
        this.checkPeriod = checkPeriod;
        Timer timer = new Timer("Pending-Operations-Checker", true);
        timer.schedule(new PendingOperationExpirationCheckTask(), checkPeriod, checkPeriod);
    }
    
    
    
    public synchronized void addPendingOperation(
            long pendingOperationId, 
            T pendingOperationData, 
            long expirationTime,
            IPendingOperationsManagerListener listener)
    {
        PendingOperation<T> po = new PendingOperation<T>(
                System.currentTimeMillis(), expirationTime, 
                pendingOperationId, pendingOperationData, listener);
        this.pendingOperations.add(po);
    }
    
    
    
    public synchronized T removePendingOperation(long pendingOperationId)
            throws PendingOperationNotFoundException
    {
        Iterator<PendingOperation> poIter = this.pendingOperations.iterator();
        while(poIter.hasNext()) {
            PendingOperation po = poIter.next();
            if (po.getOperationId() == pendingOperationId) {
                poIter.remove();
                return (T) po.getOperationData();
            }
        }
        
        throw new PendingOperationNotFoundException(pendingOperationId);
        
    }
    
    
    private boolean checkExpiration(long currentTime, PendingOperation pendingOperation)
    {
        
        return (currentTime - pendingOperation.getOperationTime()) > pendingOperation.getExpirationTimeout();
        
    }
    
    
    
    private class PendingOperationExpirationCheckTask extends TimerTask
    {

        @Override
        public void run()
        {
            
            long currentTime = System.currentTimeMillis();
            PendingOperation po;
            
            synchronized(pendingOperations) {
                
                Iterator<PendingOperation> poIter = pendingOperations.iterator();
                while(poIter.hasNext()) {
                    po = poIter.next();
                    if (checkExpiration(currentTime, po)) {
                        poIter.remove();
                        po.getListener().notifyPendingOperationExpiration(po.getOperationId(), po.getOperationData());
                    }
                }
                
            }
            
        }
        
    }
    

}
