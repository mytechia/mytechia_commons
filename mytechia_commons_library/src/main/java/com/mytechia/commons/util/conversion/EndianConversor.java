/*******************************************************************************
 *   
 *   Copyright 2007 Mytech Ingenieria Aplicada <http://www.mytechia.com>, Gervasio Varela, Alejandro Paz, 
 *   Victor Sonora
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


package com.mytechia.commons.util.conversion;

import java.math.BigInteger;

/** This class contains methods to convert multibyte data type variables
 * to other endianes (as arrays of bytes).
 * 
 * NOTE: In JAVA all multibyte data types are in BIGENDIAN (network byte order)
 *
 * @author  Gervasio Varela Fernandez, Alejandro Paz, Victor Sonora
 * @version 1
 *
 * File: EndianConversor.java
 * Date: 24/12/2007
 * Changelog:
 *
 *      24/12/2007  --  Initial version
 */
public final class EndianConversor 
{

    
    public static final int BYTE_SIZE_BYTES = 1;
    public static final int SHORT_SIZE_BYTES = 2;
    public static final int INT_SIZE_BYTES = 4;
    public static final int LONG_SIZE_BYTES = 8;
    public static final int BYTE_SIZE_BITS = BYTE_SIZE_BYTES*4;
    public static final int SHORT_SIZE_BITS = SHORT_SIZE_BYTES*4;
    public static final int INT_SIZE_BITS = INT_SIZE_BYTES*4;
    public static final int LONG_SIZE_BITS = LONG_SIZE_BYTES*4;


    
    public static void shortToBigEndian(short v, byte[] data, int dataIndex)
    {
        data[dataIndex] = (byte) (v >> 8 & 0xff);
        data[dataIndex + 1] = (byte) (v & 0xff);
    }
    
    
    public static void shortToLittleEndian(short v, byte[] data, int dataIndex)
    {
        data[dataIndex] = (byte) (v & 0xff);
        data[dataIndex + 1] = (byte) (v >> 8 & 0xff);
    }
    
    public static void ushortToLittleEndian(int v, byte[] data, int dataIndex)
    {
        data[dataIndex] = (byte)(v & 0xff);
        data[dataIndex + 1] = (byte)(v >>> 8);
    }


    public static void ushortToBigEndian(int v, byte[] data, int dataIndex)
    {
        data[dataIndex] = (byte)(v >> 8 & 0xff);;
        data[dataIndex + 1] = (byte)(v & 0xff);
    }
    
    
    public static short byteArrayLittleEndianToShort(byte[] data, int dataIndex) 
    {
        short s = 0;

        s += (short) ((data[dataIndex + 1] & 0xff) << 8);
        s += (short) (data[dataIndex] & 0xff);

        return s;
    }
    
    
    public static short byteArrayBigEndianToShort(byte[] data, int dataIndex) 
    {
        short s = 0;

        s += (short) ((data[dataIndex] & 0xff) << 8);
        s += (short) (data[dataIndex + 1] & 0xff);

        return s;
    }
    
    
    public static int byteArrayLittleEndianToUShort(byte[] data, int dataIndex) 
    {        
        return (int)byteArrayLittleEndianToShort(data, dataIndex) & 0xffff;
    }


    public static int byteArrayBigEndianToUShort(byte[] data, int dataIndex)
    {
        return (int)byteArrayBigEndianToShort(data, dataIndex);
    }



    public static void intToBigEndian(int v, byte[] data, int dataIndex)
    {
        data[dataIndex] = (byte)(v >> 24);
        data[dataIndex + 1] = (byte)(v >> 16 & 0xff);
        data[dataIndex + 2] = (byte)(v >> 8 & 0xff);
        data[dataIndex + 3] = (byte)(v & 0xff);
    }
    
    
    public static void uintToLittleEndian(long v, byte[] data, int dataIndex)
    {
        data[dataIndex] = (byte)(v & 0xff);
        data[dataIndex + 1] = (byte)(v >>> 8 & 0xff);
        data[dataIndex + 2] = (byte)(v >>> 16 & 0xff);
        data[dataIndex + 3] = (byte)(v >>> 24);
    }


    public static void uintToBigEndian(long v, byte[] data, int dataIndex)
    {
        data[dataIndex] = (byte)(v >> 24);
        data[dataIndex + 1] = (byte)(v >> 16 & 0xff);
        data[dataIndex + 2] = (byte)(v >> 8 & 0xff);
        data[dataIndex + 3] = (byte)(v & 0xff);
    }
    
    public static void intToLittleEndian(int v, byte[] data, int dataIndex)
    {
        data[dataIndex] = (byte)(v & 0xff);
        data[dataIndex + 1] = (byte)(v >> 8 & 0xff);
        data[dataIndex + 2] = (byte)(v >> 16 & 0xff);
        data[dataIndex + 3] = (byte)(v >> 24);
    }
    
    
    public static long byteArrayLittleEndianToUInt(byte[] data, int dataIndex) 
    {        
        return (long)byteArrayLittleEndianToInt(data, dataIndex) & 0xffffffffL;
    }



