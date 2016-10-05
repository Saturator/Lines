package com.linesengine.game;

import com.linesengine.engine.GameObject;
import java.awt.Graphics;
import com.linesengine.math.*;
import java.awt.Color;

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
        if(super.name.equals("dodo")) 
        {
            //System.out.println("holler");
        }
    }

    @Override
    public void tick()
    {
        
    }

    @Override
    public void render(Graphics g)
    {
        g.setColor(this.color);
        g.fillOval((int)this.physicsBody.getPosition().x, (int)this.physicsBody.getPosition().y,
                   (int) radius, (int) radius);
    }    
}
