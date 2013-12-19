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

package com.mytechia.commons.util.io.file;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;


/**
 * <p><b>
 * </b></br>
 *
 * </p>
 *
 * <p><b>Creation date:</b> 11-02-2010</p>
 *
 * <p><b>Changelog:</b></br>
 * <ul>
 * <li>1 - 11-02-2010<\br> Initial release</li>
 * </ul>
 * </p>
 *
 * @author Gervasio Varela
 * @version 1
 */
public class FilenameExtensionFilter implements FilenameFilter
{

    private String[] extensions;

    private File parent;


    public FilenameExtensionFilter(String[] extensions, File parent)
    {
        this.extensions = Arrays.copyOf(extensions, extensions.length);
        this.parent = parent;
    }


    public boolean accept(File dir, String name)
    {
        boolean r = false;

        if (dir.equals(this.parent)) {
            for(String ext : this.extensions) {
                if (name.endsWith(ext)) {
                    r = true;
                    break;
                }
            }
        }

        return r;
        
    }


}
