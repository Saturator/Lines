package com.linesengine.math;

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
    
    @Override
    public void move(Vector2 movement)
    {
        this.position.add(movement);
    }  
    
    @Override
    public void testForCollision(Collidable other)
    {
        if(other instanceof CircleBody && isCollidingWithCircle((CircleBody) other))
        {
            resolveCircleCollision((CircleBody) other);
        }
        else if(other instanceof BoxBody)
        {
            //isCollidingWithBox((BoxBody) other);
        }
    }
    
    public boolean isCollidingWithCircle(CircleBody other)
    {
        float betweenLength = 
        new Vector2(other.position.x - this.position.x, other.position.y - this.position.y).length();
        
        return (betweenLength - (this.radius + other.radius)) <= 0f;
    }
    
    //think if this should return a vector or just add to the overclass right away
    public void resolveCircleCollision(CircleBody other)
    {
        Vector2 directionalVector = 
        new Vector2(other.position.x - position.x, other.position.y - position.y);
        
        directionalVector = directionalVector.normalize();
        directionalVector.multiply(this.speed() * -0.75f);
        this.velocity.add(directionalVector);
        other.velocity.multiply(0.75f);
        this.velocity.add(other.velocity);
    }
}
