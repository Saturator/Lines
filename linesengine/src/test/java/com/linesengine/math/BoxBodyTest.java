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
        assertEquals(b1.tl.x, -3f, 0.01f);
        assertEquals(b1.tl.y, 1f, 0.01f);
        assertEquals(b1.tr.x, -1f, 0.01f);
        assertEquals(b1.tr.y, 1f, 0.01f);
        assertEquals(b1.br.x, -1f, 0.01f);
        assertEquals(b1.br.y, -1f, 0.01f);
        assertEquals(b1.bl.x, -3f, 0.01f);
        assertEquals(b1.bl.y, -1f, 0.01f);
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
        assertEquals(b1.tl.x, 0f, 0.01f);
        assertEquals(b1.tl.y, 2f, 0.01f);
        assertEquals(b1.tr.x, 2f, 0.01f);
        assertEquals(b1.tr.y, 2f, 0.01f);
        assertEquals(b1.br.x, 2f, 0.01f);
        assertEquals(b1.br.y, 0f, 0.01f);
        assertEquals(b1.bl.x, 0f, 0.01f);
        assertEquals(b1.bl.y, 0f, 0.01f);
        assertEquals(b1.position.x, b1.middle().x, 0.01f);
        assertEquals(b1.position.y, b1.middle().y, 0.01f);
    }
}
