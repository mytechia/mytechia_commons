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
import java.util.ArrayList;


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
    

    protected byte[] getData() {
        return data;
    }

    protected int getInitIndex() {
        return initIndex;
    }

    protected int getNextAvailableIndex() {
        return nextAvailableIndex;
    }

    protected int getDataCount() {
        return dataCount;
    }

    protected void setData(byte[] data) {
        this.data = data;
    }

    protected void setInitIndex(int initIndex) {
        this.initIndex = initIndex;
    }

    protected void setNextAvailableIndex(int nextAvailableIndex) {
        this.nextAvailableIndex = nextAvailableIndex;
    }

    protected void setDataCount(int dataCount) {
        this.dataCount = dataCount;
    }

    
    
    protected ModifiedIndexes pushByte(byte b) throws FullByteQueueException {   
        if (!isFull()) {
            this.data[nextAvailableIndex] = b;
            ModifiedIndexes modifiedIndexes = new ModifiedIndexes(nextAvailableIndex, nextAvailableIndex);
            addBytes(1);
            return modifiedIndexes;
        }else {
            throw new FullByteQueueException();
        }
    }
    

    @Override
    public void push(byte b) throws FullByteQueueException{   
        pushByte(b);
    }

    @Override
    public void push(byte[] bytes)  throws FullByteQueueException {  
        pushBytes(bytes, 0, bytes.length);
    }

    
    protected ArrayList<ModifiedIndexes> pushBytes(byte[] bytes, int offset, int count)  throws FullByteQueueException {
        
        ArrayList<ModifiedIndexes> modifiedIndexes = new ArrayList<ModifiedIndexes>(2);
        
        if (fits(count)) {
            int newIndex = this.nextAvailableIndex + count - this.data.length;
            if (newIndex < 0) {
                System.arraycopy(bytes, offset, this.data, nextAvailableIndex, count);
                modifiedIndexes.add(new ModifiedIndexes(nextAvailableIndex, nextAvailableIndex+count-1));
            }else {
                System.arraycopy(bytes, offset, this.data, nextAvailableIndex, this.data.length-this.nextAvailableIndex);
                System.arraycopy(bytes, offset + this.data.length-this.nextAvailableIndex, data, 0, newIndex);
                modifiedIndexes.add(new ModifiedIndexes(nextAvailableIndex, this.data.length-1));
                modifiedIndexes.add(new ModifiedIndexes(0, newIndex-1));
            }
            addBytes(count);
            //this.nextAvailableIndex = newIndex;
        }else {
            throw new FullByteQueueException();
        }        
        
        return modifiedIndexes;
        
    }
    
    
    @Override
    public void push(byte[] bytes, int offset, int count)  throws FullByteQueueException{
        pushBytes(bytes, offset, count);
    }

    
    
    
    protected byte pollByte(ModifiedIndexes modifiedIndexes) throws EmptyByteQueueException {
        byte b = this.getByte(modifiedIndexes);
        removeBytes(1);
        return b;
    }    
    
    @Override
    public byte poll() throws EmptyByteQueueException {
        return pollByte(new ModifiedIndexes());
    }

    
    protected int pollBytes(byte[] bytes, int offset, int count, ArrayList<ModifiedIndexes> modifiedIndexes) {
        int bytesRead = getBytes(bytes, offset, count, modifiedIndexes);
        if (bytesRead > 0 ) {
            removeBytes(bytesRead);
        }
        return bytesRead;
    }
    
    @Override
    public int poll(byte[] bytes, int offset, int count){
        return pollBytes(bytes, offset, count, new ArrayList<ModifiedIndexes>(2));
    }
    
    
        
    protected byte getByte(ModifiedIndexes modifiedIndexes) throws EmptyByteQueueException {
        if (!isEmpty()) {
            byte b = this.data[this.initIndex];
            modifiedIndexes.initIndex = this.initIndex;
            modifiedIndexes.endIndex = this.initIndex;
            return b;
        }
        else {
          throw  new EmptyByteQueueException();
        }        
    }
    
    @Override
    public byte get() throws EmptyByteQueueException {
        return getByte(new ModifiedIndexes());
    }
    
    protected int getBytes(byte[] bytes, int offset, int count, ArrayList<ModifiedIndexes> modifiedIndexes) {
        int bytesToRead = count;
        if (count > getUsedSpace()) {
            bytesToRead = getUsedSpace();
        }         
        if (bytesToRead > 0) {
            int newIndex = this.initIndex + bytesToRead - this.data.length; 
            if (newIndex < 0) {
                System.arraycopy(this.data, this.initIndex, bytes, offset, bytesToRead);
                modifiedIndexes.add(new ModifiedIndexes(this.initIndex, this.initIndex+bytesToRead-1));
            }else {
                System.arraycopy(this.data, this.initIndex, bytes, offset, this.data.length-this.initIndex);
                System.arraycopy(this.data, 0, bytes, offset + this.data.length-this.initIndex, newIndex);
                modifiedIndexes.add(new ModifiedIndexes(this.initIndex, this.data.length-1));
                modifiedIndexes.add(new ModifiedIndexes(0, newIndex-1));
            }
        }        
        return bytesToRead;
    }
    
    @Override
    public int get(byte[] bytes, int offset, int count) {
        return getBytes(bytes, offset, count, new ArrayList<ModifiedIndexes>(2));
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
    
    protected boolean fits(int numBytes) {
        return numBytes <= getAvailableSpace();
    }
    
    protected int incIndex(int index, int increment) {
        int newIndex = index + increment - this.data.length;
        if (newIndex < 0) {
            index += increment;
        }else {
            index = newIndex;
        }
        return index;
    }
    
    protected void removeBytes(int byteNum) {
        this.initIndex = incIndex(this.initIndex,byteNum);
        this.dataCount -= byteNum;
    }
    
    protected void addBytes(int byteNum) {
        this.nextAvailableIndex = incIndex(this.nextAvailableIndex,byteNum);
        this.dataCount += byteNum;
    }
    
    public void discardBytes(int byteNum) {
        this.removeBytes(byteNum);
    }
    
    
    protected class ModifiedIndexes 
    {
        
        public int initIndex, endIndex;
        
        
        public ModifiedIndexes() {
            
        }

        public ModifiedIndexes(int initIndex, int endIndex) {
            this.initIndex = initIndex;
            this.endIndex = endIndex;
        }

    }
    

}
