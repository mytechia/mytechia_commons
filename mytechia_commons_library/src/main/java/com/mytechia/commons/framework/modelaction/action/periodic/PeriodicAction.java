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

import com.mytechia.commons.framework.modelaction.action.ModelAction;


/**
 * <p><b>
 * </b>
 *
 *
 *
 * <p><b>Creation date:</b> 12-04-2010</p>
 *
 * <p><b>Changelog:</b></p>
 * <ul>
 * <li>1 - 12-04-2010 Initial release</li>
 * </ul>
 *
 *
 * @author Gervasio Varela
 * @version 1
 */
public abstract class PeriodicAction extends ModelAction
{

    private int tickTime = 0;
    private long lastTime = 0;

    private Boolean ret = new Boolean(true);

    
    /**
     *
     * @param tickTime Execution period of the action
     */
    public PeriodicAction(int tickTime)
    {
        this.tickTime = tickTime;
    }

    
    private boolean needsExecution()
    {

        return ((System.currentTimeMillis()-lastTime) > tickTime);

    }


    @Override
    public Object execute()
    {

        if (needsExecution()) {
            lastTime = System.currentTimeMillis();
            executePeriodic();
        }

        return ret;

    }


    public abstract void executePeriodic();

}
