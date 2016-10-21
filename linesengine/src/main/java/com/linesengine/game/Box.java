package com.linesengine.game;

import com.linesengine.physics.BoxBody;
import com.linesengine.engine.GameObject;
import com.linesengine.math.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Box extends GameObject
{
    public Box(String name)
    {
        super(name, Color.white);
        BoxBody body = new BoxBody();
        super.physicsBody = body;
    }
    
    /**
     * Creates a Box with a given size and starting position.
     * Automatically creates a PhysicsBody for the Box.
     * @param size
     * @param startPosition 
     */
    public Box(float size, Vector2 startPosition)
    {
        super("box", Color.white);
        BoxBody body = new BoxBody(size, startPosition);
        super.physicsBody = body;
    }

    @Override
    public void tick(){}
    
    @Override
    public void isColliding(){}

    @Override
    public void render(Graphics g)
    {
        BoxBody box = (BoxBody) super.physicsBody;
        Vector2[] points = box.getPts();
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(super.color);
        fastDrawLine(g2d, points[0], points[1]);
        fastDrawLine(g2d, points[1], points[2]);
        fastDrawLine(g2d, points[2], points[3]);
        fastDrawLine(g2d, points[3], points[0]);
        box.rotate(box.rotation);
        g2d.dispose();
    }
    
    public void fastDrawLine(Graphics2D g2d, Vector2 v1, Vector2 v2)
    {
        g2d.drawLine((int)v1.x, (int)v1.y, (int)v2.x, (int)v2.y);
    } 
}
