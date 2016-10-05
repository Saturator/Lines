package com.linesengine.engine;

import com.linesengine.game.CirclePrimitive;
import org.junit.Test;
import static org.junit.Assert.*;
import com.linesengine.math.*;

public class CirclePrimitiveTest
{
    @Test
    public void constructorTest1()
    {
        GameObject test = new CirclePrimitive("shaggy");
        assertEquals(test.name, "shaggy");
    }
    
    @Test
    public void constructorTest2()
    {
        PhysicsBody body = new CircleBody(new Vector2(500f, 0f));
        GameObject test = new CirclePrimitive("hello", body);
        assertEquals(test.name, "hello");
        assertEquals(test.physicsBody.getPosition().x, 500f, 0.01f);
    }
}
