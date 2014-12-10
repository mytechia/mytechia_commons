/*******************************************************************************
 *   
 *   Copyright 2008 Mytech Ingenieria Aplicada <http://www.mytechia.com>, Alejandro Paz
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


package com.mytechia.commons.util.thread;

/**
 *
 * @author Alejandro Paz
 */
public class Threads 
{

    /**
     * Find the root thread group.
     * @return 
     */
    public static ThreadGroup findRootThreadGroup()
    {
        ThreadGroup root = Thread.currentThread().getThreadGroup();
        while (root.getParent() != null) {
            root = root.getParent();
        }
        return root;
    }
    
    /**
     * Find the active thread with name 'threadName'.
     * 
     * @param threadName
     * @return 
     */
    public static Thread findActiveThreadByName(String threadName) 
    {
        return findActiveThreadByName(findRootThreadGroup(), threadName);
    }
    

    /**
     * This method recursively visits all thread groups under 'group',
     * searching for the active thread with name 'threadName'.
     * 
     * @param group
     * @param threadName
     * @return 
     */
    private static Thread findActiveThreadByName(ThreadGroup group, String threadName) 
    {
        Thread result = null;
        
        // Get threads in 'group'
        int numThreads = group.activeCount();
        Thread[] threads = new Thread[numThreads * 2];
        numThreads = group.enumerate(threads, false);

        // Enumerate each thread in 'group'
        for (int i = 0; i < numThreads; i++) {
            if (threads[i].getName().equals(threadName)) {
                return threads[i];
            }
        }

        // Get thread subgroups of 'group'
        int numGroups = group.activeGroupCount();
        ThreadGroup[] groups = new ThreadGroup[numGroups * 2];
        numGroups = group.enumerate(groups, false);

        // Recursively visit each subgroup
        for (int i = 0; i < numGroups; i++) {
            result = findActiveThreadByName(groups[i], threadName);
            
            if (result != null) {
                break;
            }
        }
        
        return result;
    }
    

}
