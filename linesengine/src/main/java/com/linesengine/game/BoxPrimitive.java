package com.linesengine.game;

import com.linesengine.engine.GameObject;
import com.linesengine.math.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class BoxPrimitive extends GameObject
{
    float i = 0;
    public BoxPrimitive(String name, PhysicsBody body, Color c)
    {
        super(name, body, c);
    }

    @Override
    public void tick()
    {
    }

    @Override
    public void render(Graphics g)
    {
        int size = 50;
        Vector2 pos = super.physicsBody.getPosition();
        Graphics2D g2d = (Graphics2D) g.create();
        //g2d.rotate(Math.toRadians(i), (int)pos.x + (size/2), (int)pos.y + (size/2));
        g2d.setColor(Color.white);
        g2d.fillRect((int)pos.x, (int)pos.y, size, size);
        g2d.dispose();
        if(i > -360) i -= 0.1f;
        else i = 0;
    }

    @Override
    public void isColliding()
    {
    }    
}
