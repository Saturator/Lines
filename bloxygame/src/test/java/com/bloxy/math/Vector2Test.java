package com.bloxy.math;

import org.junit.Test;
import static org.junit.Assert.*;

public class Vector2Test 
{
    
    public Vector2Test() 
    {
    }

    @Test
    public void testAdd1() 
    {
        Vector2 other = new Vector2(5f, 5f);
        Vector2 instance = new Vector2(10f, 5f);
        
        Vector2 expectedResult = new Vector2(15f, 10f);
        System.out.println(instance.add(other));
        System.out.println(expectedResult);
        Vector2 result = instance.add(other);
        System.out.println(expectedResult.x + " " + result.x);
        assertEquals(expectedResult.x, result.x, 0.01f);
        assertEquals(expectedResult.y, result.y, 0.01f);
        fail("Vector addition not working properly.");
    }
    
    /*
    @Test
    public void testAdd2() 
    {
        Vector2 other = new Vector2(10.42f, 2.22f);
        Vector2 instance = new Vector2(10f, 5f);
        Vector2 expectedResult = new Vector2(20.42f, 7.22f);
        Vector2 result = instance.add(other);
        assertEquals(expectedResult, result);
        fail("Vector addition not working properly.");
    }
    
    
    @Test
    public void testAdd3 () 
    {
        Vector2 other = new Vector2(5.5f, 100.5f);
        Vector2 instance = new Vector2(5.5f, 100.5f);
        Vector2 expectedResult = new Vector2(11f, 101f);
        Vector2 result = instance.add(other);
        assertEquals(expectedResult, result);
        fail("Vector addition not working properly.");
    }

    @Test
    public void testMultiply() {
        System.out.println("multiply");
        float multiplier = 0.0F;
        Vector2 instance = new Vector2();
        Vector2 expResult = null;
        Vector2 result = instance.multiply(multiplier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testLength() {
        System.out.println("length");
        Vector2 instance = new Vector2();
        float expResult = 0.0F;
        float result = instance.length();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testDotProduct() {
        System.out.println("dotProduct");
        Vector2 other = null;
        Vector2 instance = new Vector2();
        float expResult = 0.0F;
        float result = instance.dotProduct(other);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testNormalize() {
        System.out.println("normalize");
        Vector2 instance = new Vector2();
        instance.normalize();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    */
    
}
