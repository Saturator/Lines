package com.linesengine.math;

/**
 * PhysicsBody is a parent physics class that contains all the functions and variables
 * shared between all physical object.
 */

public abstract class PhysicsBody implements Collidable
{
    //public float G = 
    
    public Vector2 velocity;
    public Vector2 position;
    
    public float width;
    public float height;
    
    public float mass;
    public float friction;
    public float gravity;
    //you can add gravity by adding small a down vector 
    //that is applied on every timestep
    
    public abstract Vector2[] getPts();
    
    public PhysicsBody()
    {
        //no movement in the beginning
        velocity = new Vector2(0f, 0f);
        this.friction = 0.02f;
    }
    
    public PhysicsBody(float friction)
    {
        //friction cannot be bigger than 100% (1.00f)
        if(friction > 1f)
        {
            this.friction = 1f;
        }
        //or smaller than 0%
        else if(friction < 0f)
        {
            this.friction = 0f;
        }
        else
        {
            this.friction = friction;
        }
    }
    
    @Override
    public abstract boolean isColliding(Collidable other);
    
    public float speed()
    {
        //just the length of the velocity vector
        return velocity.length();
    }
    
    public Vector2 getVelocity()
    {
        return this.velocity;
    }
    
    public void setVelocity(Vector2 v)
    {
        this.velocity = v;
    }
    
    public void addToVelocity(Vector2 v)
    {
        this.velocity.add(v);
    }
    
    public Vector2 getPosition()
    {
        return this.position;
    }
    
    public void setPosition(Vector2 v)
    {
        this.position = v;
    }
    
    public void setGravity(float amt)
    {
        this.gravity = -amt;
    }
    
    public abstract void move(Vector2 movement);
    
    /**
     * Decreases the speed of the physics body by the amount of given friction.
     */
    public void decreaseSpeed()
    {
        //we multiply the velocity vector by friction each time step
        //if friction is 0.01, we then get 0.99, so we multiply velocity by 0.99f
        if(this.velocity.length() > 0f)
        {
            this.velocity.multiply((1f - friction));
            
            //stop the object if velocity is really really slow?
            //instead of just multiplying to infinity
            if(this.velocity.length() < 0.001f)
            {
                this.velocity = new Vector2(0f, 0f);
            }
        }
    }
    
    /**
     * Adds downward or upward gravity to this physics body.
     * @param other 
     */
    public void doGravity(PhysicsBody other)
    {
        this.velocity.add(new Vector2(0f, gravity));
        this.gravity += gravity/75;
    }
}
