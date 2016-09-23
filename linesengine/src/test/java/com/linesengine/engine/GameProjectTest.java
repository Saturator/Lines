package com.linesengine.engine;

import org.junit.Test;
import static org.junit.Assert.*;

public class GameProjectTest
{
    @Test
    public void constructorTest()
    {
        GameProject p = new GameProject("test");
        assertEquals(p.name, "test");
    }
    
    @Test
    public void findSceneTest()
    {
        GameProject p = new GameProject("test");
        GameScene s1 = new GameScene("s1");
        GameScene s2 = new GameScene("s2");
        p.addScene(s1);
        p.addScene(s2);
        assertEquals(p.findScene("s2").name, s2.name);
    }
}
