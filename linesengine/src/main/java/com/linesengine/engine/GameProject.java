package com.linesengine.engine;

import java.util.ArrayList;
import com.linesengine.game.Game;

public class GameProject
{
    protected String name;
    protected ArrayList<GameScene> scenes;
    protected GameWindow window;
    protected Game game;
    
    public GameProject(String name)
    {
        this.name = name;
        this.scenes = new ArrayList<>();
    }
    
    public GameProject(String name, Game game)
    {
        this.name = name;
        this.game = game;
        this.scenes = new ArrayList<>();
    }
    
    public void setGame(Game game)
    {
        this.game = game;
    }
    
    public void createWindow()
    {
        if(this.game != null)
        {
            this.window = new GameWindow(this.name, this);
        }
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public void addScene(GameScene scene)
    {
        this.scenes.add(scene);
    }
    
    public GameScene getScene(int i)
    {
        return this.scenes.get(i);
    }
}
