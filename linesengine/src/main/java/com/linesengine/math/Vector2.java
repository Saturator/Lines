package com.linesengine.math;

/**
 * Vector2 is a class that represents a single point in 2D space.
 */

public class Vector2 
{
    public float x, y;
    
    public Vector2()
    {
        this.x = 0f;
        this.y = 0f;
    }
    
    public Vector2(float x, float y)
    {
        this.x = x;
        this.y = y;
    }
    
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
     * Rotates this vector.
     * @param angle the amount to rotate by
     */
    public void rotate(float angle)
    {
        //rotation matrix is used here
        //maybe a matrix math class separately? is it needed?
        double rotatedX =
        this.x * Math.cos(Math.toRadians(angle)) - this.y * Math.sin(Math.toRadians(angle));
        
        double rotatedY = 
        this.x * Math.sin(Math.toRadians(angle)) + this.y * Math.cos(Math.toRadians(angle));
        
        this.x = (float) rotatedX;
        this.y = (float) rotatedY;
    }
    
    public void rotate(float angle, Vector2 center)
    {
        double rotatedX =
        this.x * Math.cos(Math.toRadians(angle)) - this.y * Math.sin(Math.toRadians(angle));
        
        double rotatedY = 
        this.x * Math.sin(Math.toRadians(angle)) + this.y * Math.cos(Math.toRadians(angle));
        
        this.x = (float) rotatedX;
        this.y = (float) rotatedY;
    }
    
    @Override
    public String toString()
    {
        float roundedX = Math.round(this.x * 1000.0f) / 1000.0f;
        float roundedY = Math.round(this.y * 1000.0f) / 1000.0f;
        return "x: " + roundedX + " | y: " + roundedY;
    }
}
