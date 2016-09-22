package com.linesengine.engine;

import java.util.ArrayList;
import com.linesengine.math.*;

public class GameScene
{
    protected GameProject project;
    protected String name;
    protected ArrayList<GameObject> sceneObjects = new ArrayList<>();

    public GameScene(String name, GameProject project)
    {
        this.name = name;
        this.project = project;
    }

    public ArrayList<GameObject> getAllObjects()
    {
        return this.sceneObjects;
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

    public void moveAllObjects()
    {
        for(int i = 0; i < sceneObjects.size() - 1; i++)
        {
            PhysicsBody body = sceneObjects.get(i).physicsBody;
            if(body != null)
            {
                body.move(body.getVelocity());
            }
        }
    }
    
    public void testForAllCollisions()
    {
        //this is supersuper slow right now..
        //make the algorithm so that the inner loops only check items which
        //are within the max widths/heights for collision at all
        for (int i = 0; i < sceneObjects.size() - 1; i++)
        {
            if (i != sceneObjects.size() - 1)
            {
                for (int j = i + 1; j < sceneObjects.size() - 1; j++)
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
