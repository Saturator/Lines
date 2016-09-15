package math;

public class Circle 
{
    protected float radius;
    protected Vector2 position;
    
    public Circle()
    {
        this.radius = 1f;
        this.position = new Vector2(0f, 0f);
    }
    
    public Circle(float radius)
    {
        this.radius = radius;
        this.position = new Vector2(0f, 0f);
    }
    
    public Circle(float radius, Vector2 position)
    {
        this.radius = radius;
        this.position = position;
    }
    
    public boolean IsCollidingWithCircle(Circle other)
    {
        return 
        (((this.position.x + this.radius) > (other.position.x - other.radius)) ||
        ((this.position.x - this.radius) < (other.position.x + other.radius)))
        &&
        (((this.position.y + this.radius) > (other.position.y - other.radius)) ||
        ((this.position.y - this.radius) < (other.position.y + other.radius)));
    }
    
    public Vector2 HasCollidedWithCircle(Circle other)
    {
        Vector2 directionalVector = 
        new Vector2(other.position.x - position.x, other.position.y - position.y);
        
        return directionalVector;
    }
    
}
