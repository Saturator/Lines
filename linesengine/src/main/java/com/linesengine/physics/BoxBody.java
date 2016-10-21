package com.linesengine.physics;

import com.linesengine.math.Line;
import com.linesengine.math.Vector2;

/**
 * BoxBody is a child object of PhysicsBody that simulates collision and movement for a box shaped are
 */
public class BoxBody extends PhysicsBody
{
    public Vector2 tl, tr, br, bl; //top left, top right, bottom right, bottom left
    
    /*

     tl->*-------*<-tr
         |       |
         |       |
         |       | 
     bl->*-------*<-br
   
    */
    
    /**
     * Creates a new BoxBody that is 50*50 by default.
     */
    public BoxBody()
    {
        this.tl = new Vector2(-50f, -50f);
        this.tr = new Vector2(50f, -50f);
        this.br = new Vector2(50f, 50f);
        this.bl = new Vector2(-50f, 50f);
        super.position = new Vector2(0f, 0f);
        super.width = 1f;
        super.height = 1f;
        rotation = 0f;
    }
    /**
     * Creates a new box body of given size.
     * @param size size of the body from the center
     */
    public BoxBody(float size)
    {
        this.tl = new Vector2(-0.5f * size, 0.5f * size);
        this.tr = new Vector2(0.5f * size, 0.5f * size);
        this.br = new Vector2(0.5f * size, -0.5f * size);
        this.bl = new Vector2(-0.5f * size, -0.5f * size);
        super.position = new Vector2(0f, 0f);
        super.width = size;
        super.height = size;
        rotation = 0f;
    }
    
    /**
     * Creates a new box body of given size, at certain position.
     * @param size size of the body from the center
     * @param pos starting position of the box
     */
    public BoxBody(float size, Vector2 pos)
    {
        this.tl = new Vector2((-0.5f * size) + pos.x, (0.5f * size) + pos.y);
        this.tr = new Vector2((0.5f * size) + pos.x, (0.5f * size) + pos.y);
        this.br = new Vector2((0.5f * size) + pos.x, (-0.5f * size) + pos.y);
        this.bl = new Vector2((-0.5f * size) + pos.x, (-0.5f * size) + pos.y);
        super.position = pos;
        super.width = size;
        super.height = size;
        rotation = 0f;
    }
    
    /**
     * Scales a box outwards from the center equally.
     * @param size the size of the resulting box
     */
    public void scaleBox(float size)
    {
        this.tl = new Vector2((-0.5f * size) + super.position.x, (0.5f * size) + super.position.y);
        this.tr = new Vector2((0.5f * size) + super.position.x, (0.5f * size) + super.position.y);
        this.br = new Vector2((0.5f * size) + super.position.x, (-0.5f * size) + super.position.y);
        this.bl = new Vector2((-0.5f * size) + super.position.x, (-0.5f * size) + super.position.y);
    }
    
    /**
     * Gets all the four corner points of the box.
     * @return the points as a Vector2 array
     */
    public Vector2[] getPts()
    {
        return new Vector2[] {tl, tr, br, bl, this.middle()};
    }
    
    /**
     * Moves the box by moving all the corner points.
     * @param movement amount of movement as a vector
     */
    @Override
    public void move(Vector2 movement)
    {
        this.tl = new Vector2(this.tl.x + movement.x, this.tl.y + movement.y);
        this.tr = new Vector2(this.tr.x + movement.x, this.tr.y + movement.y);
        this.br = new Vector2(this.br.x + movement.x, this.br.y + movement.y);
        this.bl = new Vector2(this.bl.x + movement.x, this.bl.y + movement.y);
        
        super.position.add(movement);
    }
    
    /**
     * Checks whether this BoxBody is colliding with any given PhysicsBody.
     * @param other the other PhysicsBody to be checked against
     * @return is the box colliding or not
     */
    @Override
    public boolean isColliding(PhysicsBody other)
    {   
        if(this.speed() < 0.025f && other.speed() < 0.025f) return false;
        
        if(other instanceof CircleBody && isCollidingWithCircle((CircleBody) other))
        {
            resolveCircleCollision((CircleBody) other);
            return true;
        }
        else if(other instanceof BoxBody && isCollidingWithBox((BoxBody) other))
        {
            resolveBoxCollision((BoxBody) other);
            return true;
        }
        return false;
    }
    
    /**
     * Transforms the velocity and rotation of this object on box collision.
     * @param other the other BoxBody this one is colliding with
     */
    public void resolveBoxCollision(BoxBody other)
    {            
        Vector2 vel1 = this.velocity;
        vel1.multiply(0.6f);
        
        other.velocity.add(vel1);
        super.velocity.multiply(0.2f);
        super.hasCollided = true;
        physicsRotation(other);
    }
    
    /**
     * Transforms the velocity and rotation of this (and the other) object on circle collision.
     * @param other the CircleBody this BoxBody is colliding with
     */
    public void resolveCircleCollision(CircleBody other)
    {            
        Vector2 vel1 = this.velocity;
        vel1.multiply(0.75f);
        other.velocity.add(vel1);
        super.velocity.multiply(0.33f);
        super.hasCollided = true;
        physicsRotation(other);
    }
    
    /**
     * A specific check whether this BoxBody is colliding with a CircleBody.
     * @param other the other given CircleBody
     * @return is this box colliding with the given CircleBody
     */
    public boolean isCollidingWithCircle(CircleBody other)
    {
        Vector2 direction = new Vector2
        (other.middle().x - this.middle().x, other.middle().y - this.middle().y)
        .normalize();
        float[] thisMinMax = getMinMax(this, direction);
        float[] otherMinMax = other.getMinMax(direction);
        return thisMinMax[1] > otherMinMax[0];
    }
    
