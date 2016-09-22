package com.linesengine.engine;

import java.util.ArrayList;

public class GameProject
{
    protected String projectName;
    protected ArrayList<GameScene> scenes;
    
    public GameProject(String name)
    {
        this.projectName = name;
        this.scenes = new ArrayList<>();
    }
    
    public String getName()
    {
        return this.projectName;
    }
    
    public void addScene(GameScene scene)
    {
        this.scenes.set(this.scenes.size()-1, scene);
    }
}
