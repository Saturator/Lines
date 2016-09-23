package com.linesengine.math;

public class BoxBody extends PhysicsBody
{
    protected Vector2 tl, tr, br, bl; //top left, top right, bottom right, bottom left
    protected float rotation;
    
    /*
    
    min represents the bottom left point of a box
    max represents the upper right point of a box
    
         *----------*<-tr
         |          |
         |          |
         |          | 
     bl->*----------*
    
    */
    
    //create a box that is 1x1 by default
    public BoxBody(Vector2 tl, Vector2 tr, Vector2 br, Vector2 bl)
    {
        this.tl = tl;
        this.tr = tr;
        this.br = br;
        this.bl = bl;
        this.position = this.middle();
        rotation = 0f;
    }
    
    public BoxBody(float size)
    {
        this.tl = new Vector2(-0.5f * size, 0.5f * size);
        this.tr = new Vector2(0.5f * size, 0.5f * size);
        this.br = new Vector2(0.5f * size, -0.5f * size);
        this.bl = new Vector2(-0.5f * size, -0.5f * size);
        this.position = new Vector2(0f, 0f);
        rotation = 0f;
    }
    
    public BoxBody(float size, Vector2 position)
    {
        this.tl = new Vector2(-0.5f * size, 0.5f * size);
        this.tr = new Vector2(0.5f * size, 0.5f * size);
        this.br = new Vector2(0.5f * size, -0.5f * size);
        this.bl = new Vector2(-0.5f * size, -0.5f * size);
        this.position = position;
        rotation = 0f;
    }
    
    public void scaleBox(float size)
    {
        this.tl.add(new Vector2(-0.5f * size, 0.5f * size));
        this.tr.add(new Vector2(0.5f * size, 0.5f * size));
        this.br.add(new Vector2(0.5f * size, -0.5f * size));
        this.bl.add(new Vector2(-0.5f * size, -0.5f * size));
    }
    
    @Override
    public void move(Vector2 movement)
    {
        this.tl = new Vector2(this.tl.x + movement.x, this.tl.y + movement.y);
        this.tr = new Vector2(this.tr.x + movement.x, this.tr.y + movement.y);
        this.br = new Vector2(this.br.x + movement.x, this.br.y + movement.y);
        this.bl = new Vector2(this.bl.x + movement.x, this.bl.y + movement.y);
        
        this.position.add(movement);
    }
    
    @Override
    public void testForCollision(Collidable other)
    {
        if(other instanceof CircleBody)
        {
            
        }
        else if(other instanceof BoxBody && isCollidingWithBox((BoxBody) other))
        {
            
        }
    }
    
    public boolean isCollidingWithBox(BoxBody other)
    {
        /*
        
        *------*
        | this |
        |   *--|------*
        |   |  |      |
        *------*      |
            |  other  |
            *---------*
        
        in this case: 
        (this.tr.x > other.bl.x) && (this.bl.y < other.tr.y)
        
        in plain english:
        this box's upper right corner's x position is larger than
        the other box's lower left corner's x position
        and
        this box's lower left corner's y position is smaller than
        the other box's upper right corner's y position
        
        so the collision returns true
        */
        
        return
        (this.tr.x > other.bl.x || this.bl.x < other.tr.x)
        &&
        (this.tr.y > other.bl.y || this.bl.y < other.tr.y);
    }
    
    public float distance(Vector2 point)
    {
        Vector2 middle = this.middle();
        return new Vector2(middle.x - point.x, middle.y - point.y).length();
    }
    
    public Vector2 middle()
    {
        return new Vector2((tr.x + tl.x) / 2, (tr.y + br.y) / 2);
    }
    
    public Line[] getCollisionNormals()
    {
        Line[] normals = new Line[4]; //array of lines, aka 2 points
        
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
        return "tl: " + this.tl + ", tr: " + this.tr + ", br: " + this.br + ", bl: " + this.bl +
               ", mid: " + this.middle();
    }
}
