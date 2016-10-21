package com.linesengine.math;

import com.linesengine.physics.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class BoxBodyTest
{
    @Test
    public void moveTest1()
    {
        BoxBody b1 = new BoxBody(2f);
        b1.move(new Vector2(-2f, 0f));
        //System.out.println(b1);
        assertEquals(b1.position.x, b1.middle().x, 0.01f);
        assertEquals(b1.position.y, b1.middle().y, 0.01f);
    }
    
    @Test
    public void moveTest2()
    {
        BoxBody b1 = new BoxBody(2f);
        System.out.println(b1);
        b1.move(new Vector2(1f, 1f));
        System.out.println(b1);
        assertEquals(b1.position.x, b1.middle().x, 0.01f);
        assertEquals(b1.position.y, b1.middle().y, 0.01f);
    }
    
    @Test
    public void middleTest1()
    {
        BoxBody b1 = new BoxBody(2f);
        System.out.println(b1);
        assertEquals(b1.position.x, b1.middle().x, 0.01f);
        assertEquals(b1.position.y, b1.middle().y, 0.01f);
    }
    
    @Test
    public void distTest1()
    {
        BoxBody b1 = new BoxBody(1f);
        Vector2 p = new Vector2(2, 2);
        assertEquals(b1.distance(p), 2.8f, 0.1f);
    }
    
    @Test
    public void distTest2()
    {
        BoxBody b1 = new BoxBody(1f);
        Vector2 p = new Vector2(2, 0);
        assertEquals(b1.distance(p), 2f, 0.1f);
    }
    
    @Test
    public void rotationTest1()
    {
        BoxBody b1 = new BoxBody(1f);
        b1.rotate(45);
        BoxBody b2 = new BoxBody(1f);
        b2.rotate(45);
        assertEquals(b1.tl.x, b2.tl.x, 0.01f);
        assertEquals(b1.tl.y, b2.tl.y, 0.01f);
        assertEquals(b1.tr.x, b2.tr.x, 0.01f);
        assertEquals(b1.tr.y, b2.tr.y, 0.01f);
        assertEquals(b1.br.x, b2.br.x, 0.01f);
        assertEquals(b1.br.y, b2.br.y, 0.01f);
        assertEquals(b1.bl.x, b2.bl.x, 0.01f);
        assertEquals(b1.bl.y, b2.bl.y, 0.01f);
    }
    
    @Test
    public void rotationTest2()
    {
        BoxBody b1 = new BoxBody(1f);
        b1.rotate(45);
        Vector2 p1 = new Vector2(0f, 0.707f);
        Vector2 p2 = new Vector2(0f, -0.707f);
        assertEquals(b1.tr.x, p1.x, 0.01f);
        assertEquals(b1.tr.y, p1.y, 0.01f);
        assertEquals(b1.bl.x, p2.x, 0.01f);
        assertEquals(b1.bl.y, p2.y, 0.01f);
    }
    
    @Test
    public void collisionTest1()
    {
        BoxBody b1 = new BoxBody(1.1f);
        BoxBody b2 = new BoxBody(5f, new Vector2(50f, 0f));
        b1.setVelocity(new Vector2(1f, 0f));
        for(int i = 0; i < 47; i++) b1.move(b1.velocity);
        boolean isColliding = b1.isColliding(b2);
        assertEquals(true, isColliding);
    }
    
    @Test
    public void collisionTest2()
    {
        BoxBody b1 = new BoxBody(50f);
        BoxBody b2 = new BoxBody(50f, new Vector2(0f, 980f));
        b1.setVelocity(new Vector2(0f, 5f));
        for(int i = 0; i < 1000/5; i++) b1.move(b1.velocity);
        boolean isColliding = b1.isColliding(b2);
        assertEquals(true, isColliding);
    }
    
    @Test
    public void collisionTest3()
    {
        BoxBody b1 = new BoxBody(50f);
        BoxBody b2 = new BoxBody(50f, new Vector2(0f, 980f));
        b1.setVelocity(new Vector2(0f, 5f));
        for(int i = 0; i < 2000; i++) b1.move(b1.velocity);
        boolean isColliding = b1.isColliding(b2);
        assertEquals(false, isColliding);
    }
    
    @Test
    public void collisionTest4()
    {
        BoxBody b1 = new BoxBody(50f);
        CircleBody b2 = new CircleBody(50f, new Vector2(0f, 980f));
        b1.setVelocity(new Vector2(0f, 5f));
        for(int i = 0; i < 1000/5; i++) b1.move(b1.velocity);
        boolean isColliding = b1.isColliding(b2);
        assertEquals(true, isColliding);
    }
    
    @Test
    public void collisionTest5()
    {
        BoxBody b1 = new BoxBody(50f);
        CircleBody b2 = new CircleBody(50f, new Vector2(0f, 980f));
        b1.setVelocity(new Vector2(0f, 5f));
        for(int i = 0; i < 1000; i++) b1.move(b1.velocity);
        boolean isColliding = b1.isColliding(b2);
        assertEquals(false, isColliding);
    }
}
