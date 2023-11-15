package com.hm.geek.beauty.design.ch41_ch73.structural.decorator;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

abstract class Shape {
    abstract void draw();
}

class Rectangle extends Shape {
    @Override
    void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }
}

abstract class ShapeDecorator extends Shape {
    protected Shape decoratedShape;

    public ShapeDecorator(Shape decoratedShape){
        this.decoratedShape = decoratedShape;
    }

    @Override
    public void draw(){
        decoratedShape.draw();
    }
}

class RedShapeDecorator extends ShapeDecorator {

    public RedShapeDecorator(Shape decoratedShape) {
        super(decoratedShape);  
    }

    @Override
    public void draw() {
        decoratedShape.draw();         
        setRedBorder(decoratedShape);
    }

    private void setRedBorder(Shape decoratedShape){
        System.out.println("Border Color: Red");
    }
}

class GreenShapeDecorator extends ShapeDecorator {

    public GreenShapeDecorator(Shape decoratedShape) {
        super(decoratedShape);  
    }

    @Override
    public void draw() {
        decoratedShape.draw();        
        setGreenBorder(decoratedShape);
    }

    private void setGreenBorder(Shape decoratedShape){
        System.out.println("Border Color: Green");
    }
}

public class DecoratorPatternDemo {
    public static void main(String[] args) {
        Shape rectangle = new Rectangle();
        
        Shape redRectangle = new RedShapeDecorator(new Rectangle());
        Shape greenRectangle = new GreenShapeDecorator(new RedShapeDecorator(new Rectangle()));
        
        System.out.println("Shape with normal border");
        rectangle.draw();
        
        System.out.println("\nShape of red border");
        redRectangle.draw();
        
        System.out.println("\nShape of green border");
        greenRectangle.draw();
    }
}
