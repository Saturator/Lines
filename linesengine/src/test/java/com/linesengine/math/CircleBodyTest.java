package com.linesengine.math;

import com.linesengine.physics.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class CircleBodyTest 
{
    @Test
    public void collisionTest1()
    {
        CircleBody c1 = new CircleBody(50f, new Vector2(-10f, 0f));
        CircleBody c2 = new CircleBody(50f, new Vector2(1000f, 0f));
        c1.setVelocity(new Vector2(1f, 0f));
        for(int i = 0; i < 1000; i++) c1.move(c1.velocity);
        boolean isColliding = c1.isColliding(c2);
        assertEquals(isColliding, true);
    }
    
    @Test
    public void collisionTest2()
    {
        CircleBody c1 = new CircleBody(25f, new Vector2(-5f, 0f));
        CircleBody c2 = new CircleBody(25f, new Vector2(0, 10000f));
        c1.setVelocity(new Vector2(0f, 5f));
        for(int i = 0; i < 10000/5; i++) c1.move(c1.velocity);
        boolean isColliding = c1.isColliding(c2);
        assertEquals(isColliding, true);
    }
    
    @Test
    public void collisionTest3()
    {
        CircleBody c1 = new CircleBody(new Vector2(0f, 1000f));
        CircleBody c2 = new CircleBody(new Vector2(0f, 0f));
        boolean result = c1.isCollidingWithCircle(c2);
        assertEquals(false, result);
    }
    
    @Test
    public void collisionTest4()
    {
        CircleBody c1 = new CircleBody(25f, new Vector2(-5f, 0f));
        BoxBody c2 = new BoxBody(25f, new Vector2(0, 10000f));
        c1.setVelocity(new Vector2(0f, 5f));
        for(int i = 0; i < 10000/5; i++) c1.move(c1.velocity);
        boolean isColliding = c1.isColliding(c2);
        assertEquals(isColliding, true);
    }
    
    @Test
    public void collisionTest5()
    {
        CircleBody c1 = new CircleBody(50f, new Vector2(-10f, 0f));
        BoxBody c2 = new BoxBody(50f, new Vector2(1000f, 0f));
        c1.setVelocity(new Vector2(1f, 0f));
        for(int i = 0; i < 1000; i++) c1.move(c1.velocity);
        boolean isColliding = c1.isColliding(c2);
        assertEquals(isColliding, true);
    }
    
    @Test
    public void resolveCircleCollisionTest1()
    {
        CircleBody c1 = new CircleBody(new Vector2(1f, 1f));
        CircleBody c2 = new CircleBody();
        c1.velocity = new Vector2(-10f, 0f);
        c1.resolveCircleCollision(c2);
        Vector2 expectedResult = new Vector2(-1.414f, -1.414f);
        assertEquals(c1.getVelocity().x, expectedResult.x, 0.01f);
        assertEquals(c1.getVelocity().y, expectedResult.y, 0.01f);
    }
    
    @Test
    public void resolveCircleCollisionTest2()
    {
        CircleBody c1 = new CircleBody(new Vector2(-1f, -1f));
        CircleBody c2 = new CircleBody();
        c1.velocity = new Vector2(5f, 0f);
        c1.resolveCircleCollision(c2);
        Vector2 expectedResult = new Vector2(-0.707f, -0.707f);
        System.out.println(c1.getVelocity());
        assertEquals(c1.getVelocity().x, expectedResult.x, 0.01f);
        assertEquals(c1.getVelocity().y, expectedResult.y, 0.01f);
    }
}
