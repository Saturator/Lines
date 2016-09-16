package com.bloxy.math;

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
        boolean result = c1.isCollidingWithAnotherCircle(c2);
        assertEquals(result, true);
    }
    
    @Test
    public void collisionTest2()
    {
        //two 10f sized circles along the x axis
        CircleBody c1 = new CircleBody(10f, new Vector2(10f, 10f));
        CircleBody c2 = new CircleBody(10f, new Vector2(-5f, 0f));
        boolean result = c1.isCollidingWithAnotherCircle(c2);
        assertEquals(result, true);
    }
    
    @Test
    public void collisionTest3()
    {
        //a small circle inside a big circle
        CircleBody c1 = new CircleBody(1f, new Vector2(0f, 10f));
        CircleBody c2 = new CircleBody(100f, new Vector2(0f, 0f));
        boolean result = c1.isCollidingWithAnotherCircle(c2);
        assertEquals(result, true);
    }
    
    @Test
    public void collisionTest4()
    {
        //a big circle not colliding with another
        CircleBody c1 = new CircleBody(new Vector2(0f, 1000f));
        CircleBody c2 = new CircleBody(new Vector2(0f, 0f));
        boolean result = c1.isCollidingWithAnotherCircle(c2);
        assertEquals(result, false);
    }
}
