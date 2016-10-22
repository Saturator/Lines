package com.linesengine.math;

/**
 * Vector2 is a class that represents a single point in 2D space.
 */

public class Vector2 
{
    public float x, y;
    
    /**
     * Create a vector at zero.
     */
    public Vector2()
    {
        this.x = 0f;
        this.y = 0f;
    }
    
    /**
     * Create a new 2D vector.
     * @param x horizontal amount
     * @param y vertical amount
     */
    public Vector2(float x, float y)
    {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Create a vector from another vector.
     * @param v vector to be created from
     */
    public Vector2(Vector2 v)
    {
        v.x = x;
        v.y = y;
    }
    
    /**
     * Adds another vector to this vector.
     * @param other 
     */
    public void add(Vector2 other)
    {
         this.x += other.x;
         this.y += other.y;
    }
    
    /**
     * Subtracts another vector to this vector.
     * @param other 
     */
    public void subtract(Vector2 other)
    {
         this.x -= other.x;
         this.y -= other.y;
    }
    
    /**
     * Multiplies this vector by a float scalar.
     * @param multiplier 
     */
    public void multiply(float multiplier)
    {
        this.x *= multiplier; 
        this.y *= multiplier;
    }
    
    /**
     * Gives this vector's length as a scalar.
     * @return 
     */
    public float length()
    {
        return (float) Math.sqrt((this.x * this.x) + (this.y * this.y));
    }
    
    /**
     * Gives the distance from a to b.
     * @return 
     */
    public static float distance(Vector2 a, Vector2 b)
    {
        return (float) Math.sqrt(((a.x-b.x) * (a.x-b.x)) + ((a.y-b.y) * (a.y-b.y)));
    }
    
    /**
     * Creates a dot product scalar.
     * @param other the other vector to project this vector on
     * @return 
     */
    public float dotProduct(Vector2 other)
    {
        return (this.x * other.x) + (this.y * other.y);
    }
    
    /**
     * Normalizes a vector's length to one.
     * @return resulting normalized vector
     */
    public Vector2 normalize()
    {
        float length = this.length();
        
        if(length > 0f)
        {
            return new Vector2(this.x /= length, this.y /= length);
        }
        else
        {
            return this;
        }
    }
    
    /**
     * Rotates this vector and a pivot point.
     * @param angle the amount to rotate by
     * @param pivot the point to be rotated around
     */
    public void rotate(float angle, Vector2 pivot)
    {
        Vector2 p = new Vector2(this.x, this.y);
        double s = (float) Math.sin(Math.toRadians(angle));
        double c = (float) Math.cos(Math.toRadians(angle));
        
        p.x -= pivot.x;
        p.y -= pivot.y;
        
        double xNew = p.x * c - p.y * s;
        double yNew = p.x * s + p.y * c;
        
        this.x = (float) xNew + pivot.x;
        this.y = (float) yNew + pivot.y;
    }
   
    public void rotate(float angle)
    {
        Vector2 p = new Vector2(this.x, this.y);
        double s = (float) Math.sin(Math.toRadians(angle));
        double c = (float) Math.cos(Math.toRadians(angle));
        
        double xNew = p.x * c - p.y * s;
        double yNew = p.x * s + p.y * c;
        
        this.x = (float) xNew;
        this.y = (float) yNew;
    }
    
    public void abs()
    {
        this.x = Math.abs(this.x);
        this.y = Math.abs(this.y);
    }
    
    
    @Override
    public String toString()
    {
        float roundedX = Math.round(this.x * 1000.0f) / 1000.0f;
        float roundedY = Math.round(this.y * 1000.0f) / 1000.0f;
        return "x: " + roundedX + " | y: " + roundedY;
    }
}
