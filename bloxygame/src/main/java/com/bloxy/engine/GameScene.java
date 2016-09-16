package com.bloxy.engine;

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
}