    public static long byteArrayBigEndianToUInt(byte[] data, int dataIndex)
    {
        return (long)byteArrayBigEndianToInt(data, dataIndex);
    }
    
    public static int byteArrayLittleEndianToInt(byte[] data, int dataIndex) 
    {
        int i = 0;

        i += (data[dataIndex + 3] & 0xff) << 24;
        i += (data[dataIndex + 2] & 0xff) << 16;
        i += (data[dataIndex + 1] & 0xff) << 8;
        i += (data[dataIndex] & 0xff);

        return i;
    }
    
    
    public static int byteArrayBigEndianToInt(byte[] data, int dataIndex) 
    {
        int i = 0;

        i += (data[dataIndex] & 0xff) << 24;
        i += (data[dataIndex + 1] & 0xff) << 16;
        i += (data[dataIndex + 2] & 0xff) << 8;
        i += (data[dataIndex + 3] & 0xff);

        return i;
    }


    public static void longToBigEndian(long v, byte[] data, int dataIndex)
    {
        data[dataIndex] = (byte)(v >> 56);
        data[dataIndex + 1] = (byte)(v >> 48 & 0xff);
        data[dataIndex + 2] = (byte)(v >> 40 & 0xff);
        data[dataIndex + 3] = (byte)(v >> 32 & 0xff);
        data[dataIndex + 4] = (byte)(v >> 24 & 0xff);
        data[dataIndex + 5] = (byte)(v >> 16 & 0xff);
        data[dataIndex + 6] = (byte)(v >> 8 & 0xff);
        data[dataIndex + 7] = (byte)(v & 0xff);
    }


    public static void longToLittleEndian(long v, byte[] data, int dataIndex)
    {
        data[dataIndex] = (byte)(v & 0xff);
        data[dataIndex + 1] = (byte)(v >> 8 & 0xff);
        data[dataIndex + 2] = (byte)(v >> 16 & 0xff);
        data[dataIndex + 3] = (byte)(v >> 24 & 0xff);
        data[dataIndex + 4] = (byte)(v >> 32 & 0xff);
        data[dataIndex + 5] = (byte)(v >> 40 & 0xff);
        data[dataIndex + 6] = (byte)(v >> 48 & 0xff);
        data[dataIndex + 7] = (byte)(v >> 56);
    }


    public static void doubleToLittleEndian(double v, byte[] data, int dataIndex)
    {
        longToLittleEndian(Double.doubleToLongBits(v), data, dataIndex);
    }


    public static void doubleToBigEndian(double v, byte[] data, int dataIndex)
    {
        longToBigEndian(Double.doubleToLongBits(v), data, dataIndex);
    }


    public static long byteArrayLittleEndianToLong(byte[] data, int dataIndex)
    {
        BigInteger i= new BigInteger(String.valueOf(data[dataIndex + 7] & 0xff));
        return i.shiftLeft(56).add(new BigInteger(String.valueOf(data[dataIndex + 6] & 0xff)).shiftLeft(48))
                        .add(new BigInteger(String.valueOf(data[dataIndex + 5] & 0xff)).shiftLeft(40))
                        .add(new BigInteger(String.valueOf(data[dataIndex + 4] & 0xff)).shiftLeft(32))
                        .add(new BigInteger(String.valueOf(data[dataIndex + 3] & 0xff)).shiftLeft(24))
                        .add(new BigInteger(String.valueOf(data[dataIndex + 2] & 0xff)).shiftLeft(16))
                        .add(new BigInteger(String.valueOf(data[dataIndex + 1] & 0xff)).shiftLeft(8))
                        .add(new BigInteger(String.valueOf(data[dataIndex] & 0xff)))
                        .longValue();

        
    }


    public static long byteArrayBigEndianToLong(byte[] data, int dataIndex){
        
                
        BigInteger i = new BigInteger(String.valueOf(data[dataIndex] & 0xff));
        return i.shiftLeft(56).add(new BigInteger(String.valueOf(data[dataIndex + 1] & 0xff)).shiftLeft(48))
                            .add(new BigInteger(String.valueOf(data[dataIndex + 2] & 0xff)).shiftLeft(40))
                            .add(new BigInteger(String.valueOf(data[dataIndex + 3] & 0xff)).shiftLeft(32))
                            .add(new BigInteger(String.valueOf(data[dataIndex + 4] & 0xff)).shiftLeft(24))
                            .add(new BigInteger(String.valueOf(data[dataIndex + 5] & 0xff)).shiftLeft(16))
                            .add(new BigInteger(String.valueOf(data[dataIndex + 6] & 0xff)).shiftLeft(8))
                            .add(new BigInteger(String.valueOf(data[dataIndex + 7] & 0xff)))
                            .longValue();
        
        

    }


    public static double byteArrayLittleEndianToDouble(byte[] data, int dataIndex){

        return Double.longBitsToDouble(byteArrayLittleEndianToLong(data, dataIndex));

    }


    public static double byteArrayBigEndianToDouble(byte[] data, int dataIndex){

        return Double.longBitsToDouble(byteArrayBigEndianToLong(data, dataIndex));

    }

    
}
