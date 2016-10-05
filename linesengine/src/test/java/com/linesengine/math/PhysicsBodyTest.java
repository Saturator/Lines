package com.linesengine.math;

import org.junit.Test;
import static org.junit.Assert.*;

public class PhysicsBodyTest
{
    @Test
    public void decreaseSpeedTest1()
    {
        PhysicsBody body = new CircleBody();
        body.addToVelocity(new Vector2(1f, 0f));
        assertEquals(body.speed(), 1f, 0.01f);
        body.decreaseSpeed();
        assertEquals(body.speed(), 0.98f, 0.01f);
    }
    
    @Test
    public void decreaseSpeedTest2()
    {
        PhysicsBody body = new BoxBody();
        body.addToVelocity(new Vector2(1f, 0f));
        assertEquals(body.speed(), 1f, 0.01f);
        body.decreaseSpeed();
        assertEquals(body.speed(), 0.98f, 0.01f);
    }
}
