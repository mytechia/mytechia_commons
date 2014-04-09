/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mytechia.commons.util.collections.bytequeue;

import com.mytechia.commons.util.collections.bytequeue.exception.FullByteQueueException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.TestCase;

/**
 *
 * @author alma
 */
public class ArrayByteQueueTest extends TestCase {
    
    public ArrayByteQueueTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of push method, of class ArrayByteQueue.
     */
    public void testPush_byte() throws Exception {
        System.out.println("push un byte");
        byte b = 5;
        ArrayByteQueue instance = new ArrayByteQueue(10);
        
        System.out.println("getAvailableSpace: " + instance.getAvailableSpace());
        System.out.println("getUsedSpace: " + instance.getUsedSpace());        
        
        instance.push(b);
        
        System.out.println("1 byte pushed");
        System.out.println("getAvailableSpace: " + instance.getAvailableSpace());
        System.out.println("getUsedSpace: " + instance.getUsedSpace());                        
    }

    /**
     * Test of push method, of class ArrayByteQueue.
     */
    public void testPush_byteArr() throws Exception {
        System.out.println("push array");
        byte[] bytes = new byte[]{1,2,3,4};
        
        ArrayByteQueue instance = new ArrayByteQueue(10);
      
        try {
            instance.push(bytes);
            instance.push(bytes);             
            instance.push(bytes);
        } catch (FullByteQueueException fullByteQueueException) {
            System.out.println("** Push error: full queue");
        }

    }

    /**
     * Test of push method, of class ArrayByteQueue.
     */
    public void testPush_3args() throws Exception {
        System.out.println("push subarray");
        byte[] bytes = new byte[]{1,2,3,4};
        int offset = 1;
        int count = 3;
        ArrayByteQueue instance = new ArrayByteQueue(10);
                
        try {
            instance.push(bytes, offset, count);
            instance.push(bytes, offset, count);
        } catch (FullByteQueueException fullByteQueueException) {
            System.out.println("** Push error: full queue");
        }
        
    }

    /**
     * Test of poll method, of class ArrayByteQueue.
     */
    public void testPoll_0args() throws Exception {
        System.out.println("poll");
        ArrayByteQueue instance = new ArrayByteQueue(10);
        byte[] bytes = new byte[]{1,2,3,4};
        instance.push(bytes);
                        
        byte expResult = bytes[0];
        byte result = instance.poll();
        assertEquals(expResult, result);
    }

    /**
     * Test of poll method, of class ArrayByteQueue.
     */
    public void testPoll_3args() {
        try {
            System.out.println("poll");
            ArrayByteQueue instance = new ArrayByteQueue(10);
            byte[] bytes = new byte[]{1,2,3,4};
            instance.push(bytes);            
            
            byte[] polledBytes = new byte[5];
            int offset = 0;
            int count = 5;
            int expResult = 4;
            int result = instance.poll(polledBytes, offset, count);
            assertEquals(expResult, result);
        } catch (FullByteQueueException ex) {
            Logger.getLogger(ArrayByteQueueTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void testRotatePush() {
        try {
            System.out.println("rotate push");
            ArrayByteQueue instance = new ArrayByteQueue(10);
            byte[] bytes = new byte[]{1,2,3,4,5,6,7,8};
            instance.push(bytes);            
            
            byte[] polledBytes = new byte[10];
            int offset = 0;
            int count = 4;
            int expResult = 4;
            int result = instance.poll(polledBytes, offset, count);
            
            instance.push(bytes, 1, 5);
            
            result = instance.poll(polledBytes, 0, 7);
            
            //instance.push(polledBytes, 0, 9);
            
            Arrays.fill(polledBytes, (byte) 0);
            result = instance.poll(polledBytes, 0, 9);
            
            assertEquals(result, 2);
                        
        } catch (FullByteQueueException ex) {
            Logger.getLogger(ArrayByteQueueTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    
}
