package com.linesengine.math;

/**
 * Collidable is an interface that allow us to test for a collision, 
 * for any object that executes the interface.
 */

public interface Collidable
{
    public boolean isColliding(Collidable other);
}
