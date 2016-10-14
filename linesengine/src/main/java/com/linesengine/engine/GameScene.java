package com.linesengine.engine;

import java.util.ArrayList;
import com.linesengine.math.*;
import java.awt.Graphics;

/**
 * GameScene class represents one scene within out GameProject.
 */

public class GameScene
{
    public int ticks = 0;
    protected String name;
    protected ArrayList<GameObject> sceneObjects = new ArrayList<>();

    public GameScene(String name)
    {
        this.name = name;
    }
    
    /**
     * Gets all game objects within the scene-
     * @return 
     */
    public ArrayList<GameObject> getAllObjects()
    {
        return this.sceneObjects;
    }
    
    /**
     * Adds a game object to the scene.
     * @param go game object to be added
     */
    public void addGameObject(GameObject go)
    {
        this.sceneObjects.add(go);
    }
    
    /**
     * Removes a specific game object from the scene.
     * @param go game object to be removed
     */
    public void removeGameObject(GameObject go)
    {
        this.sceneObjects.remove(go);
    }

    //right now you CANT find two objects if they have the same name,
    //so you better make a thing for that
    /**
     * Finds a specific game object by its name.
     * @param name name of the object to be found
     * @return found game object
     */
    public GameObject findGameObject(String name)
    {
        GameObject findable = null;
        for (GameObject go : this.sceneObjects)
        {
            if (go.name.equals(name))
            {
                findable = go;
            }
        }
        return findable;
    }
    
    public void clearAllObjects()
    {
        this.sceneObjects.clear();
    }
    
    /**
     * Simulates one physics tick within the whole scene.
     */
    public void tick()
    {
        this.ticks++;
        this.testForAllCollisions();
        this.moveAllObjects();
    }
    
    //put the iterations in only one place after getting this to work
    /**
     * Calls the render method for all the game objects in the scene.
     * @param g the graphics object to be rendered on
     */
    public void render(Graphics g)
    {
        for(int i = 0; i < sceneObjects.size(); i++)
        {
            sceneObjects.get(i).render(g);
        }
    }

    /**
     * Moves all the objects in the scene according to the parameters in
     * their matching physics body.
     */
    public void moveAllObjects()
    {
        for(int i = 0; i < sceneObjects.size(); i++)
        {
            PhysicsBody body = sceneObjects.get(i).physicsBody;
            if(body != null)
            {
                body.move(body.getVelocity());
                body.decreaseSpeed();
            }
        }
    }
    
    /**
     * Tests for all the collisions between objects within the scene.
     */
    public void testForAllCollisions()
    {
        //this is supersuper slow right now!!
        //make the algorithm so that the inner loops only checks items which
        //are within the max width/height for collision at all
        //otherwise jump out of the nested loop right away
        
        boolean[] hasCollided = new boolean[sceneObjects.size()];
        for(GameObject go : sceneObjects)
        {
            go.physicsBody.hasRotated = false;
        }
        for (int i = 0; i < sceneObjects.size(); i++)
        {
            for (int j = 0; j < sceneObjects.size(); j++)
            {
                if(testForSpecificCollision(i, j) == true)
                {
                    //we don't get j cos we get it in the outer loop later
                    if(!hasCollided[i]) 
                    {
                        sceneObjects.get(i).isColliding();
                        hasCollided[i] = true;
                        hasCollided[j] = true;
                    }
                }
            }
        }  
    }

    /**
     * Tests for a specific collision. Called from 'testForAllCollisions'.
     * @param i index of game object A
     * @param j index of game object B
     * @return returns whether a specific collision happened or not
     */
    public boolean testForSpecificCollision(int i, int j)
    {
        if (sceneObjects.get(i).physicsBody != null && sceneObjects.get(j).physicsBody != null)
        {
            GameObject thisObject = sceneObjects.get(i);
            GameObject otherObject = sceneObjects.get(j);
            if (thisObject.physicsBody != null && otherObject.physicsBody != null)
            {
                PhysicsBody thisBody = sceneObjects.get(i).physicsBody;
                PhysicsBody otherBody = sceneObjects.get(j).physicsBody;
                return thisBody.isColliding(otherBody);
            }
        }
        return false;
    }
}
