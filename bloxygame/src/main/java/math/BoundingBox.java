package math;

import java.lang.Math;

//this class is named BoundingBox instead of Box
//because it is a collider that's shaped like a box
//which can contain any number of shapes
//which are *bound* inside the bounding box
public class BoundingBox 
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
    public BoundingBox()
    {
        this.min = new Vector2(0f, 0f);
        this.max = new Vector2(1f, 1f); //1 right, 1 up
    }
    
    public BoundingBox(Vector2 min, Vector2 max)
    {
        this.min = min;
        this.max = max;
    }
    
    public boolean CollidingWithBox(BoundingBox other)
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
    
    public float Distance(Vector2 point)
    {
        Vector2 middle = new Vector2((min.x - max.x)/2, (min.y - max.y)/2);
        
        return (float) 
        Math.sqrt(Math.pow(middle.x - point.x, 2) + Math.pow(middle.y - point.y, 2));
    }
}
