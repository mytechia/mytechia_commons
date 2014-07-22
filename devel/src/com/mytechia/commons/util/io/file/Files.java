/*******************************************************************************
 *   
 *   Copyright 2009 Mytech Ingenieria Aplicada <http://www.mytechia.com>, Gervasio Varela
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

package com.mytechia.commons.util.io.file;

import java.io.File;


/**
 * <p><b>Description:</b>
 *
 *
 * </p>
 *
 * <p><b>Creation date:</b> 25-jun-2009</p>
 *
 * <p><b>Changelog:</b>
 * <ul>
 * <li>1 - 25-jun-2009 Initial release</li>
 * </ul>
 * </p>
 *
 * @author Gervasio Varela Fernandez
 * @version 1
 */
public class Files
{

    /** Deletes a directory and its contents.
     */
    public static boolean deleteDirectory(File dir)
    {

        if (dir.canWrite()) {
            File [] subFiles = dir.listFiles();

            for(int i=0; i<subFiles.length; i++) {
                if (subFiles[i].isDirectory())
                    deleteDirectory(subFiles[i]);
                else
                    subFiles[i].delete();
            }

        }

        return dir.delete();

    }

}
