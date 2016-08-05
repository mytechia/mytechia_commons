/*
 *   Copyright 2016 Mytech Ingenieria Aplicada <http://www.mytechia.com>
 *   Copyright 2016 Julio Gomez <julio.gomez@mytechia.com>
 * 
 *  This file is part of com.visualnw.luminare360:luminare360-render.
 */
package com.mytechia.commons.util.geometry;




/**
 * 
 * @author Julio Alberto Gomez Fernandez
 */
public class Rectangle {
    
    public static final Rectangle EMPTY = new Rectangle(0, 0, 0, 0);
    
    private final  Range horizontalSide;
    
    private final  Range verticalSide;

    private  int width;
    
    private  int height;
    
    
    

    public Rectangle(int x, int y, int width, int height) {
        super();

        int x2 = x + width;
        this.horizontalSide = new Range(x, x2);

        int y2 = y + height;
        this.verticalSide = new Range(y, y2);

        this.width = width;
        this.height = height;
    }



    public int getX() {
        return this.horizontalSide.getMinimum();
    }

    public void setX(int x) {
        this.horizontalSide.setMinimum(x);
    }

    public int getY() {
        return this.verticalSide.getMinimum();
    }

    public void setY(int y) {
        this.verticalSide.setMinimum(y);
    }

    public int getWidth() {
        return width;
    }
    

    public void setWidth(int width) {
        this.width = width;
        int x2 = getX() + width;
        this.horizontalSide.setMaximum(x2);
    }
    

    public int getHeight() {
        return height;
    }
    

    public void setHeight(int height) {
        this.height = height;
        int y2 = getY() + height;
        this.verticalSide.setMaximum(y2);
    }
    
    public void defineArea(int x, int y, int width, int height) {

        this.setX(x);
        
        this.setY(y);
        
        this.setWidth(width);
        
        this.setHeight(height);

    }
    
    
    public void moveOrigin(int newOrigenX, int newOrigenY) {

        int x1Trasladado = this.getX() - newOrigenX;

        int y1Trasladado = this.getY() - newOrigenY;

        this.setX(x1Trasladado);
        
        //Reajustamos el valor maximum de horizontalSide
        this.setWidth(this.width);

        this.setY(y1Trasladado);
        
        //Reajustamos el valor maximum de verticalSide
        this.setHeight(this.height);

    }

    public Rectangle moveOriginWithResult(int newOrigenX, int newOrigenY) {

        Rectangle cloneRectangle = this.clone();

        return this.moveOriginWithResult(newOrigenX, newOrigenY, cloneRectangle);

    }
    
    public Rectangle moveOriginWithResult(int newOrigenX, int newOrigenY, Rectangle resultRectangle) {

        resultRectangle.copy(this);

        resultRectangle.moveOrigin(newOrigenX, newOrigenY);

        return resultRectangle;

    }
    
    
    public boolean isOverlappBy(Rectangle other) {
        if (other == null) {
            return false;
        }

        if (!this.horizontalSide.isOverlappedBy(other.horizontalSide)) {
            return false;
        }

        if (!this.verticalSide.isOverlappedBy(other.verticalSide)) {
            return false;
        }

        return true;
    }
    
    
    public Rectangle intersectionWith(Rectangle other) {

    
        Rectangle result= new Rectangle(0, 0, 0, 0);
        
        this.intersectionWithResult(other, result);
        
        return result;
        
    }
    
    
    public void intersectionWithResult(Rectangle other, Rectangle result) {
        
        if (other == null) {
            result.copy(EMPTY);
            return;
        }
        
        if(!this.horizontalSide.isOverlappedBy(other.horizontalSide)){
            result.copy(EMPTY);
            return;
        }
        
        if (!this.verticalSide.isOverlappedBy(other.verticalSide)) {
            result.copy(EMPTY);
            return;
        }
        
        this.horizontalSide.intersectionWith(other.horizontalSide, result.horizontalSide);

        this.verticalSide.intersectionWith(other.verticalSide, result.verticalSide);        
        
        result.height= result.verticalSide.getMaximum()- result.verticalSide.getMinimum();
                
        result.width= result.horizontalSide.getMaximum()- result.horizontalSide.getMinimum();
        
    }
    
    
    public Rectangle unionWith(Rectangle other){
        
        Rectangle result= new Rectangle(0, 0, 0, 0);
        
        this.unionWith(other, result);
        
        return result;
        
    }
    
    
    public void unionWith(Rectangle other, Rectangle result) {

        if (other == null) {
            result.copy(EMPTY);
            return;
        }
        
        if(this.equals(EMPTY)){
            result.copy(other);
            return;
        }
        
        if(EMPTY.equals(other)){
            result.copy(this);
            return;
        }
        
        this.horizontalSide.unionWith(other.horizontalSide, result.horizontalSide);
        
        this.verticalSide.unionWith(other.horizontalSide, result.horizontalSide);
        
        if((result.getHeight()==0) || (result.getWidth()==0)){
            result.copy(EMPTY);
        }

    }
    
    
    public boolean isAdjacentWith(Rectangle rectangle2){

        return this.horizontalSide.isAdjacentWith(rectangle2.horizontalSide) 
                    || this.verticalSide.isAdjacentWith(rectangle2.verticalSide);
        
    }
    
    public boolean conatins(Rectangle otherRectangle){
        
        return (this.verticalSide.contains(otherRectangle.verticalSide))  
                && (this.horizontalSide.contains(otherRectangle.horizontalSide));
        
    }
    
    public Rectangle clone(){
        
        Rectangle cloneRectangle= new Rectangle(0, 0, 0, 0);
        
        cloneRectangle.copy(this);
        
        return cloneRectangle;
        
    }
    
    private void copy(Rectangle source){
        
        this.setX(source.getX());
        
        this.setY(source.getY());
        
        this.setWidth(source.getWidth());
        
        this.setHeight(source.getHeight());
        
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + (this.horizontalSide != null ? this.horizontalSide.hashCode() : 0);
        hash = 23 * hash + (this.verticalSide != null ? this.verticalSide.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Rectangle other = (Rectangle) obj;
        if (this.horizontalSide != other.horizontalSide && (this.horizontalSide == null || !this.horizontalSide.equals(other.horizontalSide))) {
            return false;
        }
        if (this.verticalSide != other.verticalSide && (this.verticalSide == null || !this.verticalSide.equals(other.verticalSide))) {
            return false;
        }
        return true;
    }
    
    
    
    
    @Override
    public String toString(){
        
        StringBuilder strBuilder= new StringBuilder("Rectangle[");
        strBuilder.append("x=").append(this.horizontalSide.getMinimum()).append(", ");
        strBuilder.append("y=").append(this.verticalSide.getMinimum()).append(", ");
        strBuilder.append("width=").append(this.width).append(", ");
        strBuilder.append("height=").append(this.height);
        strBuilder.append("]");
        
        return strBuilder.toString();
    }

    
}
