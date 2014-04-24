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

package com.mytechia.commons.framework.modelaction.action.async;

import com.mytechia.commons.framework.modelaction.action.ModelAction;
import com.mytechia.commons.framework.modelaction.action.ModelActionListener;
import java.util.ArrayDeque;
import java.util.ArrayList;


/** A pool of worker threads that asynchronously execute Model Actions
 *
 * @author Gervasio Varela Fernandez - Integrated Group for Engineering Research
 * @version 1
 *
 * Changelog:
 *      24-abr-2014
 */
public class AsyncActionPool 
{
    
    private static final int DEFAULT_THREAD_COUNT = 2;
    
    
    private final ArrayDeque<ModelActionEntry> actionQueue;
    
    private final ArrayList<AsyncActionWorkerThread> threadPool;
    
    
    private boolean stop = false;
    private final Integer semaphore = new Integer(0);
    
    
    /** Creates a pool of worker threads of default size (2)
     */
    public AsyncActionPool()
    {
        this(DEFAULT_THREAD_COUNT);
    }
    
    
    /** Creates a pool of worker threads of size 'threadCount'
     */
    public AsyncActionPool(int threadCount)
    {
        this.actionQueue = new ArrayDeque<ModelActionEntry>();
        this.threadPool = new ArrayList<AsyncActionWorkerThread>(threadCount);
        createThreads(threadCount);
    }
    
    
    /** Creates the worker threads and starts their execution
     * 
     * @param threadCount the number of worker threads to create
     */
    private void createThreads(int threadCount)
    {
        
        for(int i=0; i<threadCount; i++) {
            
            AsyncActionWorkerThread worker = new AsyncActionWorkerThread("Async-Action-Worker-"+i);
            this.threadPool.add(worker);
            worker.start();
            
        }
        
    }
    
    
    /** Adds an action to the processing queue
     * 
     * @param action the action to execute
     * @param listener an optional listener object to be notified of the action end state
     */
    public void addAction(ModelAction action, ModelActionListener listener)
    {
        
        synchronized(actionQueue) {
            this.actionQueue.add(new ModelActionEntry(action, listener));
        }
        
        wakeUp();
        
    }
    
    
    /** Retrieves an action from the queue
     * It is a blocking method, it blocks until an action is available in the
     * queue or the processing pool is stopped 
     */
    private ModelActionEntry pollAction()
    {
        ModelActionEntry action = null;
        
        while(!stop) {
           
            synchronized(actionQueue) {
                action = this.actionQueue.poll();
            }
            
            if (action != null) {
                break;
            }
            else {
                synchronized(semaphore) {
                    try {
                        semaphore.wait();
                    } catch (InterruptedException ex) {
                        ex.printStackTrace(); //////////
                    }
                }
            }

        }
        
        return action;
        
    }
    
    
    /** Stops the processing queue
     * It can't be started again
     */
    public void stop()
    {
        this.stop = true;
    }
    
    
    /** Wakes up a working thread that is waiting for actions
     */
    private void wakeUp()
    {
        
        synchronized(semaphore) {
            semaphore.notify();
        }
        
    }
    
    
    
    /** Used to store the actions and their associated listeners in the queue
     */
    private class ModelActionEntry
    {
        ModelAction action;
        ModelActionListener listener;

        public ModelActionEntry(ModelAction action, ModelActionListener listener)
        {
            this.action = action;
            this.listener = listener;
        }

    }
    
    
    /** The implementation of the worker thread.
     * It retrives actions from the queue, executes them, and notifies
     * the result of the execution using the (optional) listener object
     */
    private class AsyncActionWorkerThread extends Thread
    {

        
        public AsyncActionWorkerThread(String name)
        {
            super(name);
        }

        
        
        @Override
        public void run()
        {
            
            while(!stop) {
                
                ModelActionEntry actionEntry = pollAction();
                
                if (actionEntry != null) {
                    
                    try {
                        
                        Object result = actionEntry.action.execute();
                        
                        if (actionEntry.listener != null) 
                            actionEntry.listener.actionCompleted(actionEntry.action, result);
                        
                    }
                    catch(Exception ex) {
                        if (actionEntry.listener != null)
                            actionEntry.listener.exceptionThrown(actionEntry.action, ex);
                    }
                    
                }
                
            }
            
        }
        
        
        
    }
    

}
