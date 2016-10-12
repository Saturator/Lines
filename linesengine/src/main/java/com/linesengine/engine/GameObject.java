package com.linesengine.engine;

import com.linesengine.math.PhysicsBody;
import java.awt.Graphics;
import java.awt.Color;

/**
 * GameObject is a parent class for any object that is within our GameScene.
 */

public abstract class GameObject 
{
    public String name;
    public PhysicsBody physicsBody;
    public Color color;
    
    public GameObject(String name)
    {
        this.name = name;
        this.color = Color.white;
    }
    
    /**
     * Creates a game object.
     * @param name name of the object
     * @param body physics body linked to the object
     */
    public GameObject(String name, PhysicsBody body)
    {
        this.name = name;
        this.physicsBody = body;
        this.color = Color.white;
    }
    
    /**
     * Creates a game object.
     * @param body physics body linked to the object
     * @param c color of the game object
     */
    public GameObject(PhysicsBody body, Color c)
    {
        this.name = "";
        this.physicsBody = body;
        this.color = c;
    }
    
    public GameObject(Color c)
    {
        this.name = "";
        this.color = c;
    }
    
    public GameObject(String name, Color c)
    {
        this.name = name;
        this.color = c;
    }
    
    /**
     * Creates a game object.
     * @param name name of the object
     * @param body physics body linked to the object
     * @param c color of the object
     */
    public GameObject(String name, PhysicsBody body, Color c)
    {
        this.name = name;
        this.physicsBody = body;
        this.color = c;
    }
    
    /**
     * Links a new physics body to the object.
     * @param body physics body to be linked
     */
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
    
    public abstract void isColliding();
}
