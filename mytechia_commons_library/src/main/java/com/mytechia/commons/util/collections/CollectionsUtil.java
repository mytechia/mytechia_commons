/*******************************************************************************
 *   
 *   Copyright 2010 Mytech Ingenieria Aplicada <http://www.mytechia.com>, Alejandro Paz
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

package com.mytechia.commons.util.collections;

import com.mytechia.commons.util.clone.PublicCloneable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p><b>Description:</b></p>
 *
 *
 *
 * <p><b>Creation date:</b> 19-05-2010</p>
 *
 * <p><b>Changelog:</b></p>
 * <ul>
 * <li>1 - 19-05-2010 Initial release</li>
 * </ul>
 *
 *
 * @author Alejandro Paz
 * @version 1
 */
public class CollectionsUtil
{

    public static List<? extends PublicCloneable> clone(List<? extends PublicCloneable> list) throws CloneNotSupportedException
    {
        if (list == null) {
            return null;
        }
        
        ArrayList<PublicCloneable> copy = new ArrayList<PublicCloneable>(list.size());
        for (PublicCloneable obj : list) {
            copy.add((PublicCloneable) obj.clone());
        }

        return copy;
    }

}
