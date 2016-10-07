package com.linesengine.math;

import org.junit.Test;
import static org.junit.Assert.*;

public class BoxBodyTest
{
    @Test
    public void moveTest1()
    {
        BoxBody b1 = new BoxBody(2f);
        b1.move(new Vector2(-2f, 0f));
        //System.out.println(b1);
        assertEquals(b1.position.x, b1.middle().x, 0.01f);
        assertEquals(b1.position.y, b1.middle().y, 0.01f);
    }
    
    @Test
    public void moveTest2()
    {
        BoxBody b1 = new BoxBody(2f);
        System.out.println(b1);
        b1.move(new Vector2(1f, 1f));
        System.out.println(b1);
        assertEquals(b1.position.x, b1.middle().x, 0.01f);
        assertEquals(b1.position.y, b1.middle().y, 0.01f);
    }
}
