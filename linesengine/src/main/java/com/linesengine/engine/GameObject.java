package com.linesengine.engine;

import com.linesengine.math.PhysicsBody;
import java.awt.Graphics;

public class GameObject 
{
    protected String name;
    protected PhysicsBody physicsBody;
    
    public GameObject (String name)
    {
        this.name = name;
    }
    
    public GameObject (String name, PhysicsBody body)
    {
        this.name = name;
        this.physicsBody = body;
    }
    
    public void render(Graphics g)
    {
        
    }
}
