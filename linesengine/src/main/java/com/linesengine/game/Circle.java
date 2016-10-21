package com.linesengine.game;

import com.linesengine.physics.PhysicsBody;
import com.linesengine.physics.CircleBody;
import com.linesengine.engine.GameObject;
import com.linesengine.engine.GameObject;
import java.awt.Graphics;
import com.linesengine.math.*;
import java.awt.Color;

public class Circle extends GameObject
{   
    public Circle(String name)
    {
        super(name, Color.white);
        CircleBody body = new CircleBody();
        super.physicsBody = body;
    }
    
    /**
     * Creates a Circle with a given size at a given starting position.
     * Automatically creates a CircleBody for the Circle.
     * @param size
     * @param startPosition 
     */
    public Circle(float size, Vector2 startPosition)
    {
        super("circle", Color.white);
        CircleBody body = new CircleBody(size, startPosition);
        super.physicsBody = body;
    }
    
    @Override
    public void isColliding(){}

    @Override
    public void tick(){}

    @Override
    public void render(Graphics g)
    {
        g.setColor(super.color);
        PhysicsBody body = super.physicsBody;
        Vector2 pos = body.position;
        g.drawOval((int)pos.x, (int)pos.y, (int) body.size * 2, (int) body.size * 2);
    }
}
