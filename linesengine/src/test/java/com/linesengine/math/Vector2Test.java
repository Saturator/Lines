package com.linesengine.math;

import org.junit.Test;
import static org.junit.Assert.*;

public class Vector2Test 
{
    @Test
    public void additionTest1()
    {
        Vector2 result = new Vector2(10f, 10f);
        Vector2 v2 = new Vector2(1f, 1f);
        result.add(v2);
        Vector2 expectedResult = new Vector2(11f, 11f);
        assertEquals(result.x, expectedResult.x, 0.01f);
        assertEquals(result.y, expectedResult.y, 0.01f);
    }
    
    @Test
    public void additionTest2()
    {
        Vector2 result = new Vector2(5.5f, 5.5f);
        Vector2 v2 = new Vector2(4.5f, 4.5f);
        result.add(v2);
        Vector2 expectedResult = new Vector2(10f, 10f);
        assertEquals(result.x, expectedResult.x, 0.01f);
        assertEquals(result.y, expectedResult.y, 0.01f);
    }
    
    @Test
    public void additionTest3()
    {
        Vector2 result = new Vector2(0.111f, 0.111f);
        Vector2 v2 = new Vector2(0.333f, 0.333f);
        result.add(v2);
        Vector2 expectedResult = new Vector2(0.444f, 0.444f);
        assertEquals(result.x, expectedResult.x, 0.001f);
        assertEquals(result.y, expectedResult.y, 0.001f);
    }
    
    @Test
    public void multiplicationTest1()
    {
        Vector2 result = new Vector2(1f, 2f);
        result.multiply(-1f);
        Vector2 expectedResult = new Vector2(-1f, -2f);
        assertEquals(result.x, expectedResult.x, 0.001f);
        assertEquals(result.y, expectedResult.y, 0.001f);
    }
    
    @Test
    public void normalizeTest1()
    {
        Vector2 v = new Vector2(12842f, 0f);
        Vector2 result = v.normalize();
        Vector2 expectedResult = new Vector2(1f, 0f);
        assertEquals(result.x, expectedResult.x, 0.01f);
    }
    
    @Test
    public void normalizeTest2()
    {
        Vector2 v = new Vector2(0f, 534785f);
        Vector2 result = v.normalize();
        Vector2 expectedResult = new Vector2(0f, 1f);
        assertEquals(result.y, expectedResult.y, 0.01f);
    }
    
    @Test
    public void rotationTest1()
    {
        Vector2 v = new Vector2(2f, 2f);
        v.rotate(90);
        Vector2 expectedResult = new Vector2(-2f, 2f);
        assertEquals(v.x, expectedResult.x, 0.01f);
        assertEquals(v.y, expectedResult.y, 0.01f);
    }
    
    @Test
    public void rotationTest2()
    {
        Vector2 v = new Vector2(1f, 1f);
        v.rotate(45);
        Vector2 expectedResult = new Vector2(0f, 1.41f);
        assertEquals(v.x, expectedResult.x, 0.01f);
        assertEquals(v.y, expectedResult.y, 0.01f);
    }
    
    @Test
    public void rotationTest3()
    {
        //lets do it with a fake 2x2 rectangle
        Vector2[] rect = new Vector2[4];
        rect[0] = new Vector2(1f, 1f);
        rect[1] = new Vector2(-1f, 1f);
        rect[2] = new Vector2(-1f, -1f);
        rect[3] = new Vector2(1f, -1f);
        for(Vector2 p : rect)
        {
            p.rotate(45);
        }
        assertEquals(rect[0].x, 0f, 0.01f);
        assertEquals(rect[0].y, 1.41, 0.01f);
        
        assertEquals(rect[1].x, -1.41, 0.01f);
        assertEquals(rect[1].y, 0f, 0.01f);
        
        assertEquals(rect[2].x, 0f, 0.01f);
        assertEquals(rect[2].y, -1.41, 0.01f);
        
        assertEquals(rect[3].x, 1.41, 0.01f);
        assertEquals(rect[3].y, 0f, 0.01f);
    }
}
