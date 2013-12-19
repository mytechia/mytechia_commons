/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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

package com.mytechia.commons.util.io;

import java.io.IOException;
import java.io.InputStream;


/**
 * <p><b>Description:</b></br>
 * Reads multiple input streams sequentially as if they were only on input stream
 *
 * </p>
 *
 * <p><b>Creation date:</b> 01-jul-2009</p>
 *
 * <p><b>Changelog:</b></br>
 * <ul>
 * <li>1 - 01-jul-2009<\br> Initial release</li>
 * </ul>
 * </p>
 *
 * @author Gervasio Varela Fernandez
 * @version 1
 */
public class MultiInputStream extends InputStream
{

    private InputStream [] inputs = null;

    private int currentInput = 0;
    

    public MultiInputStream(InputStream [] inputs)
    {
        super();
        this.inputs = inputs;
    }


    @Override
    public int read() throws IOException
    {

        int b = 0;
        while ((currentInput < inputs.length) && (b = inputs[currentInput].read()) == -1) {
            currentInput++;
        }

        if (currentInput >= inputs.length)
            return -1;
        else
            return b;

    }


}
