package com.linesengine.game;

import com.linesengine.engine.*;

public class Game implements Runnable
{
    private GameProject project;
    private GameWindow window;
    private boolean running = false;
    private int frames = 0;
    
    public Game(String projectName)
    {
        this.project = new GameProject(projectName, this);
        this.project.addScene(new GameScene("test"));
        this.window = new GameWindow("linesengine", this.project);
        this.running = true;
        this.run();
    }
    
    public static void main(String[] args)
    {
        new Game("linesengine");
    }
    
    //TODO: move this to a GameTime class
    @Override
    public void run()
    {
        float delta = 0f;
        long currentTime = System.currentTimeMillis();
        long lastTime = currentTime;
        
        while(running)
        {
            long now = System.currentTimeMillis();
            delta += now - lastTime;
            lastTime = now;
            
            while(delta >= 12)
            {
                this.project.getScene(0).tick();
                this.window.repaint();
                this.frames++;
                delta -= 12;  
            }
            
            if(System.currentTimeMillis() - currentTime > 1000)
            {
                System.out.println("FPS: " + this.frames);
                currentTime += 1000;
                this.frames = 0;
            }
        }
    }
}
