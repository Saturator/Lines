package com.linesengine.math;

import org.junit.Test;
import static org.junit.Assert.*;

public class LineTest
{
    @Test
    public void rotationTest1()
    {
        Vector2 p0 = new Vector2(1f, 1f);
        Vector2 p1 = new Vector2(3f, 3f);
        Line l1 = new Line(p0, p1);
        l1.rotate(90);
        System.out.println(l1);
        Line expected = new Line(new Vector2(3f, 1f), new Vector2(1f, 3f));
        assertEquals(l1.points[0].x, expected.points[0].x, 0.01f);
        assertEquals(l1.points[0].y, expected.points[0].y, 0.01f);
        assertEquals(l1.points[1].x, expected.points[1].x, 0.01f);
        assertEquals(l1.points[1].y, expected.points[1].y, 0.01f);
    }
    
    @Test
    public void rotationTest2()
    {
        Vector2 p0 = new Vector2(-100f, -100f);
        Vector2 p1 = new Vector2(-50f, -50f);
        Line l1 = new Line(p0, p1);
        l1.rotate(90);
        Line expected = new Line(new Vector2(-50f, -100f), new Vector2(-100f, -50f));
        assertEquals(l1.points[0].x, expected.points[0].x, 0.01f);
        assertEquals(l1.points[0].y, expected.points[0].y, 0.01f);
        assertEquals(l1.points[1].x, expected.points[1].x, 0.01f);
        assertEquals(l1.points[1].y, expected.points[1].y, 0.01f);
    }
    
    @Test
    public void midpointTest1()
    {
        Vector2 p0 = new Vector2(2f, 2f);
        Vector2 p1 = new Vector2(0, 0f);
        Line l1 = new Line(p0, p1);
        Vector2 result = l1.getMidpoint();
        Vector2 expected = new Vector2(1f, 1f);
        assertEquals(result.x, expected.x, 0.01f);
        assertEquals(result.y, expected.y, 0.01f);   
    }
    
    @Test
    public void midpointTest2()
    {
        Vector2 p0 = new Vector2(-2f, -2f);
        Vector2 p1 = new Vector2(-4, -4f);
        Line l1 = new Line(p0, p1);
        Vector2 result = l1.getMidpoint();
        Vector2 expected = new Vector2(-3f, -3f);
        assertEquals(result.x, expected.x, 0.01f);
        assertEquals(result.y, expected.y, 0.01f);   
    }
}
