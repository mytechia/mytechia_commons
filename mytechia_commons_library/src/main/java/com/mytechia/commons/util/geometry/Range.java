/*
 *   Copyright 2016 Mytech Ingenieria Aplicada <http://www.mytechia.com>
 *   Copyright 2016 Julio Gomez <julio.gomez@mytechia.com>
 * 
 *  This file is part of com.visualnw.luminare360:luminare360-render.
 */
package com.mytechia.commons.util.geometry;

import java.util.Collections;
import java.util.List;

/**
 *
 * @author Julio Alberto Gomez Fernandez
 */
public class Range implements Comparable<Range>{

    private int minimum;

    private int maximum;
    
    public Range(){
        this.minimum=0;
        this.maximum=0;
    }

    public Range(int minimum, int maximum) {
        this.minimum = minimum;
        this.maximum = maximum;
    }

    public int getMinimum() {
        return minimum;
    }

    public int getMaximum() {
        return maximum;
    }

    public void setMinimum(int minimum) {
        this.minimum = minimum;
    }

    public void setMaximum(int maximum) {
        this.maximum = maximum;
    }
    
    
    public void copy(Range rangeOther){
        
        if(rangeOther==null){
            throw new IllegalArgumentException("The parameter rangeOther is required");
        }
        
        this.minimum= rangeOther.minimum;
        
        this.maximum= rangeOther.maximum;
        
    }

    public boolean isOverlappedBy(final Range otherRange) {

        return otherRange.contains(this.minimum) || otherRange.contains(maximum) || this.contains(otherRange.maximum);

    }

    public boolean contains(final int element) {

        return (element >= this.minimum) && (element <= this.maximum);

    }
    
    public boolean contains(Range otherRange){
        
        if(otherRange==null){
            return false;
        }
        
        return this.contains(otherRange.minimum) && this.contains(otherRange.maximum);
        
    }

    public Range intersectionWith(Range otherRange) {

        Range intersectionRange = new Range(0, 0);

        intersectionWith(otherRange, intersectionRange);

        return intersectionRange;

    }

    public void intersectionWith(Range otherRange, Range result) {

        if (!this.isOverlappedBy(otherRange)) {
            throw new IllegalArgumentException(String.format(
                    "Cannot calculate intersection with non-overlapping range %s", otherRange));
        }

        int intersectionMinimum = Math.max(this.minimum, otherRange.minimum);

        int intersectionMaximum = Math.min(this.maximum, otherRange.maximum);

        result.setMinimum(intersectionMinimum);

        result.setMaximum(intersectionMaximum);

    }

    public Range unionWith(Range otherRange) {

        Range unionRange = new Range(0, 0);

        this.unionWith(otherRange, unionRange);

        return unionRange;
    }

    public void unionWith(Range otherRange, Range result) {

        int unionMinimum = Math.min(this.minimum, otherRange.minimum);

        int unionMaximum = Math.max(this.maximum, otherRange.maximum);

        result.setMinimum(unionMinimum);

        result.setMaximum(unionMaximum);

    }

    private boolean isStartedBy(int element) {
        return this.minimum == element;
    }

    public boolean isEndedBy(int element) {

        return this.maximum == element;
    }

    public boolean isAdjacentWith(Range otherRange) {
        return this.isEndedBy(otherRange.getMinimum() - 1) || this.isStartedBy(otherRange.getMaximum() + 1);
    }
    
    
    public void difference(Range otherRange, List<Range> difference){
        
        difference.clear();
        
        if (!this.isOverlappedBy(otherRange)) {
            difference.add(new Range(this.minimum, this.maximum));
            return;
        }

        Range intersection = this.intersectionWith(otherRange);

        if (this.minimum < intersection.minimum) {
            Range firstDifference = new Range(this.minimum, intersection.minimum - 1);
            difference.add(firstDifference);
        }

        if (intersection.maximum < intersection.maximum) {
            Range secondDifference = new Range(intersection.maximum + 1, intersection.maximum);
            if (!difference.contains(secondDifference)) {
                difference.add(secondDifference);
            }
        }
        
        Collections.sort(difference);
        
    }

    public int length(){
        return Math.abs(maximum)-Math.abs(minimum)+1;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Range other = (Range) obj;
        if (this.minimum != other.minimum) {
            return false;
        }
        if (this.maximum != other.maximum) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + this.minimum;
        hash = 19 * hash + this.maximum;
        return hash;
    }

    @Override
    public String toString() {

        final StringBuilder buf = new StringBuilder(32);
        buf.append('[');
        buf.append(minimum);
        buf.append("..");
        buf.append(maximum);
        buf.append(']');
        return buf.toString();

    }

    @Override
    public int compareTo(Range otherRange) {
        
        Integer thisMinimum= this.getMinimum();
        
        return thisMinimum.compareTo(otherRange.getMinimum());
        
        
    }

}
