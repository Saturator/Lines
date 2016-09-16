package com.bloxy.math;

public class BoxBody extends PhysicsBody
{
    protected Vector2 min, max;
    
    /*
    
    min represents the bottom left point of a box
    max represents the upper right point of a box
    
         *----------*<-max
         |          |
         |          |
         |          | 
    min->*----------*
    
    */
    
    //create a box that is 1x1 by default
    public BoxBody()
    {
        this.min = new Vector2(0f, 0f);
        this.max = new Vector2(1f, 1f); //1 right, 1 up
        this.position = new Vector2(0f, 0f);
    }
    
    public BoxBody(Vector2 min, Vector2 max)
    {
        this.min = min;
        this.max = max;
        this.position = new Vector2(0f, 0f);
    }
    
    public BoxBody(Vector2 min, Vector2 max, Vector2 position)
    {
        this.min = min;
        this.max = max;
        this.position = position;
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
        (this.max.x > other.min.x) && (this.min.y < other.max.y)
        
        in plain english:
        this box's upper right corner's x position is larger than
        the other box's lower left corner's x position
        and
        this box's lower left corner's y position is smaller than
        the other box's upper right corner's y position
        
        so the collision returns true
        
        */
        
        return
        (this.max.x > other.min.x || this.min.x < other.max.x)
        &&
        (this.max.y > other.min.y || this.min.y < other.max.y);
    }
    
    public float distance(Vector2 point)
    {
        Vector2 middle = new Vector2((min.x - max.x)/2, (min.y - max.y)/2);
        return new Vector2(middle.x - point.x, middle.y - point.y).length();
    }
}
