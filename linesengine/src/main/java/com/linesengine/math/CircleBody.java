package com.linesengine.math;

/**
 * CircleBody is a child object of PhysicsBody that simulates collision and movement for a circle shaped are
 */

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
    public boolean isColliding(Collidable other)
    {
        if(other instanceof CircleBody && isCollidingWithCircle((CircleBody) other))
        {
            resolveCircleCollision((CircleBody) other);
            return true;
        }
        else if(other instanceof BoxBody)
        {
            //isCollidingWithBox((BoxBody) other);
        }
        return false;
    }
    
    /**
     * Checks whether the circle is colliding with another specific circle.
     * @param other the other circle
     * @return 
     */
    public boolean isCollidingWithCircle(CircleBody other)
    {
        if(other.equals(this)) return false;
        
        float betweenLength = 
        new Vector2(other.position.x - this.position.x, other.position.y - this.position.y).length();

        return (betweenLength - (this.radius + other.radius)) <= 0f;
    }
    
    //think if this should return a vector or just add to the overclass right away
    /**
     * Resolves the circle on circle collision by adding the resulting velocity changes.
     * @param other the other circle
     */
    public void resolveCircleCollision(CircleBody other)
    {
        if(other.equals(this)) return;
        
        Vector2 directionalVector = 
        new Vector2(other.position.x - position.x, other.position.y - position.y);
        
        directionalVector = directionalVector.normalize();
        directionalVector.multiply((this.speed() * -0.5f) + (other.speed() * -0.4f));   
        this.velocity.add(directionalVector);
    }
}
