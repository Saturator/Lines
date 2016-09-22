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
    public BoxBody()
    {
        this.scaleBox(1f);
        this.position = new Vector2(0f, 0f);
        rotation = 0f;
    }
    
    public BoxBody(float size)
    {
        this.scaleBox(size);
        this.position = new Vector2(0f, 0f);
        rotation = 0f;
    }
    
    public BoxBody(float size, Vector2 position)
    {
        this.scaleBox(size);
        this.move(position);
        rotation = 0f;
    }
    
    public void scaleBox(float size)
    {
        this.tl.add(new Vector2(-0.5f * size, 0.5f * size));
        this.tr.add(new Vector2(0.5f * size, 0.5f * size));
        this.br.add(new Vector2(0.5f * size, -0.5f * size));
        this.bl.add(new Vector2(-0.5f * size, -0.5f * size));
    }
    
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

    }
    
    @Override
    public void resolveCollision(Collidable other)
    {
        
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
        Vector2 middle = new Vector2((bl.x - tr.x)/2, (bl.y - tr.y)/2);
        return new Vector2(middle.x - point.x, middle.y - point.y).length();
    }
    
    public void rotate(float angle)
    {           
        this.tl.rotate(angle);
        this.tr.rotate(angle);
        this.br.rotate(angle);
        this.bl.rotate(angle);
        
        //change the places of top left dots, etc as the box spins
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
}
