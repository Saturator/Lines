package com.linesengine.physics;

import com.linesengine.math.Vector2;

/**
 * CircleBody is a child object of PhysicsBody that simulates the movement and collisions for a circle shaped area.
 */
public class CircleBody extends PhysicsBody
{
    protected float radius;
    
    /**
     * Creates a CircleBody with a radius of 25 at position 500x500.
     */
    public CircleBody()
    {
        this.radius = 25f;
        super.size = 25f;
        this.position = new Vector2(500f, 500f);
    }
    
    /**
     * Creates a CircleBody with a radius of 25 at a given position.
     * @param position 
     */
    public CircleBody(Vector2 position)
    {
        this.radius = 25f;
        super.size = 25f;
        this.position = position;
    }
    
    /**
     * Creates a new CircleBody with a given radius at position 500x500.
     * @param radius
     */
    public CircleBody(float radius)
    {
        this.radius = radius/2;
        super.size = radius/2;
        this.position = new Vector2(500f, 500f);
    }
    
    /**
     * Creates a new CircleBody with a given radius and position.
     * @param radius
     * @param position 
     */
    public CircleBody(float radius, Vector2 position)
    {
        this.radius = radius/2;
        super.size = radius/2;
        this.position = position;
    }
    
    @Override
    public void move(Vector2 movement)
    {
        this.position.add(movement);
    }  
    
    @Override
    public void rotate(float angle) 
    {
    }
    
    /**
     * Checks whether this CircleBody is colliding with the given PhysicsBody.
     * @param other the other given PhysicsBody.
     * @return is this CircleBody colliding with another PhysicsBody
     */
    @Override
    public boolean isColliding(PhysicsBody other)
    {
        if(this.speed() < 0.1f && other.speed() < 0.1f) return false;
        
        if(other instanceof CircleBody && isCollidingWithCircle((CircleBody) other))
        {
            if(!this.hasCollided) resolveCircleCollision((CircleBody) other);
            this.hasCollided = true;
            return true;
        }
        else if(other instanceof BoxBody && isCollidingWithBox((BoxBody) other))
        {
            if(!this.hasCollided) resolveBoxCollision((BoxBody) other);
            this.hasCollided = true;
            return true;
        }
        this.hasCollided = false;
        return false;
    }
    
    /**
     * Gets the minimum and maximum points of this circle in a given direction.
     * @param direction the direction of the min and max points
     * @return the dot product of the two points as a float array
     */
    public float[] getMinMax(Vector2 direction)
    {
        Vector2 axis = direction.normalize();
        Vector2 minPt = direction.normalize();
        minPt.multiply(-radius);
        minPt.add(this.middle());
        Vector2 maxPt = direction.normalize();
        maxPt.multiply(radius);
        maxPt.add(this.middle());
        float thisMin = minPt.dotProduct(axis);
        float thisMax = maxPt.dotProduct(axis);
        return new float[] {thisMin, thisMax};
    }
    
    /**
     * Transforms the movement of this CircleBody. 
     * Also transforms the movement and rotation of the other given BoxBody.
     * @param other the given BoxBody
     */
    public void resolveBoxCollision(BoxBody other)
    {
        Vector2 vel1 = this.velocity;
        vel1.multiply(0.6f);
        other.velocity.add(vel1);
        Vector2 collisionVector = other.getCollisionSideVector(this);
        if(collisionVector == null) super.velocity.multiply(-0.3f);
        else super.velocity.add(collisionVector);
        other.physicsRotation(this);
        super.hasCollided = true;
    }
    
    /**
     * Checks whether this CircleBody is colliding with a given BoxBody.
     * @param other the given BoxBody
     * @return is this CircleBody colliding with the given BoxBody
     */
    public boolean isCollidingWithBox(BoxBody other)
    {
        Vector2 direction = new Vector2
        (other.middle().x - this.middle().x, other.middle().y - this.middle().y)
        .normalize();
        float[] thisMinMax = getMinMax(direction);
        float[] otherMinMax = other.getMinMax(other, direction);
        return thisMinMax[1] > otherMinMax[0];
    }
    
    /**
     * Gets the middle point of this circle as a vector.
     * @return
     */
    @Override
    public Vector2 middle()
    {
        return new Vector2(position.x+radius, position.y+radius);
    }
    
    /**
     * Checks whether this CircleBody is colliding with another given CircleBody.
     * @param other the other CircleBody
     * @return is this CircleBody colliding with the CircleBody
     */
    public boolean isCollidingWithCircle(CircleBody other)
    {
        if(other.equals(this)) return false;
        float distance = Vector2.distance(this.middle(), other.middle());
        return distance <= other.radius + this.radius;
    }
    
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
        directionalVector.multiply((this.speed() * -0.2f) + (other.speed() * -0.4f));   
        this.velocity.add(directionalVector);
        this.velocity.multiply(0.75f);
    }
}
