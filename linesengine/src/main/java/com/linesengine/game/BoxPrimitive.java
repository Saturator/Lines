package com.linesengine.game;

import com.linesengine.engine.GameObject;
import com.linesengine.math.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;

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
        Vector2[] points = super.physicsBody.getPts();
        Graphics2D g2d = (Graphics2D) g.create();
        //g2d.rotate(Math.toRadians(i), (int)pos.x + (size/2), (int)pos.y + (size/2));
        g2d.setColor(Color.white);
        /*
        g2d.drawOval((int)points[0].x, (int)points[0].y, 3, 3);
        g2d.drawOval((int)points[1].x, (int)points[1].y, 3, 3);
        g2d.drawOval((int)points[2].x, (int)points[2].y, 3, 3);
        g2d.drawOval((int)points[3].x, (int)points[3].y, 3, 3);
        g2d.drawOval((int)points[4].x, (int)points[4].y, 3, 3);
        */
        fastDrawLine(g2d, points[0], points[1]);
        fastDrawLine(g2d, points[1], points[2]);
        fastDrawLine(g2d, points[2], points[3]);
        fastDrawLine(g2d, points[3], points[0]);
        
        fastDrawLine(g2d, points[0], points[2]);
        fastDrawLine(g2d, points[1], points[3]);
        g2d.setFont(new Font("Courier", Font.PLAIN, 10));
        super.physicsBody.rotate(super.physicsBody.rotation);
        //System.out.println(this.physicsBody);
        //g2d.drawString(points[1] + "", (int)points[1].x, (int)points[1].y - 4);
        //g2d.drawString(points[0] + "", (int)points[0].x - 100, (int)points[0].y - 4);
        //g2d.drawString(points[2] + "", (int)points[2].x, (int)points[2].y + 20);
        //g2d.drawString(points[3] + "", (int)points[3].x - 100, (int)points[3].y + 20);
        g2d.dispose();
    }
    
    public void fastDrawLine(Graphics2D g2d, Vector2 v1, Vector2 v2)
    {
        g2d.drawLine((int)v1.x, (int)v1.y, (int)v2.x, (int)v2.y);
    }

    @Override
    public void isColliding()
    {
    }    
}
