package com.linesengine.math;

/**
 * Line is a class that mathematically represents a line in two dimensional space.
 * This means that we have two 2D vector points.
 */

public class Line
{
    public Vector2[] points;
    
    /**
     * Creates a new line.
     * @param p0 start point of the line
     * @param p1 end point of the line
     */
    public Line(Vector2 p0, Vector2 p1)
    {
        this.points = new Vector2[2];
        this.points[0] = p0;
        this.points[1] = p1;
    }
    
    /**
     * Gets the point exactly in the middle of the line.
     * @return the midpoint
     */
    public Vector2 getMidpoint()
    {
        Vector2 midpoint = new Vector2();
        for(Vector2 p : this.points)
        {
            midpoint.add(p);
        }
        return new Vector2(midpoint.x/2, midpoint.y/2);
    }
    
    /**
     * Rotates the line
     * @param angle 
     */
    public void rotate(float angle)
    {
        Vector2 midpoint = this.getMidpoint();
        Vector2[] normalized = new Vector2[2];
        normalized[0] = new Vector2(this.points[0].x - midpoint.x, this.points[0].y - midpoint.y);
        normalized[1] = new Vector2(this.points[1].x - midpoint.x, this.points[1].y - midpoint.y);
        
        
        normalized[0].rotate(angle, midpoint);
        normalized[1].rotate(angle, midpoint);
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
