package com.linesengine.game;

import com.linesengine.engine.*;
import com.linesengine.math.*;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.image.*;
import java.awt.Graphics;
import java.awt.Toolkit;


//tämä classi ei ole täysin omatekemä, apua täältä:
//https://www.youtube.com/watch?v=1gir2R7G9ws
//passaako tämä 3rd party kirjaston käytöstä vai olisiko suotavampaa luoda oma luuppirakenne?

//pistä pit testit ja checkstylet tänne
//https://htmlpreview.github.io
public class Game implements Runnable
{
    GameProject project;
    GameWindow window;
    
    private Thread thread;
    private boolean running = false;
    
    public Game(String projectName)
    {
        this.project = new GameProject(projectName, this);
        this.project.addScene(new GameScene("test"));
        this.window = new GameWindow("test", this.project);
        //this.start();
        this.running = true;
        run();
    }
    
    public static void main(String[] args)
    {
        new Game("linesengine");
    }
    
    public synchronized void start()
    {
        this.thread = new Thread(this);
        this.thread.start();
        this.running = true;
    }
    
    public synchronized void stop()
    {
        try
        {
           this.thread.join();
           this.running = false;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    //TODO: move this to a GameTime class
    public void run()
    {
        int i = 0;
        //float amountOfTicks = 60f;
        //float ns = 1000000000 / amountOfTicks;
        float delta = 0f;
        long currentTime = System.currentTimeMillis();
        long lastTime = currentTime;
        int frames = 0;
        
        while(running)
        {
            long now = System.currentTimeMillis();
            delta += now - lastTime;
            lastTime = now;
            
            while(delta >= 10)
            {
                render();
                tick();
                delta -= 10;   
            }
            
            frames++;
            
            /*
            if(System.currentTimeMillis() - currentTime > 1000)
            {
                System.out.println("Ticks: " + this.project.getScene(0).ticks);
                this.project.getScene(0).ticks = 0;
                currentTime += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
            */
        }
        //stop();
    }
    
    private void tick()
    {
        //TODO: we only get scene 0 atm, change it after the scene is up and running
        //tick moves the physics forward when deltaTime is 1, so we have a fixed physics update
        this.project.getScene(0).tick();
    }
    
    private void render()
    {        
        this.window.repaint();
    }
}
