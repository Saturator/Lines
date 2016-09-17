package com.linesengine.engine;

import com.linesengine.math.PhysicsBody;

public class GameObject 
{
    protected String name;
    protected GameScene scene;
    protected PhysicsBody physicsBody;
    
    public GameObject(String name, GameScene scene)
    {
        this.name = name;
        this.scene = scene;
    }
    
    public GameObject(String name, GameScene scene, PhysicsBody body)
    {
        this.name = name;
        this.scene = scene;
        this.physicsBody = body;
    }
}
