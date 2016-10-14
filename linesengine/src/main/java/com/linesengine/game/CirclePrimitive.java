package com.linesengine.game;

import com.linesengine.engine.GameObject;
import java.awt.Graphics;
import com.linesengine.math.*;
import java.awt.Color;
import java.awt.Font;

public class CirclePrimitive extends GameObject
{
    public float radius = 50f;
    
    public CirclePrimitive(String name)
    {
        super(name);
    }
    
    public CirclePrimitive(String name, PhysicsBody body)
    {
        super(name, body);
    }
    
    public CirclePrimitive(String name, PhysicsBody body, Color c, float r)
    {
        super(name, body, c);
        this.radius = r;
    }
    
    @Override
    public void isColliding()
    {
    }

    @Override
    public void tick()
    {
        
    }

    @Override
    public void render(Graphics g)
    {
        g.setColor(this.color);
        //g.setFont(new Font("Courier", Font.PLAIN, 8));
        Vector2 pos = this.physicsBody.getPosition();
        g.drawOval((int)pos.x, (int)pos.y, (int) radius, (int) radius);
        //g.drawString(pos + "", (int)pos.x - 25, (int)pos.y - 5);
    }
}
