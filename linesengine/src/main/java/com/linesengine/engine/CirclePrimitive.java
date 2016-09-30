package com.linesengine.engine;

import java.awt.Graphics;
import com.linesengine.math.*;
import java.awt.Color;

public class CirclePrimitive extends GameObject
{
    
    public CirclePrimitive(String name)
    {
        super(name);
    }
    
    public CirclePrimitive(String name, PhysicsBody body)
    {
        super(name, body);
    }

    @Override
    public void tick()
    {
        
    }

    @Override
    public void render(Graphics g)
    {
        g.setColor(Color.white);
        g.drawOval((int)this.physicsBody.getPosition().x, (int)this.physicsBody.getPosition().y,
                   50, 50);
    }    
}
