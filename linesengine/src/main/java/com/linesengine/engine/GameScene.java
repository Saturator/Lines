package com.linesengine.engine;

import java.util.ArrayList;
import com.linesengine.math.*;
import java.awt.Graphics;

public class GameScene
{
    protected String name;
    protected ArrayList<GameObject> sceneObjects = new ArrayList<>();

    public GameScene(String name)
    {
        this.name = name;
    }

    public ArrayList<GameObject> getAllObjects()
    {
        return this.sceneObjects;
    }
    
    public void addGameObject(GameObject go)
    {
        this.sceneObjects.add(go);
    }
    
    public void removeGameObject(GameObject go)
    {
        this.sceneObjects.remove(go);
    }

    //right now you CANT find two objects if they have the same name,
    //so you better make a thing for that
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
    
    public void updateScene()
    {
        this.testForAllCollisions();
        this.moveAllObjects();
    }
    
    //put the iterations in only one place after getting this to work
    public void render(Graphics g)
    {
        for(int i = 0; i < sceneObjects.size(); i++)
        {
            sceneObjects.get(i).render(g);
        }
    }

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
    
    public void testForAllCollisions()
    {
        //this is supersuper slow right now!!
        //make the algorithm so that the inner loops only checks items which
        //are within the max width/height for collision at all
        //otherwise jump out of the nested loop right away
        for (int i = 0; i < sceneObjects.size(); i++)
        {
            if (i != sceneObjects.size())
            {
                for (int j = i + 1; j < sceneObjects.size(); j++)
                {
                    testForSpecificCollision(i, j);
                }
            }

            if (i != 0)
            {
                for (int j = i - 1; j > 0; j--)
                {
                    testForSpecificCollision(i, j);
                }
            }
        }
    }

    public void testForSpecificCollision(int i, int j)
    {
        if (sceneObjects.get(i).physicsBody != null && sceneObjects.get(j).physicsBody != null)
        {
            GameObject thisObject = sceneObjects.get(i);
            GameObject otherObject = sceneObjects.get(j);
            if (thisObject.physicsBody != null && otherObject.physicsBody != null)
            {
                PhysicsBody thisBody = sceneObjects.get(i).physicsBody;
                PhysicsBody otherBody = sceneObjects.get(j).physicsBody;
                thisBody.testForCollision(otherBody);
            }
        }
    }
}
