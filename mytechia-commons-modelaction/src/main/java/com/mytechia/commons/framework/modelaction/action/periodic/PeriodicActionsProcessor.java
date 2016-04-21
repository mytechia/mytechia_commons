/*******************************************************************************
 *   
 *   Copyright 2010 Mytech Ingenieria Aplicada <http://www.mytechia.com>, Gervasio Varela
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

package com.mytechia.commons.framework.modelaction.action.periodic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


/**
 * <p><b>Description:</b></p>
 *
 *
 * <p><b>Creation date:</b> 04-02-2010</p>
 *
 * <p><b>Changelog:</b></p>
 * <ul>
 * <li>1 - 04-02-2010 Initial release.</li>
 * </ul>
 *
 *
 * @author Gervasio Varela Fernandez
 * @version 1
 */
public class PeriodicActionsProcessor extends Thread
{

    private final static int WAIT_TIME = 50; //time to wait before checking again for actions to execute

    private boolean checking = false;


    private final List<PeriodicAction> periodicActions;

    

    public PeriodicActionsProcessor(Collection<PeriodicAction> actions)
    {
        this.periodicActions = Collections.synchronizedList(new ArrayList<PeriodicAction>());
        this.periodicActions.addAll(actions);
    }


    public PeriodicActionsProcessor()
    {
        this(new ArrayList<PeriodicAction>(0));
    }


    public void startChecking()
    {
        this.checking = true;
        this.start();
    }


    public void stopChecking()
    {
        this.checking = false;
    }


    public void addPeriodicAction(PeriodicAction action)
    {
        this.periodicActions.add(action);
    }


    public void removePeriodicAction(PeriodicAction action)
    {
        this.periodicActions.remove(action);
    }


    @Override
    public void run()
    {

        while(this.checking) {

            synchronized(periodicActions) {
                Iterator<PeriodicAction> iter = periodicActions.iterator();

                while(iter.hasNext()) {
                    iter.next().execute();
                }
            }

            try {
                Thread.sleep(WAIT_TIME);
            }
            catch (InterruptedException ex) { }

        }

    }


}
