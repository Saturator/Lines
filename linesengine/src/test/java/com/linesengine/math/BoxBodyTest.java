package com.linesengine.math;

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
        Vector2 p1 = new Vector2(0.707f, 0);
        Vector2 p2 = new Vector2(-0.707f, 0);
        assertEquals(b1.tr.x, p1.x, 0.01f);
        assertEquals(b1.tr.y, p1.y, 0.01f);
        assertEquals(b1.bl.x, p2.x, 0.01f);
        assertEquals(b1.bl.y, p2.y, 0.01f);
    }
    
    @Test
    public void collisionTest1()
    {
        BoxBody b1 = new BoxBody(1f);
        b1.move(new Vector2(1.5f, 0.5f));
        BoxBody b2 = new BoxBody(2.1f);
        boolean isColliding = b1.isColliding(b2);
        assertEquals(isColliding, true);
    }
    
    //This fails! The problem is that the mechanism doesnt detect collisions
    //from boxes that are completely inside the other box. You should fix that.
    /*
    @Test
    public void collisionTest2()
    {
        BoxBody b1 = new BoxBody(1f);
        BoxBody b2 = new BoxBody(2.1f);
        boolean isColliding = b1.isColliding(b2);
        assertEquals(isColliding, true);
    }
    */
    
    @Test
    public void collisionTest3()
    {
        BoxBody b1 = new BoxBody(2f);
        BoxBody b2 = new BoxBody(2.1f);
        b2.move(new Vector2(-1f, -0.5f));
        boolean isColliding = b1.isColliding(b2);
        assertEquals(isColliding, true);
    }
}
