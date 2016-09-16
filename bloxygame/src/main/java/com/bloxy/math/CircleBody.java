package com.bloxy.math;

public class CircleBody extends PhysicsBody
{
    protected float radius;
    protected Vector2 position;
    
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
    
    public boolean isCollidingWithCircle(CircleBody other)
    {
        return 
        (((this.position.x + this.radius) > (other.position.x - other.radius)) ||
        ((this.position.x - this.radius) < (other.position.x + other.radius)))
        &&
        (((this.position.y + this.radius) > (other.position.y - other.radius)) ||
        ((this.position.y - this.radius) < (other.position.y + other.radius)));
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
