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
 * <p><b>Changelog:</b></br>
 * <ul>
 * <li>08-abr-2014</br> Initial release</li>
 * </ul>
 * </p>
 *
 * @author Alma Mallo Casdelo 
 * @version 1
 */


public class ArrayByteQueue implements IByteQueue{
    
    private byte[] data;
    private int initIndex;
    private int nextAvailableIndex;
    private int dataCount;

    public ArrayByteQueue(int size) {
        this.data = new byte[size];
        this.initIndex = 0;
        this.nextAvailableIndex = 0;
        this.dataCount = 0;
    }
    

    @Override
    public void push(byte b) throws FullByteQueueException{   
        if (!isFull()) {
            this.data[nextAvailableIndex] = b;
            addBytes(1);            
        }else {
            throw new FullByteQueueException();
        }
    }

    @Override
    public void push(byte[] bytes)  throws FullByteQueueException{  
        if (fits(bytes.length)) {
            int newIndex = this.nextAvailableIndex + bytes.length - this.data.length;
            if (newIndex < this.data.length) {
                System.arraycopy(bytes, 0, this.data, nextAvailableIndex, bytes.length);
            }else {
                System.arraycopy(bytes, 0, this.data, nextAvailableIndex, this.data.length-this.nextAvailableIndex);
                System.arraycopy(bytes, this.data.length-this.nextAvailableIndex, data, 0, newIndex);
            }
            //addBytes(bytes.length);
            this.nextAvailableIndex = newIndex;
        }else {
            throw new FullByteQueueException();
        }
        
    }

    @Override
    public void push(byte[] bytes, int offset, int count)  throws FullByteQueueException{
        if (fits(bytes.length)) {
            int newIndex = this.nextAvailableIndex + count - this.data.length;
            if (newIndex < this.data.length) {
                System.arraycopy(bytes, offset, this.data, nextAvailableIndex, count);
            }else {
                System.arraycopy(bytes, offset, this.data, nextAvailableIndex, this.data.length-this.nextAvailableIndex);
                System.arraycopy(bytes, offset + this.data.length-this.nextAvailableIndex, data, 0, newIndex);
            }
            //addBytes(bytes.length);
            this.nextAvailableIndex = newIndex;
        }else {
            throw new FullByteQueueException();
        }        
    }

    @Override
    public byte poll() throws EmptyByteQueueException{
        if (!isEmpty()) {
            byte b = this.data[this.initIndex];
            removeBytes(1);
            return b;
        }
        else {
          throw  new EmptyByteQueueException();
        }
    }

    @Override
    public int poll(byte[] bytes, int offset, int count){
        int bytesToRead = count;
        if (count > getAvailableSpace()) {
            bytesToRead = getAvailableSpace();
        }         
        if (bytesToRead > 0) {
            int newIndex = this.initIndex + bytesToRead - this.data.length; 
            if (newIndex < this.data.length) {
                System.arraycopy(this.data, this.initIndex, bytes, offset, bytesToRead);
            }else {
                System.arraycopy(this.data, this.initIndex, bytes, offset, this.data.length-this.initIndex);
                System.arraycopy(this.data, 0, bytes, offset + this.data.length-this.initIndex, newIndex);
            }
            this.initIndex = newIndex;
        }        
        return bytesToRead;
    }

    @Override
    public void clear() {
        this.initIndex = 0;
        this.nextAvailableIndex = 0;
    }

    @Override
    public int getAvailableSpace() {
        return this.data.length - this.dataCount;
    }

    @Override
    public int getUsedSpace() {
        return this.dataCount;
    }
    
    
    @Override
    public boolean isEmpty()
    {
        return this.dataCount == 0;
    }
    
    public boolean isFull() 
    {
        return this.dataCount == this.data.length;
    }
    
    private boolean fits(int numBytes) {
        return numBytes <= getAvailableSpace();
    }
    
    private int incIndex(int index, int increment) {
        int newIndex = index + increment - this.data.length;
        if (newIndex < 0) {
            index += increment;
        }else {
            index = newIndex;
        }
        return index;
    }
    
    private void removeBytes(int byteNum) {
        this.initIndex = incIndex(this.initIndex,byteNum);
    }
    
    private void addBytes(int byteNum) {
        this.nextAvailableIndex = incIndex(this.nextAvailableIndex,byteNum);
    }

}
