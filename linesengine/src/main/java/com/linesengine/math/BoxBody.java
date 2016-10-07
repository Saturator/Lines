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
        return new Vector2[] {tl, tr, br, bl};
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
    
    //doesnt work properly atm
    /**
     * Gets the closest point of another box to this box's center.
     * @param other
     * @return 
     */
    public Vector2 closestPointToMid(BoxBody other)
    {
        Vector2 mid = this.middle();
        Vector2[] otherPts = new Vector2[] {other.tl, other.tr, other.br, other.bl};
        Vector2 closestToMid = other.tl;
        for(Vector2 otherPt : otherPts)
        {
            Vector2 diff = new Vector2(mid.x - otherPt.x, mid.y - otherPt.y);
            if(diff.length() < closestToMid.length())
            {
                closestToMid = otherPt;
            }
        }
        return closestToMid;
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
        
        Line up = new Line(this.tl, this.tr);
        Line right = new Line(this.tr, this.br);
        Line down = new Line(this.br, this.bl);
        Line left = new Line(this.bl, this.tl);
        Vector2 upMid = up.getMidpoint();
        Vector2 rightMid = right.getMidpoint();
        Vector2 downMid = down.getMidpoint();
        Vector2 leftMid = left.getMidpoint();
        
        Vector2 closestPt = closestPointToMid(other);
        Vector2[] diffs = new Vector2[4];
        diffs[0] = new Vector2(closestPt.x - upMid.x, closestPt.y - upMid.y);
        diffs[1] = new Vector2(closestPt.x - rightMid.x, closestPt.y - rightMid.y);
        diffs[2] = new Vector2(closestPt.x - downMid.x, closestPt.y - downMid.y);
        diffs[3] = new Vector2(closestPt.x - leftMid.x, closestPt.y - leftMid.y);
        
        Vector2[] normals = new Vector2[4];
        Vector2 mid = this.middle();
        normals[0] = new Vector2(upMid.x - mid.x, upMid.y - mid.y).normalize();
        normals[1] = new Vector2(rightMid.x - mid.x, rightMid.y - mid.y).normalize();
        normals[2] = new Vector2(downMid.x - mid.x, downMid.y - mid.y).normalize();
        normals[3] = new Vector2(leftMid.x - mid.x, leftMid.y - mid.y).normalize();
        //System.out.println(closestPt);
        //System.out.println(this);
        //we look for one vector difference where both values are positive
        boolean colliding = false;
        for(int i = 0; i < normals.length; i++)
        {
            //System.out.println(normals[i].dotProduct(diffs[i]));
            if(normals[i].dotProduct(diffs[i]) < 0)
            {
                System.out.println("rightMid: " + rightMid);
                System.out.println("leftMid: " + leftMid);
                System.out.println("downMid: " + downMid);
                System.out.println("upMid: " + upMid);
                System.out.println(this);
                System.out.println(normals[i]);
                System.out.println(diffs[i]);
                System.out.println(normals[i].dotProduct(diffs[i]));
                colliding = true;
                break;
            }
        }
        
        return colliding;
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
