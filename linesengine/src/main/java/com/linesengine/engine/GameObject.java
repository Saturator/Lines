package com.linesengine.engine;

import com.linesengine.math.PhysicsBody;
import java.awt.Graphics;

public abstract class GameObject 
{
    public String name;
    public PhysicsBody physicsBody;
    
    public GameObject(String name)
    {
        this.name = name;
    }
    
    public GameObject (String name, PhysicsBody body)
    {
        this.name = name;
        this.physicsBody = body;
    }
    
    public void addPhysicsBody(PhysicsBody body)
    {
        this.physicsBody = body;
    }
    
    public PhysicsBody getPhysicsBody()
    {
        return this.physicsBody;
    }
    
    public abstract void tick();
    
    public abstract void render(Graphics g);
}
