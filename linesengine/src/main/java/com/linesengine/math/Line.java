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
     * Rotates the line around a given pivot point.
     * @param angle amount of rotation
     * @param pivot point to be rotated around
     */
    public void rotate(float angle, Vector2 pivot)
    {
        for(Vector2 p : this.points) p.rotate(angle, pivot);
    }
    
    @Override
    public String toString()
    {
        return "(" + this.points[0] + "), (" + this.points[1] + ")";
    }
}
