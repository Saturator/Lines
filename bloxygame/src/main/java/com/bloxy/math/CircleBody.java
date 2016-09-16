package com.bloxy.math;

public class CircleBody extends PhysicsBody
{
    protected float radius;
    
    public CircleBody()
    {
        this.radius = 1f;
        this.position = new Vector2(0f, 0f);
    }
    
    public CircleBody(float radius)
    {
        this.radius = radius;
        this.position = new Vector2(0f, 0f);
    }
    
    public CircleBody(float radius, Vector2 position)
    {
        this.radius = radius;
        this.position = position;
    }
    
    public CircleBody(Vector2 position)
    {
        this.radius = 1f;
        this.position = position;
    }
    
    public boolean isCollidingWithAnotherCircle(CircleBody other)
    {
        float betweenLength = 
        new Vector2(other.position.x - this.position.x, other.position.y - this.position.y).length();
        
        return (betweenLength - (this.radius + other.radius)) <= 0f;
    }
    
    //think if this should return a vector or just add to the overclass right away
    public void resolveCollision(CircleBody other)
    {
        Vector2 directionalVector = 
        new Vector2(other.position.x - position.x, other.position.y - position.y);
        
        directionalVector.normalize();
        directionalVector.multiply(this.speed() * -0.5f);
        this.velocity.add(directionalVector);
    }
    
}
