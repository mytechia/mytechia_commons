/*******************************************************************************
 *   
 *   Copyright 2010 Mytech Ingenieria Aplicada <http://www.mytechia.com>, Alma Mallo, Gervarsio Varela
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

package com.mytechia.commons.util.collections.bytequeue;

import com.mytechia.commons.util.collections.bytequeue.exception.EmptyByteQueueException;
import com.mytechia.commons.util.collections.bytequeue.exception.FullByteQueueException;


/**
 * <p></p>
 *
 * <p><b>Creation date:</b>08-abr-2014</p>
 *
 * <p><b>Changelog:</b>
 * <ul>
 * <li>08-abr-2014 Initial release</li>
 * </ul>
 * </p>
 *
 * @author Alma Mallo Casdelo 
 * @version 1
 */

public interface IByteQueue 
{
    void push(byte b) throws FullByteQueueException;
    void push(byte[] bytes) throws FullByteQueueException;
    void push(byte[] bytes, int offset, int count) throws FullByteQueueException;
    
    byte poll() throws EmptyByteQueueException;
    int poll(byte[] bytes, int offset, int count);
    
    byte get() throws EmptyByteQueueException;
    int get(byte[] bytes, int offset, int count);

    void clear();   
    int getAvailableSpace();
    int getUsedSpace();
    boolean isEmpty();
    void discardBytes(int byteNum);
}
