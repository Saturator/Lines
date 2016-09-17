package com.linesengine.engine;

import java.util.ArrayList;

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
    
    //right now you CANT find two objects if they have the same name,
    //so you better make a thing for that
    public GameObject findGameObject(String name)
    {
        GameObject findable = null;
        for(GameObject go : this.sceneObjects)
        {
            if(go.name.equals(name))
            {
                findable = go;
            }
        }
        return findable;
    }
}
