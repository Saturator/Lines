package com.linesengine.math;


public interface Collidable
{
    public void testForCollision(Collidable other);
    
    public void resolveCollision(Collidable other);
}
