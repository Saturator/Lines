package com.linesengine.math;

/**
 * BoxBody is a child object of PhysicsBody that simulates collision and movement for a box shaped are
 */

public class BoxBody extends PhysicsBody
{
    public Vector2 tl, tr, br, bl; //top left, top right, bottom right, bottom left
    protected float rotation;
    
    /*

     tl->*----------*<-tr
         |          |
         |          |
         |          | 
     bl->*----------*<-br
    
    */
    
    public Vector2[] getPts()
    {
        return new Vector2[] {tl, tr, br, bl, this.middle()};
    }
    
    //create a box that is 1x1 by default
    public BoxBody()
    {
        this.tl = new Vector2(-0.5f, -0.5f);
        this.tr = new Vector2(0.5f, -0.5f);
        this.br = new Vector2(0.5f, 0.5f);
        this.bl = new Vector2(-0.5f, 0.5f);
        super.position = new Vector2(0f, 0f);
        super.width = 1f;
        super.height = 1f;
        rotation = 0f;
    }
    
    public BoxBody(float size)
    {
        this.tl = new Vector2(-0.5f * size, -0.5f * size);
        this.tr = new Vector2(0.5f * size, -0.5f * size);
        this.br = new Vector2(0.5f * size, 0.5f * size);
        this.bl = new Vector2(-0.5f * size, 0.5f * size);
        super.position = new Vector2(0f, 0f);
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
    
    @Override
    public void move(Vector2 movement)
    {
        this.tl = new Vector2(this.tl.x + movement.x, this.tl.y + movement.y);
        this.tr = new Vector2(this.tr.x + movement.x, this.tr.y + movement.y);
        this.br = new Vector2(this.br.x + movement.x, this.br.y + movement.y);
        this.bl = new Vector2(this.bl.x + movement.x, this.bl.y + movement.y);
        
        super.position.add(movement);
    }
    
    @Override
    public boolean isColliding(Collidable other)
    {
        if(other instanceof CircleBody)
        {
            return true;
        }
        else if(other instanceof BoxBody && isCollidingWithBox((BoxBody) other))
        {
            System.out.println("hola");
            return true;
        }
        return false;
    }
    
    public float[] getMinMax(BoxBody body, Vector2 direction)
    {
        //Get the DIRECTION
        Vector2 axis = direction;
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
    
    //this is still under work
    /**
     * Checks whether this specific box is colliding with another specific box.
     * @param other
     * @return 
     */
    public boolean isCollidingWithBox(BoxBody other)
    {   
        if(other == this) return false;
        Vector2 direction = new Vector2
        (other.middle().x - this.middle().x, other.middle().y - this.middle().y)
        .normalize();
        float[] thisMinMax = getMinMax(this, direction);
        float[] otherMinMax = getMinMax(other, direction);
        return thisMinMax[1] > otherMinMax[0];
    }
    
    /**
     * Distance from the center of the box to a specific point.
     * @param point
     * @return 
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
    public Vector2 middle()
    {
        return new Vector2((tr.x + tl.x) / 2, (tr.y + br.y) / 2);
    }
    
    /**
     * Creates collision normals, which are lines perpendicular to the given sides.
     * @return 
     */
    public Line[] getCollisionNormals()
    {
        Line[] normals = new Line[4]; //array of lines, = [4][2] list of Vector2's
        
        normals[0] = new Line(this.tl, this.tr);
        normals[1] = new Line(this.tr, this.br);
        normals[2] = new Line(this.br, this.bl);
        normals[3] = new Line(this.bl, this.tl);
        
        for(Line l : normals)
        {
            l.rotate(90);
        }
        
        return normals;
    }
    
    /**
     * Rotates the box.
     * @param angle the amount of rotation as an angle
     */
    public void rotate(float angle)
    {           
        //these call the vector2 rotation func, not this one
        this.tl.rotate(angle);
        this.tr.rotate(angle);
        this.br.rotate(angle);
        this.bl.rotate(angle);
        
        //changes the places of top left dots, etc as the box spins
        //because as the box spins, the top left and all others also change
        //we change them as many times as the box has been rotated a full 90 degrees
        for(float i = this.rotation; i <= angle+this.rotation; i += 90)
        {
            Vector2 oldTl = this.tl;
            this.tl = this.tr;
            this.tr = this.br;
            this.br = this.bl;
            this.bl = oldTl;
        }
        
        //put rotation to 0 every time the box has rotated fully
        if(this.rotation < 360f)
        {
            this.rotation += angle;   
        }
        else
        {
            this.rotation = angle - 360f;
        }
    }
    
    @Override
    public String toString()
    {
        return "tl: (" + this.tl + "), tr: (" + this.tr + "), br: (" + this.br + "), bl: (" + this.bl +
               "), mid: " + this.middle();
    }
}
