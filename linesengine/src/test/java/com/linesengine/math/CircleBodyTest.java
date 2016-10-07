package com.linesengine.math;

import org.junit.Test;
import static org.junit.Assert.*;

public class CircleBodyTest 
{
    @Test
    public void collisionTest1()
    {
        //two 1f sized circles along the x axis
        CircleBody c1 = new CircleBody(new Vector2(0.5f, 0f));
        CircleBody c2 = new CircleBody(new Vector2(1f, 0f));
        boolean result = c1.isCollidingWithCircle(c2);
        assertEquals(result, true);
    }
    
    @Test
    public void collisionTest2()
    {
        //two 10f sized circles along the x axis
        CircleBody c1 = new CircleBody(10f, new Vector2(10f, 10f));
        CircleBody c2 = new CircleBody(10f, new Vector2(-5f, 0f));
        boolean result = c1.isCollidingWithCircle(c2);
        assertEquals(result, true);
    }
    
    @Test
    public void collisionTest3()
    {
        //a small circle inside a big circle
        CircleBody c1 = new CircleBody(1f, new Vector2(0f, 10f));
        CircleBody c2 = new CircleBody(100f, new Vector2(0f, 0f));
        boolean result = c1.isCollidingWithCircle(c2);
        assertEquals(result, true);
    }
    
    @Test
    public void collisionTest4()
    {
        //a big circle not colliding with another
        CircleBody c1 = new CircleBody(new Vector2(0f, 1000f));
        CircleBody c2 = new CircleBody(new Vector2(0f, 0f));
        boolean result = c1.isCollidingWithCircle(c2);
        assertEquals(false, result);
    }
    
    @Test
    public void resolveCircleCollisionTest1()
    {
        CircleBody c1 = new CircleBody(new Vector2(1f, 1f));
        CircleBody c2 = new CircleBody();
        c1.velocity = new Vector2(-10f, 0f);
        c1.resolveCircleCollision(c2);
        Vector2 expectedResult = new Vector2(-6.46f, 3.53f);
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
        Vector2 expectedResult = new Vector2(3.23f, -1.76f);
        System.out.println(c1.getVelocity());
        assertEquals(c1.getVelocity().x, expectedResult.x, 0.01f);
        assertEquals(c1.getVelocity().y, expectedResult.y, 0.01f);
    }
}
