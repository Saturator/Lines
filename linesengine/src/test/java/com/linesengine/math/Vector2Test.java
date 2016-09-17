package com.linesengine.math;

import org.junit.Test;
import static org.junit.Assert.*;

public class Vector2Test 
{
    @Test
    public void additionTest1()
    {
        Vector2 v1 = new Vector2(10f, 10f);
        Vector2 v2 = new Vector2(1f, 1f);
        Vector2 result = v1.add(v2);
        Vector2 expectedResult = new Vector2(11f, 11f);
        assertEquals(result.x, expectedResult.x, 0.01f);
        assertEquals(result.y, expectedResult.y, 0.01f);
    }
    
    @Test
    public void additionTest2()
    {
        Vector2 v1 = new Vector2(5.5f, 5.5f);
        Vector2 v2 = new Vector2(4.5f, 4.5f);
        Vector2 result = v1.add(v2);
        Vector2 expectedResult = new Vector2(10f, 10f);
        assertEquals(result.x, expectedResult.x, 0.01f);
        assertEquals(result.y, expectedResult.y, 0.01f);
    }
    
    @Test
    public void additionTest3()
    {
        Vector2 v1 = new Vector2(0.111f, 0.111f);
        Vector2 v2 = new Vector2(0.333f, 0.333f);
        Vector2 result = v1.add(v2);
        Vector2 expectedResult = new Vector2(0.444f, 0.444f);
        assertEquals(result.x, expectedResult.x, 0.001f);
        assertEquals(result.y, expectedResult.y, 0.001f);
    }
    
    @Test
    public void multiplicationTest1()
    {
        Vector2 v1 = new Vector2(1f, 2f);
        Vector2 result = v1.multiply(-1f);
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
        System.out.println(result);
        Vector2 expectedResult = new Vector2(0f, 1f);
        assertEquals(result.y, expectedResult.y, 0.01f);
    }
}
