package com.linesengine.engine;

import java.util.ArrayList;
import com.linesengine.game.Game;
import java.awt.event.*;

/**
 * GameProject class represents a single, whole game.
 */
public class GameProject implements Runnable
{
    protected String name;
    public ArrayList<GameScene> scenes;
    public GameWindow window;
    protected Game game;
    
    public int frames = 0;
    
    /**
     * Creates a new GameProject with a given name.
     * Automatically creates a new GameWindow.
     * @param name 
     */
    public GameProject(String name)
    {
        this.name = name;
        this.scenes = new ArrayList<>();
        this.window = new GameWindow(name, this);
    }
    
    /**
     * The main game loop. Runs within a fixed time step through measuring delta time.
     */
    @Override
    public void run()
    {
        float delta = 0f;
        long currentTime = System.currentTimeMillis();
        long lastTime = currentTime;
        
        while(true)
        {
            long now = System.currentTimeMillis();
            delta += now - lastTime;
            lastTime = now;
            
            while(delta >= 200)
            {
                this.getScene(0).tick();
                this.window.repaint();
                this.frames++;
                delta -= 200;  
            }
            
            if(System.currentTimeMillis() - currentTime > 1000)
            {
                System.out.println("FPS: " + this.frames);
                currentTime += 1000;
                this.frames = 0;
            }
        }
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
     * Adds a MouseListener to the project.
     * The linked MouseListener should be added from the game's logic side.
     * @param input any given class that implements the MouseListener interface
     */
    public void setMouseInput(MouseListener input)
    {
        this.window.addMouseListener(input);
    }
    
    /**
     * Adds a KeyListener to the project.
     * The linked KeyListener should be added from the game's logic side.
     * @param input any given class that implements the KeyListener interface
     */
    public void setKeyInput(KeyListener input)
    {
        this.window.addKeyListener(input);
    }
    
    /**
     * Adds a new scene to the project.
     * @param scene the scene to be added
     */
    public void addScene(GameScene scene)
    {
        this.scenes.add(scene);
    }
    
    /**
     * Gets a scene by its index.
     * @param i index of the scene
     * @return 
     */
    public GameScene getScene(int i)
    {
        return this.scenes.get(i);
    }
}
