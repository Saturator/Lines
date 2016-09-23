package com.linesengine.math;

public class Line
{
    public Vector2[] points;
    
    public Line(Vector2 p0, Vector2 p1)
    {
        this.points = new Vector2[2];
        this.points[0] = p0;
        this.points[1] = p1;
    }
    
    public Vector2 getMidpoint()
    {
        Vector2 midpoint = new Vector2();
        for(Vector2 p : this.points)
        {
            midpoint.add(p);
        }
        return new Vector2(midpoint.x/2, midpoint.y/2);
    }
    
    public void rotate(float angle)
    {
        Vector2 midpoint = this.getMidpoint();
        Vector2[] normalized = new Vector2[2];
        normalized[0] = new Vector2(this.points[0].x - midpoint.x, this.points[0].y - midpoint.y);
        normalized[1] = new Vector2(this.points[1].x - midpoint.x, this.points[1].y - midpoint.y);
        
        
        normalized[0].rotate(angle);
        normalized[1].rotate(angle);
        normalized[0].add(midpoint);
        normalized[1].add(midpoint);
        this.points[0] = normalized[0];
        this.points[1] = normalized[1];
    }
    
    @Override
    public String toString()
    {
        return "(" + this.points[0] + "), (" + this.points[1] + ")";
    }
}
