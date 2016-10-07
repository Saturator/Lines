package com.linesengine.game;

import com.linesengine.engine.GameObject;
import com.linesengine.engine.GameProject;
import java.awt.event.*;
import com.linesengine.math.*;
import java.awt.Color;

public class MouseInput extends MouseAdapter
{
    GameProject project;
    Vector2 start;
    Vector2 end;
    
    int size = 50;
    int i = 0;
    
    public MouseInput (GameProject p)
    {
        this.project = p;
    }
    
    @Override
    public void mouseClicked(MouseEvent e)
    {
        //System.out.println("mouse pressed");
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        start = new Vector2(e.getX(), e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        
        //this is circle
        float radius = 50;
        end = new Vector2(e.getX() - (radius/2), e.getY() - (radius/2));
        start = new Vector2(start.x - (radius/2), start.y - (radius/2));
        PhysicsBody body = new CircleBody(radius/2, start);
        body.setGravity(-0.18f);
        Vector2 diff = new Vector2(end.x - start.x, end.y - start.y);
        diff.multiply(0.1f);
        body.setVelocity(diff);
        GameObject go = new CirclePrimitive("dodo", body, RNG.getRandomColor(), radius);
        this.project.getScene(0).addGameObject(go);
        
        /*
        end = new Vector2(e.getX(), e.getY());
        start = new Vector2(start.x, start.y);
        PhysicsBody body = new BoxBody(size);
        body.move(start);
        Vector2 diff = new Vector2(end.x - start.x, end.y - start.y);
        diff.multiply(0.1f);
        body.setVelocity(diff);
        GameObject go = new BoxPrimitive("boxy", body, Color.CYAN);
        this.project.getScene(0).addGameObject(go);
        size--;
        */
    }
}
