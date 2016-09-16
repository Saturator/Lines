package com.bloxy.math;

public class Vector2 
{
    protected float x, y;
    
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
    
    public Vector2 add(Vector2 other)
    {
        return new Vector2(this.x + other.x, this.y + other.y);
    }
    
    public Vector2 multiply(float multiplier)
    {
        return new Vector2(this.x * multiplier, this.y * multiplier);
    }
    
    public float length()
    {
        return (float) Math.sqrt((this.x * this.x) + (this.y * this.y));
    }
    
    public float dotProduct(Vector2 other)
    {
        return (this.x * other.x) + (this.y * other.y);
    }
    
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
    
    @Override
    public String toString()
    {
        return "x: " + this.x + " | y: " + this.y;
    }
}