    /**
     * Checks whether this specific box is colliding with another specific box.
     * @param other the other BoxBody we check if this BoxBody is colliding with
     * @return is the box colliding or not
     */
    public boolean isCollidingWithBox(BoxBody other)
    {   
        if(other == this) return false;
        
        Vector2 direction = new Vector2
        (other.middle().x - this.middle().x, other.middle().y - this.middle().y);
        float[] thisMinMax = getMinMax(this, direction);
        float[] otherMinMax = getMinMax(other, direction);
        return thisMinMax[1] > otherMinMax[0];
    }
    
    /**
     * Calculates the rotational behavior to be applied when two objects collide.
     * @param other the other body that this one is colliding with
     */
    public void physicsRotation(PhysicsBody other)
    {
        if(!super.hasRotated)
        {
            if(other.speed() < 0.5f) other = this;
            Vector2 diffMiddle = new Vector2(other.middle().x - this.middle().x, other.middle().y - this.middle().y);
            float rotAmt;
            if(Math.abs(other.velocity.x) >= Math.abs(other.velocity.y)) 
            {
                rotAmt = Math.abs(other.velocity.x);
            }
            else rotAmt = Math.abs(other.velocity.y);
            
            boolean movingRight = false;
            boolean movingLeft = false;
            boolean movingUp = false;
            boolean movingDown = false;
            if(Math.abs(other.velocity.x) > Math.abs(other.velocity.y))
            {
                if(other.velocity.x < 0f) movingLeft = true;
                else movingRight = true;
            }
            else if(Math.abs(other.velocity.y) > Math.abs(other.velocity.x))
            {
                if(other.velocity.y < 0f) movingUp = true;
                else movingDown = true;
            }
            float mult = 0.25f;
            if(movingUp)
            {
                if(diffMiddle.x < 0f) rotateBoth(mult * rotAmt, other);
                else rotateBoth(-mult * rotAmt, other);
            }
            else if(movingDown)
            {
                if(diffMiddle.x < 0f) rotateBoth(-mult * rotAmt, other);
                else rotateBoth(mult * rotAmt, other);
            }
            else if(movingRight)
            {
                if(diffMiddle.y < 0f) rotateBoth(mult * rotAmt, other);
                else rotateBoth(-mult * rotAmt, other);
            }
            else if(movingLeft)
            {
                if(diffMiddle.y < 0f) rotateBoth(-mult * rotAmt, other);
                else rotateBoth(mult * rotAmt, other);
            }
            super.hasRotated = true;
            other.hasRotated = true;
        }
    }
    
    /**
     * A shortcut method to rotate two given PhysicsBodys.
     * @param amt amount of rotation
     * @param other the other PhysicsBody to be rotated
     */
    public void rotateBoth(float amt, PhysicsBody other)
    {
        super.rotation = amt;
        other.rotation = amt;
    }
    
    /**
     * Gets the minimum and maximum points of the box in any given direction.
     * @param body the BoxBody of which min and max we get
     * @param direction the direction of the minimum and maximum
     * @return minimum and maximum points
     */
    public float[] getMinMax(BoxBody body, Vector2 direction)
    {
        Vector2 axis = direction.normalize();
        Vector2[] points = new Vector2[] {body.tl, body.tr, body.br, body.bl};
        float thisMin = points[0].dotProduct(axis);
        float thisMax = points[0].dotProduct(axis);
        for(Vector2 point : points)
        {
            float projection = point.dotProduct(axis);
            if(projection > thisMax)
            {
                thisMax = projection;
            }
            if(projection < thisMin)
            {
                thisMin = projection;
            }
        }
        return new float[] {thisMin, thisMax};
    }
    
    /**
     * Distance from the center of the box to a specific point.
     * @param point the points of comparison
     * @return distance to the point of comparison
     */
    public float distance(Vector2 point)
    {
        Vector2 middle = this.middle();
        return new Vector2(middle.x - point.x, middle.y - point.y).length();
    }
    
    /**
     * Gets the middle of the box in world space.
     * @return 
     */
    @Override
    public Vector2 middle()
    {
        Line line1 = new Line(tr, tl);
        Line line2 = new Line(br, bl);
        Vector2 mid1 = line1.getMidpoint();
        Vector2 mid2 = line2.getMidpoint();
        Line line3 = new Line(mid1, mid2);
        Vector2 middle = line3.getMidpoint();
        return middle;
    }
    
    /**
     * Creates collision normals, which are vectors perpendicular to the given sides.
     * @return an array that contains all four collision normals
     */
    public Vector2[] getCollisionNormals()
    {
        Line[] normals = new Line[2]; //array of lines, = [2][2] list of Vector2's 
        normals[0] = new Line(this.tl, this.tr);
        normals[1] = new Line(this.tr, this.br);
        Vector2[] normalVectors = new Vector2[2];
        Vector2 mid = this.middle();
        for(int i = 0; i < normals.length; i++)
        {
            normalVectors[i] = normals[i].getMidpoint();
            normalVectors[i].subtract(mid);
        }
        
        return normalVectors;
    }
    
    /**
     * Rotates the box.
     * @param angle the amount of rotation as an angle
     */
    @Override
    public void rotate(float angle)
    {           
        //these call the vector2 rotation func, not this one
        Vector2 mid = this.middle();
        this.tl.rotate(angle, mid);
        this.tr.rotate(angle, mid);
        this.br.rotate(angle, mid);
        this.bl.rotate(angle, mid);
    }
    
    @Override
    public String toString()
    {
        return "tl: (" + this.tl + "), tr: (" + this.tr + "), br: (" + this.br + "), bl: (" + this.bl +
               "), mid: " + this.middle();
    }
}
