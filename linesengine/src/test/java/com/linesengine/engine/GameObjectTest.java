package com.linesengine.engine;

import org.junit.Test;
import static org.junit.Assert.*;

public class GameObjectTest
{
    @Test
    public void constructorTest()
    {
        GameObject go1 = new GameObject("test");
        assertEquals(go1.name, "test");
    }
}
