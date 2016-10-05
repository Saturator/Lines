package com.linesengine.engine;

import com.linesengine.game.CirclePrimitive;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;
import com.linesengine.math.*;

public class GameSceneTest
{
    @Test
    public void constructorTest1()
    {
        GameScene s1 = new GameScene("testS");
        assertEquals(s1.name, "testS");
    }
    
    @Test
    public void objectAddTest1()
    {
        GameScene s1 = new GameScene("testS");
        GameObject go1 = new CirclePrimitive("gogo");
        s1.addGameObject(go1);
        GameObject foundObj = s1.findGameObject("gogo");
        assertEquals(foundObj.name, go1.name);
    }
    
    @Test
    public void testSpecificCollisionTest()
    {
        
    }
    
    @Test
    public void objectAddTest2()
    {
        GameScene s1 = new GameScene("testS");
        GameObject go1 = new CirclePrimitive("t1");
        GameObject go2 = new CirclePrimitive("t2");
        GameObject go3 = new CirclePrimitive("t3");
        GameObject go4 = new CirclePrimitive("t4");
        s1.addGameObject(go1);
        s1.addGameObject(go2);
        s1.addGameObject(go3);
        s1.addGameObject(go4);
        GameObject foundObj1 = s1.findGameObject("t1");
        GameObject foundObj2 = s1.findGameObject("t2");
        GameObject foundObj3 = s1.findGameObject("t3");
        GameObject foundObj4 = s1.findGameObject("t4");
        assertEquals(foundObj1.name, go1.name);
        assertEquals(foundObj2.name, go2.name);
        assertEquals(foundObj3.name, go3.name);
        assertEquals(foundObj4.name, go4.name);
    }
    
    @Test
    public void objectMoveTest1()
    {
        GameScene s1 = new GameScene("testS");
        PhysicsBody body = new CircleBody();
        GameObject go1 = new CirclePrimitive("gogo", body);
        go1.physicsBody.setVelocity(new Vector2(10f, 0f));
        s1.addGameObject(go1);
        s1.moveAllObjects();
        Vector2 expectedPosition = new Vector2(10f, 0f);
        assertEquals(expectedPosition.x, go1.physicsBody.getPosition().x, 0.1f);
        assertEquals(expectedPosition.y, go1.physicsBody.getPosition().y, 0.1f);
    }
    
    @Test
    public void objectMoveTest2()
    {
        GameScene s1 = new GameScene("testS");
        PhysicsBody body1 = new CircleBody();
        PhysicsBody body2 = new CircleBody();
        PhysicsBody body3 = new CircleBody();
        GameObject go1 = new CirclePrimitive("gogo1", body1);
        GameObject go2 = new CirclePrimitive("gogo2", body2);
        GameObject go3 = new CirclePrimitive("gogo3", body3);
        go1.physicsBody.setVelocity(new Vector2(100f, 0f));
        go2.physicsBody.setVelocity(new Vector2(-10f, 0f));
        go3.physicsBody.setVelocity(new Vector2(-10f, 10f));
        s1.addGameObject(go1);
        s1.addGameObject(go2);
        s1.addGameObject(go3);
        s1.moveAllObjects();
        Vector2 expectedPosition1 = new Vector2(100f, 0f);
        Vector2 expectedPosition2 = new Vector2(-10f, 0f);
        Vector2 expectedPosition3 = new Vector2(-10f, 10f);
        assertEquals(expectedPosition1.x, go1.physicsBody.getPosition().x, 0.1f);
        assertEquals(expectedPosition1.y, go1.physicsBody.getPosition().y, 0.1f);
        assertEquals(expectedPosition2.x, go2.physicsBody.getPosition().x, 0.1f);
        assertEquals(expectedPosition2.y, go2.physicsBody.getPosition().y, 0.1f);
        assertEquals(expectedPosition3.x, go3.physicsBody.getPosition().x, 0.1f);
        assertEquals(expectedPosition3.y, go3.physicsBody.getPosition().y, 0.1f);
    }
    
    @Test
    public void objectMoveTest3()
    {
        GameScene s1 = new GameScene("testS");
        PhysicsBody body = new CircleBody();
        GameObject go1 = new CirclePrimitive("gogo", body);
        go1.physicsBody.setVelocity(new Vector2(10f, 10f));
        s1.addGameObject(go1);
        //lets run it for 30 ticks
        for(int i = 0; i <= 30; i++)
        {
            s1.moveAllObjects();
        }
        Vector2 expectedPosition = new Vector2(232.7f, 232.7f);
        assertEquals(expectedPosition.x, go1.physicsBody.getPosition().x, 0.1f);
        assertEquals(expectedPosition.y, go1.physicsBody.getPosition().y, 0.1f);
    }
}
