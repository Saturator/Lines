package com.linesengine.engine;

import org.junit.Test;
import static org.junit.Assert.*;
import com.linesengine.math.*;

public class GameObjectTest
{
    @Test
    public void constructorTest1()
    {
        GameObject go1 = new GameObject("test");
        assertEquals(go1.name, "test");
    }
    
    @Test
    public void constructorTest2()
    {
        PhysicsBody c = new CircleBody(new Vector2(1f, 2f));
        GameObject go1 = new GameObject("test", c);
        assertEquals(go1.name, "test");
        assertEquals(go1.physicsBody.getPosition().x, 1f, 0.01f);
        assertEquals(go1.physicsBody.getPosition().y, 2f, 0.01f);
    }
}
