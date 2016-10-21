package com.linesengine.engine;

import com.linesengine.game.Circle;
import org.junit.Test;
import static org.junit.Assert.*;
import com.linesengine.math.*;

public class GameObjectTest
{
    @Test
    public void constructorTest1()
    {
        GameObject go1 = new Circle(50f, new Vector2());
        assertEquals(go1.name, "circle");
    }
    
    @Test
    public void constructorTest2()
    {
        GameObject go1 = new Circle(50f, new Vector2(1f, 2f));
        assertEquals(go1.name, "circle");
        assertEquals(go1.physicsBody.getPosition().x, 1f, 0.01f);
        assertEquals(go1.physicsBody.getPosition().y, 2f, 0.01f);
    }
    
    @Test
    public void addPhysicsBodyTest()
    {
        GameObject go1 = new Circle(50f, new Vector2());
        go1.physicsBody.setVelocity(new Vector2(2f, 0f));
        assertEquals(go1.physicsBody.getVelocity().x, 2f, 0.01f);
    }
}
