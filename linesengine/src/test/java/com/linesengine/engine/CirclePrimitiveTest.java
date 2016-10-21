package com.linesengine.engine;

import com.linesengine.game.Circle;
import org.junit.Test;
import static org.junit.Assert.*;
import com.linesengine.math.*;

public class CirclePrimitiveTest
{
    @Test
    public void constructorTest1()
    {
        GameObject test = new Circle(50f, new Vector2());
        assertEquals(test.name, "circle");
    }
    
    @Test
    public void constructorTest2()
    {
        GameObject test = new Circle(50f, new Vector2(500f, 0f));
        assertEquals(test.name, "circle");
        assertEquals(test.physicsBody.getPosition().x, 500f, 0.01f);
    }
}
