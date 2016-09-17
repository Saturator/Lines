package com.linesengine.math;

public class PhysicsBody 
{
    protected Vector2 velocity;
    protected Vector2 position;
    protected float mass;
    protected float friction;
    //you can add gravity by adding small a down vector 
    //that is applied on every timestep
    
    public PhysicsBody()
    {
        //no movement in the beginning
        velocity = new Vector2(0f, 0f);
    }
    
    public PhysicsBody(float friction)
    {
        //friction cannot be bigger than 100% (1.00f)
        if(friction > 1f)
        {
            this.friction = 1f;
        }
        else
        {
            this.friction = friction;
        }
    }
    
    public float speed()
    {
        //just the length of the velocity vector
        return velocity.length();
    }
    
    public void decreaseSpeed()
    {
        //we multiply the velocity vector by friction each time step
        //if is friction 0.01, we then get 0.99, so we multiply velocity by 0.99f
        if(this.velocity.length() > 0f)
        {
            this.velocity.multiply((1f - friction));
        }
    }
}
