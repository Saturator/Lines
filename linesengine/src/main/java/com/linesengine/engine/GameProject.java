package com.linesengine.engine;

import java.util.ArrayList;
import com.linesengine.game.Game;

/**
 * GameProject class represents a single, whole game.
 */

public class GameProject
{
    protected String name;
    protected ArrayList<GameScene> scenes;
    protected Game game;
    
    public GameProject(String name)
    {
        this.name = name;
        this.scenes = new ArrayList<>();
    }
    
    /**
     * Creates a new project.
     * @param name name of the project
     * @param game game to be linked to the project
     */
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

    public String getName()
    {
        return this.name;
    }
    
    /**
     * Finds a scene within the project by name.
     * @param findableName name of the scene to be found
     * @return found scene
     */
    public GameScene findScene(String findableName)
    {
        //TODO: make so that scenes cant have the same name
        GameScene findable = null;
        for(int i = 0; i < this.scenes.size(); i++)
        {
            if(this.scenes.get(i).name.equals(findableName))
            {
                findable = this.scenes.get(i);
            }
        }
        return findable;
    }
    
    /**
     * Adds a new scene to the project.
     * @param scene the scene to be added
     */
    public void addScene(GameScene scene)
    {
        this.scenes.add(scene);
    }
    
    public GameScene getScene(int i)
    {
        return this.scenes.get(i);
    }
}
